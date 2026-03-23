package com.api.tests;

import com.api.pojo.UserCredentials;
import io.restassured.http.ContentType;
import static io.restassured.module.jsv.JsonSchemaValidator.*;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.*;

public class LoginAPITest {

        UserCredentials userCredentials = new UserCredentials("iamfd","password");

        @Test
         public void loginTest() {
                given().
                        baseUri("http://64.227.160.186:9000/v1")
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
