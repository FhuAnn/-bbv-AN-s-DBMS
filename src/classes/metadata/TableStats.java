package classes.metadata;

public class TableStats {
    private long rowCount;
    private long pageCount;
    private long deadTuples;

    // Getters and Setters
    public long getRowCount() {
        return rowCount;
    }

    public void setRowCount(long rowCount) {
        this.rowCount = rowCount;
    }

    public long getPageCount() {
        return pageCount;
    }

    public void setPageCount(long pageCount) {
        this.pageCount = pageCount;
    }

    public long getDeadTuples() {
        return deadTuples;
    }

    public void setDeadTuples(long deadTuples) {
        this.deadTuples = deadTuples;
    }
}