package org.example.userData.models;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RegisterUserRequest {
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String state;
    private String country;
    private String postcode;
    private String phone;
    private String dob;
    private String password;
    private String email;
}