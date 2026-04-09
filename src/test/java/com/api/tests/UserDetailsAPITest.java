package com.api.tests;

import static io.restassured.RestAssured.*;

import static com.api.utils.ConfigManager.*;

import static com.api.utils.AuthTokenProvider.*;

import static com.api.enums.Roles.*;

import com.api.enums.Roles;
import com.api.utils.SpecUtils;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import static org.hamcrest.Matchers.*;

import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.annotations.Test;

import java.io.IOException;


public class UserDetailsAPITest {

    @Test
    public void userDetailsAPITest() throws IOException {

        given()
                .spec(SpecUtils.requestSpecWithAuth(FD))
        .when()
                .get("/userdetails")
        .then()
                .spec(SpecUtils.responseSpec_OK())
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("responseSchema/userDetailsAPIResponseSchema.json"));

    }
}
