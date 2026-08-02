package dbms_api.dtos.column;

import core.enums.DataType;

public record UpdateColumnRequest(
        DataType dataType,
        Boolean nullable,
        Object defaultValue,
        Integer length,
        Integer precision,
        Integer scale) {

}
