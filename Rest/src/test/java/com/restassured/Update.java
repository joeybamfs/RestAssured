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

public class Update {

    final static String BASEURL = "https://reqres.in/api/users/2";

    @Test
    public void updateUsers(){
        String updatedUsers = "{\"name\": \"morpheus\"job\": \"zion resident\"}";
    }
}
