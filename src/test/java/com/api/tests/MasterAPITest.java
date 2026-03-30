package com.api.tests;

import static io.restassured.RestAssured.*;

import static  com.api.enums.Roles.*;
import static com.api.utils.AuthTokenProvider.*;
import static com.api.utils.ConfigManager.*;

import static org.hamcrest.Matchers.*;

import com.api.utils.ConfigManager;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.annotations.Test;

import java.io.IOException;

public class MasterAPITest {

    @Test
    public void verifyMasterAPIResponse(){
        given()
                .baseUri(getProperty("BASE_URI"))
                .and()
                .contentType("") //	 default content type is application-form-urlencoded
                .header("Authorization", getToken(FD))
                .log().all()
        .when()
                .post("/master")
        .then()
                .log().all()
                .statusCode(200)
                .time(lessThan(1500L))
                .body("message",equalTo("Success"))
                .body("data",notNullValue())
                .body("data",hasKey("mst_oem"))
                .body("data",hasKey("mst_model"))
                .body("$",hasKey("message"))
                .body("$",hasKey("data"))
                .body("data.mst_oem.size()",greaterThanOrEqualTo(0))
                .body("data.mst_model.size()",greaterThan(0))
                .body("data.mst_oem.id",everyItem(notNullValue()))
                .body("data.mst_oem.name",everyItem(notNullValue()))
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("responseSchema/masterAPIResponseSchema_FD.json"));


    }

    @Test
    public void countAPITest_InvalidAuthToken() throws IOException {
        given()
                .baseUri(ConfigManager.getProperty("BASE_URI"))
                .and()
                .header("Authorization","")
                .log().uri()
                .log().method()
                .log().headers()
                .when()
                .get("/master")
                .then()
                .log().all()
                .statusCode(404);


    }
}
