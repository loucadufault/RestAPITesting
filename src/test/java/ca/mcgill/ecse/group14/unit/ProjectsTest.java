package ca.mcgill.ecse.group14.unit;

import ca.mcgill.ecse.group14.Utils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static ca.mcgill.ecse.group14.Resources.*;

import static ca.mcgill.ecse.group14.Utils.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.junit.Assume.assumeTrue;

public class ProjectsTest extends BaseTest {
    private static String DEFAULT_TITLE = "";
    private static boolean DEFAULT_COMPLETED = false;
    private static boolean DEFAULT_ACTIVE = false;
    private static String DEFAULT_DESCRIPTION = "";

    private static int NON_EXISTENT_ID = 99999;

    public ProjectsTest() {
        RestAssured.baseURI = BASE_URL + "/projects";
    }

    ////////////////////////////////////////////////////////////////////////////////
    // POST
    ////////////////////////////////////////////////////////////////////////////////

    @Test
    public void test_CreateProject_XML() {
        String title = "test title";
        String description = "test description";

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        stringBuilder.append("<project>");
            stringBuilder.append("<title>" + title + "</title>");
            stringBuilder.append("<description>" + description + "</description>");
        stringBuilder.append("</project>");

        given().header("Content-Type", "application/xml").header("Accept", "application/json").body(stringBuilder.toString())
                .when().post()
                .then().assertThat().statusCode(STATUS_CODE.CREATED).body(
                        "title", equalTo(title),
                "description", equalTo(description),
                "active", equalTo(String.valueOf(DEFAULT_ACTIVE)),
                "completed", equalTo(String.valueOf(DEFAULT_COMPLETED)));
    }

    @Test
    public void test_CreateProject_XMLButJSONHeader() {
        String title = "test title";
        String description = "test description";

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        stringBuilder.append("<project>");
        stringBuilder.append("<title>" + title + "</title>");
        stringBuilder.append("<description>" + description + "</description>");
        stringBuilder.append("</project>");

        given().header("Content-Type", "application/json").header("Accept", "application/json").body(stringBuilder.toString())
                .when().post()
                .then().assertThat().statusCode(STATUS_CODE.BAD_REQUEST);
    }

    @Test
    public void test_CreateProject_MalformedJSON() {
        String title = "test title";
        String description = "test description";
        JSONObject requestBody = new JSONObject();
        requestBody.put("title", title);
        requestBody.put("description", description);

        String malformedJSONString = requestBody.toJSONString().replaceFirst(":", "");
        malformedJSONString =
                malformedJSONString.substring(0, 10) +
                ": \" : garbled\"{{" +
                malformedJSONString.substring(10).replaceFirst("\"", "'");
        buildJSONRequest().body(malformedJSONString).when().post().then().assertThat().statusCode(STATUS_CODE.BAD_REQUEST);
    }

    @Test
    public void test_CreateProject_ValidTitle() {
        String title = "test";

        JSONObject requestBody = new JSONObject();
        requestBody.put("title", title);

        Response response = given().body(requestBody.toJSONString()).when().post();

        assertHasDefaultValues(response);
        assertHasTitle(response, title);
    }

    @Test
    public void test_CreateProject_ValidDescription() {
        String description = "test";

        JSONObject requestBody = new JSONObject();
        requestBody.put("description", description);

        buildJSONRequestWithJSONResponse().body(requestBody.toJSONString())
                .when().post()
                .then().assertThat().statusCode(STATUS_CODE.CREATED).body(
                "title", equalTo(DEFAULT_TITLE),
                "completed", equalTo(String.valueOf(DEFAULT_COMPLETED)),
                "active", equalTo(String.valueOf(DEFAULT_ACTIVE)),
                "description", equalTo(description));
    }

    @Test
    public void test_CreateProject_ValidAllValues() {
        String title = "test title";
        String description = "test description";
        boolean active = false;
        boolean completed = true;

        JSONObject requestBody = new JSONObject();
        requestBody.put("title", title);
        requestBody.put("description", description);
        requestBody.put("active", active);
        requestBody.put("completed", completed);

        buildJSONRequestWithJSONResponse().body(requestBody.toJSONString())
                .when().post()
                .then().assertThat().statusCode(STATUS_CODE.CREATED).body("title", equalTo(title),
                "completed", equalTo(String.valueOf(completed)),
                "active", equalTo(String.valueOf(active)),
                "description", equalTo(description));
    }

