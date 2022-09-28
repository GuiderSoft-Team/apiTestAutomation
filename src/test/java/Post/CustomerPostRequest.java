package Post;

import com.github.javafaker.Faker;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;


import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class CustomerPostRequest {

    String accessToken;

    @BeforeClass
    public void beforeClass() {
        baseURI = ConfigurationReader.get("springularURL");
    }

        /*
    {
      "id": 321,
      "firstName": "Muhammed",
      "lastName": "Salah",
      "company": "guidersoft",
      "email": "string@string.com",
      "phone": "112233445566",
      "address1": "string_1",
      "address2": "string_2",
      "postalCode": "09500",
      "city": "izmir",
      "country": "Turkey",
      "state": ""
    }
     */

    @Test
    public void CustomerPostTest() {
        CustomerPost customerPost = new CustomerPost();
        customerPost.setId(321);
        customerPost.setFirstName("Muhammed");
        customerPost.setLastName("Salah");
        customerPost.setCompany("guidersoft");
        customerPost.setEmail("string@string.com");
        customerPost.setPhone("112233445566");
        customerPost.setAddress1("string_1");
        customerPost.setAddress2("string_2");
        customerPost.setPostalCode("09500");
        customerPost.setCity("izmir");
        customerPost.setCountry("Turkey");
        customerPost.setState("TR");

        TokenGenerator tokenGenerator = new TokenGenerator();
        accessToken = tokenGenerator.TokenGenerator("guidersoft", "quality_hunter");

        Response response = given().header("Authorization", accessToken)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .and()
                .body(customerPost)
                .when()
                .post("/api/customers");
        response.prettyPrint();

        String operationStatus = response.path("operationStatus");
        String operationMessage = response.path("operationMessage");

        System.out.println("operationStatus = " + operationStatus);
        System.out.println("operationMessage = " + operationMessage);

        assertEquals(operationStatus, "SUCCESS");
        assertEquals(operationMessage, "Customer Added");

    }

    @Test
    public void CustomerPostAutoTest() {
        int id = 1020;
        CustomerPost customerPost = new CustomerPost();

        for (int i = id; i < 1320; i++) {

            customerPost.setId(i);
            customerPost.setFirstName("Muhammed");
            customerPost.setLastName("Salah");
            customerPost.setCompany("guidersoft");
            customerPost.setEmail("string@string.com");
            customerPost.setPhone("112233445566");
            customerPost.setAddress1("string_1");
            customerPost.setAddress2("string_2");
            customerPost.setPostalCode("09500");
            customerPost.setCity("izmir");
            customerPost.setCountry("Turkey");
            customerPost.setState("TR");

            TokenGenerator tokenGenerator = new TokenGenerator();
            accessToken = tokenGenerator.TokenGenerator("guidersoft", "quality_hunter");

            Response response = given().header("Authorization", accessToken)
                    .header("Accept", "application/json")
                    .header("Content-Type", "application/json")
                    .and()
                    .body(customerPost)
                    .when()
                    .post("/api/customers");
            response.prettyPrint();
        }


    }

    @Test
    public void CustomerPostAutoFakerTest() {
        int id = 1321;
        CustomerPost customerPost = new CustomerPost();
        Faker faker = new Faker();

        for (int i = id; i < 1620; i++) {
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            String city = faker.address().city();

            customerPost.setId(i);
            customerPost.setFirstName(firstName);
            customerPost.setLastName(lastName);
            customerPost.setCompany("guidersoft");
            customerPost.setEmail(firstName + "." + lastName + "@guidersoft.com");
            customerPost.setPhone("112233445566");
            customerPost.setAddress1("string_1");
            customerPost.setAddress2("string_2");
            customerPost.setPostalCode("09500");
            customerPost.setCity(city);
            customerPost.setCountry(faker.address().country());
            customerPost.setState("TR");

            TokenGenerator tokenGenerator = new TokenGenerator();
            accessToken = tokenGenerator.TokenGenerator("guidersoft", "quality_hunter");

            Response response = given().header("Authorization", accessToken)
                    .header("Accept", "application/json")
                    .header("Content-Type", "application/json")
                    .and()
                    .body(customerPost)
                    .when()
                    .post("/api/customers");
            response.prettyPrint();
        }


    }
}
