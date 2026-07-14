package classes;

public class SyntaxErrorException extends RuntimeException {
    SyntaxErrorException(String message) {
        super(message);
    }
}
