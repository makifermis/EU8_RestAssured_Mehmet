package com.cydeo.day2;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.baseURI;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanNegativeGetTest {

    @BeforeAll
    public static void init(){
        //save baseUrl inside this variable so that we don't need to type each http method.
        baseURI="http://100.24.6.145:8000";
    }

    /*
    Given accept type application/xml
    When user send GET request to api/spartans/10 end point
    Then status code must be 406
    And response Content Type must be application/xml
     */

    @DisplayName("Get request to /api/spartans/10 ")
    @Test
    public void test1(){

        Response response = given().accept(ContentType.XML)
                .when().get("/api/spartans/10");

        //status code 406
        assertEquals(406,response.statusCode());

        //verify content Type application/xml;charset=UTF-8
        assertTrue(response.contentType().equals("application/xml;charset=UTF-8"));

    }

}
