package apiHelper;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojo.DeleteBookRequest;

public class DeleteBookHelper {


    protected RequestSpecification reqSpec;

    public DeleteBookHelper(RequestSpecification reqSpec)
    {
        this.reqSpec=reqSpec; //Constructor receives common reqSpec
    }

    //Reusable delete Method
    public Response deleteBookHelper(String bookId)
    {
        DeleteBookRequest deleteBookRequest = new DeleteBookRequest(bookId);
        Response response = reqSpec
                .body(deleteBookRequest)
                .post("Library/DeleteBook.php")
                .then()
                .extract().response();

        System.out.println("response from helper class : "+response.asString());
        return response;
    }

}
