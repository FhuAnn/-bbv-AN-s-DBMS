package classes.database;

import java.util.UUID;

import classes.metadata.Catalog;
import classes.queryprocessor.QueryResult;
import classes.storageengine.StorageEngine;
import classes.tx.TransactionManager;

public class Database {

	private final String name;
	private final UUID id;
	private final Catalog catalog;
	private final StorageEngine storage;
	private final TransactionManager txManager;
	private final SecurityManager security;

	public Database(String name) {
		this.name = name;
		this.id = UUID.randomUUID();
		this.catalog = new Catalog();
		this.storage = new StorageEngine();
		this.txManager = new TransactionManager();
		this.security = new SecurityManager();
	}

	public QueryResult executeSQL(String sql) {
		throw new UnsupportedOperationException("SQL execution is not implemented yet.");
	}

	public String getName() {
		return name;
	}

	public UUID getId() {
		return id;
	}

	public Catalog getCatalog() {
		return catalog;
	}

	public StorageEngine getStorage() {
		return storage;
	}
}
