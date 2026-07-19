package exception;
public class SchemaAlreadyExistsException extends RuntimeException {

    public SchemaAlreadyExistsException(String schemaName) {
        super("Schema already exists: " + schemaName);
    }
}