package core.classes.tx;

public class ConflictDetector {

    private VersionManager versionManager;

    public ConflictDetector(
            VersionManager versionManager) {
        // TODO: Implement
    }

    public boolean hasConflict(
            Transaction transaction) {
        // TODO: Implement
        return false;
    }

    public boolean detectReadConflict(
            Transaction transaction) {
        // TODO: Implement
        return false;
    }

    public boolean detectWriteConflict(
            Transaction transaction) {
        // TODO: Implement
        return false;
    }

    public VersionManager getVersionManager() {
        // TODO: Implement
        return null;
    }
}