    @Test
    public void test_CreateProject_NoValues() {
        Response response = buildJSONRequest().when().post();
        assertHasDefaultValues(response);
        assertHasTitle(response, "");
    }

    @Test
    public void test_CreateProject_EmptyTitle() {
        String title = "";

        JSONObject requestBody = new JSONObject();
        requestBody.put("title", title);

        Response response = buildJSONRequest().body(requestBody.toJSONString()).when().post();
        response.then().assertThat().statusCode(STATUS_CODE.CREATED);
        assertHasDefaultValues(response);
        assertHasTitle(response, title);
    }

    @Test
    public void test_CreateProject_EmptyDescription() {
        String description = "";

        JSONObject requestBody = new JSONObject();
        requestBody.put("description", description);

        Response response = buildJSONRequest().body(requestBody.toJSONString()).when().post();
        response.then().assertThat().statusCode(STATUS_CODE.CREATED);
        assertHasDefaultValues(response);
        response.then().assertThat().body("title", equalTo(String.valueOf(description)));
    }

    /**
     * Bug.
     */
    @Test
    public void test_CreateProject_FloatTitle() {
        assumeTrue(!CI); // skip this test when run on the CI
        float title = 4.0f;

        JSONObject requestBody = new JSONObject();
        requestBody.put("title", title);

        Response response = buildJSONRequest().body(requestBody.toJSONString()).when().post();
        response.then().assertThat().statusCode(STATUS_CODE.BAD_REQUEST);
    }

    @Test
    public void test_CreateProject_StringCompleted() {
        String completed = "true";

        JSONObject requestBody = new JSONObject();
        requestBody.put("completed", completed);
        Response response = buildJSONRequest().body(requestBody.toJSONString()).when().post();
        response.then().assertThat().statusCode(STATUS_CODE.BAD_REQUEST);
        assertHasErrorMessage(response, "Failed Validation: completed should be BOOLEAN");
    }

    @Test
    public void test_CreateProject_StringActive() {
        String active = "true";

        JSONObject requestBody = new JSONObject();
        requestBody.put("active", active);
        Response response = buildJSONRequest().body(requestBody.toJSONString()).when().post();
        response.then().assertThat().statusCode(STATUS_CODE.BAD_REQUEST);
        assertHasErrorMessage(response, "Failed Validation: active should be BOOLEAN");
    }

    @Test
    public void test_CreateProject_StringActiveAndCompleted() {
        String completed = "true";
        String active = "true";

        JSONObject requestBody = new JSONObject();
        requestBody.put("active", active);
        requestBody.put("completed", completed);
        Response response = buildJSONRequest().body(requestBody.toJSONString()).when().post();
        response.then().assertThat().statusCode(STATUS_CODE.BAD_REQUEST);
        assertHasErrorMessage(response, "Failed Validation: active should be BOOLEAN, completed should be BOOLEAN");
    }

    @Test
    public void test_CreateProject_ValidId() {
        int id = 25;
        JSONObject requestBody = new JSONObject();
        requestBody.put("id", id);
        Response response = buildJSONRequest().body(requestBody.toJSONString()).when().post();
        response.then().assertThat().statusCode(STATUS_CODE.BAD_REQUEST);
        assertHasErrorMessage(response, "Invalid Creation: Failed Validation: Not allowed to create with id");
    }

    @Test
    public void test_AmendProject_ValidIdAsRequestParam() {
        String title = "old";
        int id = createProject(title);

        Response response = Utils.getProject(id);
        response.then().assertThat().statusCode(STATUS_CODE.OK).body(
                "projects[0].title", equalTo(title));

        String newTitle = "new";
        String description = "test description";

        JSONObject requestBody = new JSONObject();
        requestBody.put("title", newTitle);
        requestBody.put("description", description);

        buildJSONRequestWithJSONResponse().body(requestBody.toJSONString())
                .when().post("/" + id)
                .then().assertThat()
                .statusCode(STATUS_CODE.OK).body(
                        "title", equalTo(newTitle),
                "description", equalTo(description));
    }

