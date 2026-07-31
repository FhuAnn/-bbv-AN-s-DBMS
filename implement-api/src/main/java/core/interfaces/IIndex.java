package core.interfaces;

import java.util.List;
import java.util.UUID;

import core.classes.storageengine.RecordId;
import core.enums.IndexType;

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
