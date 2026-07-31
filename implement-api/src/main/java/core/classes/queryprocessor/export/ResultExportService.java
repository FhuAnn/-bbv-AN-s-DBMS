package core.classes.queryprocessor.export;

import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import core.classes.queryprocessor.QueryResult;
import core.classes.queryprocessor.export.IResultExporter;
public class ResultExportService {

    private final Map<ExportFormat, IResultExporter> exporters;

    public ResultExportService(
            List<IResultExporter> exporters) {
        // TODO: Implement

        this.exporters = null;
    }

    public String export(
            QueryResult result,
            ExportFormat format) {
        // TODO: Implement
        return null;
    }

    public void export(
            QueryResult result,
            ExportFormat format,
            OutputStream output) {
        // TODO: Implement
    }

    public void registerExporter(
            IResultExporter exporter) {
        // TODO: Implement
    }

    public boolean supports(
            ExportFormat format) {
        // TODO: Implement
        return false;
    }

    private IResultExporter getExporter(
            ExportFormat format) {
        // TODO: Implement
        return null;
    }
}
