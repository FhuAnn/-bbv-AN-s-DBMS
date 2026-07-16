package classes.metadata;



import java.util.*;



public class Catalog {

    private Map<UUID, TableMetadata> tables = new HashMap<>();

    private Map<UUID, Schema> schemas = new HashMap<>();



    public TableMetadata getTable(String name) {

        return null;

    }



    public void registerTable(TableMetadata meta) {

    }

    public Map<UUID, TableMetadata> getTables() {

        return tables;

    }

      public void setTables( Map<UUID, TableMetadata> tables) {

        this.tables = tables;


    }
    public Map<UUID, Schema> getSchemas() {

        return schemas;

    }

    public void setSchemas(Map<UUID, Schema> schemas) {

        this.schemas = schemas;

    }

    public Catalog() {
        this.tables = new HashMap<>();
        this.schemas = new HashMap<>();
    }
    
   public void putTable(TableMetadata table) {
        this.tables.put(table.getId(), table);
    }

    public void putSchema(Schema schema) {
        this.schemas.put(schema.getId(), schema);
    }

}