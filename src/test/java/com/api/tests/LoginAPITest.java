package com.api.tests;

import com.api.pojo.UserCredentials;
import static com.api.utils.ConfigManager.*;

import com.api.utils.SpecUtils;
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
                        spec(SpecUtils.requestSpec(userCredentials))
                .when()
                        .post("/login")
                .then()
                        .spec(SpecUtils.responseSpec_OK())
                        .body("message", equalTo("Success"))
                        .body(matchesJsonSchemaInClasspath("responseSchema/loginAPIResponseSchema.json"));


        }






}
