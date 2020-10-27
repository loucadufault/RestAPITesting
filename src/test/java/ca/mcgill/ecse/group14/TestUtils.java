package ca.mcgill.ecse.group14;

import static ca.mcgill.ecse.group14.Resources.*;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;

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

    public static int createTodoHelper(String title) {
        JSONObject fields = new JSONObject();
        fields.put("title", title);

        RequestSpecification request = RestAssured.given();
        request.body(fields.toJSONString());

        Response response = request.post(BASE_URL+"/todos");
        return Integer.parseInt((String) response.jsonPath().get("id"));
    }

    public static int createProjectHelper(String title) {
        JSONObject fields = new JSONObject();
        fields.put("title", title);

        RequestSpecification request = RestAssured.given();
        request.body(fields.toJSONString());

        Response response = request.post(BASE_URL+"/projects");
        return Integer.parseInt((String) response.jsonPath().get("id"));
    }

    public static int createCategoryHelper(String title) {
        JSONObject fields = new JSONObject();
        fields.put("title", title);

        RequestSpecification request = RestAssured.given();
        request.body(fields.toJSONString());

        Response response = request.post(BASE_URL+"/categories");
        return Integer.parseInt((String) response.jsonPath().get("id"));
    }
}
