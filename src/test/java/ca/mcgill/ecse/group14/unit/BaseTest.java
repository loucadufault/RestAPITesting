package ca.mcgill.ecse.group14.unit;

import ca.mcgill.ecse.group14.Server;
import org.junit.*;

import static io.restassured.RestAssured.*;

import static ca.mcgill.ecse.group14.Resources.*;
import static org.junit.Assert.assertEquals;

public class BaseTest {
    @BeforeClass
    public static void setup() {
        Server.start();
        assertEquals(0, Server.check());
        Server.waitUntilReady();
    }

    @AfterClass
    public static void teardown() throws InterruptedException {
        Server.stop();
        Thread.sleep(500);
    }
}
