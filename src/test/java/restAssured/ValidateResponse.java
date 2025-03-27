package restAssured;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.*;

public class ValidateResponse {

    @Test
    void basicValidateExample(){
        RestAssured.get(Addresses.BASE_URL)
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .header("x-ratelimit-limit", "60");
    }

    @Test
    void simpleHamcrestMatchersDemo(){
        RestAssured.get(Addresses.BASE_URL)
                .then()
                .statusCode(200)
                .statusCode(lessThan(300))
                .header("cache-control", containsStringIgnoringCase("max-age=60"))
                .time(lessThan(2L), TimeUnit.SECONDS)
                .header("etaq", notNullValue())
                .header("etaq", not(emptyString()));
        }
    }
