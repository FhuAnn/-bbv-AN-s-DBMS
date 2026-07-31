package core.classes.queryprocessor.export.csv;

import core.classes.queryprocessor.export.ExportFormat;
import core.classes.queryprocessor.export.IResultExporter;

import java.io.OutputStream;
import java.util.List;

import core.classes.queryprocessor.QueryResult;

//csv adaptee
public class CsvResultExporterAdapter
        implements IResultExporter {

    private final CsvWriter csvWriter;

    public CsvResultExporterAdapter(
            CsvWriter csvWriter) {
        // TODO: Implement

        this.csvWriter = null;
    }

    @Override
    public ExportFormat getFormat() {
        // TODO: Implement
        return null;
    }

    @Override
    public String export(
            QueryResult result) {
        // TODO: Implement
        return null;
    }

    @Override
    public void export(
            QueryResult result,
            OutputStream output) {
        // TODO: Implement
    }

    private List<List<Object>> convertRows(
            QueryResult result) {
        // TODO: Implement
        return List.of();
    }
}
