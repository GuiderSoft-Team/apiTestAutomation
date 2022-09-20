package apiTests;


import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class _02_Token {

    String sprinGularUrl = "http://142.93.110.12:9119";
    String accessToken = "Bearer " +
            "eyJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2NjM3MDU5MDUsInN1YiI6Ik1yaW5tb3lNYWp1bWRhciIsInVzZXJJZCI6Imd1aWRlcnNvZnQiLCJyb2xlIjoiVVNFUiJ9.igIfBoRWZlaoV_hXjg1VEqrKsaOj3jJaAlvLmL4WTis";

    @Test
    public void orders() {
        Response response = given().header("Authorization", accessToken)
                .when().get(sprinGularUrl + "/api/orders");

        response.prettyPrint();
    }


}
