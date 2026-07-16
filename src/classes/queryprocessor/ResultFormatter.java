package classes.queryprocessor;

import java.util.List;

public class ResultFormatter {
    private final TupleFormatStrategy jsonStrategy = new JsonTupleFormatStrategy();
    private final TupleFormatStrategy csvStrategy = new CsvTupleFormatStrategy();

    public String formatAsJSON(List<Tuple> tuples) {
        return null;
    }

    public String formatAsCSV(List<Tuple> tuples) {
        return null;
    }
}
