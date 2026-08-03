package dbms_api.mappers;

import java.util.UUID;

import core.classes.builder.ColumnMetadataBuilder;
import core.classes.metadata.ColumnMetadata;
import dbms_api.dtos.column.ColumnResponse;
import dbms_api.dtos.column.CreateColumnRequest;
import dbms_api.dtos.column.UpdateColumnRequest;

public class ColumnMapper {
    public ColumnMetadata toDomain(UUID tableId, CreateColumnRequest request) {
        return ColumnMetadataBuilder.builder()
                .tableId(tableId)
                .name(request.name())
                .dataType(request.dataType())
                .nullable(request.nullable())
                .defaultValue(request.defaultValue())
                .position(request.position())
                .length(request.length())
                .precision(request.precision())
                .scale(request.scale())
                .build();
    }

    public ColumnResponse toResponse(ColumnMetadata column) {
        return new ColumnResponse(
                column.getId(),
                column.getName(),
                column.getDataType(),
                column.getLength(),
                column.isNullable(),
                column.getPosition(),
                column.getPrecision(),
                column.getScale());
    }

    public void updateDomain(
            ColumnMetadata column,
            UpdateColumnRequest request) {

        if (request.dataType() != null) {
            column.setDataType(request.dataType());
        }

        if (request.nullable() != null) {
            column.setNullable(request.nullable());
        }

        if (request.defaultValue() != null) {
            column.setDefaultValue(request.defaultValue());
        }

        if (request.length() != null) {
            column.setLength(request.length());
        }

        if (request.precision() != null) {
            column.setPrecision(request.precision());
        }

        if (request.scale() != null) {
            column.setScale(request.scale());
        }
    }
}
