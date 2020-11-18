package ca.mcgill.ecse.group14.unit;

import ca.mcgill.ecse.group14.Resources;
import ca.mcgill.ecse.group14.Server;
import ca.mcgill.ecse.group14.Utils;
import io.restassured.RestAssured;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import java.net.ConnectException;

public class BaseTest {
    static final boolean CI = Boolean.valueOf(System.getenv("CI"));

    @BeforeClass
    public static void setup() throws ConnectException {
        RestAssured.baseURI = Resources.BASE_URL;
        if (Server.ping() == 0) {
            return;
        }
        Server.start();
        Server.waitUntilReady();
    }

    @AfterClass
    public static void teardown() throws InterruptedException {
        Utils.clearData();
    }
}
