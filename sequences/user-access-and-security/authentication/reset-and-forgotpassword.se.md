```mermaid
sequenceDiagram
    participant Client
    participant AuthService
    participant User
    participant SessionMgr

    %% Stage 1: Submit request
    Client->>AuthService: ForgotPassword(email)
    AuthService->>User: Check if email exists
    User-->>AuthService: User exists (User ID)
    AuthService->>AuthService: Generate Reset Token & Trigger Email Validation
    AuthService-->>Client: Success (Please check your email)

    %% Stage 2: Submit a new password with the token received by email
    Client->>AuthService: ResetPassword(ResetToken, newPassword)
    AuthService->>AuthService: Validate Reset Token (Check expiration/authenticity)
    AuthService->>AuthService: Hash(newPassword)
    AuthService->>User: Update Password(UserID, hashedNewPassword)
    User-->>AuthService: Confirm Update Success

    %% Additional security: revoke any existing login sessions
    AuthService->>SessionMgr: Revoke all active sessions for UserID
    SessionMgr-->>AuthService: Sessions cleared

    AuthService-->>Client: Password Reset Successfully!
```
