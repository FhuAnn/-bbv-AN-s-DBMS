package classes.storageengine;

import classes.tx.Transaction;
import enums.LockMode;

public class Lock {
    public LockMode mode;
    public Resource resource;
    public Transaction transaction;
}