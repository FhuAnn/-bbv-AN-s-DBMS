package exception;

public class ReadOnlyDatabaseException extends RuntimeException {

    public ReadOnlyDatabaseException(String databaseName) {
        super("Database is read-only: " + databaseName);
    }
}