    @Test
    public void test_AmendProject_NonexistentIdAsRequestParam() {
        String title = "old";
        int id = createProject(title);

        Response response = Utils.getProject(id);
        response.then().assertThat().statusCode(STATUS_CODE.OK).body(
                "projects[0].title", equalTo(title));

        String newTitle = "new";
        String description = "test description";

        JSONObject requestBody = new JSONObject();
        requestBody.put("title", newTitle);
        requestBody.put("description", description);

        buildJSONRequestWithJSONResponse().body(requestBody.toJSONString())
                .when().post("/" + NON_EXISTENT_ID)
                .then().assertThat()
                .statusCode(STATUS_CODE.NOT_FOUND);
    }

    @Test
    public void test_CreateProject_InvalidId() {
        int id = -25;
        JSONObject requestBody = new JSONObject();
        requestBody.put("id", id);
        Response response = buildJSONRequest().body(requestBody.toJSONString()).when().post();
        response.then().assertThat().statusCode(STATUS_CODE.BAD_REQUEST);
        assertHasErrorMessage(response, "Invalid Creation: Failed Validation: Not allowed to create with id");
    }

    @Test
    public void test_CreateProjectTaskRelationship() {
        int todoID = createTodo("todoTitleText");
        int projID = createProject("projTitleText");

        JSONObject fields = new JSONObject();
        fields.put("id", String.valueOf(todoID));

        given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .body(fields.toJSONString())
                .post(BASE_URL + "/projects/" + projID + "/tasks")
                .then().assertThat()
                .statusCode(STATUS_CODE.CREATED);
    }

    @Test
    public void test_CreateProjectCategoryRelationship() {
        int catID = createCategory("catTitleText");
        int projID = createProject("projTitleText");

        JSONObject fields = new JSONObject();
        fields.put("id", String.valueOf(catID));

        given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .body(fields.toJSONString())
                .post(BASE_URL + "/projects/" + projID + "/categories")
                .then().assertThat()
                .statusCode(STATUS_CODE.CREATED);
    }

    private void assertHasDefaultValues(Response response) {
        response.then().assertThat().body(
                "completed", equalTo(String.valueOf(DEFAULT_COMPLETED)),
                "active", equalTo(String.valueOf(DEFAULT_ACTIVE)),
                "description", equalTo(DEFAULT_DESCRIPTION));
    }

    private void assertHasTitle(Response response, String title) {
        response.then().assertThat().body("title", equalTo(String.valueOf(title)));
    }

    private void assertHasErrorMessage(Response response, String errorMessage) {
        response.then().assertThat().body("errorMessages[0]", equalTo(errorMessage));
    }

    ////////////////////////////////////////////////////////////////////////////////
    // GET
    ////////////////////////////////////////////////////////////////////////////////

    @Test
    public void test_GetAllProjects_StatusCodeOK() {
        buildJSONRequestWithJSONResponse().when().get().then().assertThat().statusCode(STATUS_CODE.OK).contentType(ContentType.JSON);
    }

    @Test
    public void test_GetAllProjects_NewProjects() {
        deleteAllProjects();

        String title1 = "test 1";
        String title2 = "test 2";
        createProject(title1);
        createProject(title2);

        Response response = buildJSONRequestWithJSONResponse().when().get();
        response.then()
                .assertThat().statusCode(STATUS_CODE.OK)
                .body("projects.size()", equalTo(2),
                        "projects.title", hasItems(title1, title2));
    }

    @Test
    public void test_GetProject_ValidId() {
        String title = "test";
        int id = createProject(title);

        Response response = Utils.getProject(id);
        response.then().assertThat().body("projects[0].title", equalTo(title));
    }

    @Test
    public void test_GetProject_ValidIdAsXMLPayload() {
        String title = "test";
        int id = createProject(title);

        String response = given().header("Content-Type", "application/json").header("Accept", "application/xml")
                .when().get("/" + id).asString();

        assert response.contains("<title>" + title + "</title>");
    }

    /**
     * Bug.
     */
    @Test
    public void test_GetProject_NegativeIntegerId() {
        assumeTrue(!CI); // skip this test when run on the CI
        createProject("test");
        int id = -1;

        Response response = Utils.getProject(id);
        response.then().assertThat().statusCode(STATUS_CODE.BAD_REQUEST);
    }

    /**
     * Bug.
     */
    @Test
    public void test_GetProject_StringId() {
        assumeTrue(!CI); // skip this test when run on the CI
        String id = "invalid id";

        Response response = buildJSONRequestWithJSONResponse().when().get("/" + id);
        response.then().assertThat().statusCode(STATUS_CODE.BAD_REQUEST);
    }

