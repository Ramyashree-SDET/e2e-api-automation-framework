package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.swing.plaf.PanelUI;

public class FirstApiTest {

    @Test
            public void getUserList()
    {
        Response response = RestAssured.given().when().get("http://216.10.245.166/Library/Addbook.php");
        System.out.println("Response body : ");
        response.prettyPrint();
        Assert.assertEquals(response.getStatusCode(),200);
    }


}
