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

    public ColumnMetadata() {

        

    }
    // Getters + Setters

    public UUID getId() {

        return id;

    }

    public void setId(UUID id) {

        this.id = id;

    }

    public String getName() {

        return name;

    }


    public void setName(String name) {

        this.name = name;

    }


    public DataType getType() {

        return type;

    }


    public void setType(DataType type) {

        this.type = type;

    }

    public boolean isNullable() {

        return nullable;

    }

    public void setNullable(boolean nullable) {

        this.nullable = nullable;

    }

    public int getPosition() {

        return position;

    }

    public void setPosition(int position) {

        this.position = position;

    }

    public ColumnStats getStats() {

        return stats;

    }

    public void setStats(ColumnStats stats) {

        this.stats = stats;

    }

    
}

