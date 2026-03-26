package com.api.tests;

import static io.restassured.RestAssured.*;

import static com.api.utils.ConfigManager.*;

import static com.api.utils.AuthTokenProvider.*;

import static com.api.enums.Roles.*;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import static org.hamcrest.Matchers.*;

import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.annotations.Test;

import java.io.IOException;


public class UserDetailsAPITest {

    @Test
    public void userDetailsAPITest() throws IOException {



        Header authHeader = new Header("Authorization", getToken(FD));

        given()
                .baseUri(getProperty("BASE_URI"))
                .and()
                .header(authHeader)
                .and()
                .accept(ContentType.JSON)
                .log().uri()
                .log().method()
                .log().headers()
                .log().body()
        .when()
                .get("/userdetails")
        .then()
                .log().all()
                .statusCode(200)
                .time(lessThan(2000L))
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("responseSchema/userDetailsAPIResponseSchema.json"));

    }
}
