package com.restassured;

import static io.restassured.RestAssured.*;

import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.apache.commons.lang3.StringUtils.substring;
import static org.hamcrest.Matchers.*;


public class CreateUsers {

    final static String BASEURL = "https://reqres.in/api/users";
    @Test
    public void createUser(){
        String requestBody = "{\"name\": \"barney\" ,\"job\": \"member\"}";

        RestAssured.given()
                    .body(requestBody)
                .when()
                    .post(BASEURL)
                .then()
                    .statusCode(201) //Created Status Response
                    .log()
                    .body();
    }

    @Test
    public void getUsers(){
        given()
                    .get(BASEURL)
                .then()
                    .log()
                    .body();
    }
}
