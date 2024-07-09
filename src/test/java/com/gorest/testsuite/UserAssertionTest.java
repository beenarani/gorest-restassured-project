package com.gorest.testsuite;

import com.gorest.testbase.TestBase;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

/**
 * Assert the following:
 * 1. Verify the if the total record is 20
 * 2. Verify the if the name of id = 5914197 is equal to ”Bhilangana Dhawan”
 * 3. Check the single ‘Name’ in the Array list (Dev Bhattacharya)
 * 4. Check the multiple ‘Names’ in the ArrayList (Usha Kaul Esq., Akshita Mishra, Chetanaanand Reddy )
 * 5. Verify the emai of userid = 5914185 is equal “tandon_iv_aanandinii@prosacco.example”
 * 6. Verify the status is “Active” of user name is “Amaresh Rana”
 * 7. Verify the Gender = male of user name is “Dhanalakshmi Pothuvaal”
 */
public class UserAssertionTest extends TestBase{
    static ValidatableResponse response;


    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in/public/v2";

        response = given()
                .when()
                .queryParam("page", "1")
                .queryParam("per_page", "20")
                .get("/users?page=1&per_page=20")
                .then().statusCode(200);
    }

    //1. Verify if the total record is 25
    @Test
    public void test001() {
        response.body("size()", equalTo(20));
    }

    //2. Verify the if the name of id = 7018226 is equal to ”Chaten Kakkar”
    @Test
    public void test002() {
        response.body("find{it.id == 7018226}.name", equalTo("Chaten Kakkar"));
    }

    //3. Check the single ‘Name’ in the Array list (Chaten Kakkar)
    @Test
    public void test003() {
        response.body("name", hasItem("Chaten Kakkar"));
    }

    //4. Check the multiple ‘Names’ in the ArrayList ("Chaten Kakkar", "Manik Malik", "Keerti Kaul")
    @Test
    public void test004() {
        response.body("name", hasItems("Chaten Kakkar", "Aryan Varman", "Keerti Kaul"));
    }

    //5. Verify the email of userid = 7018225 is equal "mehra_mina@schultz.example"
    @Test
    public void test005() {
        response.body("find{it.id == 7018225}.email", equalTo("mehra_mina@schultz.example"));
    }

    //6. Verify the status is “Active” of user name is “Dandapaani Agarwal”
    @Test
    public void test6() {
        response.body("[5].status", equalTo("active"));
    }

    //7. Verify the Gender = female
    @Test
    public void test7() {
        response.body("[1].gender", equalTo("female"));

    }

}




