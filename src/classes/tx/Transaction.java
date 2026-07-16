package classes.tx;

import enums.IsolationLevel;
import enums.TransactionState;

import java.util.UUID;

public class Transaction {
    public UUID txId = UUID.randomUUID();
    public TransactionState state = TransactionState.ACTIVE;
    public IsolationLevel isolation = IsolationLevel.READ_COMMITTED;
}