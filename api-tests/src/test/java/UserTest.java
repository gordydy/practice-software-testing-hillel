import org.example.userData.assertions.LoginResponseAsserts;
import org.example.userData.assertions.RegisterUserResponseAsserts;
import org.example.userData.models.*;
import org.example.userData.userController.UserController;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;


public class UserTest extends BaseTest {
    UserController userController = new UserController();

    private String userId;

    @Test
    void testUser() {
        var expectedUser = buildUserForRequest(userEmail, USER_PASSWORD);
        var registerUserResponse = userController.registerUser(expectedUser)
                .assertStatusCode(201)
                .as();
        new RegisterUserResponseAsserts(registerUserResponse)
                .createdAtIsNotNull()
                .firstNameIs(expectedUser.getFirstName())
                .lastNameIs(expectedUser.getLastName())
                .countryIs(expectedUser.getCountry())
                .phoneIs(expectedUser.getPhone())
                .cityIs(expectedUser.getCity())
                .addressIs(expectedUser.getAddress());

        var loginRequestBody = new LoginRequest(userEmail, USER_PASSWORD);
        var userLoginResponse = userController.loginUser(loginRequestBody)
                .assertStatusCode(200)
                .as();
        new LoginResponseAsserts(userLoginResponse)
                .isNotExpired()
                .accessTokenIsNotNull()
                .tokenTypeIs("bearer");
        userId = registerUserResponse.getId();

        //Change password
        var changePasswordRequest = buildPasswordRequest();
        userController
                .changePassword(changePasswordRequest)
                .assertStatusCode(201);
    }

    @AfterEach
    void deleteUser() {
        var token = loginAsAdmin();
        userController.withToken(token).deleteUser(userId)
                .assertStatusCode(204);
    }

    private ChangePasswordRequest buildPasswordRequest() {
        return ChangePasswordRequest.builder()
                .currentPassword(USER_PASSWORD)
                .newPassword("hendRix222!")
                .newPasswordConfirmation("hendRix222!")
                .build();
    }
}