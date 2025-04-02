package restAssured;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

public class NestedBodyValidationDemo {

    @Test
    public void nestedBodyValidation(){
        RestAssured.get(Addresses.RATE_LIMIT_URL)
                .then()
                .rootPath("resources.core")
                .body("limit", Matchers.equalTo(60))
                .body("remaining", Matchers.lessThanOrEqualTo(60))
                .rootPath("resources.search")
                .body("limit", Matchers.equalTo(10))
                .noRootPath()
                .body("resource.graphql.limit", Matchers.equalTo(0));
    }
}
