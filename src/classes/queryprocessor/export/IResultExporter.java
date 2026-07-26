package classes.queryprocessor.export;

import java.io.OutputStream;
import classes.queryprocessor.QueryResult;
public interface IResultExporter {
    ExportFormat getFormat();

    String export(QueryResult result);

    void export(
            QueryResult result,
            OutputStream output);
}
