package classes.metadata;

import java.util.UUID;

import enums.IndexType;

public class Index {
    public UUID id;
    public String name;
    public IndexType type;

    public Index() {
    }

    public void insert(Object key, Object pointer) {
    }

    public Object search(Object key) {
        return null;
    }
}
