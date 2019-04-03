package me.jastz.app.mini;

/**
 * @author zhiwen
 */
public class UserNotLoginException extends Exception{

    public UserNotLoginException() {
    }

    public UserNotLoginException(String message) {
        super(message);
    }

    public UserNotLoginException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNotLoginException(Throwable cause) {
        super(cause);
    }
}
