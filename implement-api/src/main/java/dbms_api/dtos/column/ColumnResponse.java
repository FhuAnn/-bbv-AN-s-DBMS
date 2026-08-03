package dbms_api.dtos.column;

import java.util.UUID;

import org.hibernate.validator.constraints.Length;

import core.enums.DataType;

public record ColumnResponse(
        UUID id,
        String name,
        DataType dataType,
        int length,
        boolean nullable,
        Integer position,
        Integer precision,
        Integer scale
) {

}
