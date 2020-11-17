package ca.mcgill.ecse.group14.unit;

import ca.mcgill.ecse.group14.Resources;
import ca.mcgill.ecse.group14.Server;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.ConnectException;

import static ca.mcgill.ecse.group14.Resources.BASE_URL;
import static io.restassured.RestAssured.get;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class ServerTest {
    public ServerTest() {
        RestAssured.baseURI = BASE_URL;
    }

    @Before
    public void setupEach() {
        Server.boot();
    }

    @After
    public void teardownEach() {
        Server.shutdown();
    }

    @Test
    public void test_CanRestart() {
        get().then().assertThat().statusCode(Resources.STATUS_CODE.OK);
        Server.shutdown();
        try{
            get();
        } catch (Exception e){
            assertEquals(e.toString().contains("Connection Refused"),false);
        }
        Server.start();
        assertEquals(0, Server.waitUntilReady());
        get().then().assertThat().statusCode(Resources.STATUS_CODE.OK);
    }
    @Test
    public void test_CanPing() {
        assertEquals(0, Server.ping());
    }
    @Test
    public void test_CanStop() {
        Server.stop();
        try{
            get();
        } catch (Exception e){
            assertEquals(e.toString().contains("Connection Refused"),false);
        }
    }
}