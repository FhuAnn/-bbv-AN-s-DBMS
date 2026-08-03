package dbms_api.dtos.index;

import java.util.UUID;

public record RenameIndexRequest(String newName, UUID tableId) {
}
