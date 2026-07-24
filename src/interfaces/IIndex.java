package interfaces;

import java.util.List;
import java.util.UUID;

import classes.storageengine.RecordId;
import enums.IndexType;

public interface IIndex {
    UUID getId();

    String getName();

    IndexType getType();

    void insert(
            Object key,
            RecordId recordId);

    List<RecordId> search(Object key);

    void delete(
            Object key,
            RecordId recordId);
}
