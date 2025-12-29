package base;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
public class BaseSpec {

    private static RequestSpecification reqSpec;

    private BaseSpec(){};

    public static RequestSpecification getReqestSpec()
    {
        if(reqSpec==null)
        {
            reqSpec=new RequestSpecBuilder()
                    .setBaseUri("http://216.10.245.166")
                    .setContentType(ContentType.JSON)
                    .log(LogDetail.ALL)
                    .build();
        }
        return reqSpec;
    }
}
