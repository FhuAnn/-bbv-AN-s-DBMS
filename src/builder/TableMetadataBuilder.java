package builder;

import java.util.UUID;

import classes.metadata.StorageInfo;

public final class TableMetadataBuilder {
    private String name;
    private UUID schemaId;
    private StorageInfo storageInfo = new StorageInfo("heap", "data/default", 8192);
    private final List<ColumnDraft> columns = new ArrayList<>();

    public TableMetadataBuilder name(String name) {
        this.name = name;
        return this;
    }

    public TableMetadataBuilder schemaId(UUID schemaId) {
        this.schemaId = schemaId;
        return this;
    }

    public TableMetadataBuilder storageInfo(StorageInfo storageInfo) {
        this.storageInfo = storageInfo;
        return this;
    }

    public TableMetadataBuilder addColumn(ColumnDraft column) {
        columns.add(column);
        return this;
    }

    public TableMetadata build() {
        if (columns.isEmpty()) {
            throw new IllegalStateException("A table must contain at least one column.");
        }
        TableMetadata table = new TableMetadata(name, schemaId, storageInfo);
        for (int position = 0; position < columns.size(); position++) {
            ColumnDraft draft = columns.get(position);
            ColumnMetadata column = new ColumnMetadataBuilder()
                    .name(draft.name())
                    .tableId(table.getId())
                    .dataType(draft.dataType())
                    .nullable(draft.nullable())
                    .position(position)
                    .length(draft.length())
                    .defaultValue(draft.defaultValue())
                    .build();
            table.addChild(column);
        }
        return table;
    }

    public record ColumnDraft(
            String name,
            com.ansdb.metadata.domain.DataType dataType,
            boolean nullable,
            Integer length,
            Object defaultValue) {
    }
}
