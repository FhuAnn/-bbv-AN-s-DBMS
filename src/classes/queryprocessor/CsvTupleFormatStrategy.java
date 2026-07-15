package classes.queryprocessor;

import java.util.ArrayList;
import java.util.List;

final class CsvTupleFormatStrategy implements TupleFormatStrategy {
    @Override
    public String format(List<Tuple> tuples) {
        if (tuples.isEmpty()) {
            return "";
        }

        List<String> headers = new ArrayList<>(tuples.get(0).values.keySet());
        StringBuilder builder = new StringBuilder(String.join(",", headers)).append('\n');
        for (Tuple tuple : tuples) {
            List<String> cells = new ArrayList<>();
            for (String header : headers) {
                cells.add(String.valueOf(tuple.values.get(header)));
            }
            builder.append(String.join(",", cells)).append('\n');
        }
        return builder.toString().trim();
    }
}