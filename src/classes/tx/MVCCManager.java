package classes.tx;

public class MVCCManager {
    public MVCCManager() {
        // TODO: Implement
    }

    public Object createSnapshot(
            Transaction transaction) {
        // TODO: Implement
        return null;
    }

    public Object getSnapshot(
            Transaction transaction) {
        // TODO: Implement
        return null;
    }

    public Object readVisibleVersion(
            Transaction transaction,
            Resource resource) {
        // TODO: Implement
        return null;
    }

    public void createVersion(
            Transaction transaction,
            Resource resource,
            Object value) {
        // TODO: Implement
    }

    public void commitVersions(
            Transaction transaction) {
        // TODO: Implement
    }

    public void rollbackVersions(
            Transaction transaction) {
        // TODO: Implement
    }
}
