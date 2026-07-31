package core.classes.metadata.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import core.interfaces.MetadataComponent;


public interface MetadataRepository<T extends MetadataComponent> {

    T save(T entity);

    Optional<T> findById(UUID id);

    Optional<T> findByName(String name);

    List<T> findAll();

    boolean existsById(UUID id);

    boolean existsByName(String name);

    void deleteById(UUID id);

    long count();

    void clear();
}
