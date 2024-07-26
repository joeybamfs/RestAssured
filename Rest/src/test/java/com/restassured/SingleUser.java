package com.restassured;

import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static org.apache.commons.lang3.StringUtils.substring;
import static org.hamcrest.Matchers.*;
public class SingleUser {

    final static String BASEURL = "https://reqres.in/api/users";

    @Test
    public void listUsersHeaders(){
        given()
                    .get(BASEURL + "/2")
                .then()
                    .statusCode(200)
                    .contentType(ContentType.fromContentType("application/json"))
                    .assertThat()
                    .header("nel", not(emptyOrNullString())); //Assert that the nel custom header is not null
    }

    @Test
    public void listUSerBody(){
        given()
                    .get(BASEURL + "/2")
                .then()
                .rootPath("data")
                .assertThat()
                    .body("id", equalTo(2))
                    .body("email", containsString("janet.weaver@reqres.in"))
                    .body("first_name", containsStringIgnoringCase("janet"))
                    .body("last_name",containsStringIgnoringCase("weaver"))
                    .body("avatar", containsString("https://"))
                .rootPath("support")
                .assertThat()
                    .body("url", containsString("https://"))
                    .and().body("url", equalTo("https://reqres.in/#support-heading"))
                    .body("text", containsString("To keep ReqRes free, contributions towards server costs are appreciated!"));
    }

    @Test
    public void SingleUserNotFound(){
        given()
                    .get(BASEURL + "/23")
                .then()
                    .statusCode(404)
                    .contentType(ContentType.fromContentType("application/json"))
                    .body(emptyOrNullString());
    }
}
