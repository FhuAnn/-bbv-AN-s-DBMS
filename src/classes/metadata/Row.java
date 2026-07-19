package classes.metadata;
import java.util.Map;
import java.util.UUID;

public class Row {
    private UUID id;
    private Map<String, Object> values;
    private boolean deleted;
    private int version;

    public Row() {};

    public UUID getId() {
        return id;
    };

    public void setValue(String columnName, Object value) {

    };
    public Object getValue(String columnName) {
        return  null;
    };
    public boolean containsColumn(String columnName) {
        return false;
    };
    public void removeValue(String columnName) {

    };

    public Map<String, Object> getValues() {
        return values;
    };
    public void updateValues(Map<String, Object> newValues){

    };

    public void markDeleted() {

    };
    public void restore() {

    };
    public boolean isDeleted() {
        return deleted;
    };

    public Row copy() {
        return null;
    };
    public int calculateSize() {
        return 0;
    };
}