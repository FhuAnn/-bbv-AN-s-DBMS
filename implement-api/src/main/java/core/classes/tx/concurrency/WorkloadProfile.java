package core.classes.tx.concurrency;

public class WorkloadProfile {
    private long estimatedReads;
    private long estimatedWrites;
    private double conflictRate;
    private int concurrentTransactions;

    public WorkloadProfile() {
        // TODO: Implement
    }

    public WorkloadProfile(
            long estimatedReads,
            long estimatedWrites,
            double conflictRate,
            int concurrentTransactions
    ) {
        // TODO: Implement
    }

    public long getEstimatedReads() {
        // TODO: Implement
        return 0;
    }

    public long getEstimatedWrites() {
        // TODO: Implement
        return 0;
    }

    public double getConflictRate() {
        // TODO: Implement
        return 0;
    }

    public int getConcurrentTransactions() {
        // TODO: Implement
        return 0;
    }

    public boolean isReadHeavy() {
        // TODO: Implement
        return false;
    }

    public boolean isWriteHeavy() {
        // TODO: Implement
        return false;
    }
}
