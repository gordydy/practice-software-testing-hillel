package org.example.common;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class BaseController<T> {
    private static final String BASE_URI = "https://api.practicesoftwaretesting.com";

    private String token;

    protected RequestSpecification baseClient() {
        var requestSpecification = RestAssured.given()
                .baseUri(BASE_URI)
                .contentType(ContentType.JSON);
        if (token != null) {
            requestSpecification.header("Authorization", "Bearer " + token);
        }
        return requestSpecification;
    }

    public T withToken(String token) {
        this.token = token;
        return (T) this;
    }

    public void cleanToken() {
        this.token = null;
    }
}