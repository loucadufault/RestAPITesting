package ca.mcgill.ecse.group14.acceptance;

import ca.mcgill.ecse.group14.Resources;
import ca.mcgill.ecse.group14.Server;

import ca.mcgill.ecse.group14.Utils;
import io.restassured.RestAssured;

import java.net.ConnectException;

public class BaseStepDefinitions {
    static int errorCode;
    static int counter;
    static int projectCount;
    static int taskCount;

    static void setup() {
        RestAssured.baseURI = Resources.BASE_URL;
        Server.start();
        Server.waitUntilReady();
    }

    static void teardown() throws InterruptedException {
        Utils.clearData();
        Server.shutdown();
    }
}
