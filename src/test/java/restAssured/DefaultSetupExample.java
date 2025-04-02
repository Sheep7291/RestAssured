package restAssured;

import io.restassured.RestAssured;
import io.restassured.config.FailureConfig;
import io.restassured.listener.ResponseValidationFailureListener;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class DefaultSetupExample {

    public ResponseValidationFailureListener defaultFailureListener(){
        ResponseValidationFailureListener failureListener =
                ((requestSpecification, responseSpecification, response) ->
                        System.out.printf("Test failed, " +
                                "response status was %s and the body contained: %s",
                                response.getStatusCode(), response.body().asPrettyString()));
        return failureListener;
    }

    @BeforeSuite
    public void setup() {
        RestAssured.baseURI = Addresses.BASE_URL;
        RestAssured.basePath = "rate_limit";
        RestAssured.rootPath = "resources.core";
        RestAssured.config = RestAssured
                .config()
                .failureConfig(FailureConfig.failureConfig().failureListeners(defaultFailureListener()));
    }

    @Test
    public void testUsingGlobalConstants() {
        RestAssured.get()
                .then()
                .body("limit", Matchers.equalTo(60));
    }

    @Test
    public void failedTestExample(){
        RestAssured.get()
                .then()
                .body("tests", Matchers.equalTo(222));
    }

    @Test
    public void logExample(){
            RestAssured.get()
                    .then()
                    .log().ifValidationFails()
                    .body("tests", Matchers.equalTo(222));
    }
}
