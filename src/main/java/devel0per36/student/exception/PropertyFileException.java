package devel0per36.student.exception;

/**
 * Класс для описания исключения для property-файла
 * @version 1.0
 */
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
