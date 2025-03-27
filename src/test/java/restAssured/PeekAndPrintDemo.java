package restAssured;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class PeekAndPrintDemo {

    @Test
    void peek(){
        RestAssured.get(Addresses.BASE_URL)
                .prettyPeek();
    }

    @Test
    void print(){
        RestAssured.get(Addresses.BASE_URL)
                .prettyPrint();
    }
}
