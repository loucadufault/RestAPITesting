package ca.mcgill.ecse.group14.acceptance;


import ca.mcgill.ecse.group14.Resources;
import ca.mcgill.ecse.group14.Server;

import io.restassured.RestAssured;
import org.junit.AfterClass;
import org.junit.BeforeClass;

public class BaseStepDefinitions {
    static int errorCode;
    static int counter;

    @BeforeClass
    static void setup() {
        RestAssured.baseURI = Resources.BASE_URL;
        Server.start();
        Server.waitUntilReady();
    }

    @AfterClass
    static void teardown() throws InterruptedException {
        Server.stop();
        Thread.sleep(500);
    }
}
