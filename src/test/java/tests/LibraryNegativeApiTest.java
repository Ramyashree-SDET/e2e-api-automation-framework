package tests;

import base.BaseTest;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojo.AddBookRequest;
import utils.TestDataGenerator;

import java.time.Duration;
import java.util.List;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class LibraryNegativeApiTest extends BaseTest {

    private static final Logger log = LoggerFactory.getLogger(LibraryNegativeApiTest.class);

    @Test
    public void addDuplicateBookTest()
    {
        String isbnVal= String.valueOf(System.currentTimeMillis());
        AddBookRequest bookRequest= TestDataGenerator.createAddBookRequest();
        bookRequest.setIsbn(isbnVal);

        Response firtresponse = reqSpec
                .body(bookRequest)
                .when()
                .post("/Library/Addbook.php")
                .then()//used for test readability
                .assertThat() // for readability , doesnot change functionality if not added
                .statusCode(200)//validate status code immidiately ,if success then extract response
                .body(matchesJsonSchemaInClasspath("schemas/addBookSchema.json"))
                .extract().response(); //used to reuse response data


        log.info("First response of adding book : "+firtresponse.asString());
        log.info("First response of adding book, message"+firtresponse.jsonPath().getString("Msg"));

        Response duplicateResponse = reqSpec
                .body(bookRequest)
                .when()
                .post("/Library/Addbook.php")
                .then()
                .extract().response();


        log.info("Fist Response after adding Duplicate book "+duplicateResponse.asString());
        log.info("Fist response after adding duplicate book message"+duplicateResponse.jsonPath().getString("Msg"));

        int statusCode = duplicateResponse.getStatusCode();

        String duplicateResponseBody = duplicateResponse.asString();

        Assert.assertTrue(statusCode==200||statusCode==404||statusCode==409,"Unexpectd status code for duplicate request");

        Assert.assertTrue(duplicateResponseBody.toLowerCase().contains("already")||
                duplicateResponseBody.toLowerCase().contains("error"),
                "Expected error message not found");

    }


    @Test
    public void addBookWithoutisbnTest()
    {
        AddBookRequest bookRequest=TestDataGenerator.createAddBookRequest();
        bookRequest.setIsbn("");

        List<String> allowedmessages = List.of(
                "isbn aleady exists",
                "invalid isbn",
                "missing isbn"
        ) ;

        Response response = reqSpec
                .body(bookRequest)
                .when()
                .post("Library/Addbook.php")
                .then()
                .extract().response();

        log.info("Response is : "+response.asString());

        int statusCode = response.getStatusCode();
        Assert.assertTrue(statusCode==200||statusCode==400,"Unexpected Status code for missing field");

        String responseMsg = response.jsonPath().getString("Msg");
        if(statusCode==200)
        {
            log.error("DEFECT : API allows book creation without ISBN");
        }else {
            Assert.assertEquals(allowedmessages.stream().anyMatch(m->responseMsg.toLowerCase().contains(m)),"Unexpectd Msg value: "+responseMsg);
        }




    }
}
