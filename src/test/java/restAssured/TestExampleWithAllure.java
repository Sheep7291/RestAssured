package restAssured;

import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import testConfiguration.BaseTestConfiguration;

public class TestExampleWithAllure extends BaseTestConfiguration {
    @Test
    @Description("Test Nested Body Validation")
    public void nestedBodyValidation(){

        RestAssured
                .get(Addresses.RATE_LIMIT_URL)
                .then()
                .rootPath("resources.core")
                .body("limit", Matchers.equalTo(60))
                .body("remaining", Matchers.lessThanOrEqualTo(60))
                .rootPath("resources.search")
                .body("limit", Matchers.equalTo(10));
    }
}
