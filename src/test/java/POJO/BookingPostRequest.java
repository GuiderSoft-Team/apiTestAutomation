package POJO;

import com.google.gson.Gson;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import org.testng.annotations.BeforeClass;
import utilities.ConfigurationReader;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class BookingPostRequest {

    String accessToken = ConfigurationReader.get("accessTokenHeroku");

    @BeforeClass
    public void beforeClass() {
        baseURI = ConfigurationReader.get("herokuURL");
    }

    /*
    Given header type and Content-Type is application/json
    And request json body is:
    {
      "firstname":"GuiderSoft1",
      "lastname":"Hunters",
      "totalprice":"1250",
      "depositpaid":true,
      "bookingdates":{
                "checkin": "2022-01-01",
                "checkout": "2023-01-01"
                },
      "additionalneeds":"dinner"
   }
    When user sends POST request to '/booking'
    Then status code 200
    And json payload/response/body should contain:
    the same data what is posted
 */
    @Test
    public void PostNewBooking() {

        String jsonBody = "  {\n" +
                "      \"firstname\":\"GuiderSoft1\",\n" +
                "      \"lastname\":\"Hunters\",\n" +
                "      \"totalprice\":\"1250\",\n" +
                "      \"depositpaid\":true,\n" +
                "      \"bookingdates\":{\n" +
                "                \"checkin\": \"2022-01-01\",\n" +
                "                \"checkout\": \"2023-01-01\"\n" +
                "                },\n" +
                "      \"additionalneeds\":\"dinner\"\n" +
                "   }";

        Response response = given().header("Authorization", accessToken)
                .header("Content-Type", "application/json")
                .and()
                .body(jsonBody)
                .when()
                .post("/booking");
        //End of the post Request

        response.prettyPrint();
        assertEquals(response.statusCode(), 200);

        int bookingid = response.path("bookingid");
        String firstname = response.path("booking.firstname");
        String lastname = response.path("booking.lastname");
        String checkin = response.path("booking.bookingdates.checkin");

        System.out.println("bookingid = " + bookingid);
        System.out.println("firstname = " + firstname);
        System.out.println("lastname = " + lastname);
        System.out.println("checkin = " + checkin);

    }

    @Test
    public void PostNewBooking2() {
        //Cretae a map keep request json body information
        Map<String, Object> bookingdatesMap = new HashMap<>();
        bookingdatesMap.put("checkin", "2022-01-01");
        bookingdatesMap.put("checkout", "2023-01-01");

        Map<String, Object> requestBodyMap = new HashMap<>();
        requestBodyMap.put("firstname", "GuiderSoft2");
        requestBodyMap.put("lastname", "Hunters");
        requestBodyMap.put("totalprice", "2500");
        requestBodyMap.put("depositpaid", false);
        requestBodyMap.put("bookingdates", bookingdatesMap);
        requestBodyMap.put("additionalneeds", "lunch");


        Response response = given().header("Authorization", accessToken)
                .header("Content-Type", "application/json")
                .and()
                .body(requestBodyMap)
                .when()
                .post("/booking");
        //End of the post Request

        response.prettyPrint();
        assertEquals(response.statusCode(), 200);

        int bookingid = response.path("bookingid");
        String firstname = response.path("booking.firstname");
        String lastname = response.path("booking.lastname");
        String checkin = response.path("booking.bookingdates.checkin");

        System.out.println("bookingid = " + bookingid);
        System.out.println("firstname = " + firstname);
        System.out.println("lastname = " + lastname);
        System.out.println("checkin = " + checkin);

    }

    @Test
    public void PostNewBooking3() {

        Bookingdates bookingdatesNewObject = new Bookingdates();
        bookingdatesNewObject.setCheckin("2025-01-01");
        bookingdatesNewObject.setCheckout("2026-01-01");

        Booking bookingNewObject = new Booking();
        bookingNewObject.setFirstname("GuiderSoft3");
        bookingNewObject.setLastname("Hunters");
        bookingNewObject.setTotalprice(25000);
        bookingNewObject.setDepositpaid(true);
        bookingNewObject.setBookingdates(bookingdatesNewObject);
        bookingNewObject.setAdditionalneeds("lunch");


        Response response = given().header("Authorization", accessToken)
                .header("Content-Type", "application/json")
                .and()
                .body(bookingNewObject)
                .when()
                .post("/booking");
        //End of the post Request

        response.prettyPrint();
        assertEquals(response.statusCode(), 200);

        int bookingid = response.path("bookingid");
        String firstname = response.path("booking.firstname");
        String lastname = response.path("booking.lastname");
        String checkin = response.path("booking.bookingdates.checkin");

        System.out.println("bookingid = " + bookingid);
        System.out.println("firstname = " + firstname);
        System.out.println("lastname = " + lastname);
        System.out.println("checkin = " + checkin);

    }

    @Test
    public void PostNewBooking4() {
        Bookingdates bookingdatesNewObject = new Bookingdates("2025-01-01", "2026-01-01");
        Booking bookingNewObject = new Booking("GuiderSoft4", "Hunters", 3500, false, bookingdatesNewObject, "taxi");

        Response response = given().header("Authorization", accessToken)
                .header("Content-Type", "application/json")
                .and()
                .body(bookingNewObject)
                .when()
                .post("/booking");
        //End of the post Request

        response.prettyPrint();
        assertEquals(response.statusCode(), 200);

        int bookingid = response.path("bookingid");
        String firstname = response.path("booking.firstname");
        String lastname = response.path("booking.lastname");
        String checkin = response.path("booking.bookingdates.checkin");

        System.out.println("bookingid = " + bookingid);
        System.out.println("firstname = " + firstname);
        System.out.println("lastname = " + lastname);
        System.out.println("checkin = " + checkin);

        Map<String, Object> bookingdatesMap = new HashMap<>();
        bookingdatesMap.put("checkin", "2025-01-01");
        bookingdatesMap.put("checkout", "2026-01-01");

        Map<String, Object> bookingMap = new HashMap<>();
        bookingMap.put("firstname", "ali");
        bookingMap.put("lastname", "vali");
        bookingMap.put("bookingdates", bookingdatesMap);

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("bookingid",125);
        responseMap.put("booking",bookingMap);



    }
}
