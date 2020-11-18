package ca.mcgill.ecse.group14.unit;

import static ca.mcgill.ecse.group14.Resources.*;
import static ca.mcgill.ecse.group14.Utils.*;

import static io.restassured.RestAssured.*;

import org.junit.Test;

import org.json.simple.JSONObject;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assume.assumeTrue;


public class TodosTest extends BaseTest {
    /**
     * Test GET http://localhost:4567/todos
     */
    @Test
    public void testGetTodosStatusCode() {
        assertGETStatusCode("/todos", STATUS_CODE.OK);
    }

    /**
     * Test HEAD http://localhost:4567/todos
     */
    @Test
    public void testHeadTodos() {
        createTodo("todoTitleText");

        given()
                .head("/todos")
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

        buildJSONRequestWithJSONResponse()
                .body(fields.toJSONString())
                .post( "/todos")
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

        buildJSONRequest()
                .body(fields.toJSONString())
                .post( "/todos")
                .then().assertThat()
                .statusCode(STATUS_CODE.BAD_REQUEST);
    }

    /**
     * Test POST http://localhost:4567/todos using no title
     */
    @Test
    public void testPostTodosWithNoFields() {
        buildJSONRequest()
                .post("/todos")
                .then().assertThat()
                .statusCode(STATUS_CODE.BAD_REQUEST);
    }

    /**
     * Test POST http://localhost:4567/todos with a float title
     * Bug: Got STATUS_CODE 201, however title should be a String
     */
    @Test
    public void testPostTodosWithFloatTitle() {
        assumeTrue(!CI); // skip this test when run on the CI
        JSONObject fields = new JSONObject();
        fields.put("title", 4.0f);

        buildJSONRequest()
                .body(fields.toJSONString())
                .post("/todos")
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
                .post("/todos")
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
                .post("/todos")
                .then().assertThat()
                .statusCode(STATUS_CODE.BAD_REQUEST);
    }

    /**
     * Test GET http://localhost:4567/todos/:id using a valid id
     */
    @Test
    public void testGetTodosValidID() {
        int todoID = createTodo("todoTitleText");
        buildJSONRequestWithJSONResponse()
                .get("/todos/" + todoID)
                .then().assertThat()
                .statusCode(STATUS_CODE.OK)
                .body("todos[0].title", equalTo("todoTitleText"));
    }

    /**
     * Test GET http://localhost:4567/todos/:id using an invalid id ("A")
     */
    @Test
    public void testGetTodosInvalidID() {
        createTodo("todoTitleText");
        buildJSONRequest()
                .get( "/todos/" + "A")
                .then().assertThat()
                .statusCode(STATUS_CODE.NOT_FOUND);
    }

    /**
     * Test HEAD http://localhost:4567/todos/:id
     */
    @Test
    public void testHeadTodosID(){
        int todoID = createTodo("todoTitleText");
        buildJSONRequest()
                .head("/todos/" + todoID)
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
        int todoID = createTodo("todoTitleText");
        buildJSONRequestWithJSONResponse()
                .body(fields.toJSONString())
                .post("/todos/" + todoID)
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
        int todoID = createTodo("todoTitleText");
        buildJSONRequestWithJSONResponse()
                .body(fields.toJSONString())
                .put("/todos/" + todoID)
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
        int todoID = createTodo("todoTitleText");
        buildJSONRequest()
                .body(fields.toJSONString())
                .put("/todos/" + "-1")
                .then().assertThat()
                .statusCode(STATUS_CODE.NOT_FOUND);
    }

    /**
     * Test DELETE http://localhost:4567/todos/:id
     */
    @Test
    public void testDeleteTodosID() {
        int todoID = createTodo("todoTitleText");
        buildJSONRequest()
                .delete("/todos/" + todoID)
                .then().assertThat()
                .statusCode(STATUS_CODE.OK);
    }

    /**
     * Test DELETE http://localhost:4567/todos/:id on a deleted to-do (invalid operation)
     */
    @Test
    public void testDeleteDeletedTodosID() {
        int todoID = createTodo("todoTitleText");
        given()
                .delete("/todos/" + todoID);

        given()
                .delete("/todos/"+todoID)
                .then().assertThat()
                .statusCode(STATUS_CODE.NOT_FOUND);
    }

    /**
     * Test GET http://localhost:4567/todos/:id/categories using a valid id
     */
    @Test
    public void testGetTodosCategoriesWithValidID() {
        int todoID = createTodo("todoTitleText");
        int catID = createCategory("catTitleText");

        JSONObject fields = new JSONObject();
        fields.put("id", String.valueOf(catID));

        buildJSONRequest()
                .body(fields.toJSONString())
                .post( "/todos/" + todoID + "/categories")
                .then().assertThat()
                .statusCode(STATUS_CODE.CREATED);

        given()
                .get( "/todos/" + todoID + "/categories")
                .then().assertThat()
                .statusCode(STATUS_CODE.OK);
    }

