package classes.queryprocessor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Executor {
   private final Map<String, List<Map<String, Object>>> tables =
            new LinkedHashMap<>();

    public void registerTable(
            String tableName,
            List<Map<String, Object>> rows
    ) {
       
    }

    public List<Map<String, Object>> executeSelect(
            String tableName,
            String column,
            Object expectedValue
    ) {
      return null;
    }

    public int executeInsert(
            String tableName,
            Map<String, Object> row
    ) {
        return 1;
    }

    public int executeUpdate(
            String tableName,
            String matchColumn,
            Object matchValue,
            String updateColumn,
            Object updateValue
    ) {
        return 0;
    }

    public int executeDelete(
            String tableName,
            String matchColumn,
            Object matchValue
    ) {
       return 0;
    }

    public int getRowCount(String tableName) {
       return 0;
    }

    public boolean containsTable(String tableName) {
        return true;
    }

    public Map<String, List<Map<String, Object>>> getTables() {
        return null;
    }

    private List<Map<String, Object>> requireTable(String tableName) {
       
        return new ArrayList<>(tables.getOrDefault(tableName, Collections.emptyList()));
    }

    private void validateTableName(String tableName) {
      
    }

    private List<Map<String, Object>> deepCopyRows(
            List<Map<String, Object>> rows
    ) {
       return null;
    }
}
