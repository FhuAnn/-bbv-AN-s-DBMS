package classes.queryprocessor;

import java.util.List;
import java.util.Map;

final class JsonTupleFormatStrategy implements TupleFormatStrategy {
    @Override
    public String format(List<Tuple> tuples) {
        StringBuilder builder = new StringBuilder("[");
        for (int i = 0; i < tuples.size(); i++) {
            Tuple tuple = tuples.get(i);
            builder.append("{");
            int index = 0;
            for (Map.Entry<String, Object> entry : tuple.values.entrySet()) {
                if (index++ > 0) {
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

    private String escape(String value) {
        return value.replace("\\", "\\\\").replace("\"", "\\\"");
    }
}