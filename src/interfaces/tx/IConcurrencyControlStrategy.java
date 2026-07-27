package interfaces.tx;

import classes.tx.Resource;
import classes.tx.Transaction;
import classes.tx.concurrency.ConcurrencyContext;
import classes.tx.concurrency.TransactionOptions;
import classes.tx.concurrency.WorkloadProfile;
import enums.strategy.ConcurrencyStrategyType;

public interface IConcurrencyControlStrategy {
    void onBegin(
            Transaction transaction,
            ConcurrencyContext context);

    Object read(
            Transaction transaction,
            Resource resource,
            ConcurrencyContext context);

    void write(
            Transaction transaction,
            Resource resource,
            Object value,
            ConcurrencyContext context);

    boolean validate(
            Transaction transaction,
            ConcurrencyContext context);

    void commit(
            Transaction transaction,
            ConcurrencyContext context);

    void rollback(
            Transaction transaction,
            ConcurrencyContext context);

    boolean supports(
            TransactionOptions options);

    double estimateCost(
            TransactionOptions options,
            WorkloadProfile workload);

    ConcurrencyStrategyType getType();
}
