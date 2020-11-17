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

        RequestSpecification request = buildJSONRequestWithJSONResponse().body(fields.toJSONString());

        Response response = request.post(BASE_URL+"/todos");
        return Integer.parseInt(response.jsonPath().get("id"));
    }

    public static int createProject(String title) {
        JSONObject requestParams = new JSONObject();
        requestParams.put("title", title);

        RequestSpecification request = buildJSONRequestWithJSONResponse().body(requestParams.toJSONString());

        Response response = request.post(BASE_URL+"/projects");
        return Integer.parseInt(response.jsonPath().get("id"));
    }

    public static int createProject() {
        RequestSpecification request = Utils.buildJSONRequestWithJSONResponse();
        Response response = request.post(BASE_URL + "/projects");
        return Integer.parseInt(response.jsonPath().get("id"));
    }

    public static int createProject(String title, String description, String completed, String active) {
        JSONObject requestBody = new JSONObject();
        requestBody.put("title", title);
        requestBody.put("description", description);
        requestBody.put("completed", Boolean.valueOf(completed));
        requestBody.put("active", Boolean.valueOf(active));

        RequestSpecification request = Utils.buildJSONRequestWithJSONResponse().body(requestBody.toJSONString());

        Response response = request.post(BASE_URL + "/projects");
        return Integer.parseInt(response.jsonPath().get("id"));
    }

    public static int createCategory(String title) {
        JSONObject fields = new JSONObject();
        fields.put("title", title);

        RequestSpecification request = buildJSONRequestWithJSONResponse().body(fields.toJSONString());

        Response response = request.post(BASE_URL+"/categories");
        return Integer.parseInt(response.jsonPath().get("id"));
    }

    public static RequestSpecification buildJSONRequest() {
        return given().header("Content-Type", "application/json");
    }

    public static RequestSpecification buildJSONRequestWithJSONResponse() {
        return buildJSONRequest().header("Accept", "application/json");
    }

    public static int getFirstId(String title, String endpoint) {
        Response response = buildJSONRequestWithJSONResponse().when().get(BASE_URL + "/" + endpoint);
        List<Map<String, String>> things = response.jsonPath().getList(endpoint);
        for (Map<String, String> thing : things) {
            if (thing.get("title").equals(title)) {
                return Integer.parseInt(thing.get("id"));
            }
        }
        return -1;
    }

    private static int count(String endpoint) {
        Response response = buildJSONRequestWithJSONResponse().when().get(BASE_URL + "/" + endpoint);
        List<Map<String, String>> things = response.jsonPath().getList(endpoint);
        return things.size();
    }

    private static boolean exists(int id, String endpoint) {
        return buildJSONRequestWithJSONResponse().when().get(BASE_URL + "/" + endpoint + "/" + String.valueOf(id)).getStatusCode() != STATUS_CODE.NOT_FOUND;
    }

    private static boolean exists(String title, String endpoint) {
        return getFirstId(title, endpoint) != -1;
    }

    private static void remove(String title, String endpoint) {
        deleteProject(getFirstId(title, endpoint));
    }

    private static Response delete(int id, String endpoint) {
        return buildJSONRequestWithJSONResponse().when().delete(BASE_URL + "/" + endpoint + "/" + id);
    }

    public static void deleteProject(int id) {
        System.out.println(id); delete(id, "projects");
    }

    public static void removeProject(String title) { remove(title, "projects"); }

    public static int countProjects() {
        return count("projects");
    }

    public static Response getProject(int id) {
        return buildJSONRequestWithJSONResponse().when().get("/" + id);
    }

    public static boolean existsProject(int id) {
        return exists(id, "projects");
    }

    public static boolean existsProject(String title) {
        return exists(title, "projects");
    }

    public static void deleteTodo(int id) {
        delete(id, "todos");
    }

    public static void removeTodo(String title) {
        remove(title, "todos");
    }

    public static boolean existsTodo(String title) {
        return exists(title, "todos");
    }

    public static int countTodos() { return count("todos"); }

    public static void deleteCategory(int id) {
        delete(id, "categories");
    }

    public static void removeCategory(String title) {
        remove(title, "categories");
    }

    public static void existsCategory(String title) {
        exists(title, "categories");
    }
}
