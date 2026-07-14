package classes;

public class ResultOutput {
    final Boolean success;
    final String message;
    final int rowCount;
    final String payload;

    ResultOutput(Boolean success, String message, int rowCount, String payload) {
        this.success = success;
        this.message = message;
        this.rowCount = rowCount;
        this.payload = payload;
    }
}
