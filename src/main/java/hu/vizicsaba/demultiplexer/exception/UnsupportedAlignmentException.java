package hu.vizicsaba.demultiplexer.exception;

public class UnsupportedAlignmentException extends RuntimeException {

    public UnsupportedAlignmentException(String message) {
        super(message);
    }

    public UnsupportedAlignmentException(String message, Throwable cause) {
        super(message, cause);
    }

}
