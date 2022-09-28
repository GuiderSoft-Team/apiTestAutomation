package PUT_PATCH_DELETE;

import Post.TokenGenerator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;


import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class DeleteRequest {
    String accessToken;

    @BeforeClass
    public void beforeClass() {
        baseURI = ConfigurationReader.get("springularURL");
    }

    @Test
    public void test1(){

        TokenGenerator tokenGenerator = new TokenGenerator();
        accessToken = tokenGenerator.TokenGenerator("guidersoft", "quality_hunter");

        Random rn = new Random();
        int idDelete= rn.nextInt(299)+1001;
        //0 - 100
        //rn.nextInt(100); sıfırdan 99 e bir sayı
        //100-200
        //rn.nextInt(100)+100; 100 dahil 199 a kadar bir sayı seç
        //1001-1300
        //rn.nextInt(299)+1001;1001 dahil 1300 e kadar bir sayı seç

        System.out.println("idDelete = " + idDelete);

        given().header("Authorization", accessToken)
                .pathParam("id",idDelete)
                .when()
                .delete("/api/customers/{id}")
                .then()
                .statusCode(200).log().all();
    }
}
