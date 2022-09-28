package PUT_PATCH_DELETE;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;


import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class PutRequest {

    String accessToken = ConfigurationReader.get("accessTokenHeroku");

    @BeforeClass
    public void beforeClass() {
        baseURI = ConfigurationReader.get("herokuURL");
    }



        /*
{
Accept: application/json
Content-Type: application/json
id 71

    "firstname" : "James_1",
    "lastname" : "Brown_1",
    "totalprice" : 111,
    "depositpaid" : true,
    "bookingdates" : {
        "checkin" : "3018-01-01",
        "checkout" : "3019-01-01"
    },
    "additionalneeds" : "Breakfast"
}
     */


    @Test
    public void PutTest(){
        //Create one innermap for the put request json bdoy
        Map<String,Object> bookingdatesMap= new HashMap<>();
        bookingdatesMap.put("checkin" , "3018-01-01");
        bookingdatesMap.put("checkout" , "3019-01-01");

        Map<String,Object> jsonBodyMap = new HashMap<>();
        jsonBodyMap.put("firstname","James_1");
        jsonBodyMap.put("lastname","Brown_1");
        jsonBodyMap.put("totalprice",1250);
        jsonBodyMap.put("depositpaid",false);
        jsonBodyMap.put("bookingdates",bookingdatesMap);
        jsonBodyMap.put("additionalneeds","Orange Juice");

        given().log().all()
                .and()
                .header("Authorization",accessToken)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Cookie", "token=5c2da28b3cc9f23")
                .and()
                .body(jsonBodyMap)
                .when()
                .put("booking/952")
                .then().log().all()
                .assertThat().statusCode(200);


    }
            /*
{
Accept: application/json
Content-Type: application/json
id 952

    "firstname" : "James_1",
    "lastname" : "Brown_1",
    "totalprice" : 111,
    "depositpaid" : true,
    "bookingdates" : {
        "checkin" : "5018-01-01",
        "checkout" : "5019-01-01"
    },
    "additionalneeds" : "Breakfast"
}
     */

    @Test
    public void PathTest(){
        //Create one innermap for the put request json bdoy
        Map<String,Object> bookingdatesMap= new HashMap<>();
        bookingdatesMap.put("checkin" , "5018-01-01");
        bookingdatesMap.put("checkout" , "5019-01-01");

        Map<String,Object> jsonBodyMap = new HashMap<>();
        jsonBodyMap.put("firstname","Bahri");
        jsonBodyMap.put("lastname","Baba");
        jsonBodyMap.put("bookingdates",bookingdatesMap);
        jsonBodyMap.put("additionalneeds","Orange");

        given().log().all()
                .and()
                .header("Authorization",accessToken)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Cookie", "token=5c2da28b3cc9f23")
                .and()
                .body(jsonBodyMap)
                .when()
                .patch("booking/952")
                .then().log().all()
                .assertThat().statusCode(200);
    }


}
