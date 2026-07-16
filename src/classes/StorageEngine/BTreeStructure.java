package classes.storageengine;

public class BTreeStructure {
    public int order;
    public BTreeNode root;

    public BTreeStructure(int order) {
        this.order = order;
    }

    public void insert(Key key, Pointer ptr) {
    }
}