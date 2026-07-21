package unit.security;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import classes.authentication.AuthService;

@DisplayName("User Tests")
class UserTests {

    private AuthService authService;

    @BeforeEach
    void setUp() {
        authService = new AuthService();
    }

    @Nested
    class ConstructorTests {
        @Test
        void constructor_ShouldCreateAuthService() {
            assertNotNull(authService);
        }

        @Test
        void constructor_ShouldInitializeEmptyUsers() {
        }
    }

    @Nested
    class RegistrationTests {
        @Test
        void register_ShouldCreateUser() {
        }

        @Test
        void register_ShouldIncreaseUserCount() {
        }

        @Test
        void register_ShouldRejectDuplicateUsername() {
        }

        @Test
        void register_ShouldRejectBlankUsername() {
        
        }

        @Test
        void register_ShouldRejectShortPassword() {

        }
    }

    @Nested
    class LoginTests {
        @Test
        void login_ShouldCreateSessionToken() {

        }

        @Test
        void login_ShouldIncreaseSessionCount() {
  
        }

        @Test
        void login_ShouldRejectUnknownUser() {
          
        }

        @Test
        void login_ShouldRejectWrongPassword() {
           
        }
    }

    @Nested
    class SessionTests {
        @Test
        void isAuthenticated_ShouldReturnTrueForValidToken() {
         
        }

        @Test
        void logout_ShouldRemoveSession() {
           
        }

        @Test
        void logout_ShouldReturnFalseForMissingToken() {
           
        }
    }

    @Nested
    class PasswordTests {
        @Test
        void changePassword_ShouldAllowLoginWithNewPassword() {
          
        }

        @Test
        void changePassword_ShouldRejectWrongOldPassword() {

        }
    }

    @Nested
    class DisableTests {
        @Test
        void disableUser_ShouldInvalidateSessions() {
            
        }

        @Test
        void disableUser_ShouldRejectFutureLogin() {
         
        }

        @Test
        void disableUser_ShouldRejectMissingUser() {

        }
    }
}
