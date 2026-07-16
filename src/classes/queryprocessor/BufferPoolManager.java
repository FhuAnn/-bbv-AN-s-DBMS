package classes.queryprocessor;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BufferPoolManager {
    private final Map<String, Page> pages = new HashMap<>();
    private final Map<String, List<Tuple>> tables = new HashMap<>();

    Page fetchPage(String pageId) {
        return null;
    }

    void pinPage(String pageId) {
    }

    void unpinPage(String pageId, boolean isDirty) {
    }

    List<Tuple> getTableRows(String tableId) {
        return null;
    }

    void insertRow(String tableId, Tuple tuple) {
    }
}
