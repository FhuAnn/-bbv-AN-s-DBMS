package classes.queryprocessor;

public class ResultOutput {
    public final Boolean success;
    public final String message;
    public final int rowCount;
    public final String payload;

    public ResultOutput(Boolean success, String message, int rowCount, String payload) {
        this.success = success;
        this.message = message;
        this.rowCount = rowCount;
        this.payload = payload;
    }
}
