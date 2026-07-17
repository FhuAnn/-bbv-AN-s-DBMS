package classes.queryprocessor;

public class ResultOutput {
    public Boolean success;
    public String message;
    public int rowCount;
    public String payload;

    public ResultOutput(Boolean success, String message, int rowCount, String payload) {
        this.success = success;
        this.message = message;
        this.rowCount = rowCount;
        this.payload = payload;
    }
}
