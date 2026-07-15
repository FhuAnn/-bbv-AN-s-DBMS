package classes.queryprocessor;

public class SyntaxErrorException extends RuntimeException {
    SyntaxErrorException(String message) {
        super(message);
    }
}
