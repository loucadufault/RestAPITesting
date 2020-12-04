package ca.mcgill.ecse.group14;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;

import java.sql.SQLOutput;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import static ca.mcgill.ecse.group14.Resources.*;
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

    private static int create(String title, String endpoint) {
        JSONObject requestParams = new JSONObject();
        requestParams.put("title", title);

        RequestSpecification request = buildJSONRequestWithJSONResponse().body(requestParams.toJSONString());

        Response response = request.post(BASE_URL + "/" + endpoint);
        return Integer.parseInt(response.jsonPath().get("id"));
    }

    public static int createTodo(String title) {
        return create(title, "todos");
    }

    public static int createProject(String title) {
        return create(title, "projects");
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
        return create(title, "categories");
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

    public static int countAllObjects() {
        return countTodos() + countProjects() + countCategories();
    }

    private static boolean exists(int id, String endpoint) {
        return buildJSONRequestWithJSONResponse().when().get(BASE_URL + "/" + endpoint + "/" + String.valueOf(id)).getStatusCode() != STATUS_CODE.NOT_FOUND;
    }

    private static boolean exists(String title, String endpoint) {
        return getFirstId(title, endpoint) != -1;
    }

    private static void remove(String title, String endpoint) {
        delete(getFirstId(title, endpoint), endpoint).asString();
    }

    private static Response delete(int id, String endpoint) {
        return buildJSONRequestWithJSONResponse().when().delete(BASE_URL + "/" + endpoint + "/" + id);
    }

    public static void deleteProject(int id) {
        delete(id, "projects");
    }

    public static void removeProject(String title) { remove(title, "projects"); }

    public static int countProjects() {
        return count("projects");
    }

    public static Response getProject(int id) {
        return buildJSONRequestWithJSONResponse().when().get(BASE_URL + "/projects/" + id);
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

    public static int countCategories() { return count("categories"); }



    public static void clearData() {
        post(BASE_URL + "/"+ CLEAR_PATH);
        assertEquals(0, countProjects());
        assertEquals(0, countTodos());
        assertEquals(0, countCategories());
    }

    private static String randomString() {
        return UUID.randomUUID().toString();
    }

    private static boolean randomBoolean() {
        Random rd = new Random();
        return rd.nextBoolean();
    }

    private static JSONObject populateTodoRequestBody() {
        JSONObject requestBody = new JSONObject();
        requestBody.put("title", randomString());
        requestBody.put("description", randomString());
        requestBody.put("doneStatus", randomBoolean());
        return requestBody;
    }

    public static Response createPopulatedTodo() {
        RequestSpecification request = buildJSONRequestWithJSONResponse().body(populateTodoRequestBody().toJSONString());
        return request.post(BASE_URL + "/todos");
    }

    public static void createPopulatedTodos(int n) {
        for (int i=0; i<n; i++) {
            createPopulatedTodo();
        }
    }

    public static Response changePopulatedTodo() {
        int id = Integer.parseInt(createPopulatedTodo().jsonPath().get("id"));
        return buildJSONRequestWithJSONResponse()
                .body(populateTodoRequestBody().toJSONString())
                .put(BASE_URL + "/todos/"+ id);
    }

    public static Response deletePopulatedTodo() {
        int id = Integer.parseInt(createPopulatedTodo().jsonPath().get("id"));
        return buildJSONRequestWithJSONResponse().delete(BASE_URL + "/todos/" + id);
    }

    private static JSONObject populateCategoryRequestBody() {
        JSONObject requestBody = new JSONObject();
        requestBody.put("title", randomString());
        requestBody.put("description", randomString());
        return requestBody;
    }

    public static Response createPopulatedCategory() {
        RequestSpecification request = Utils.buildJSONRequestWithJSONResponse().body(populateCategoryRequestBody().toJSONString());
        return request.post(BASE_URL + "/categories");
    }
    public static void createPopulatedCategories(int n) {
        for (int i=0; i<n; i++) {
            createPopulatedCategory();
        }
    }

    public static Response changePopulatedCategory() {
        int id = Integer.parseInt(createPopulatedCategory().jsonPath().get("id"));
        return buildJSONRequestWithJSONResponse()
                .body(populateCategoryRequestBody().toJSONString())
                .put(BASE_URL + "/categories/"+ id);
    }

    public static Response deletePopulateCategory() {
        int id = Integer.parseInt(createPopulatedCategory().jsonPath().get("id"));
        return buildJSONRequestWithJSONResponse().delete(BASE_URL + "/categories/" + id);
    }

    private static JSONObject populateProjectRequestBody() {
        JSONObject requestBody = new JSONObject();
        requestBody.put("title", randomString());
        requestBody.put("description", randomString());
        requestBody.put("completed", randomBoolean());
        requestBody.put("active", randomBoolean());
        return requestBody;
    }

    public static Response createPopulatedProject() {
        RequestSpecification request = Utils.buildJSONRequestWithJSONResponse().body(populateProjectRequestBody().toJSONString());
        return request.post(BASE_URL + "/projects");
    }

    public static void createPopulatedProjects(int n) {
        for (int i=0; i<n; i++) {
            createPopulatedProject();
        }
    }

    public static Response changePopulateProject() {
        int id = Integer.parseInt(createPopulatedProject().jsonPath().get("id"));
        return buildJSONRequestWithJSONResponse()
                .body(populateProjectRequestBody().toJSONString())
                .put(BASE_URL + "/projects/"+ id);
    }

    public static Response deletePopulatedProject() {
        int id = Integer.parseInt(createPopulatedProject().jsonPath().get("id"));
        return buildJSONRequestWithJSONResponse().delete(BASE_URL + "/projects/" + id);
    }
}
