package com.restassured;


import static io.restassured.RestAssured.*;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static org.apache.commons.lang3.StringUtils.substring;
import static org.hamcrest.Matchers.*;

public class ListUsers {

    final String BASEURL = "https://reqres.in";

    @Test
    public void listUsersHeaders(){
        Response response = get(BASEURL + "/api/users?page=2");

        //Assert that status code is 200
       response
                .then()
                .statusCode(equalTo(200));


       //Asserting the age header
        response
                .then()
                .assertThat()
                .header("age", notNullValue());


        //Asserting the standard headers
        response
                .then()
                .header("etag", notNullValue())
                .header("date",containsStringIgnoringCase("19 Jul 2024"));

        //Asserting custom headers
        response
                .then()
                .header("x-powered-by", equalTo("Express"))
                .header("server", stringContainsInOrder("cloudflare"));


    }

    @Test
    public void listAdvBody(){
        Response response = get(BASEURL + "/api/users?page=1");

        ResponseBody responseBody = response.body();
        responseBody.peek();

        JsonPath jsonPath = response.body().jsonPath();

        List<Map<String, String>> fullPath = jsonPath.get("data");

        for (Map<String, String> path : fullPath){
            System.out.println(path);
        }

        List<Integer> idNumbers = jsonPath.get("data.id");

        for (int idNumber : idNumbers){
            System.out.println(idNumber);
        }

    }


    @Test
    public void getRequest(){
        Response response = get(BASEURL + "/api/users?page=2");

        //Asserting Get request Body
        response
                .then()
                .rootPath("data.id")
                .assertThat()
                    .body("[0]", response1 -> Matchers.equalTo(7))
                    .body("[2]", response1 -> Matchers.lessThan(15))
                    .body("[3]", response1 -> Matchers.notNullValue())
                .rootPath("data.first_name")
                .assertThat()
                    .body("[0]", response1 -> Matchers.containsStringIgnoringCase("chael"))
                .noRootPath()
                .assertThat()
                    .body("data.first_name", hasItems("Michael", "George"))
                    .body("data.first_name[0]", containsString("chael"));
    }

    @Test
    public void listUheaders(){
        given()
                .get(BASEURL + "/api/users?page=2")
                .then()
                .statusCode(equalTo(200))
                .assertThat()
                .header("age", lessThan("800"));
    }

    @Test
    public void headRequest(){
        Response response = head(BASEURL + "/api/users?page=2");

        response
                .then()
                .statusCode(200)
                .body(emptyOrNullString());
    }

    @Test
    public void optionsRequest(){
        Response response = options(BASEURL + "/api/users?page=2");

        response
                .then()
                .statusCode(204);
    }

}
