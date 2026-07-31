      package core.classes.facade;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import core.classes.abstraction.Constraint;
import core.classes.builder.ColumnMetadataBuilder;
import core.classes.database.Database;
import core.classes.factories.ConstraintFactory;
import core.classes.metadata.Catalog;
import core.classes.metadata.ColumnMetadata;
import core.classes.metadata.Index;
import core.classes.metadata.Schema;
import core.classes.metadata.Table;
import core.classes.metadata.definition.ConstraintDefinition;
import core.classes.metadata.repository.ConstraintRepository;
import core.classes.metadata.repository.IndexMetadataRepository;
import core.classes.metadata.repository.SchemaRepository;
import core.classes.metadata.repository.TableMetadataRepository;


public class MetadataManager {

    private Database database;
    private Catalog catalog;
    private ConstraintFactory constraintFactory;
    private SchemaRepository schemaRepository;
    private TableMetadataRepository tableRepository;
    private IndexMetadataRepository indexRepository;
    private ConstraintRepository constraintRepository;

    public MetadataManager(
            SchemaRepository schemaRepository,
            TableMetadataRepository tableRepository,
            IndexMetadataRepository indexRepository,
            ConstraintRepository constraintRepository) {
        // TODO: Implement

        this.schemaRepository = null;
        this.tableRepository = null;
        this.indexRepository = null;
        this.constraintRepository = null;
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

    public Schema saveSchema(Schema schema) {
        // TODO: Implement
        return null;
    }

    public Optional<Schema> findSchemaById(
            UUID schemaId) {
        // TODO: Implement
        return Optional.empty();
    }

    public Optional<Schema> findSchema(
            UUID databaseId,
            String schemaName) {
        // TODO: Implement
        return Optional.empty();
    }

    public List<Schema> findSchemasByDatabase(
            UUID databaseId) {
        // TODO: Implement
        return List.of();
    }

    public Table saveTable(
            Table table) {
        // TODO: Implement
        return null;
    }

    public Optional<Table> findTableById(
            UUID tableId) {
        // TODO: Implement
        return Optional.empty();
    }

    public Optional<Table> findTable(
            UUID schemaId,
            String tableName) {
        // TODO: Implement
        return Optional.empty();
    }

    public List<Table> findTablesBySchema(
            UUID schemaId) {
        // TODO: Implement
        return List.of();
    }

    public Index saveIndex(
            Index index) {
        // TODO: Implement
        return null;
    }

    public List<Index> findIndexesByTable(
            UUID tableId) {
        // TODO: Implement
        return List.of();
    }

    public Constraint saveConstraint(
            Constraint constraint) {
        // TODO: Implement
        return null;
    }

    public List<Constraint> findConstraintsByTable(
            UUID tableId) {
        // TODO: Implement
        return List.of();
    }

    public void deleteSchema(UUID schemaId) {
        // TODO: Implement
    }

    public void deleteTable(UUID tableId) {
        // TODO: Implement
    }

    public void deleteIndex(UUID indexId) {
        // TODO: Implement
    }

    public void deleteConstraint(UUID constraintId) {
        // TODO: Implement
    }
}