    /**
     * Test GET http://localhost:4567/todos/:id/categories using an invalid id
     * Bug: Should not be able to find it with an invalid id but it's returning STATUS_CODE 200
     */
    @Test
    public void testGetTodosCategoriesWithInvalidID() {
        assumeTrue(!CI); // skip this test when run on the CI

        int todoID = createTodo("todoTitleText");
        int catID = createCategory("catTitleText");

        JSONObject fields = new JSONObject();
        fields.put("id", String.valueOf(catID));

        buildJSONRequest()
                .body(fields.toJSONString())
                .post("/todos/" + todoID + "/categories");

        given()
                .get("/todos/ABC/categories")
                .then().assertThat()
                .statusCode(STATUS_CODE.NOT_FOUND);
    }

    /**
     * Test HEAD http://localhost:4567/todos/:id/categories
     */
    @Test
    public void testHeadTodosCategories() {
        int todoID = createTodo("todoTitleText");
        int catID = createCategory("catTitleText");

        JSONObject fields = new JSONObject();
        fields.put("id", String.valueOf(catID));

        buildJSONRequest()
                .body(fields.toJSONString())
                .post("/todos/" + todoID + "/categories")
                .then().assertThat()
                .statusCode(STATUS_CODE.CREATED);

        given()
                .head("/todos/" + todoID + "/categories")
                .then().assertThat()
                .statusCode(STATUS_CODE.OK);
    }

    /**
     * Test POST http://localhost:4567/todos/:id/categories
     */
    @Test
    public void testPostTodosCategories() {
        int todoID = createTodo("todoTitleText");
        int catID = createCategory("catTitleText");

        JSONObject fields = new JSONObject();
        fields.put("id", String.valueOf(catID));

        buildJSONRequest()
                .body(fields.toJSONString())
                .post("/todos/" + todoID + "/categories")
                .then().assertThat()
                .statusCode(STATUS_CODE.CREATED);
    }

    /**
     * Test DELETE http://localhost:4567/todos/:id/categories
     */
    @Test
    public void testDeleteTodosCategories() {
        int todoID = createTodo("todoTitleText");
        int catID = createCategory("catTitleText");

        JSONObject fields = new JSONObject();
        fields.put("id", String.valueOf(catID));

        buildJSONRequest()
                .body(fields.toJSONString())
                .post("/todos/" + todoID + "/categories")
                .then().assertThat()
                .statusCode(STATUS_CODE.CREATED);

        given()
                .delete("/todos/" + todoID + "/categories/" + catID)
                .then().assertThat()
                .statusCode(STATUS_CODE.OK);
    }

    /**
     * Test GET http://localhost:4567/todos/:id/tasksof with valid id
     */
    @Test
    public void testGetTodosTasksofWithValidID() {
        int todoID = createTodo("todoTitleText");
        int projID = createProject("projTitleText");

        JSONObject fields = new JSONObject();
        fields.put("id", String.valueOf(projID));

        buildJSONRequest()
                .body(fields.toJSONString())
                .post("/todos/" + todoID + "/tasksof");

        given()
                .get("/todos/" + todoID + "/tasksof")
                .then().assertThat()
                .statusCode(STATUS_CODE.OK);
    }

    /**
     * Test GET http://localhost:4567/todos/:id/tasksof with invalid id
     * Bug: Should not be able to find it with an invalid id but it's returning STATUS_CODE 200
     */
    @Test
    public void testGetTodosTasksofWithInvalidID() {
        assumeTrue(!CI); // skip this test when run on the CI
        int todoID = createTodo("todoTitleText");
        int projID = createProject("projTitleText");

        JSONObject fields = new JSONObject();
        fields.put("id", String.valueOf(projID));

        buildJSONRequest()
                .body(fields.toJSONString())
                .post("/todos/" + todoID + "/tasksof");

        given()
                .get("/todos/ABC/tasksof")
                .then().assertThat()
                .statusCode(STATUS_CODE.NOT_FOUND);
    }

    /**
     * Test HEAD http://localhost:4567/todos/:id/tasksof
     */
    @Test
    public void testHeadTodosTasksofWithValidID() {
        int todoID = createTodo("todoTitleText");
        int projID = createCategory("projTitleText");

        JSONObject fields = new JSONObject();
        fields.put("id", String.valueOf(projID));

        buildJSONRequest()
                .body(fields.toJSONString())
                .post("/todos/" + todoID + "/tasksof");

        given()
                .head("/todos/" + todoID + "/tasksof")
                .then().assertThat()
                .statusCode(STATUS_CODE.OK);
    }

    /**
     * Test POST http://localhost:4567/todos/:id/tasksof
     */
    @Test
    public void testPostTodosTasksof() {
        int todoID = createTodo("todoTitleText");
        int projID = createProject("projTitleText");

        JSONObject fields = new JSONObject();
        fields.put("id", String.valueOf(projID));

        buildJSONRequest()
                .body(fields.toJSONString())
                .post("/todos/" + todoID + "/tasksof")
                .then().assertThat()
                .statusCode(STATUS_CODE.CREATED);
    }

    /**
     * Test DELETE http://localhost:4567/todos/:id/tasksof
     */
    @Test
    public void testDeleteTodosTasksof() {
        int todoID = createTodo("todoTitleText");
        int projID = createProject("projTitleText");

        JSONObject fields = new JSONObject();
        fields.put("id", String.valueOf(projID));

        buildJSONRequest()
                .body(fields.toJSONString())
                .post("/todos/" + todoID + "/categories");

        given()
                .delete("/todos/" + todoID + "/categories/" + projID)
                .then().assertThat()
                .statusCode(STATUS_CODE.OK);
    }
}
