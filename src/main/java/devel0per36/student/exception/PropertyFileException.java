package devel0per36.student.exception;

public class PropertyFileException extends Exception {
    public PropertyFileException() {}
    public PropertyFileException(String message) {
        super(message);
    }
    public PropertyFileException(String message, Throwable cause) {
        super(message, cause);
    }
    public PropertyFileException(Throwable cause) {
        super(cause);
    }
}
