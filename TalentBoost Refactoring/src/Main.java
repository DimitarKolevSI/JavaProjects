public class Main {

    public handleRequest(Request request, Response response) {
        // ...
        if (recordNotFound(request)) {
            response.writeStatusCode(404);
            return;
        }
        // ...
    }

    public static void main(String[] args) {
	// write your code here
    }
}
