package pl.robert.blog.app.user.domain.exception;

public class InvalidPasswordException extends RuntimeException {

    public InvalidPasswordException(String msg) {
        super(msg, null, false, false);
    }
}
