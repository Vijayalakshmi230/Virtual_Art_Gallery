package myexceptions;

public class UserNotFoundException extends Exception {
    public UserNotFoundException(String message) {
        super("The user is not there");
    }
}
