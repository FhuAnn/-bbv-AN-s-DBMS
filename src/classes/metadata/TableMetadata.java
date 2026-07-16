package classes.metadata;

import java.util.*;

public class TableMetadata {
    private UUID id;
    private String name;
    private UUID schemaId;
    private StorageInfo storageInfo;
    private TableStats stats;
    private List<ColumnMetadata> columns = new ArrayList<>();
    private List<Index> indexes = new ArrayList<>();
    private List<Constraint> constraints = new ArrayList<>();

    public TableMetadata(String name, UUID schemaId) {
        return null;
    }
    // Getters + Setters
}