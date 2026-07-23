package facade;

import java.util.UUID;

import builder.ColumnMetadataBuilder;
import classes.abstraction.Constraint;
import classes.database.Database;
import classes.metadata.Catalog;
import classes.metadata.ColumnMetadata;
import classes.metadata.Schema;
import classes.metadata.Table;
import classes.metadata.definition.ColumnDefinition;
import classes.metadata.definition.ConstraintDefinition;
import factories.ConstraintFactory;

public class MetadataManager {

    private Database database;
    private Catalog catalog;
    private ConstraintFactory constraintFactory;

    public MetadataManager() {
        // TODO: Implement
    }

    public MetadataManager(
            Database database,
            Catalog catalog,
            ConstraintFactory constraintFactory) {
        // TODO: Implement
    }

    public Schema createSchema(
            String name,
            UUID ownerId) {
        return null;
    }

    public Table createTable(
            UUID schemaId,
            String name) {
        return null;
    }

    public ColumnMetadataBuilder newColumnBuilder() {
        return ColumnMetadataBuilder.builder();
    }

    public ColumnMetadata addColumn(
            UUID tableId,
            ColumnMetadataBuilder builder) {
        return null;
    }

    public Constraint addConstraint(
            UUID tableId,
            ConstraintDefinition definition) {
        return null;
    }

    public Table removeTable(
            UUID schemaId,
            String tableName) {
        return null;
    }

    public void renameColumn(
            UUID tableId,
            String columnName,
            String newName) {
        // TODO: Implement
    }

    public Schema findSchema(UUID schemaId) {
        return null;
    }

    public Table findTable(UUID tableId) {
        return null;
    }

    public Database getDatabase() {
        return null;
    }

    public Catalog getCatalog() {
        return null;
    }

    public ConstraintFactory getConstraintFactory() {
        return null;
    }
}
