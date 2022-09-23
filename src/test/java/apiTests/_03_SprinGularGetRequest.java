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
            "eyJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2NjM5NTU0NjAsInN1YiI6Ik1yaW5tb3lNYWp1bWRhciIsInVzZXJJZCI6Imd1aWRlcnNvZnQiLCJyb2xlIjoiVVNFUiJ9.Pc7cPuQfXBv3mD8gHH5Kwvf8iLoyim71FN3TZWf2wTA";

    @Test
    public void test1() {
        Response response = given().header("Authorization", accessToken)
                .when().get(sprinGularUrl + "/api/orders");

        response.prettyPrint();
        Assert.assertEquals(response.statusCode(), 200);
    }

    // orderID si 4003 olan order ı print ediniz.

    @Test
    public void test2() {

        Response response = given().header("Authorization", accessToken)
                .when().get(sprinGularUrl + "/api/orders?orderid=4003");

        response.prettyPrint();
    }

    // orderID si 4500 olan order ı print ediniz.
    //status code 200 olduğunu test ediniz.

    @Test
    public void test3(){
        Response response= given().header("Authorization", accessToken)
                .when().get(sprinGularUrl + "/api/orders?orderid=4500");
        response.prettyPrint();
    }
}
