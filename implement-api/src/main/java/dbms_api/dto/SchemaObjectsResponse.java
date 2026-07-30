package dbms_api.dto;

import java.util.List;

public class SchemaObjectsResponse {
    private List<TableSummary> tables;
    private List<ViewSummary> views;
    private List<SequenceSummary> sequences;
}
