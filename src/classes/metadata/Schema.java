
package classes.metadata;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import classes.abstraction.AbstractMetadataComponent;
import enums.MetadataType;
import interfaces.MetadataComponent;

public class Schema extends AbstractMetadataComponent {

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
        this.databaseId = Objects.requireNonNull(
                databaseId,
                "Database ID must not be null");
        this.ownerId = Objects.requireNonNull(
                ownerId,
                "Owner ID must not be null");
        this.tables = new ArrayList<>();
        this.views = new ArrayList<>();
    }

    public void setId(UUID id) {
        this.id = Objects.requireNonNull(
                id,
                "Schema ID must not be null");
    }

    public void setName(String name) {
        rename(name);
    }

    public UUID getDatabaseId() {
        return databaseId;
    }

    public void setDatabaseId(UUID databaseId) {
        this.databaseId = Objects.requireNonNull(
                databaseId,
                "Database ID must not be null");
    }

    public UUID getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(UUID ownerId) {
        this.ownerId = Objects.requireNonNull(
                ownerId,
                "Owner ID must not be null");
    }

    public List<Table> getTables() {
        return Collections.unmodifiableList(tables);
    }

    public void setTables(List<Table> tables) {
        Objects.requireNonNull(
                tables,
                "Tables must not be null");

        this.tables.clear();

        for (Table table : tables) {
            addTable(table);
        }
    }

    public List<View> getViews() {
        return Collections.unmodifiableList(
                new ArrayList<>(views));
    }

    public void setViews(List<View> views) {
        Objects.requireNonNull(
                views,
                "Views must not be null");

        this.views.clear();

        for (View view : views) {
            addView(view);
        }
    }

    public void addTable(Table table) {
        Objects.requireNonNull(
                table,
                "Table must not be null");

        if (!id.equals(table.getSchemaId())) {
            throw new IllegalArgumentException(
                    "Table belongs to another schema");
        }

        if (containsTable(table.getName())) {
            throw new IllegalArgumentException(
                    "Duplicate table: " + table.getName());
        }

        tables.add(table);
    }

    public Table getTable(String tableName) {
        validateName(tableName);

        return tables.stream()
                .filter(table -> table.getName()
                        .equalsIgnoreCase(tableName))
                .findFirst()
                .orElse(null);
    }

    public boolean containsTable(String tableName) {
        if (tableName == null || tableName.isBlank()) {
            return false;
        }

        return tables.stream()
                .anyMatch(table -> table.getName()
                        .equalsIgnoreCase(tableName));
    }

    public Table removeTable(String tableName) {
        Table table = getTable(tableName);

        if (table != null) {
            tables.remove(table);
        }

        return table;
    }

    public void addView(View view) {
        Objects.requireNonNull(
                view,
                "View must not be null");

        if (containsView(view.getName())) {
            throw new IllegalArgumentException(
                    "Duplicate view: " + view.getName());
        }

        views.add(view);
    }

    public View getView(String viewName) {
        validateName(viewName);

        return views.stream()
                .filter(view -> view.getName()
                        .equalsIgnoreCase(viewName))
                .findFirst()
                .orElse(null);
    }

    public boolean containsView(String viewName) {
        if (viewName == null || viewName.isBlank()) {
            return false;
        }

        return views.stream()
                .anyMatch(view -> view.getName()
                        .equalsIgnoreCase(viewName));
    }

    public View removeView(String viewName) {
        View view = getView(viewName);

        if (view != null) {
            views.remove(view);
        }

        return view;
    }

    public int getTableCount() {
        return tables.size();
    }

    public int getViewCount() {
        return views.size();
    }

    public boolean isEmpty() {
        return tables.isEmpty() && views.isEmpty();
    }

    @Override
    public MetadataType getMetadataType() {
        return MetadataType.SCHEMA;
    }

    @Override
    public List<MetadataComponent> getChildren() {
        List<MetadataComponent> children = new ArrayList<>();

        children.addAll(tables);
        children.addAll(views);

        return Collections.unmodifiableList(children);
    }

    @Override
    public void addChild(MetadataComponent child) {
        Objects.requireNonNull(
                child,
                "Child must not be null");

        if (child instanceof Table table) {
            addTable(table);
            return;
        }

        if (child instanceof View view) {
            addView(view);
            return;
        }

        throw new IllegalArgumentException(
                "Schema can contain only Table or View objects");
    }

    @Override
    public MetadataComponent removeChild(UUID childId) {
        Objects.requireNonNull(
                childId,
                "Child ID must not be null");

        Table table = tables.stream()
                .filter(item -> item.getId().equals(childId))
                .findFirst()
                .orElse(null);

        if (table != null) {
            tables.remove(table);
            return table;
        }

        View view = views.stream()
                .filter(item -> item.getId().equals(childId))
                .findFirst()
                .orElse(null);

        if (view != null) {
            views.remove(view);
            return view;
        }

        return null;
    }

}