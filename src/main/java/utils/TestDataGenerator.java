package utils;

import pojo.AddBookRequest;

public class TestDataGenerator {

    public static AddBookRequest createAddBookRequest()
    {
        AddBookRequest request = new AddBookRequest();

        String uniqueValue = String.valueOf(System.currentTimeMillis());

        request.setName("Api Automation Book");
        request.setAuthor("Automation Tester");
        request.setIsbn("isbn"+uniqueValue);
        request.setAisle(uniqueValue.substring(4));
        return request;
    }
}
