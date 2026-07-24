package factories;

import java.util.List;
import java.util.UUID;

public class IndexDefinition {
      private String name;
    private UUID tableId;
    private List<UUID> columnIds;
    private boolean unique;

    public IndexDefinition() {
        // TODO: Implement
    }

    public IndexDefinition(
            String name,
            UUID tableId,
            List<UUID> columnIds,
            boolean unique
    ) {
        // TODO: Implement
    }

    public String getName() {
        return null;
    }

    public UUID getTableId() {
        return null;
    }

    public List<UUID> getColumnIds() {
        return List.of();
    }

    public boolean isUnique() {
        return false;
    }
}

