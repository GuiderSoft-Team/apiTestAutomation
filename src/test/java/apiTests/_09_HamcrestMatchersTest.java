package apiTests;

import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class _09_HamcrestMatchersTest {


    String accessToken = ConfigurationReader.get("accessTokenSprinGular");

    @BeforeClass
    public void beforeClass() {
        baseURI = ConfigurationReader.get("springularURL");
    }


    @Test
    public void test1() {
        given().accept(ContentType.JSON)
                .when().get(ConfigurationReader.get("herokuURL") + "/booking")
                .then().assertThat().statusCode(200)
                .and().contentType("application/json; charset=utf-8")
                .header("Server", "Cowboy")
                .statusLine("HTTP/1.1 200 OK");
    }

    /*

      given query param size is 1000
      When user sends a get request to /api/customers
      Then status code is 200
      And content type is application/json;charset=UTF-8
      And json data has following
          "id": 1,
          "lastName": "Gray",
          "firstName": "Clarence",
          "email": "cgray0@rambler.ru",
          "company": "Jetpulse",
          "phone": "1-(260)615-5114",
          "city": "Fort Wayne",
          "state": "Indiana",
          "postalCode": "46805",
          "country": "United States"
   */
    @Test
    public void onCustomerHamcrest() {
        given().header("Authorization", accessToken)
                .and().queryParam("size", "1000")
                .when().get("/api/customers")
                .then().statusCode(200)
                .and().assertThat().contentType(equalTo("application/json;charset=UTF-8"))
                .and().assertThat().body("items.id[0]", equalTo(1),
                        "items.lastName[0]", equalTo("Gray"),
                        "items.firstName[0]", equalTo("Clarence"),
                        "items.email[0]", equalTo("cgray0@rambler.ru"),
                        "items.company[0]", equalTo("Jetpulse"),
                        "items.phone[0]", equalTo("1-(260)615-5114"),
                        "items.city[0]", equalTo("Fort Wayne"),
                        "items.state[0]", equalTo("Indiana"),
                        "items.postalCode[0]", equalTo("46805"),
                        "items.country[0]", equalTo("United States"))
        ;


    }
    /*
       given query param size is 1000
      When user sends a get request to /user
      Then status code is 200
      And content type is application/json;charset=UTF-8
      And X-Content-Type-Options is nosniff
      And Pragma is no-cache
      And X-Frame-Options is DENY
      And json data has following
         "userId": "guidersoft",
        "password": "quality_hunter",
        "company": "Abshire Inc",
        "firstName": "Mrinmoy",
        "lastName": "Majumdar",
        "email": "arivera2@joomla.org",
        "role": "USER",
        "fullName": "MrinmoyMajumdar"

     */
    @Test
    public void userData(){
        given().header("Authorization", accessToken)
                .and().queryParam("size", "1000")
                .when().get("/user")
                .then().statusCode(200)
                .and().contentType(equalTo("application/json;charset=UTF-8"))
                .and().assertThat().header("X-Content-Type-Options",equalTo("nosniff"))
                .and().assertThat().header("Pragma",equalTo("no-cache"))
                .and().assertThat().header("X-Frame-Options","DENY")
                .and().assertThat().body("data.userId",equalTo("guidersoft"),
                        "data.password",equalTo("quality_hunter"),
                        "data.company",equalTo("Abshire Inc"),
                        "data.firstName",equalTo("Mrinmoy"),
                        "data.lastName",equalTo("Majumdar"),
                        "data.email",equalTo("arivera2@joomla.org"),
                        "data.role",equalTo("USER"),
                        "data.fullName",equalTo("MrinmoyMajumdar"))
                .log().all()
        ;
    }
}
