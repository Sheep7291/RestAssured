package restAssured;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

public class BasicResponseBodyDemo {

    @Test
    public void jsonPathReturnsMap() {
        Response response = RestAssured.get(Addresses.RATE_LIMIT_URL);


        JsonPath jPath2 = response
                .body()
                .jsonPath();


        Map<String, String> fullJson = jPath2.get();
        Map<String, String> subMap = jPath2.get("resources");
        Map<String, String> subMap2 = jPath2.get("resources.core");

        int value = jPath2.get("resources.core.limit");
        int value2 = jPath2.get("resources.graphql.remaining");

        System.out.println(fullJson);
        System.out.println(subMap);
        System.out.println(subMap2);
        System.out.println(value);
        System.out.println(value2);

        Assert.assertEquals(value, 60);
        Assert.assertEquals(value2, 0);
    }

    //poniższy kod pokaże jak można zrobić to bardziej czytelne weryfikowanie body
    @Test
    public void matcherExample(){
        RestAssured.get(Addresses.BASE_URL)
                .then()
                .body("current_user_url", Matchers.equalTo(Addresses.BASE_URL + "/user"))
                .body(Matchers.containsString("feeds_url"))
                .body(Matchers.containsString("feeds_url"), Matchers.containsString("current_user_url"));
    }
}
