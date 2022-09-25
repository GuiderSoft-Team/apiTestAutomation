package apiTests;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.List;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class _06_SprinGularTestWithPath {

    String accessToken = ConfigurationReader.get("accessTokenSprinGular");

    @BeforeClass
    public void beforeClass(){
        baseURI= ConfigurationReader.get("springularURL");
    }

    /*
    {
    "operationStatus": "SUCCESS",
    "operationMessage": null,
    "data": {
        "userId": "guidersoft", // data.userId
        "password": "quality_hunter",
        "company": "Abshire Inc",
        "firstName": "Mrinmoy",
        "lastName": "Majumdar",
        "email": "arivera2@joomla.org",
        "role": "USER",
        "fullName": "MrinmoyMajumdar"
                           "test" : {
                                    "test1":"pass",  //data.test.test1
                                    "test2":"pass"
                                    "test3":"fail"
                            }
    /*
    }
}
     */
    @Test
    public void testUserWithPath(){
        Response response = given().header("Authorization",accessToken)
                .accept("application/json")
                .when().get("/user");
        //response.prettyPrint();

        String operationStatus = response.path("operationStatus");
        String operationMessage = response.path("operationMessage");
        //String data = response.path("data");
        //String data = response.path("data").toString();
        String userId = response.path("data.userId");
        String password = response.path("data.password");
        String company = response.path("data.company");
        String firstName = response.path("data.firstName");
        String lastName = response.path("data.lastName");
        String email = response.path("data.email");
        String role = response.path("data.role");
        String fullName = response.path("data.fullName");

//        System.out.println("operationStatus = " + operationStatus);
//        System.out.println("operationMessage = " + operationMessage);
//        //System.out.println("data = " + data);
//        System.out.println("userId = " + userId);
//        System.out.println("password = " + password);
//        System.out.println("company = " + company);
//        System.out.println("firstName = " + firstName);
//        System.out.println("lastName = " + lastName);
//        System.out.println("email = " + email);
//        System.out.println("role = " + role);
//        System.out.println("fullName = " + fullName);
        assertEquals(operationStatus,"SUCCESS");
        assertEquals(operationMessage,null);
        assertEquals(userId,"guidersoft");
        assertEquals(password,"quality_hunter");
        assertEquals(company,"Abshire Inc");
        assertEquals(firstName,"Mrinmoy");
        assertEquals(lastName,"Majumdar");
        assertEquals(email,"arivera2@joomla.org");
        assertEquals(role, "USER");
        assertEquals(fullName, "MrinmoyMajumdar");

    }

    /*
    "items": [
        {
            "id": 0,
            "lastName": "baba",
            "firstName": "sadri",
            "email": "string.gmail",
            "avatar": "tests",
            "jobTitle": "baba",
            "department": "IT",
            "managerId": 0,
            "phone": "1234567",
            "address1": "no:17",
            "address2": "ayrancı",
            "city": "ankara",
            "state": "string",
            "postalCode": "06200",
            "country": "Turkey"
        }
     */
    @Test
    public void getAllEmployeeWithPath(){

        Response response = given()
                .header("Authorization",accessToken)
                .and().queryParam("size",50)
                .when().get("/api/employees");

        assertEquals(response.statusCode(),200);
        assertEquals(response.getHeader("Content-Type"),"application/json;charset=UTF-8");

        int firstEmployeeId = response.path("items.id[0]");
        String firstEmployeeFirstName = response.path("items.firstName[0]");
        String lastMinusOneFirstName = response.path("items.firstName[38]");

        System.out.println("firstEmployeeId = " + firstEmployeeId);
        System.out.println("firstEmployeeFirstName = " + firstEmployeeFirstName);
        System.out.println("lastMinusOneFirstName = " + lastMinusOneFirstName);

        List<String> employeeFirstNames= response.path("items.firstName");
        System.out.println("employeeFirstNames = " + employeeFirstNames);

        List<Integer> employeeIDs = response.path("items.id");
        System.out.println("employeeIDs = " + employeeIDs);


        //iter --> forech loop
        //itar --> for  loop
        for (Integer employeeID : employeeIDs) {
            if (employeeID==75){
            System.out.println("employeeID = " + employeeID);
            break;
            }
        }

    }
}
