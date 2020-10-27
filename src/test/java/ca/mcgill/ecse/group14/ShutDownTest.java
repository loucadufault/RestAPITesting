package ca.mcgill.ecse.group14;

import io.restassured.http.ContentType;
import org.junit.*;

import static ca.mcgill.ecse.group14.Resources.*;

import org.junit.Before;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.path.xml.XmlPath.CompatibilityMode.HTML;

import static io.restassured.RestAssured.*;
import static org.hamcrest.core.IsEqual.equalTo;

public class ShutDownTest {
    @Before
    public void setup() {
        Server.start();
        Server.check();
    }

    @Test
    public void testShutdown() throws InterruptedException{
         try{
             given()
                     .header("Content-Type", "application/json")
                     .header("Accept", "application/json")
                     .get(BASE_URL+"/shutdown");
         } catch (Exception e){
             Assert.assertEquals(e.toString().contains("Connection Refused"),false);
         }
    }
}
