package bg.sofia.uni.fmi.mjt.wish.list;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Set;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;


public class WishListServer {


    private static final String REGISTER = "register";
    private static final String LOGIN = "login";
    private static final String POST_WISH = "post-wish";
    private static final String GET_WISH = "get-wish";
    private static final String LOGOUT = "logout";
    private static final String DISCONNECT = "disconnect";
    private static final String SERVER_HOST = "localhost";
    private static final String REGEX_FOR_USERNAME = "^[a-zA-Z0-9-._]+$";
    public final int serverPort;
    private static final int BUFFER_SIZE = 1024;
    private static final Map<String, Set<String>> wishListByStudent = new HashMap<>();
    private ServerSocketChannel serverSocketChannel;

    public WishListServer(int serverPort) {
        this.serverPort = serverPort;
        try {
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress(SERVER_HOST, this.serverPort));
            serverSocketChannel.configureBlocking(false);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public void start() {
        try {
            Selector selector = Selector.open();
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);

            runningServer:
            while (true) {
                int readyChannels = selector.select();
                if (readyChannels == 0) {
                    continue;
                }

                Set<SelectionKey> selectedKeys = selector.selectedKeys();
                Iterator<SelectionKey> keyIterator = selectedKeys.iterator();

                while (keyIterator.hasNext()) {
                    SelectionKey key = keyIterator.next();
                    if (key.isReadable()) {
                        SocketChannel sc = (SocketChannel) key.channel();
                        buffer.clear();
                        int r = sc.read(buffer);
                        if (r <= 0) {
                            System.out.println("nothing to read, will close channel");
                            sc.close();
                            break runningServer;
                        }
                        buffer.flip();
                        byte[] clientInputBytes = new byte[buffer.remaining()];
                        buffer.get(clientInputBytes);
                        String respond;
                        boolean isDisconnecting = false;
                        String[] words = new String(clientInputBytes, StandardCharsets.UTF_8).split("\\s+");
                        String command = words[0];
                        switch (command) {
                            case REGISTER -> respond = generateRespondForRegister(words[1], words[2]);
                            case LOGIN -> respond = generateRespondForLogin(words[1], words[2], sc);
                            case LOGOUT -> respond = generateRespondForLogout(sc);
                            case POST_WISH -> respond = generateRespondForPostingWish(words, sc);
                            case GET_WISH -> respond = generateRespondForGettingWish(sc);
                            case DISCONNECT -> {
                                isDisconnecting = true;
                                respond = MessageTemplates.DISCONNECTION_MESSAGE;
                            }
                            default -> respond = MessageTemplates.UNKNOWN_MESSAGE_COMMAND;
                        }
                        buffer.clear();
                        buffer.put((respond + System.lineSeparator()).getBytes(StandardCharsets.UTF_8));
                        buffer.flip();
                        sc.write(buffer);
                        buffer.clear();
                        if (isDisconnecting) {
                            break;
                        }
                    } else if (key.isAcceptable()) {
                        ServerSocketChannel sockChannel = (ServerSocketChannel) key.channel();
                        SocketChannel accept = sockChannel.accept();
                        accept.configureBlocking(false);
                        accept.register(selector, SelectionKey.OP_READ);
                    }

                    keyIterator.remove();
                }

            }

        } catch (IOException e) {
            System.out.println("There is a problem with the server socket");
            e.printStackTrace();
        }
    }

    public void stop() {
        try {
            serverSocketChannel.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    private String generateRespondForPostingWish(String[] words, SocketChannel sc) {
        if (!AuthenticationService.isClientIsLoggedIn(sc)) {
            return MessageTemplates.FAILED_COMMAND_MESSAGE;
        }
        String student = words[1];
        String wish = Arrays.toString(Arrays.copyOfRange(words, 2, words.length));
        wish = wish.substring(1, wish.length() - 1).replaceAll(",", "");
        if (!wishListByStudent.containsKey(student)) {
            wishListByStudent.put(student, new HashSet<>());
            wishListByStudent.get(student).add(wish);
            return String.format(MessageTemplates.MESSAGE_FOR_SUCCESSFUL_ADD, wish, student);
        } else if (!wishListByStudent.get(student).contains(wish)) {
            wishListByStudent.get(student).add(wish);
            return String.format(MessageTemplates.MESSAGE_FOR_SUCCESSFUL_ADD, wish, student);
        } else {
            return String.format(MessageTemplates.MESSAGE_FOR_DUPLICATION, student);
        }
    }

    private String generateRespondForGettingWish(SocketChannel sc) {
        if (!AuthenticationService.isClientIsLoggedIn(sc)) {
            return MessageTemplates.FAILED_COMMAND_MESSAGE;
        }
        String randomStudent = generateRandomStudent(AuthenticationService.getUsernameOfClient(sc));
        if (randomStudent == null) {
            return MessageTemplates.EMPTY_STUDENTS_MESSAGE;
        } else {
            Set<String> wishes = wishListByStudent.get(randomStudent);
            wishListByStudent.remove(randomStudent);
            return String.format(MessageTemplates.FORMATTED_WISH_LIST,
                    randomStudent, Arrays.toString(wishes.toArray()));
        }
    }

    private String generateRandomStudent(String currentUsername) {
        Set<String> students = new HashSet<>(wishListByStudent.keySet());
        students.remove(currentUsername);
        if (students.size() == 0 || currentUsername == null) {
            return null;
        } else {
            List<String> studentsList = new ArrayList<>(wishListByStudent.keySet());
            Random random = new Random();
            return studentsList.get(random.nextInt(studentsList.size()));
        }
    }

    private String generateRespondForRegister(String username, String password) {
        if (!username.matches(REGEX_FOR_USERNAME)) {
            return String.format(MessageTemplates.INVALID_USERNAME_MESSAGE, username);
        } else if (!AuthenticationService.registerNewUser(username, password)) {
            return String.format(MessageTemplates.REGISTRATION_FAILED_MESSAGE, username);
        } else {
            return String.format(MessageTemplates.SUCCESSFUL_REGISTRATION_MESSAGE, username);
        }
    }

    private String generateRespondForLogin(String username, String password, SocketChannel sc) {
        return AuthenticationService.loginUser(sc, username, password) ?
                String.format(MessageTemplates.SUCCESSFUL_LOGIN_MESSAGE, username) :
                MessageTemplates.FAILED_LOGIN_MESSAGE;
    }

    private String generateRespondForLogout(SocketChannel sc) {
        return AuthenticationService.logoutUser(sc) ? MessageTemplates.SUCCESSFUL_LOGOUT_MESSAGE :
                MessageTemplates.FAILED_COMMAND_MESSAGE;
    }


}
