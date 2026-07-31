package core.classes.queryprocessor.export.json;

import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import core.classes.queryprocessor.QueryResult;
import core.classes.queryprocessor.export.ExportFormat;
import core.classes.queryprocessor.export.IResultExporter;

public class JsonResultExporterAdapter
        implements IResultExporter {

    private final JsonWriter jsonWriter;

    public JsonResultExporterAdapter(
            JsonWriter jsonWriter) {
        // TODO: Implement

        this.jsonWriter = null;
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

    private List<Map<String, Object>> convertRows(
            QueryResult result) {
        // TODO: Implement
        return List.of();
    }
}
