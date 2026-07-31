package core.classes.metadata.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import core.classes.abstraction.Constraint;
import core.enums.ConstraintType;


public interface ConstraintRepository
        extends MetadataRepository<Constraint> {

    List<Constraint> findByTableId(UUID tableId);

    Optional<Constraint> findByTableIdAndName(
            UUID tableId,
            String name
    );

    List<Constraint> findByType(ConstraintType type);
}
