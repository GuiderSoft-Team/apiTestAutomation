package apiTests;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class _04_TestWithParameter {

    String sprinGularURL = "http://142.93.110.12:9119";
    String petStoreURL = "https://petstore.swagger.io/v2";

    String accessToken = "Bearer " +
            "eyJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2NjM5NjM1NjAsInN1YiI6Ik1yaW5tb3lNYWp1bWRhciIsInVzZXJJZCI6Imd1aWRlcnNvZnQiLCJyb2xlIjoiVVNFUiJ9.sKf7KlMc7aIqZxRdgSZS2cWsiZu1orVMbH8jef7q-Dk";

        /*
          Given Id parameter value is 12
          When user sends GET request to /pet/{id}
          Then response status code should be 200
          And response content-type: application/json
          And "doggie" should be in response payload
       */

    @Test
    public void getPetStore_Positive_PathParam() {

        Response response = given().pathParam("id", 12)
                .when().get(petStoreURL + "/pet/{id}");
        response.prettyPrint();

        assertEquals(response.statusCode(), 200);
        assertEquals(response.contentType(), "application/json");
        assertTrue(response.body().asString().contains("doggie"));
    }

    //orderID 4003 query parameter
    @Test
    public void positiveTestWithQueryParam() {
        Response response = given().header("Authorization", accessToken)
                .and().queryParam("orderid","4003")
                .when().get(sprinGularURL + "/api/orders");

        response.prettyPrint();

        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json;charset=UTF-8");
    }

    /*
    Given query parameter values are :
        customerId|54
        shipName|Jerry Frazier
        When user sends GET request to /api/orders
        Then response status code should be 200
        And response content-type: application/json;charset=UTF-8
        And "Baby" should be in response payload
        And "Jenkins" should be in response payload
     */

    @Test
    public void positiveTestWithQueryParam1(){
        Response response = given().header("Authorization", accessToken)
                .and().queryParam("orderid",4003)
                .and().queryParam("employeeid",218)
                .and().queryParam("customerid",54)
                .and().queryParam("shipName","Jerry Frazier")
                .when().get(sprinGularURL + "/api/orders");

        response.prettyPrint();

        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json;charset=UTF-8");
        assertTrue(response.body().asString().contains("Baby"));
        assertTrue(response.body().asString().contains("Jenkins"));

    }


    /*
            "orderId": 4003,
            "employeeId": 218,
            "customerId": 54,
            "orderDate": 1471564800000,
            "shippedDate": 1481155200000,
            "paidDate": 1474934400000,
            "shipName": "Jerry Frazier",
            "shipAddress1": "23 Sundown Junction",
            "shipAddress2": null,
            "shipCity": "Obodivka",
            "shipState": null,
            "shipPostalCode": null,
            "shipCountry": "Ukraine",
            "shippingFee": 2.2900,
            "paymentType": "Cash",
            "orderStatus": "On Hold",
            "customerName": "Lisa Mitchell",
            "customerPhone": "1-(225)794-6979",
            "customerEmail": "lmitchell1h@live.com",
            "customerCompany": "Trudoo",
            "employeeName": "Frances Jenkins",
            "employeeDepartment": "Baby",
            "employeeJobTitle": "Developer IV"
     */

    @Test
    public void positiveTestWithQueryParamWithMap(){

        Map<String,Object> queryMap = new HashMap<>();
        queryMap.put("orderid", 4003);
        queryMap.put("employeeid", 218);
        queryMap.put("customerid", 54);
        queryMap.put("orderDate", 1471564800000L);
        queryMap.put("shippedDate", 1481155200000L);
        queryMap.put("paidDate", 1474934400000L);
        queryMap.put("shipName", "Jerry Frazier");
        queryMap.put("shipAddress1", "23 Sundown Junction");
        queryMap.put("shipCity", "Obodivka");
        queryMap.put("shipCountry", "Ukraine");

        Response response = given().header("Authorization", accessToken)
                .and().queryParams(queryMap)
                .when().get(sprinGularURL + "/api/orders");

        response.prettyPrint();

        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(), "application/json;charset=UTF-8");
        assertTrue(response.body().asString().contains("Baby"));
        assertTrue(response.body().asString().contains("Jenkins"));

    }
}
