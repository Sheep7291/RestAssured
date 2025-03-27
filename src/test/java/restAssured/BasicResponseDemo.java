package restAssured;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BasicResponseDemo {

    @Test
    void convenienceMethods() {
        Response response = RestAssured.get(Addresses.BASE_URL);
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.getContentType(), "application/json; charset=utf-8");

    }

    @Test
    public void genericHeader() {
        Response response = RestAssured.get(Addresses.BASE_URL);
        Assert.assertEquals(response.getHeader("server"), "Github.com");
        Assert.assertEquals(response.getHeader("x-ratelimit-limit"), "60");
    }
    }
