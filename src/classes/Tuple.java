package classes;

import java.util.LinkedHashMap;
import java.util.Map;

public class Tuple {
    final Map<String, Object> values = new LinkedHashMap<>();

    Tuple copy() {
        Tuple tuple = new Tuple();
        tuple.values.putAll(values);
        return tuple;
    }
}
