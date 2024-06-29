package org.example.userData.userController;

import org.example.common.BaseController;
import org.example.common.ResponseDecorator;
import org.example.userData.models.*;

public class UserController extends BaseController<UserController> {
    public ResponseDecorator<RegisterUserResponse> registerUser(RegisterUserRequest registerUserRequest) {
        return new ResponseDecorator<>(
                baseClient()
                        .body(registerUserRequest)
                        .post("/users/register"),
                RegisterUserResponse.class
        );
    }

    public ResponseDecorator<LoginResponse> loginUser(LoginRequest loginRequest) {
        return new ResponseDecorator<>(
                baseClient()
                        .body(loginRequest)
                        .post("/users/login"),
                LoginResponse.class
        );
    }

    public ResponseDecorator<Void> deleteUser(String userId) {
        return new ResponseDecorator<>(
                baseClient()
                        .delete("users/" + userId),
                Void.class
        );
    }

    public ResponseDecorator<ChangePasswordResponse> changePassword(ChangePasswordRequest changePasswordRequest) {
        return new ResponseDecorator<>(
                baseClient()
                        .post("/users/change-password"),
                ChangePasswordResponse.class
        );
    }

}