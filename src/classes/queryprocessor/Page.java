package classes.queryprocessor;

import java.util.ArrayList;
import java.util.List;

public class Page {
    final String pageId;
    boolean isDirty;
    final List<Tuple> slots = new ArrayList<>();

    Page(String pageId) {
        this.pageId = pageId;
    }
}
