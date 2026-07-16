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

      

    }

    // Getters + Setters

    public UUID getId() {

        return id;

    }

    public void setId(UUID id) {

        this.id = id;

    }

    public String getName() {

        return name;

    }


    public void setName(String name) {

        this.name = name;

    }

    public UUID getSchemaId() {

        return schemaId;

    }

    public void setSchemaId(UUID schemaId) {

        this.schemaId = schemaId;

    }


    public StorageInfo getStorageInfo() {

        return storageInfo;

    }


    public void setStorageInfo(StorageInfo storageInfo) {

        this.storageInfo = storageInfo;

    }


    public TableStats getStats() {

        return stats;

    }


    public void setStats(TableStats stats) {

        this.stats = stats;

    }


    public List<ColumnMetadata> getColumns() {

        return columns;

    }

    public void setColumns(List<ColumnMetadata> columns) {

        this.columns = columns;

    }


    public List<Index> getIndexes() {

        return indexes;

    }


    public void setIndexes(List<Index> indexes) {

        this.indexes = indexes;

    }


    public List<Constraint> getConstraints() {

        return constraints;

    }

    public void setConstraints(List<Constraint> constraints) {

        this.constraints = constraints;

    }


    

}