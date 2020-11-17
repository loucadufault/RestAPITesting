package ca.mcgill.ecse.group14.acceptance;


import ca.mcgill.ecse.group14.Resources;
import ca.mcgill.ecse.group14.Server;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.restassured.RestAssured;

import static org.junit.Assert.assertEquals;

public class BaseStepDefinitions {
    private static int errorCode;
    private static int counter;

    @Before
    public static void setup() {
        RestAssured.baseURI = Resources.BASE_URL;
        Server.start();
        assertEquals(0, Server.check());
        Server.waitUntilReady();
    }

    @After
    public static void teardown() throws InterruptedException {
        Server.stop();
        Thread.sleep(500);
    }
}
