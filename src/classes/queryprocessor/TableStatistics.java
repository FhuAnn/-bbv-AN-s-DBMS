package classes.queryprocessor;

import java.util.HashMap;
import java.util.Map;

public class TableStatistics {
    int rowCount;
    int pageCount;
    Map<String, String> indexes = new HashMap<>();
}
