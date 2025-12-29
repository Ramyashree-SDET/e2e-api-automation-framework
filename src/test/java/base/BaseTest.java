package base;

import config.ConfigManager;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;

import static io.restassured.RestAssured.given;

public class BaseTest {

    protected RequestSpecification reqSpec;

    @BeforeClass
    public void setup()
    {
       reqSpec= given()
               .spec(BaseSpec.getReqestSpec());
    }
}
