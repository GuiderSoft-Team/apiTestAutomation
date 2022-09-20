package apiTests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class _01_SimpleGetRequest {
    String herokuUrl = "https://restful-booker.herokuapp.com";

    @Test
    public void test1() {
        //Response response = RestAssured.get("https://restful-booker.herokuapp.com/booking");
        Response response = RestAssured.get(herokuUrl + "/booking");

        //print the status code
        response.statusCode();
        System.out.println(response.statusCode());
        System.out.println("StatusCode = " + response.statusCode());
    }

    @Test
    public void test2() {
        Response response = RestAssured.get(herokuUrl + "/booking");

        //print the json body
        response.prettyPrint();
    }

    @Test
    public void test3() {
        Response response = RestAssured.get(herokuUrl + "/booking");

        Assert.assertEquals(response.statusCode(), 200);

        System.out.println("ContentType = " + response.contentType());

        Assert.assertEquals(response.contentType(), "application/json; charset=utf-8");
    }

    @Test
    public void test4() {

        given().when().get(herokuUrl + "/booking")
                .then().assertThat().statusCode(200)
                .and().contentType("application/json; charset=utf-8");


    }

    @Test
    public void test5() {
        Response response = given().accept("application/json")
                .when().get(herokuUrl + "/booking/150");
        response.prettyPrint();

        Assert.assertEquals(response.statusCode(), 200);

        //System.out.println("response.statusCode() = " + response.statusCode());

        Assert.assertTrue(response.body().asString().contains("a"), "name");
    }

    /**
     * https://restful-booker.herokuapp.com/booking/100 url’ine bir GET request
     * gonderdigimizde donen Response’un,
     * status code’unun 200,
     * ve content type’inin application/json; charset=utf-8,
     * ve Server isimli Header’in degerinin Cowboy,
     * ve status Line’in HTTP/1.1 200 OK
     */

    @Test
    public void test6() {
        //https://restful-booker.herokuapp.com/booking/100 url’ine bir GET request
        Response response = given().get(herokuUrl + "/booking/100");
        response.prettyPrint();

        //gonderdigimizde donen Response’un,
        //     * status code’unun 200,
        Assert.assertEquals(response.statusCode(), 200);

        //* ve content type’inin application/json; charset=utf-8,
        Assert.assertEquals(response.contentType(), "application/json; charset=utf-8");

        //ve Server isimli Header’in degerinin Cowboy,
        Assert.assertTrue(response.getHeader("Server").equals("Cowboy"));

        //ve status Line’in HTTP/1.1 200 OK
        Assert.assertEquals(response.statusLine(),"HTTP/1.1 200 OK");


    }
    @Test
    public void test7(){
        given().get(herokuUrl+"/booking/100")
                .then().assertThat().statusCode(200)
                .and().contentType("application/json; charset=utf-8")
                .header("Server","Cowboy")
                .statusLine("HTTP/1.1 200 OK");
    }

        /* TASK
    When users sends a get request to /booking/150
    Then status code should be 200
    And content type should be application/json;charset=UTF-8
    And json body should contain Howard
    */

    @Test
    public void test8(){
        //When users sends a get request to /booking/150
        Response response = given().get(herokuUrl+"/booking/150");
//        response.prettyPrint();

//        Then status code should be 200
        Assert.assertEquals(response.statusCode(),200);

//        And content type should be application/json;charset=UTF-8
        Assert.assertEquals(response.contentType(),"application/json; charset=utf-8");

//        And json body should contain Howard
        Assert.assertTrue(response.body().asString().contains("Howard"));
    }

    @Test
    public void test9(){
        given().get(herokuUrl+"/booking/150")
                .then().assertThat().statusCode(200)
                .and().contentType("application/json; charset=utf-8");
    }

    @Test
    public void test10(){
        Response response= given().get(herokuUrl+"/booking");

        response.prettyPrint();             // Response u yazdırır.
        response.getStatusCode();           // Status kodu verir.
        response.getHeaders();              // Tüm headerları verir.
        response.getHeader("Server"); // İstenen header değerini verir.
        response.getContentType();          // Response Content Type ını verir.
        response.getStatusLine();           // Response StatusLine verir.
        response.getTime();                 // Response gerçekleşme süresini verir.

    }
    /**
     * https://restful-booker.herokuapp.com/booking/250 url’ine bir GET request
     * gonderdigimizde donen Response’un,
     * status code’unun 200,
     * ve content type’inin application/json; charset=utf-8,
     * ve Server isimli Header’in degerinin Cowboy,
     * ve status Line’in HTTP/1.1 200 OK
     * ve response suresinin 5 sn’den kisa oldugunu manuel olarak test ediniz. 5 sn = 5000 ms
     */

    @Test
    public void test11(){
        Response response = given().get(herokuUrl+"/booking/250");

        Assert.assertEquals(response.statusCode(),200);
        Assert.assertEquals(response.contentType(),"application/json; charset=utf-8");
        Assert.assertTrue(response.getHeader("Server").equals("Cowboy"));
        Assert.assertEquals(response.statusLine(),"HTTP/1.1 200 OK");
        Assert.assertTrue(response.getTime()<5000);
    }
}
