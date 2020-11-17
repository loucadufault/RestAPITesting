package ca.mcgill.ecse.group14.unit;

import static ca.mcgill.ecse.group14.Resources.*;
import static ca.mcgill.ecse.group14.Utils.*;

import static io.restassured.RestAssured.*;

import org.junit.Test;

import org.json.simple.JSONObject;

import static org.hamcrest.core.IsEqual.equalTo;


public class TodosTest extends BaseTest {
    /**
     * Test GET http://localhost:4567/todos
     */
    @Test
    public void testGetTodosStatusCode() {
        assertGETStatusCode(BASE_URL + "/todos", STATUS_CODE.OK);
    }

    /**
     * Test HEAD http://localhost:4567/todos
     */
    @Test
    public void testHeadTodos() {
        createTask("todoTitleText");

        given()
                .head(BASE_URL + "/todos")
                .then().assertThat()
                .statusCode(STATUS_CODE.OK);
    }

    /**
     * Test POST http://localhost:4567/todos using a valid title
     */
    @Test
    public void testPostTodosWithValidTitle() {
        JSONObject fields = new JSONObject();
        fields.put("title", "titleText");

        given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .body(fields.toJSONString())
                .post(BASE_URL + "/todos")
                .then().assertThat()
                .statusCode(STATUS_CODE.CREATED)
                .body("title", equalTo("titleText"));
    }

    /**
     * Test POST http://localhost:4567/todos using an empty title
     */
    @Test
    public void testPostTodosWithEmptyTitle() {
        JSONObject fields = new JSONObject();
        fields.put("title", "");

        given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .body(fields.toJSONString())
                .post(BASE_URL + "/todos")
                .then().assertThat()
                .statusCode(STATUS_CODE.BAD_REQUEST);
    }

    /**
     * Test POST http://localhost:4567/todos using no title
     */
    @Test
    public void testPostTodosWithNoFields() {
        given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .post(BASE_URL + "/todos")
                .then().assertThat()
                .statusCode(STATUS_CODE.BAD_REQUEST);
    }

    /**
     * Test POST http://localhost:4567/todos with a float title
     * Bug? Got STATUS_CODE 201, however title should be a String
     */
    @Test
    public void testPostTodosWithFloatTitle() {
        JSONObject fields = new JSONObject();
        fields.put("title", 4.0f);

        given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .body(fields.toJSONString())
                .post(BASE_URL + "/todos")
                .then().assertThat()
                .statusCode(STATUS_CODE.BAD_REQUEST);
    }

    /**
     * Test POST http://localhost:4567/todos using XML payload
     */
    @Test
    public void testPostTodosUsingXML() {
        StringBuilder payload = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        payload.append("<todo>").append("<title>" + "titleText" + "</title>").append("</todo>");

        given()
                .header("Content-Type", "application/xml")
                .header("Accept", "application/xml")
                .body(payload.toString()).when()
                .post(BASE_URL+"/todos")
                .then().assertThat()
                .statusCode(STATUS_CODE.CREATED);
    }

    /**
     * Test POST http://localhost:4567/todos using malformed XML payload
     */
    @Test
    public void testPostTodosUsingMalformedXML(){
        StringBuilder payload = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        payload.append("<text>").append("<title>>" + "titleText" + "<</title>").append("<</todo>>");

        given()
                .header("Content-Type", "application/xml")
                .header("Accept", "application/xml")
                .body(payload.toString()).when()
                .post(BASE_URL+"/todos")
                .then().assertThat()
                .statusCode(STATUS_CODE.BAD_REQUEST);
    }

    /**
     * Test GET http://localhost:4567/todos/:id using a valid id
     */
    @Test
    public void testGetTodosValidID() {
        int todoID = createTask("todoTitleText");
        given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .get(BASE_URL + "/todos/" + todoID)
                .then().assertThat()
                .statusCode(STATUS_CODE.OK)
                .body("todos[0].title", equalTo("todoTitleText"));
    }

    /**
     * Test GET http://localhost:4567/todos/:id using an invalid id ("A")
     */
    @Test
    public void testGetTodosInvalidID() {
        createTask("todoTitleText");
        given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .get(BASE_URL + "/todos/" + "A")
                .then().assertThat()
                .statusCode(STATUS_CODE.NOT_FOUND);
    }

