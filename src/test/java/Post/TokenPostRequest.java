package Post;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;


import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class TokenPostRequest {
    String accessToken = ConfigurationReader.get("accessTokenSprinGular");

    @BeforeClass
    public void beforeClass() {
        baseURI = ConfigurationReader.get("springularURL");
    }

    @Test
    public void PostNewToken(){
        String jsonBody = "{\n" +
                "  \"username\": \"guidersoft\",\n" +
                "  \"password\": \"quality_hunter\"\n" +
                " }";

        Response response = given().log().all()
                .header("Accept", "*/*")
                .header("Content-Type","application/json")
                .and()
                .body(jsonBody)
                .when()
                .post("/session");

        response.prettyPrint();
        assertEquals(response.statusCode(),200);
/*
        String operationStatus = response.path("operationStatus");
        String operationMessage = response.path("operationMessage");
        String item = response.path("item");

        System.out.println("operationStatus = " + operationStatus);
        System.out.println("operationMessage = " + operationMessage);
        System.out.println("item = " + item);
        */
        JsonPath jsonPath = response.jsonPath();
        String operationStatus = jsonPath.getString("operationStatus");
        String operationMessage =jsonPath.getString("operationMessage");
        String item = jsonPath.getString("item");

        System.out.println("operationStatus = " + operationStatus);
        System.out.println("operationMessage = " + operationMessage);
        System.out.println("item = " + item);

        assertEquals(operationStatus,"SUCCESS");
        assertEquals(operationMessage,"Login Success");
    }

    @Test
    public void PostNewTokenPOJO(){
        //Create an object
        TokenPost tokenPost = new TokenPost();
        tokenPost.setUsername("guidersoft");
        tokenPost.setPassword("quality_hunter");

        Response response = given().header("Accept", "*/*")
                .header("Content-Type","application/json")
                .and()
                .body(tokenPost)
                .when()
                .post("/session");

        response.prettyPrint();

        JsonPath jsonPath =response.jsonPath();
        String operationStatus = jsonPath.getString("operationStatus");
        String operationMessage =jsonPath.getString("operationMessage");
        String item = jsonPath.getString("item");
        String userId = jsonPath.getString("item.userId");
        String firstName = jsonPath.getString("item.firstName");
        String lastName = jsonPath.getString("item.lastName");
        String email = jsonPath.getString("item.email");
        String roles = jsonPath.getString("item.roles");
        String token = jsonPath.getString("item.token");


        System.out.println("operationStatus = " + operationStatus);
        System.out.println("operationMessage = " + operationMessage);
        System.out.println("item = " + item);
        System.out.println("userId = " + userId);
        System.out.println("firstName = " + firstName);
        System.out.println("lastName = " + lastName);
        System.out.println("email = " + email);
        System.out.println("roles = " + roles);
        System.out.println("token = " + token);
    }


public static String TokenGenerator(String username, String password){
    //Create an object
    TokenPost tokenPost = new TokenPost();
    tokenPost.setUsername(username);
    tokenPost.setPassword(password);

    Response response = given().header("Accept", "*/*")
            .header("Content-Type","application/json")
            .and()
            .body(tokenPost)
            .when()
            .post("/session");

    response.prettyPrint();

    JsonPath jsonPath =response.jsonPath();
    String token = jsonPath.getString("item.token");

    return token;
}
}
