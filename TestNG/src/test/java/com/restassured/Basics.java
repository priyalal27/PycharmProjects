package com.restassured;

import io.restassured.RestAssured;
import io.restassured.config.SSLConfig;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

public class Basics {

    @BeforeClass
    public void setup() {
        // Configure SSL to relax validation for all requests
        RestAssured.config = RestAssured.config()
                .sslConfig(SSLConfig.sslConfig()
                        .relaxedHTTPSValidation()
                        .allowAllHostnames());

        RestAssured.baseURI = "https://reqres.in";
    }

    @Test
    public void getcall() {
        given()
                .when()
                .get("/api/users/2")
                .then()
                .statusCode(200)
                .body("data.id", equalTo(2))
                .body("data.first_name", equalTo("Janet"));
    }
}