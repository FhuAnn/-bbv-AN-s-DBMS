package exception.transaction;

public class TransactionCommandException extends RuntimeException {
  public TransactionCommandException(
            String message
    ) {
        super(message);
    }

    public TransactionCommandException(
            String message,
            Throwable cause
    ) {
        super(message, cause);
    }
}