    @Test
    public void test_GetProject_ValidNonexistentId() {
        int id = NON_EXISTENT_ID;

        Response response = Utils.getProject(id);
        response.then().assertThat().statusCode(STATUS_CODE.NOT_FOUND);
    }

    @Test
    public void test_GetProjectTaskRelationship(){
        int todoID = createTodo("todoTitleText");
        int projID = createCategory("projTitleText");

        JSONObject fields = new JSONObject();
        fields.put("id", String.valueOf(todoID));

        given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .body(fields.toJSONString())
                .post(BASE_URL + "/projects/" + projID + "/tasks");

        given()
                .get(BASE_URL + "/projects/" + projID + "/tasks")
                .then().assertThat()
                .statusCode(STATUS_CODE.OK);
    }

    @Test
    public void test_GetProjectCategoryRelationship() {
        int projID = createProject("projTitleText");
        int catID = createCategory("catTitleText");

        JSONObject fields = new JSONObject();
        fields.put("id", String.valueOf(catID));

        given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .body(fields.toJSONString())
                .post(BASE_URL + "/projects/" + projID + "/categories");

        given()
                .get(BASE_URL + "/projects/" + projID + "/categories")
                .then().assertThat()
                .statusCode(STATUS_CODE.OK);
    }

    private static int getNumberOfProjects() {
        return buildJSONRequestWithJSONResponse().when().get().then().assertThat().extract().response().path("projects.size()");
    }

    @Test
    public void test_GetProject_CommandLineQuery() {
        deleteAllProjects();
        String title = "test";
        int id = createProject(title);

        String command = "curl " + BASE_URL + "/projects/" + id;
        try {
            Process process = Runtime.getRuntime().exec(command);

            final InputStream in = process.getInputStream();
            final BufferedReader out = new BufferedReader(new InputStreamReader(in));

            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(out.readLine());
            assertTrue(json.toJSONString().contains("\"title\":\"test\""));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    ////////////////////////////////////////////////////////////////////////////////
    // DELETE
    ////////////////////////////////////////////////////////////////////////////////

    @Test
    public void test_Delete_RootPath() {
        int id = createProject("test");

        Response response = buildJSONRequestWithJSONResponse().when().delete();
        response.then().assertThat().statusCode(STATUS_CODE.NOT_ALLOWED);
    }

    @Test
    public void test_DeleteProject_ExistingId() {
        int id = createProject("test");

        buildJSONRequestWithJSONResponse().when().delete("/" + id).then().assertThat().statusCode(STATUS_CODE.OK);
        Utils.getProject(id).then().assertThat().statusCode(STATUS_CODE.NOT_FOUND);
    }

    @Test
    public void test_DeleteProject_NonexistentId() {
        createProject("test");
        int id = NON_EXISTENT_ID;
        buildJSONRequestWithJSONResponse().when().delete("/" + id).then().assertThat().statusCode(STATUS_CODE.NOT_FOUND);
    }

    @Test
    public void test_DeleteProjectTaskRelationship(){
        int todoID = createTodo("todoTitleText");
        int projID = createProject("projTitleText");

        JSONObject fields = new JSONObject();
        fields.put("id", String.valueOf(todoID));

        given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .body(fields.toJSONString())
                .post(BASE_URL + "/projects/" + projID + "/tasks");

        given()
                .delete(BASE_URL + "/projects/" + projID + "/tasks/" + todoID)
                .then().assertThat()
                .statusCode(STATUS_CODE.OK);
    }

    @Test
    public void test_DeleteProjectCategoryRelationship(){
        int catID = createCategory("catTitleText");
        int projID = createProject("projTitleText");

        JSONObject fields = new JSONObject();
        fields.put("id", String.valueOf(catID));

        given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .body(fields.toJSONString())
                .post(BASE_URL + "/projects/" + projID + "/categories");

        given()
                .delete(BASE_URL + "/projects/" + projID + "/categories/" + catID)
                .then().assertThat()
                .statusCode(STATUS_CODE.OK);
    }

    private void deleteAllProjects() {
        while (getNumberOfProjects() != 0) {
            Utils.deleteProject(Integer.parseInt(buildJSONRequestWithJSONResponse().when().get().then().assertThat().extract().response().path("projects[0].id")));
        }
    }


}
