package core.classes.metadata.repository.memory;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import core.classes.abstraction.Constraint;
import core.classes.metadata.repository.ConstraintRepository;
import core.enums.ConstraintType;


public class InMemoryConstraintRepository extends AbstractInMemoryMetadataRepository<Constraint>
        implements ConstraintRepository {
    public InMemoryConstraintRepository() {
        // TODO: Implement
    }

    @Override
    public List<Constraint> findByTableId(UUID tableId) {
        // TODO: Implement
        return List.of();
    }

    @Override
    public Optional<Constraint> findByTableIdAndName(
            UUID tableId,
            String name) {
        // TODO: Implement
        return Optional.empty();
    }

    @Override
    public List<Constraint> findByType(
            ConstraintType type) {
        // TODO: Implement
        return List.of();
    }

    @Override
    protected boolean isSameNamespace(
            Constraint first,
            Constraint second) {
        // TODO: Implement
        return false;
    }
}
