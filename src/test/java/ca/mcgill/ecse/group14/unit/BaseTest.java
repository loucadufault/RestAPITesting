package ca.mcgill.ecse.group14.unit;

import ca.mcgill.ecse.group14.Server;
import ca.mcgill.ecse.group14.Utils;
import io.restassured.RestAssured;
import org.junit.*;

import java.net.ConnectException;

import static ca.mcgill.ecse.group14.Resources.*;

public class BaseTest {
    @BeforeClass
    public static void setup() throws ConnectException {
        RestAssured.baseURI = BASE_URL;
        Server.boot();
    }

    @AfterClass
    public static void teardown() throws InterruptedException {
        Utils.clearData();
        Server.shutdown();
    }
}
