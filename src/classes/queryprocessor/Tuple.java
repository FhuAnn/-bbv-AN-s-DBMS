package classes.queryprocessor;

import java.util.LinkedHashMap;
import java.util.Map;

public class Tuple {
    final Map<String, Object> values = new LinkedHashMap<>();

    public Tuple copy() {
        Tuple tuple = new Tuple();
        tuple.values.putAll(values);
        return tuple;
    }

    public Map<String, Object> getValues() {
        return values;
    }
}
