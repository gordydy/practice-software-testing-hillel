package org.example.userData.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginResponse {

        private String accessToken;
        private String tokenType;
        private int expiresIn;

    }