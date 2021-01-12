package bg.sofia.uni.fmi.mjt.wish.list;

public interface MessageTemplates {

    String MESSAGE_FOR_SUCCESSFUL_ADD = "[ Gift %s for student %s submitted successfully ]";
    String MESSAGE_FOR_DUPLICATION = "[ The same gift for student %s was already submitted ]";
    String FORMATTED_WISH_LIST = "[ %s: %s ]";
    String EMPTY_STUDENTS_MESSAGE = "[ There are no students present in the wish list ]";
    String SUCCESSFUL_REGISTRATION_MESSAGE = "[ Username %s successfully registered ]";
    String REGISTRATION_FAILED_MESSAGE = "[ Username %s is already taken, select another one ]";
    String INVALID_USERNAME_MESSAGE = "[ Username %s is invalid, select a valid one ]";
    String SUCCESSFUL_LOGIN_MESSAGE = "[ User %s successfully logged in ]";
    String FAILED_LOGIN_MESSAGE = "[ Invalid username/password combination ]";
    String SUCCESSFUL_LOGOUT_MESSAGE = "[ Successfully logged out ]";
    String FAILED_COMMAND_MESSAGE = "[ You are not logged in ]";
    String DISCONNECTION_MESSAGE = "[ Disconnected from server ]";
    String UNKNOWN_MESSAGE_COMMAND = "[ Unknown command ]";

}
