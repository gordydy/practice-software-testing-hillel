import org.example.userData.models.*;
import org.example.userData.userController.UserController;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserTest extends BaseTest{
    UserController userController = new UserController();
    long timestamp = System.currentTimeMillis();
    private static final String USER_PASSWORD = "jimiJIMI27!";
    private final String userEmail = "hendrixxx" + timestamp + "@gmail.com";

    @Test
    void testUser() {
        var registerUserRequest = buildUserForRequest();
        var registerUserResponse = userController
                .registerUser(registerUserRequest)
                .as(RegisterUserResponse.class);
        assertNotNull(registerUserResponse.getId());

        var loginRequestBody = new LoginRequest(userEmail, USER_PASSWORD);
        var userLoginResponse = userController
                .loginUser(loginRequestBody)
                .as(LoginResponse.class);
        assertNotNull(userLoginResponse.getAccessToken());

        var adminLoginRequestBody = new LoginRequest("admin@practicesoftwaretesting.com", "welcome01");
        var adminloginResponse = userController
                .loginUser(adminLoginRequestBody)
                .as(LoginResponse.class);

        var changePasswordRequest = buildPasswordRequest();
        var changePasswordResponse = userController
                .changePassword(changePasswordRequest)
                .as(ChangePasswordResponse.class);
        assertEquals(changePasswordResponse.getMessage(), "true");

        var userId = registerUserResponse.getId();
        var token = adminloginResponse.getAccessToken();
        userController.deleteUser(userId, token)
                .then()
                .statusCode(204);
    }

    private RegisterUserRequest buildUserForRequest() {
        return RegisterUserRequest.builder()
                .firstName("Jimi")
                .lastName("Hendrix")
                .phone("0987654321")
                .email(userEmail)
                .password(USER_PASSWORD)
                .country("USA")
                .state("Washington")
                .city("Seattle")
                .address("Hendrix ave. 27")
                .postcode("11223344")
                .dob("1941-01-01")
                .build();
    }

    private ChangePasswordRequest buildPasswordRequest() {
        return ChangePasswordRequest.builder()
                .currentPassword(USER_PASSWORD)
                .newPassword("hendRix222!")
                .newPasswordConfirmation("hendRix222!")
                .build();
    }

}