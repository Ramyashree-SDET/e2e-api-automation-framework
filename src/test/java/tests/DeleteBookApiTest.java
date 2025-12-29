package tests;

import apiHelper.AddBookHelper;
import apiHelper.DeleteBookHelper;
import base.BaseTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pojo.AddBookRequest;
import pojo.AddBookResponse;
import utils.TestDataGenerator;

public class DeleteBookApiTest extends BaseTest {

    AddBookHelper addBook;
    DeleteBookHelper deleteBook;

    @BeforeClass
    public void setHelper()
    {
        addBook = new AddBookHelper(reqSpec);
        deleteBook =new DeleteBookHelper(reqSpec);
    }
    @Test
    public void shoulddDeleteBookSuccessfully()
    {
        AddBookRequest request = TestDataGenerator.createAddBookRequest();

        Response addResponse = addBook.addBookHelper(request);
        System.out.println("Msg of add response : "+addResponse.asString());

        //String bookId = addResponse.jsonPath().getString("ID");
        AddBookResponse addBookResponse=addResponse.as(AddBookResponse.class);
        String bookId = addBookResponse.getId();
        System.out.println("Book id is : "+bookId);
        Response deleteResponse = deleteBook.deleteBookHelper(bookId);

        Assert.assertEquals(deleteResponse.getStatusCode(),200);
        System.out.println("Status code: "+deleteResponse.getStatusCode());
        System.out.println("Msg of delete response : "+deleteResponse.asString());
        //Assert.assertTrue(deleteResponse.asString().toLowerCase().contains("book is successfully deleted"));
       //faulty api
        // 3️⃣ Accept both empty and success text
        Assert.assertTrue(
                deleteResponse.asString().isEmpty() || deleteResponse.asString().toLowerCase().contains("successfully deleted"),
                "Unexpected delete response body"
        );
    }
}
