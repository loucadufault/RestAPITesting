package ca.mcgill.ecse.group14.unit;

import org.junit.*;

import static ca.mcgill.ecse.group14.Resources.*;

import org.junit.Test;

import static io.restassured.RestAssured.*;

public class ShutDownTest {
    @BeforeClass
    public static void setup() {
        Server.start();
        Server.check();
    }

    @AfterClass
    public static void teardown() throws InterruptedException {
        Server.stop();
        Thread.sleep(500);
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
