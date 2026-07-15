package classes.queryprocessor;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BufferPoolManager {
    private final Map<String, Page> pages = new HashMap<>();
    private final Map<String, List<Tuple>> tables = new HashMap<>();

    Page fetchPage(String pageId) {
        return pages.computeIfAbsent(pageId, Page::new);
    }

    void pinPage(String pageId) {
        fetchPage(pageId);
    }

    void unpinPage(String pageId, boolean isDirty) {
        Page page = fetchPage(pageId);
        page.isDirty = page.isDirty || isDirty;
    }

    List<Tuple> getTableRows(String tableId) {
        return tables.computeIfAbsent(tableId, key -> new ArrayList<>());
    }

    void insertRow(String tableId, Tuple tuple) {
        getTableRows(tableId).add(tuple.copy());
        Page page = fetchPage(tableId + "_page_0");
        page.slots.add(tuple.copy());
        page.isDirty = true;
    }
}
