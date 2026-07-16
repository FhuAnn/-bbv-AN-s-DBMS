package classes.metadata;

import java.util.UUID;

public class ColumnMetadata {
    private UUID id;
    private String name;
    private DataType type;
    private boolean nullable;
    private int position;
    private ColumnStats stats;

    public ColumnMetadata(String name, DataType type) {
        return null;
    }
    // Getters + Setters
}
