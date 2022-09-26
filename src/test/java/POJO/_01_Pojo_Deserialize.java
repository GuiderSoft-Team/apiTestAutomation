package POJO;

import com.google.gson.Gson;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import org.testng.annotations.BeforeClass;
import utilities.ConfigurationReader;

import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class _01_Pojo_Deserialize {
    String accessToken = ConfigurationReader.get("accessTokenSprinGular");

    @BeforeClass
    public void beforeClass() {
        baseURI = ConfigurationReader.get("springularURL");
    }

    /*
    {
    "version": "1.0.0",
    "major": 1,
    "minor": 0,
    "patch": 0
}
     */
    @Test
    public void versionPOJO() {
        Response response = given().header("Authorization", accessToken)
                .when().get("/version");
        assertEquals(response.statusCode(), 200);

        //JSON to POJO --> de-serialize to java custom class
        //Json to OUR version class object

        Version version1 = response.body().as(Version.class);
        System.out.println(version1);

        System.out.println("version1.getVersion() = " + version1.getVersion());
        System.out.println("version1.getMajor() = " + version1.getMajor());
        System.out.println("version1.getMinor() = " + version1.getMinor());
        System.out.println("version1.getPatch() = " + version1.getPatch());

        assertEquals(version1.getVersion(),"1.0.0");
        assertEquals(version1.getMajor(),1);
        assertEquals(version1.getMinor(),0);
        assertEquals(version1.getPatch(),0);


    }
    /*
    {
    "firstname": "Marija",
    "lastname": "Živković",
    "totalprice": 888,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2022-09-26",
        "checkout": "2022-10-08"
    },
    "additionalneeds": "Late Checkout"
}
     */

    @Test
    public void bookingToPOJO(){
        Response response = given().accept("application/json")
                .when().get("https://restful-booker.herokuapp.com/booking/15");
        assertEquals(response.statusCode(), 200);

        Booking booking = response.body().as(Booking.class);

        System.out.println("booking.getFirstname() = " + booking.getFirstname());


    }
    @Test
    public void gson_example(){
        Gson gson = new Gson();

        //Json to Java collections or POJO --> de-serialization

        String ourJsonData ="{\n" +
                "    \"version\": \"1.0.0\",\n" +
                "    \"major\": 1,\n" +
                "    \"minor\": 0,\n" +
                "    \"patch\": 0\n" +
                "}";

        Map<String,Object> map = gson.fromJson(ourJsonData,Map.class);
        System.out.println(map);

        Version version = gson.fromJson(ourJsonData,Version.class);
        System.out.println(version);

//-------SERIALIZATION-------
        //JAVA Collection or POJO to JSON


        Version versionNew = new Version("1.1.0",2,1,1);

        String anotherVersion = gson.toJson(versionNew);
        System.out.println(anotherVersion);

    }


}
