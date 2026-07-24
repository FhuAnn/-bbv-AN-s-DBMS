package chain;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class ConstraintValidationData {
    private Map<UUID, Set<List<Object>>> existingValues;
    private Map<UUID, Set<List<Object>>> referencedValues;

    public ConstraintValidationData() {
        // TODO: Implement
    }

    public Set<List<Object>> getExistingValues(
            UUID constraintId) {
        return Set.of();
    }

    public Set<List<Object>> getReferencedValues(
            UUID constraintId) {
        return Set.of();
    }
}
