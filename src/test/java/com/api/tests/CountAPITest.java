package com.api.tests;

import static com.api.enums.Roles.FD;
import static io.restassured.RestAssured.*;

import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager;

import static org.hamcrest.Matchers.*;

import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.annotations.Test;

import java.io.IOException;

public class CountAPITest {



    @Test
    public void verifyCountAPIResponse() throws IOException {


        given()
                .baseUri(ConfigManager.getProperty("BASE_URI"))
                .and()
                .header("Authorization", AuthTokenProvider.getToken(FD))
                .log().uri()
                .log().method()
                .log().headers()
        .when()
                .get("/dashboard/count")
        .then()
                .log().all()
                .statusCode(200)
                .body("message", equalTo("Success"))
                .time(lessThan(1500L))
                .body("data", notNullValue())
                .body("data.size()",equalTo(3))
                .body("data.count",everyItem(greaterThanOrEqualTo(0)))
                .body("data.label",everyItem(not(blankOrNullString())))
                .body("data.key",containsInAnyOrder("pending_for_delivery","created_today","pending_fst_assignment"))
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("responseSchema/countAPIResponseSchema_FD.json"));

    }


    @Test
    public void countAPITest_MissingAuthToken() throws IOException {
        given()
                .baseUri(ConfigManager.getProperty("BASE_URI"))
                .and()
                .log().uri()
                .log().method()
                .log().headers()
        .when()
                .get("/dashboard/count")
        .then()
                .log().all()
                .statusCode(401);


    }
}
