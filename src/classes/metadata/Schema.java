
package classes.metadata;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class Schema {

    private UUID id;
    private String name;
    private UUID databaseId;
    private UUID ownerId;
    private List<Table> tables;
    private List<View> views;

    public Schema() {
        this.id = UUID.randomUUID();
        this.name = "";
        this.databaseId = null;
        this.ownerId = null;
        this.tables = new ArrayList<>();
        this.views = new ArrayList<>();
    }

    public Schema(String name, UUID databaseId, UUID ownerId) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.databaseId = databaseId;
        this.ownerId = ownerId;
        this.tables = new ArrayList<>();
        this.views = new ArrayList<>();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        // TODO: Implement
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        // TODO: Implement
    }

    public UUID getDatabaseId() {
        return databaseId;
    }

    public void setDatabaseId(UUID databaseId) {
        // TODO: Implement
    }

    public UUID getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(UUID ownerId) {
        // TODO: Implement
    }

    public List<Table> getTables() {
        return Collections.emptyList();
    }

    public void setTables(List<Table> tables) {
        // TODO: Implement
    }

    public List<View> getViews() {
        return Collections.emptyList();
    }

    public void setViews(List<View> views) {
        // TODO: Implement
    }

    public void addTable(Table table) {
        // TODO: Implement
    }

    public Table getTable(String tableName) {
        return null;
    }

    public boolean containsTable(String tableName) {
        return false;
    }

    public Table removeTable(String tableName) {
        return null;
    }

    public void addView(View view) {
        // TODO: Implement
    }

    public View getView(String viewName) {
        return null;
    }

    public boolean containsView(String viewName) {
        return false;
    }

    public View removeView(String viewName) {
        return null;
    }

    public void rename(String newName) {
        // TODO: Implement
    }

    public int getTableCount() {
        return 0;
    }

    public int getViewCount() {
        return 0;
    }

    public boolean isEmpty() {
        return true;
    }
}