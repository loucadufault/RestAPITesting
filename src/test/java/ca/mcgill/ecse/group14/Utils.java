package ca.mcgill.ecse.group14;

import static ca.mcgill.ecse.group14.Resources.*;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class Utils {
    public static void assertResponseStatusCode(Response response, int statusCode) {
        assertEquals(statusCode, response.getStatusCode());
    }

    public static void assertGETStatusCode(String url, int statusCode) {
        assertResponseStatusCode(get(url), statusCode);
    }

    public static void assertHEADStatusCode(String url, int statusCode) {
        assertResponseStatusCode(head(url), statusCode);
    }

    public static int createTodo(String title) {
        JSONObject fields = new JSONObject();
        fields.put("title", title);

        RequestSpecification request = RestAssured.given();
        request.body(fields.toJSONString());

        Response response = request.post(BASE_URL+"/todos");
        return Integer.parseInt((String) response.jsonPath().get("id"));
    }

    public static int createProject(String title) {
        JSONObject requestParams = new JSONObject();
        requestParams.put("title", title);

        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.header("Accept", "application/json");
        request.body(requestParams.toJSONString());

        Response response = request.post(BASE_URL+"/projects");
        return Integer.parseInt((String) response.jsonPath().get("id"));
    }

    public static int createCategory(String title) {
        JSONObject fields = new JSONObject();
        fields.put("title", title);

        RequestSpecification request = RestAssured.given();
        request.body(fields.toJSONString());

        Response response = request.post(BASE_URL+"/categories");
        return Integer.parseInt((String) response.jsonPath().get("id"));
    }

    public static RequestSpecification buildJSONRequest() {
        return given().header("Content-Type", "application/json");
    }

    public static RequestSpecification buildJSONRequestWithJSONResponse() {
        return buildJSONRequest().header("Accept", "application/json");
    }

    public static int getFirstId(String title, String endpoint) {
        Response response = buildJSONRequestWithJSONResponse().when().get(BASE_URL + "/" + endpoint);
        JsonPath json = response.jsonPath();
        List<Map<String, String>> things = response.jsonPath().getList(endpoint);
        for (Map<String, String> thing : things) {
            if (thing.get("title").equals(title)) {
                return Integer.parseInt(thing.get("id"));
            }
        }
        return -1;
    }

    private static boolean exists(String title, String endpoint) {
        return getFirstId(title, endpoint) != -1;
    }

    private static void remove(String title, String endpoint) {
        deleteProject(getFirstId(title, endpoint));
    }

    public static Response delete(int id, String endpoint) {
        return buildJSONRequestWithJSONResponse().when().delete(BASE_URL + endpoint + "/" + id);
    }

    public static void deleteProject(int id) {
        delete(id, "projects");
    }

    public static void removeProject(String title) {
        remove(title, "projects");
    }

    public static boolean existsProject(String title) {
        return exists(title, "projects");
    }

    public static void removeTodo(String title) {
        remove(title, "todos");
    }

    public static boolean existsTodo(String title) {return true;}

}