    /**
     * Test HEAD http://localhost:4567/todos/:id
     */
    @Test
    public void testHeadTodosID(){
        int todoID = createTask("todoTitleText");
        given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .head(BASE_URL + "/todos/" + todoID)
                .then().assertThat()
                .statusCode(STATUS_CODE.OK);
    }

    /**
     * Test POST http://localhost:4567/todos/:id
     */
    @Test
    public void testPostTodosID() {
        JSONObject fields = new JSONObject();
        fields.put("title", "todosIDText");
        int todoID = createTask("todoTitleText");
        given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .body(fields.toJSONString())
                .post(BASE_URL + "/todos/" + todoID)
                .then().assertThat()
                .statusCode(STATUS_CODE.OK)
                .body("title", equalTo("todosIDText"));
    }

    /**
     * Test PUT http://localhost:4567/todos/:id with existing id
     */
    @Test
    public void testPutTodosExistingID() {
        JSONObject fields = new JSONObject();
        fields.put("title", "titleText");
        int todoID = createTask("todoTitleText");
        given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .body(fields.toJSONString())
                .put(BASE_URL + "/todos/" + todoID)
                .then().assertThat()
                .statusCode(STATUS_CODE.OK)
                .body("title", equalTo("titleText"));
    }

    /**
     * Test PUT http://localhost:4567/todos/:id with nonexistent id ("-1")
     */
    @Test
    public void testPutTodosNonexistentID() {
        JSONObject fields = new JSONObject();
        fields.put("title", "titleText");
        int todoID = createTask("todoTitleText");
        given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .body(fields.toJSONString())
                .put(BASE_URL + "/todos/" + "-1")
                .then().assertThat()
                .statusCode(STATUS_CODE.NOT_FOUND);
    }

    /**
     * Test DELETE http://localhost:4567/todos/:id
     */
    @Test
    public void testDeleteTodosID() {
        int todoID = createTask("todoTitleText");
        given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .delete(BASE_URL + "/todos/" + todoID)
                .then().assertThat()
                .statusCode(STATUS_CODE.OK);
    }

    /**
     * Test DELETE http://localhost:4567/todos/:id on a deleted to-do (invalid operation)
     */
    @Test
    public void testDeleteDeletedTodosID() {
        int todoID = createTask("todoTitleText");
        given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .delete(BASE_URL + "/todos/" + todoID);

        given()
                .delete(BASE_URL+"/todos/"+todoID)
                .then().assertThat()
                .statusCode(STATUS_CODE.NOT_FOUND);
    }

    /**
     * Test GET http://localhost:4567/todos/:id/categories using a valid id
     */
    @Test
    public void testGetTodosCategoriesWithValidID() {
        int todoID = createTask("todoTitleText");
        int catID = createCategory("catTitleText");

        JSONObject fields = new JSONObject();
        fields.put("id", String.valueOf(catID));

        given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .body(fields.toJSONString())
                .post(BASE_URL + "/todos/" + todoID + "/categories")
                .then().assertThat()
                .statusCode(STATUS_CODE.CREATED);

        given()
                .get(BASE_URL + "/todos/" + todoID + "/categories")
                .then().assertThat()
                .statusCode(STATUS_CODE.OK);
    }

    /**
     * Test GET http://localhost:4567/todos/:id/categories using an invalid id
     * Bug? Should not be able to find it with an invalid id but it's returning STATUS_CODE 200
     */
    @Test
    public void testGetTodosCategoriesWithInvalidID() {
        int todoID = createTask("todoTitleText");
        int catID = createCategory("catTitleText");

        JSONObject fields = new JSONObject();
        fields.put("id", String.valueOf(catID));

        given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .body(fields.toJSONString())
                .post(BASE_URL + "/todos/" + todoID + "/categories");

        given()
                .get(BASE_URL + "/todos/ABC/categories")
                .then().assertThat()
                .statusCode(STATUS_CODE.NOT_FOUND);
    }

    /**
     * Test HEAD http://localhost:4567/todos/:id/categories
     */
    @Test
    public void testHeadTodosCategories() {
        int todoID = createTask("todoTitleText");
        int catID = createCategory("catTitleText");

        JSONObject fields = new JSONObject();
        fields.put("id", String.valueOf(catID));

        given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .body(fields.toJSONString())
                .post(BASE_URL + "/todos/" + todoID + "/categories")
                .then().assertThat()
                .statusCode(STATUS_CODE.CREATED);

        given()
                .head(BASE_URL + "/todos/" + todoID + "/categories")
                .then().assertThat()
                .statusCode(STATUS_CODE.OK);
    }

