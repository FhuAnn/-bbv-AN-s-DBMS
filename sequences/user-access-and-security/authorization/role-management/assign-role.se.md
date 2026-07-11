sequenceDiagram
participant AdminClient
participant RoleService
participant UserRoleRecord

    AdminClient->>RoleService: Request to assign role (userID, roleID)
    RoleService ->> RoleService: Validate user and role existence
    alt Role Assignment Exists
        RoleService->>UserRoleRecord: Add user-role mapping
        UserRoleRecord-->>RoleService: Assignment successful
        RoleService -->> AdminClient: Respond with success message
        RoleService -->> AdminClient: Return error (no such assignment)
    end
