package ca.mcgill.ecse.group14.acceptance;


import ca.mcgill.ecse.group14.Resources;
import ca.mcgill.ecse.group14.Server;

import io.restassured.RestAssured;

public class BaseStepDefinitions {
    private static int errorCode;
    private static int counter;

    static void setup() {
        RestAssured.baseURI = Resources.BASE_URL;
        Server.start();
        Server.waitUntilReady();
    }

    static void teardown() throws InterruptedException {
        Server.stop();
        Thread.sleep(500);
    }
}
