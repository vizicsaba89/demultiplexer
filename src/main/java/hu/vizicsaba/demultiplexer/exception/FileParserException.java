package hu.vizicsaba.demultiplexer.exception;

public class FileParserException extends RuntimeException {

    public FileParserException(String message) {
        super(message);
    }

    public FileParserException(String message, Throwable cause) {
        super(message, cause);
    }

}
