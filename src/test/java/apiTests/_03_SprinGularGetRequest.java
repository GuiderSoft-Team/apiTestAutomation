package apiTests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class _03_SprinGularGetRequest {

    String sprinGularUrl = "http://142.93.110.12:9119";
    String accessToken = "Bearer " +
            "eyJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2NjM3MDU5MDUsInN1YiI6Ik1yaW5tb3lNYWp1bWRhciIsInVzZXJJZCI6Imd1aWRlcnNvZnQiLCJyb2xlIjoiVVNFUiJ9.igIfBoRWZlaoV_hXjg1VEqrKsaOj3jJaAlvLmL4WTis";

    @Test
    public void test1(){
        Response response = given().header("Authorization",accessToken)
                .when().get(sprinGularUrl+"/api/orders");

        response.prettyPrint();
        Assert.assertEquals(response.statusCode(),200);
    }
}
