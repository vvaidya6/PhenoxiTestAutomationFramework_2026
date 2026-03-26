package com.api.tests;

import com.api.pojo.UserCredentials;
import static com.api.utils.ConfigManager.*;
import io.restassured.http.ContentType;

import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.*;

public class LoginAPITest {


        UserCredentials userCredentials = new UserCredentials("iamfd","password");

        @Test
         public void loginTest() throws IOException {
                given().
                        baseUri(getProperty("BASE_URI"))
                        .and()
                        .contentType(ContentType.JSON)
                        .and()
                        .accept(ContentType.JSON)
                        .and()
                        .body(userCredentials)
                        .log().uri()
                        .log().method()
                        .log().headers()
                        .log().body()
                .when()
                        .post("/login")
                .then()
                        .log().all()
                        .statusCode(200)
                        .time(lessThan(2000L))
                        .and()
                        .body("message", equalTo("Success"))
                        .and()
                        .body(matchesJsonSchemaInClasspath("responseSchema/loginAPIResponseSchema.json"));


        }






}
