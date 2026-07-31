package core.classes.tx;

public class VersionManager {
    public VersionManager() {
        // TODO: Implement
    }

    public long getVersion(
            Resource resource) {
        // TODO: Implement
        return 0;
    }

    public Object readVersion(
            Resource resource,
            long version) {
        // TODO: Implement
        return null;
    }

    public void createVersion(
            Transaction transaction,
            Resource resource,
            Object value) {
        // TODO: Implement
    }

    public void applyPendingVersions(
            Transaction transaction) {
        // TODO: Implement
    }

    public void discardPendingVersions(
            Transaction transaction) {
        // TODO: Implement
    }

    public boolean hasChangedSince(
            Resource resource,
            long expectedVersion) {
        // TODO: Implement
        return false;
    }
}
