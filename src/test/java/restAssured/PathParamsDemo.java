package restAssured;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Map;

public class PathParamsDemo {


    @Test
    void withoutParamHardCoded() {
        RestAssured
                .get(Addresses.BASIC_REPO_URL + "/Sheep7291/java")
                .then()
                .statusCode(200)
                .body("id", Matchers.equalTo(772529504));
    }

    @Test
    void withParamOverloadedGet() {
        RestAssured.get(Addresses.BASIC_REPO_URL + "/{user}/{repo}", "Sheep7291", "java")
                .then()
                .statusCode(200)
                .body("id", Matchers.equalTo(772529504));
    }

    @Test
    void withPathParam() {
        RestAssured
                .given()
                .pathParams(Map.of(
                        "user", "Sheep7291",
                        "repo_name", "java")
                )
                .get(Addresses.BASIC_REPO_URL + "/{user}/{repo_name}")
                .then()
                .statusCode(200)
                .body("id", Matchers.equalTo(772529504));
    }

    @Test
    void withParam() {
        RestAssured
                .given()
                .pathParams(Map.of(
                        "user", "Sheep7291",
                        "repo_name", "java")
                )
                .params(Map.of(
                        "type", "public",
                        "per_page", "1"))
                .get(Addresses.BASIC_REPO_URL + "/{user}/{repo_name}")
                .then()
                .statusCode(200);
    }

    @DataProvider
    public Object[][] queryParams(){
        return new Object[][]{
                {Map.of("per_page", "1", "page", "1")},
                {Map.of("per_page", "2", "page", "1")}
        };
    }

    @Test(dataProvider = "queryParams" )
    void testWithDataProvider(Map<String, String> params){

                RestAssured
                        .given()
                        .pathParams(Map.of(
                                "user", "Sheep7291",
                                "repo_name", "java")
                        )
                        .params(params)
                        .get(Addresses.BASIC_REPO_URL + "/{user}/{repo_name}")
                        .prettyPeek()
                        .then()
                        .statusCode(200);
    }


}
