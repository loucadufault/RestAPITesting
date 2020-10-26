package ca.mcgill.ecse.group14;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class TestUtils {
    public static void assertResponseStatusCode(Response response, int statusCode) {
        assertEquals(statusCode, response.getStatusCode());
    }

    public static void assertGETStatusCode(String url, int statusCode) {
        assertResponseStatusCode(get(url), statusCode);
    }

    public static void assertHEADStatusCode(String url, int statusCode) {
        assertResponseStatusCode(head(url), statusCode);
    }
}
