package classes.queryprocessor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ResultFormatter {
    String formatAsJSON(List<Tuple> tuples) {
        StringBuilder builder = new StringBuilder("[");
        for (int i = 0; i < tuples.size(); i++) {
            Tuple tuple = tuples.get(i);
            builder.append("{");
            int j = 0;
            for (Map.Entry<String, Object> entry : tuple.values.entrySet()) {
                if (j++ > 0) {
                    builder.append(",");
                }
                builder.append('"').append(escape(entry.getKey())).append('"').append(":");
                Object value = entry.getValue();
                if (value instanceof Number || value instanceof Boolean) {
                    builder.append(value);
                } else {
                    builder.append('"').append(escape(String.valueOf(value))).append('"');
                }
            }
            builder.append("}");
            if (i < tuples.size() - 1) {
                builder.append(",");
            }
        }
        builder.append("]");
        return builder.toString();
    }

    String formatAsCSV(List<Tuple> tuples) {
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

    private String escape(String value) {
        return value.replace("\\", "\\\\").replace("\"", "\\\"");
    }
}
