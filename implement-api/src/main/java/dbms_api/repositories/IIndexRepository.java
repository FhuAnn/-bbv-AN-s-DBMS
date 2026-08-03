package dbms_api.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import core.classes.metadata.Index;

public interface IIndexRepository {
    Index save(Index index);

    Optional<Index> findById(UUID indexId);

    Optional<Index> findByTableIdAndName(
            UUID tableId,
            String name);

    List<Index> findByTableId(UUID tableId);

    boolean existsById(UUID indexId);

    boolean existsByTableIdAndName(
            UUID tableId,
            String name);

    void deleteById(UUID indexId);

    long countByTableId(UUID tableId);

    void clear();
}
