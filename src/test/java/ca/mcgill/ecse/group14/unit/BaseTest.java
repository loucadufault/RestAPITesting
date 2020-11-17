package ca.mcgill.ecse.group14.unit;

import ca.mcgill.ecse.group14.Server;
import ca.mcgill.ecse.group14.Utils;
import io.restassured.RestAssured;
import org.junit.*;

import static io.restassured.RestAssured.*;

import static ca.mcgill.ecse.group14.Resources.*;
import static io.restassured.config.ConnectionConfig.connectionConfig;
import static io.restassured.config.HttpClientConfig.httpClientConfig;
import static org.junit.Assert.assertEquals;

public class BaseTest {
    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = BASE_URL;
        RestAssured.useRelaxedHTTPSValidation("TLSv1");
        Server.boot();
    }

    @AfterClass
    public static void teardown() throws InterruptedException {
        Utils.clearData();
        Server.stop();
    }
}
