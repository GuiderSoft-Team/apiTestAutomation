package apiTests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class _01_SimpleGetRequest {
    String heroku_url ="https://restful-booker.herokuapp.com/booking";

    @Test
    public void test1(){
        //Response response = RestAssured.get("https://restful-booker.herokuapp.com/booking");
        Response response = RestAssured.get(heroku_url);

        //print the status code
        System.out.println(response.statusCode());
        System.out.println("StatusCode = " + response.statusCode());
    }

    @Test
    public void test2(){
        Response response = RestAssured.get(heroku_url);

        //print the json body
        response.prettyPrint();
    }

    @Test
    public void test3(){
        Response response = RestAssured.get(heroku_url);

        Assert.assertEquals(response.statusCode(),200);

        System.out.println("ContentType = " + response.contentType());

        Assert.assertEquals(response.contentType(),"application/json; charset=utf-8");
    }

    @Test
    public void test4(){

       given().when().get(heroku_url)
               .then().assertThat().statusCode(200)
               .and().contentType("application/json; charset=utf-8");


    }

    @Test
    public void test5(){
        Response response = given().accept("application/json")
                .when().get(heroku_url + "/150");
        response.prettyPrint();

        Assert.assertEquals(response.statusCode(),200);

        //System.out.println("response.statusCode() = " + response.statusCode());

        Assert.assertTrue(response.body().asString().contains("Edgar"),"name");
    }
}
