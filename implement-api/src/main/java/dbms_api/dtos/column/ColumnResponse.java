package dbms_api.dtos.column;

import java.util.UUID;

import org.hibernate.validator.constraints.Length;

public record ColumnResponse(
        UUID id,
        String name,
        String dataType,
        int length,
        boolean nullable,
        Integer Length,
        Integer precision,
        Integer scale
) {
}
