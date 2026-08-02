package dbms_api.dtos.column;

import core.enums.DataType;

public record CreateColumnRequest(
        String name,
        DataType dataType,
        boolean nullable,
        Object defaultValue,
        int position,
        Integer length,
        Integer precision,
        Integer scale,
        boolean generated

) {
}