    /**
     * Test POST http://localhost:4567/todos/:id/categories
     */
    @Test
    public void testPostTodosCategories() {
        int todoID = createTask("todoTitleText");
        int catID = createCategory("catTitleText");

        JSONObject fields = new JSONObject();
        fields.put("id", String.valueOf(catID));

        given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .body(fields.toJSONString())
                .post(BASE_URL + "/todos/" + todoID + "/categories")
                .then().assertThat()
                .statusCode(STATUS_CODE.CREATED);
    }

    /**
     * Test DELETE http://localhost:4567/todos/:id/categories
     */
    @Test
    public void testDeleteTodosCategories() {
        int todoID = createTask("todoTitleText");
        int catID = createCategory("catTitleText");

        JSONObject fields = new JSONObject();
        fields.put("id", String.valueOf(catID));

        given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .body(fields.toJSONString())
                .post(BASE_URL + "/todos/" + todoID + "/categories")
                .then().assertThat()
                .statusCode(STATUS_CODE.CREATED);

        given()
                .delete(BASE_URL + "/todos/" + todoID + "/categories/" + catID)
                .then().assertThat()
                .statusCode(STATUS_CODE.OK);
    }

    /**
     * Test GET http://localhost:4567/todos/:id/tasksof with valid id
     */
    @Test
    public void testGetTodosTasksofWithValidID() {
        int todoID = createTask("todoTitleText");
        int projID = createProject("projTitleText");

        JSONObject fields = new JSONObject();
        fields.put("id", String.valueOf(projID));

        given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .body(fields.toJSONString())
                .post(BASE_URL + "/todos/" + todoID + "/tasksof");

        given()
                .get(BASE_URL + "/todos/" + todoID + "/tasksof")
                .then().assertThat()
                .statusCode(STATUS_CODE.OK);
    }

    /**
     * Test GET http://localhost:4567/todos/:id/tasksof with invalid id
     * Bug? Should not be able to find it with an invalid id but it's returning STATUS_CODE 200
     */
    @Test
    public void testGetTodosTasksofWithInvalidID() {
        int todoID = createTask("todoTitleText");
        int projID = createProject("projTitleText");

        JSONObject fields = new JSONObject();
        fields.put("id", String.valueOf(projID));

        given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .body(fields.toJSONString())
                .post(BASE_URL + "/todos/" + todoID + "/tasksof");

        given()
                .get(BASE_URL + "/todos/ABC/tasksof")
                .then().assertThat()
                .statusCode(STATUS_CODE.NOT_FOUND);
    }

    /**
     * Test HEAD http://localhost:4567/todos/:id/tasksof
     */
    @Test
    public void testHeadTodosTasksofWithValidID() {
        int todoID = createTask("todoTitleText");
        int projID = createCategory("projTitleText");

        JSONObject fields = new JSONObject();
        fields.put("id", String.valueOf(projID));

        given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .body(fields.toJSONString())
                .post(BASE_URL + "/todos/" + todoID + "/tasksof");

        given()
                .head(BASE_URL + "/todos/" + todoID + "/tasksof")
                .then().assertThat()
                .statusCode(STATUS_CODE.OK);
    }

    /**
     * Test POST http://localhost:4567/todos/:id/tasksof
     */
    @Test
    public void testPostTodosTasksof() {
        int todoID = createTask("todoTitleText");
        int projID = createProject("projTitleText");

        JSONObject fields = new JSONObject();
        fields.put("id", String.valueOf(projID));

        given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .body(fields.toJSONString())
                .post(BASE_URL + "/todos/" + todoID + "/tasksof")
                .then().assertThat()
                .statusCode(STATUS_CODE.CREATED);
    }

    /**
     * Test DELETE http://localhost:4567/todos/:id/tasksof
     */
    @Test
    public void testDeleteTodosTasksof() {
        int todoID = createTask("todoTitleText");
        int projID = createProject("projTitleText");

        JSONObject fields = new JSONObject();
        fields.put("id", String.valueOf(projID));

        given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .body(fields.toJSONString())
                .post(BASE_URL + "/todos/" + todoID + "/categories");

        given()
                .delete(BASE_URL + "/todos/" + todoID + "/categories/" + projID)
                .then().assertThat()
                .statusCode(STATUS_CODE.OK);
    }
}
