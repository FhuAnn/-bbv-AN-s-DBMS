package classes.metadata;

import classes.storageengine.BTreeStructure;
import classes.storageengine.Key;
import classes.storageengine.Pointer;
import enums.IndexType;

import java.util.UUID;

public class Index {
    public UUID id;
    public String name;
    public IndexType type;
    public BTreeStructure btree;

    public Index() {
        this.id = UUID.randomUUID();
    }

    public void insert(Key key, Pointer pointer) {
    }

    public Pointer search(Key key) {
        return null;
    }
}