package apiHelper;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojo.AddBookRequest;
import utils.TestDataGenerator;

public class AddBookHelper {

    protected RequestSpecification reqSpec;


    public AddBookHelper(RequestSpecification reqSpec)
    {
        this.reqSpec = reqSpec;
    }

    public Response addBookHelper(AddBookRequest addBookRequest)
    {
        Response response = reqSpec
                .body(addBookRequest)
                .when()
                .post("Library/Addbook.php")
                .then()
                .extract().response();

        return response;
    }
}
