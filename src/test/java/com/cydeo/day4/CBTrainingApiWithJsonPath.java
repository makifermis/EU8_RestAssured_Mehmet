package com.cydeo.day4;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
public class CBTrainingApiWithJsonPath {

    @BeforeAll
    public static void init(){
        //save baseUrl inside this variable so that we don't need to type each http method.
        baseURI="http://api.cybertektraining.com";

    }

    @DisplayName("GET Reguest to individual student")
    @Test
    public void test1(){
        // send a get request to student id 32881 as a path parameter
        Response response = given().pathParam("id", 32881)
                .get("/student/{id}");
        //verify status code 200/content type=application/json;charset=UTF-8/Content-Encoding = gzip
        assertEquals(200,response.statusCode());
        assertEquals("application/json;charset=UTF-8",response.contentType());
        assertEquals("gzip",response.header("Content-Encoding"));
        // verify Date header exists
        assertTrue(response.headers().hasHeaderWithName("Date"));
        // assert that
        /*
        firstName Vera
        batch 14
        section 12
        emailAddress aaa@gmail.com
        companyName Cybertek
        state IL
        zipCode 60606

        using JsonPath
         */
        JsonPath jsonPath = response.jsonPath();

        String firstName = jsonPath.getString("students[0].firstName");
        int batch = jsonPath.getInt("students[0].batch");
        int section = jsonPath.getInt("students[0].section");
        String email = jsonPath.getString("students[0].contact.emailAddress");
        String companyName = jsonPath.getString("students[0].company.companyName");
        String state = jsonPath.getString("students[0].company.address.state");
        int zipCode = jsonPath.getInt("students[0].company.address.zipCode");
        assertEquals("Vera",firstName);
        assertEquals(14,batch);
        assertEquals(12,section);
        assertEquals("aaa@gmail.com",email);
        assertEquals("Cybertek",companyName);
        assertEquals("IL",state);
        assertEquals(60606,zipCode);

    }

}
