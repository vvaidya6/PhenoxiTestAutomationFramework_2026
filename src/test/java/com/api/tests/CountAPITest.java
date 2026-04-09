package com.api.tests;

import static com.api.enums.Roles.FD;
import static io.restassured.RestAssured.*;

import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager;

import static org.hamcrest.Matchers.*;

import com.api.utils.SpecUtils;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.annotations.Test;

import java.io.IOException;

public class CountAPITest {



    @Test
    public void verifyCountAPIResponse() throws IOException {


        given()
                .spec(SpecUtils.requestSpecWithAuth(FD))
        .when()
                .get("/dashboard/count")
        .then()
                .spec(SpecUtils.responseSpec_OK())
                .body("message", equalTo("Success"))
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
                .spec(SpecUtils.requestSpec())
        .when()
                .get("/dashboard/count")
        .then()
                .spec(SpecUtils.responseSpec_TEXT(401));


    }
}
