package classes.queryprocessor;

import java.util.ArrayList;
import java.util.List;

public abstract class LogicalOperator {
    final List<LogicalOperator> children = new ArrayList<>();

    public List<LogicalOperator> getChildren() {
        return null;
    }
}
