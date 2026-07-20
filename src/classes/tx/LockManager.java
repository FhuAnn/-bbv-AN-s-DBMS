package classes.tx;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

import enums.LockMode;

public class LockManager {

    public enum LockMode {
        SHARED, EXCLUSIVE
    }

    private static final class LockEntry {

    }

    private final Map<String, LockEntry> locks = new LinkedHashMap<>();

    public boolean acquire(String resource, UUID owner, LockMode mode) {
        return true;
    }

    public boolean release(String resource, UUID owner) {
        return false;
    }

    public boolean isLocked(String resource) {
        return true;
    }

    public LockMode getLockMode(String resource) {
        return null;
    }

    public UUID getOwner(String resource) {
        return null;
    }

    public int getLockCount() {
        return 0;
    }

    public void releaseAll(UUID owner) {

    }

    public Map<String, LockMode> getLocks() {
        return null;
    }

    private void validate(String resource, UUID owner, LockMode mode) {

    }

    private void validateResource(String resource) {

    }
}
