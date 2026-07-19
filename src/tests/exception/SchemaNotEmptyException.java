package exception;

public class SchemaNotEmptyException extends RuntimeException {

    public SchemaNotEmptyException(String schemaName) {
        super("Schema is not empty: " + schemaName);
    }
}
