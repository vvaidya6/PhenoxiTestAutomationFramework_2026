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

import org.checkerframework.checker.units.qual.C;
import org.testng.annotations.Test;

public class CreateJobAPITest {


    @Test
    public void createJobAPITest() {

        Customer customer = new Customer("Vaibhav", "Vaidya", "7620564789", "", "test@gmail.com", "");
        CustomerAddress customerAddress = new CustomerAddress("G-101", "Nyati Evoke", "DN Parande Park", "Opp.to Jakat Maka", "Baner", "411017", "India", "Maharashtra");
        CustomerProduct customerProduct = new CustomerProduct("2025-06-10T18:30:00.000Z", "1194102672060847", "11941026720867", "11941026720869", "2025-06-10T18:30:00.000Z", 1, 1);
        Problems problems = new Problems(1, "Battery Issue");
        Problems[] problemsArray = new Problems[1];
        problemsArray[0] = problems;

        CreateJobPayload createJobPayload = new CreateJobPayload(0, 2, 1, 1, customer, customerAddress, customerProduct, problemsArray);

        given()
                .spec(SpecUtils.requestSpecWithAuth(FD, createJobPayload))
                .when()
                .post("/job/create")
                .then()
                .spec(SpecUtils.responseSpec_OK());
    }

}
