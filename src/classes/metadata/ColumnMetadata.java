package classes.metadata;



import java.util.UUID;

import enums.DataType;



public class ColumnMetadata {

    private UUID id;

    private String name;

    private DataType type;

    private boolean nullable;

    private int position;

    private ColumnStats stats;



    public ColumnMetadata(String name, DataType type) {

        this.id = UUID.randomUUID();

        this.name = name;

        this.type = type;

    }

    // Getters + Setters

}

