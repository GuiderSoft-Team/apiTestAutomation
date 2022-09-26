package apiTests;

import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;
import static org.hamcrest.Matchers.*;

public class _10_JsonToJavaCollection {

    String accessToken = ConfigurationReader.get("accessTokenSprinGular");

    @BeforeClass
    public void beforeClass() {
        baseURI = ConfigurationReader.get("springularURL");
    }

    //De-Serialization  --> Json to Java Collection for example List, Set, Map
    /*
    {
    "version": "1.0.0",
    "major": 1,
    "minor": 0,
    "patch": 0
}
     */
    @Test
    public void versionToMap() {
        Response response = given().header("Authorization", accessToken)
                .when().get("/version");
        assertEquals(response.statusCode(), 200);

        //we will convert json response to java map
        Map<String, Object> jsonDataMap = response.body().as(Map.class);
        System.out.println(jsonDataMap);

        String version = (String) jsonDataMap.get("version");
        Double major = (Double) jsonDataMap.get("major");
        Double minor = (Double) jsonDataMap.get("minor");
        Double patch = (Double) jsonDataMap.get("patch");


        System.out.println("version = " + version);
        System.out.println("major = " + major);
        System.out.println("minor = " + minor);
        System.out.println("patch = " + patch);

        assertEquals(version, "1.0.0");
        assertEquals(major, 1.0);
        assertEquals(minor, 0.0);
        assertEquals(patch, 0.0);
    }
    /*
    @Test
    public void allEmployeeListOfMap(){
        Response response = given().header("Authorization", accessToken)
                .when().get("/api/employees");
        assertEquals(response.statusCode(),200);

        // we need to de-serialize JSON response to List Of Maps
        JsonPath jsonPath=response.jsonPath();
       // jsonPath.prettyPrint();

//       String items = jsonPath.getString("items");
//        System.out.println("items = " + items);

        List<Map<String,Object>> allEmployeeList = response.body().as(List.class);
        System.out.println(allEmployeeList);
    }
     */

    @Test
    public void allBookingIdToListOfMap() {

        Response response = given().get("https://restful-booker.herokuapp.com/booking");

        assertEquals(response.statusCode(), 200);

        // we need to de-serialize JSON response to List Of Maps

        List<Map<String, Object>> allBookingIdList = response.body().as(List.class);

        System.out.println(allBookingIdList);

        //print first booking bokingId
        System.out.println(allBookingIdList.get(0).get("bookingid"));
        System.out.println(allBookingIdList.get(15).get("bookingid"));
        System.out.println(allBookingIdList.get(4));


        //save the bookingid 5 in map
        Map<String, Object> bookingId5 = allBookingIdList.get(4);
        System.out.println(bookingId5);


    }

    @Test
    public void productsToMap() {
        Response response = given().header("Authorization", accessToken)
                .when().get("/api/products");

        assertEquals(response.statusCode(), 200);

        //we de-serialize JSON response to Map
        Map<String, Object> productMap = response.body().as(Map.class);

        System.out.println(productMap.get("operationStatus"));
        System.out.println(productMap.get("first"));
        System.out.println(productMap.get("pageSize"));
        System.out.println(productMap.get("totalItems"));
        System.out.println(productMap.get("sort"));
        System.out.println(productMap.get("items"));

        List<Map<String, Object>> itemList = (List<Map<String, Object>>) productMap.get("items");

        //System.out.println(itemList);
/*
        {
            "id": 601,
            "productCode": "P1",
            "productName": "Nikon D810",
            "description": null,
            "standardCost": 1167,
            "listPrice": 1123,
            "targetLevel": 75,
            "reorderLevel": 10,
            "minimumReorderQuantity": 10,
            "quantityPerUnit": "50",
            "discontinued": 1,
            "category": "Camera"
        }
 */
        System.out.println(itemList.get(0).get("id"));
        System.out.println(itemList.get(0).get("productCode"));
        System.out.println(itemList.get(0).get("productName"));
        System.out.println(itemList.get(0).get("description"));
        System.out.println(itemList.get(0).get("standardCost"));
        System.out.println(itemList.get(0).get("listPrice"));
        System.out.println(itemList.get(0).get("targetLevel"));
        System.out.println(itemList.get(0).get("reorderLevel"));
        System.out.println(itemList.get(0).get("minimumReorderQuantity"));
        System.out.println(itemList.get(0).get("quantityPerUnit"));
        System.out.println(itemList.get(0).get("discontinued"));
        System.out.println(itemList.get(0).get("category"));
        System.out.println("==============================");
        System.out.println(itemList.get(1).get("id"));
        System.out.println(itemList.get(1).get("productCode"));

        assertEquals(itemList.get(0).get("id"),601.0);
        assertEquals(itemList.get(0).get("productCode"),"P1");
        assertEquals(itemList.get(0).get("productName"),"Nikon D810");
        assertEquals(itemList.get(0).get("description"),null);
        assertEquals(itemList.get(0).get("standardCost"),1167.0);
        assertEquals(itemList.get(0).get("listPrice"),1123.0);
        assertEquals(itemList.get(0).get("targetLevel"),75.0);
        assertEquals(itemList.get(0).get("reorderLevel"),10.0);
        assertEquals(itemList.get(0).get("minimumReorderQuantity"),10.0);
        assertEquals(itemList.get(0).get("quantityPerUnit"),"50");
        assertEquals(itemList.get(0).get("discontinued"),1.0);
        assertEquals(itemList.get(0).get("category"),"Camera");

    }


}
