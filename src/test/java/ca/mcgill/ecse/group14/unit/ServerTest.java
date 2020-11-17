package ca.mcgill.ecse.group14.unit;

import ca.mcgill.ecse.group14.Resources;
import ca.mcgill.ecse.group14.Server;
import ca.mcgill.ecse.group14.Utils;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static ca.mcgill.ecse.group14.Resources.BASE_URL;
import static io.restassured.RestAssured.get;
import static org.junit.Assert.assertEquals;

public class ServerTest extends BaseTest {
    public ServerTest() {
        RestAssured.baseURI = BASE_URL;
    }

    @Before
    public void setupEach() {
        setup();
    }

    @Test
    public void test_CanRestart() {
        Server.shutdown();
        setup();
        get().then().assertThat().statusCode(Resources.STATUS_CODE.OK);
    }
    @Test
    public void test_IsRunning() {
        get().then().assertThat().statusCode(Resources.STATUS_CODE.OK);
    }
    @Test
    public void test_Shutdown() throws InterruptedException{
        try{
            get("/shutdown");
        } catch (Exception e){
            assertEquals(e.toString().contains("Connection Refused"),false);
        }
    }
}