package classes.core;

import java.util.UUID;

import classes.authentication.SecurityManager;
import classes.metadata.Catalog;
import classes.queryprocessor.QueryResult;
import classes.storageengine.StorageEngine;
import classes.tx.TransactionManager;



public class Database {

    private String name;

    private UUID id;

    private Catalog catalog;

    private StorageEngine storage;

    private TransactionManager txManager;

    private SecurityManager security;



    public Database(String name) {


    }



    public QueryResult executeSQL(String sql) {

        return null;

    }



    // Getters and Setters

    public String getName() {

        return null;

    }

    public UUID getId() {

        return null;

    }

    public Catalog getCatalog() {

        return null;

    }

    public StorageEngine getStorage() {

        return null;

    }

}