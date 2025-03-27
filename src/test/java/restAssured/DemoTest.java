package restAssured;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class DemoTest {

    @Test
    void demoTest(){
        RestAssured.get(Addresses.BASE_URL)
                .then()
                .statusCode(200);
    }
}
