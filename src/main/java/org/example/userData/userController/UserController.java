package org.example.userData.userController;

import io.restassured.response.Response;
import org.example.BaseController;
import org.example.userData.models.ChangePasswordRequest;
import org.example.userData.models.LoginRequest;
import org.example.userData.models.RegisterUserRequest;

public class UserController extends BaseController {
    public Response registerUser(RegisterUserRequest registerUserRequest) {
        return baseClient()
                .body(registerUserRequest)
                .post("/users/register");
    }

    public Response loginUser(LoginRequest loginRequest) {
        return baseClient()
                .body(loginRequest)
                .post("/users/login");
    }

    public Response deleteUser(String userId, String token) {
        return baseClient()
                .header("Authorization", "Bearer " + token)
                .delete("/users/" + userId);
    }

    public Response changePassword (ChangePasswordRequest changePasswordRequest){
        return baseClient()
                .body(changePasswordRequest)
                .post("/users/change-password");
    }

}