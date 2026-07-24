package classes.storageengine;

import java.util.List;
import java.util.UUID;

import enums.IndexType;
import interfaces.IIndex;

public class BTreeIndex implements IIndex {

    private UUID id;
    private String name;
    private UUID tableId;
    private List<UUID> columnIds;
    private boolean unique;

    public BTreeIndex() {
        // TODO: Implement
    }

    public BTreeIndex(
            String name,
            UUID tableId,
            List<UUID> columnIds,
            boolean unique) {
        // TODO: Implement
    }

    @Override
    public UUID getId() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public IndexType getType() {
        return null;
    }

    @Override
    public void insert(
            Object key,
            RecordId recordId) {
        // TODO: Implement
    }

    @Override
    public List<RecordId> search(Object key) {
        return List.of();
    }

    @Override
    public void delete(
            Object key,
            RecordId recordId) {
        // TODO: Implement
    }
}
