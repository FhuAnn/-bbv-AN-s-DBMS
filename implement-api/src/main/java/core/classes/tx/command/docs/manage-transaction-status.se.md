- Sequence — BEGIN Command
```mermaid
sequenceDiagram
    actor Client
    participant Invoker as TransactionCommandInvoker
    participant Command as BeginTransactionCommand
    participant Manager as TransactionManager
    participant Tx as Transaction

    Client->>Invoker: execute(beginCommand)
    Invoker->>Command: execute()

    Command->>Manager: begin()
    Manager->>Tx: new Transaction()
    Tx-->>Manager: transaction
    Manager-->>Command: transaction

    Command-->>Invoker: successful result
    Invoker->>Invoker: record(command, result)
    Invoker-->>Client: result
```

- Sequence - COMMIT Command
```mermaid
sequenceDiagram
    actor Client
    participant Invoker as TransactionCommandInvoker
    participant Command as CommitTransactionCommand
    participant Manager as TransactionManager
    participant Tx as Transaction
    participant State as ActiveTransactionState

    Client->>Invoker: execute(commitCommand)
    Invoker->>Command: execute()

    Command->>Manager: findById(transactionId)
    Manager-->>Command: transaction

    Command->>Manager: commit(transaction)
    Manager->>Tx: commit()
    Tx->>State: commit(transaction)
    State->>Tx: doCommit()
    State->>Tx: changeState(COMMITTED)

    Tx-->>Manager: completed
    Manager-->>Command: completed

    Command-->>Invoker: successful result
    Invoker->>Invoker: record(command, result)
    Invoker-->>Client: result
```
- Sequence - ROLLBACK Command
```mermaid
    sequenceDiagram
    actor Client
    participant Invoker as TransactionCommandInvoker
    participant Command as RollbackTransactionCommand
    participant Manager as TransactionManager
    participant Tx as Transaction
    participant State as ActiveTransactionState

    Client->>Invoker: execute(rollbackCommand)
    Invoker->>Command: execute()

    Command->>Manager: findById(transactionId)
    Manager-->>Command: transaction

    Command->>Manager: rollback(transaction)
    Manager->>Tx: rollback()
    Tx->>State: rollback(transaction)
    State->>Tx: doRollback()
    State->>Tx: changeState(ABORTED)

    Tx-->>Manager: completed
    Manager-->>Command: completed

    Command-->>Invoker: successful result
    Invoker->>Invoker: record(command, result)
    Invoker-->>Client: result
```
-  Sequence — SAVEPOINT Command
```mermaid
sequenceDiagram
    actor Client
    participant Invoker as TransactionCommandInvoker
    participant Command as CreateSavepointCommand
    participant Manager as TransactionManager
    participant Tx as Transaction
    participant State as ActiveTransactionState

    Client->>Invoker: execute(savepointCommand)
    Invoker->>Command: execute()

    Command->>Manager: findById(transactionId)
    Manager-->>Command: transaction

    Command->>Manager: createSavepoint(transaction, name)
    Manager->>Tx: createSavepoint(name)
    Tx->>State: createSavepoint(transaction, name)
    State->>Tx: doCreateSavepoint(name)

    Tx-->>Manager: completed
    Manager-->>Command: completed

    Command-->>Invoker: successful result
    Invoker->>Invoker: record(command, result)
    Invoker-->>Client: result
```

- How to use in system
```java
    TransactionCommandInvoker invoker =
            new TransactionCommandInvoker();

    TransactionCommand beginCommand =
            new BeginTransactionCommand(
                    transactionManager
            );
    TransactionCommandResult beginResult =
            invoker.execute(beginCommand);
```