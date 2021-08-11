package dao;

public class DvdLibraryException extends Exception {
    public DvdLibraryException(String message) {
        super(message);
    }

    public DvdLibraryException(String message, Throwable cause) {
        super(message, cause);
    }
}