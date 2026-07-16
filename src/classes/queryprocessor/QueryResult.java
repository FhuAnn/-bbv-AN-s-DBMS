package classes.queryprocessor;

import classes.metadata.Schema;

import java.util.ArrayList;
import java.util.List;

public class QueryResult {
    public List<Tuple> rows = new ArrayList<>();
    public Schema schema;
}