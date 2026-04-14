package com.api.tests;

import static io.restassured.RestAssured.*;

import static com.api.enums.Roles.*;
import static com.api.utils.AuthTokenProvider.*;

import com.api.enums.Roles;
import com.api.pojo.*;
import com.api.utils.ConfigManager;
import com.api.utils.SpecUtils;
import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.*;

import static io.restassured.module.jsv.JsonSchemaValidator.*;

import org.checkerframework.checker.units.qual.A;
import org.checkerframework.checker.units.qual.C;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class CreateJobAPITest {


    @Test
    public void createJobAPITest() {

        //Creating the CreateJobPayload object

        Customer customer = new Customer("Vaibhav", "Vaidya", "7620564789", "", "test@gmail.com", "");
        CustomerAddress customerAddress = new CustomerAddress("G-101", "Nyati Evoke", "DN Parande Park", "Opp.to Jakat Maka", "Baner", "411017", "India", "Maharashtra");
        CustomerProduct customerProduct = new CustomerProduct("2025-06-10T18:30:00.000Z", "11941826720455847", "11141026745837", "11891026722869", "2025-06-10T18:30:00.000Z", 1, 1);
        Problems problems = new Problems(1, "Battery Issue");
        List<Problems> problemsList = new ArrayList<Problems>();
        problemsList.add(problems);

        CreateJobPayload createJobPayload = new CreateJobPayload(0, 2, 1, 1, customer, customerAddress, customerProduct, problemsList);

        given()
                .spec(SpecUtils.requestSpecWithAuth(FD, createJobPayload))
        .when()
                .post("/job/create")
        .then()
                .spec(SpecUtils.responseSpec_OK())
                .body(matchesJsonSchemaInClasspath("responseSchema/createJobAPIResponseSchema.json"))
                .body("message",equalTo("Job created successfully. "))
                .body("data.mst_service_location_id",equalTo(1))
                .body("data.job_number",startsWith("JOB_"));

    }

}
