package com.gorest.testsuite;

import com.gorest.testbase.TestBase;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

/**
 * 1. Verify the if the total record is 25
 * 2. Verify the if the title of id = 93997 is equal to ”Demitto conqueror atavus argumentum corrupti
 * cohaero libero.”
 * 3. Check the single user_id in the Array list (5914249)
 * 4. Check the multiple ids in the ArrayList (5914243, 5914202, 5914199)
 * 5. Verify the body of userid = 5914197 is equal “Desidero vorax adsum. Non confero clarus.
 * Velut defessus acceptus. Alioqui dignissimos alter. Tracto vel sordeo. Vulpes curso tollo. Villa usus
 * vos. Terreo vos curtus. Condico correptius praesentium. Curatio deripio attero. Tempus creptio
 * tumultus. Adhuc consequatur undique. Adaugeo terminatio antiquus. Stultus ex temptatio. Autus
 * acerbitas civitas. Comptus terminatio tertius. Utpote fugit voluptas. Sequi adulescens caecus.”
 */
public class PostsAssertionTest extends TestBase {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in/public/v2";

        response = given()
                .when()
                .queryParam("page", "1")
                .queryParam("per_page", "20")
                .get("/posts?page=1&per_page=25")
                .then().statusCode(200);
    }

    //1. Verify  if the total record is 25
    @Test
    public void test1() {
        response.body("size()", equalTo(25));
    }

    //2. Verify the if the title of id =  139915 is equal to ”Theologus sequi stabilis cimentarius verto delectus adipiscor cauda varius.".
    @Test
    public void test2() {
        response.body("[0].title", equalTo("Theologus sequi stabilis cimentarius verto delectus adipiscor cauda varius."));
    }

    //3. Check the single user_id in the Array list (7018214)
    @Test
    public void test3() {
        response.body("[0].user_id", equalTo(7018214));
    }

    //4. Check the multiple ids in the ArrayList (140328, 140298, 140297)
    @Test
    public void test4() {
        response.body("id", hasItems(140328, 140298, 140297));
    }


    //5. Verify the body of userid = 139915 is equal “Aptus pectus animadverto. Cognomen dignissimos validus. Socius abbas cupiditate. Perferendis vilicus caput. Verecundia turba solio. Comminor tribuo certe. Architecto coniecto nihil. Sollicito inflammatio modi. Perspiciatis consectetur tepesco. Itaque commodo terreo. Labore strues cubitum. Alienus demo temperantia. Amiculum curia antepono. Vespillo mollitia vomica..
    @Test
    public void test5() {
        response.body("[0].body", equalTo("Aptus pectus animadverto. Cognomen dignissimos validus. Socius abbas cupiditate. Perferendis vilicus caput. Verecundia turba solio. Comminor tribuo certe. Architecto coniecto nihil. Sollicito inflammatio modi. Perspiciatis consectetur tepesco. Itaque commodo terreo. Labore strues cubitum. Alienus demo temperantia. Amiculum curia antepono. Vespillo mollitia vomica."));
    }

}


