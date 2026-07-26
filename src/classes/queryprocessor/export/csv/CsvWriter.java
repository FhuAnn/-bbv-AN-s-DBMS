package classes.queryprocessor.export.csv;

import java.io.OutputStream;
import java.util.List;

public interface CsvWriter {
    String write(
            List<String> headers,
            List<List<Object>> rows);

    void write(
            List<String> headers,
            List<List<Object>> rows,
            OutputStream output);
}
