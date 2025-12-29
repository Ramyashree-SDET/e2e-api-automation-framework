package tests;

import base.BaseTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojo.AddBookRequest;
import pojo.AddBookResponse;
import utils.TestDataGenerator;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class AddBookApiTest extends BaseTest {

    @Test
    public void shouldAddBookSuccessfully()
    {
        /* Method 1 : for small project
        String payload = "{\n" +
                "\n" +
                "\n" +
                "\"name\":\"Learn Appium Automation with Java\",\n" +
                "\"isbn\":\"abcd\",\n" +
                "\"aisle\":\"2271zz\",\n" +
                "\"author\":\"John foe\"\n" +
                "}\n";

        Response response = reqSpec
                .body(payload)
                .when()
                .post("Library/Addbook.php");

        Assert.assertEquals(response.getStatusCode(),200);
        Assert.assertEquals(response.jsonPath().getString("Msg"),"successfully added");
        //Assert.assertEquals(response.jsonPath().getString("ID"),"bcd227");

         */

        AddBookRequest addBookRequest = TestDataGenerator.createAddBookRequest();

        Response response =reqSpec
                .body(addBookRequest)
                .when()
                .post("Library/Addbook.php")
                .then()
                .body(matchesJsonSchemaInClasspath("schemas/addBookSchema.json"))
                .extract().response();

        System.out.println("Add Response is: "+response.asString());
        Assert.assertEquals(response.getStatusCode(),200);

        //Assert.assertEquals(response.jsonPath().getString("Msg"),"successfully added");
        //instead fetching and validating JSON raw data, use deserialization

        AddBookResponse addBookResponse = response.as(AddBookResponse.class);
        Assert.assertEquals(addBookResponse.getMsg(),"successfully added");

        String expectedID = addBookRequest.getIsbn()+ addBookRequest.getAisle();
        //Assert.assertEquals(response.jsonPath().getString("ID"),expectedID);
        Assert.assertEquals(addBookResponse.getId(),expectedID);

    }


}
