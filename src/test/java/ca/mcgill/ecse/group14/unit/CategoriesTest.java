package ca.mcgill.ecse.group14.unit;
import ca.mcgill.ecse.group14.Utils;
import io.restassured.response.Response;
import org.junit.*;
import org.json.simple.JSONObject;

import static io.restassured.RestAssured.*;
import static org.hamcrest.core.IsEqual.*;
import static ca.mcgill.ecse.group14.Resources.*;
import static ca.mcgill.ecse.group14.Utils.*;
import static org.junit.Assume.assumeTrue;

public class CategoriesTest extends BaseTest {
    /*
    Test GET /categories
     */
    @Test
    public void testGetCategories() {
        Utils.assertGETStatusCode( "/categories", STATUS_CODE.OK);
    }

    /*
    Test GET /categories/:id with a valid ID
     */
    @Test
    public void testGetCategoryWithValidID(){
        int catID = createCategory("catEx");
        Utils.buildJSONRequestWithJSONResponse()
                .get("/categories/"+String.valueOf(catID))
                .then().assertThat()
                .statusCode(STATUS_CODE.OK)
                .body("categories[0].title",equalTo("catEx"));
    }

    /*
    Test GET /categories/:id with an invalid ID
     */
    @Test
    public void testGetCategoryWithInvalidID(){
        int catID = createCategory("catEx");
        Utils.buildJSONRequest()
                .get("/categories/ABC")
                .then().assertThat()
                .statusCode(STATUS_CODE.NOT_FOUND);
    }

    /*
    Test GET /categories/:id/projects with valid ID
     */
    @Test
    public void testGetProjectsOfCategoryWithValidID(){
        int catID = createCategory("catEx");
        int projID = createProject("projEx");
        JSONObject body = new JSONObject();
        body.put("id",String.valueOf(projID));

        Utils.buildJSONRequest()
                .body(body.toJSONString())
                .post("/categories/"+String.valueOf(catID)+"/projects");
        Utils.buildJSONRequest()
                .get("/categories/"+String.valueOf(catID)+"/projects")
                .then().assertThat()
                .statusCode(STATUS_CODE.OK);
    }
    /**
     * Bug.
     * Test GET /categories/:id/projects with invalid ID (bug?)
     */
    @Test
    public void testGetProjectsOfCategoryWithInvalidID() {
        assumeTrue(!CI); // skip this test when run on the CI
        int catID = createCategory("catEx");
        int projID = createProject("projEx");
        JSONObject body = new JSONObject();
        body.put("id",String.valueOf(projID));

        Utils.buildJSONRequest()
                .body(body.toJSONString())
                .post("/categories/" + String.valueOf(catID) + "/projects");
        Utils.buildJSONRequest()
                .get("/categories/100/projects")
                .then().assertThat()
                .statusCode(STATUS_CODE.NOT_FOUND);
    }
    /*
    Test GET /categories/:id/todos with valid ID
     */
    @Test
    public void testGetTodosOfCategoryWithValidID(){
        int catID = createCategory("catEx");
        int todoID = createTodo("todoEx");
        JSONObject body = new JSONObject();
        body.put("id",String.valueOf(todoID));

        Utils.buildJSONRequest()
                .body(body.toJSONString())
                .post("/categories/"+String.valueOf(catID)+"/projects");
        Utils.buildJSONRequest()
                .get("/categories/"+String.valueOf(catID)+"/projects")
                .then().assertThat()
                .statusCode(STATUS_CODE.OK);
    }

    /**
     * Bug.
     * Test GET /categories/:id/todos with invalid ID (bug?)
     */
    @Test
    public void testGetTodosOfCategoryWithInvalidID() {
        assumeTrue(!CI); // skip this test when run on the CI
        int catID = createCategory("catEx");
        int todoID = createTodo("todoEx");
        JSONObject body = new JSONObject();
        body.put("id",String.valueOf(todoID));

        Utils.buildJSONRequest()
                .body(body.toJSONString())
                .post("/categories/"+String.valueOf(catID)+"/projects");
        Utils.buildJSONRequest()
                .get("/categories/ABC/projects")
                .then().assertThat()
                .statusCode(STATUS_CODE.NOT_FOUND);
    }

    /*
    Test POST /categories with no body
     */
    @Test
    public void testPostCategoryNoBody(){
        Utils.buildJSONRequest()
                .post("/categories")
                .then().assertThat().statusCode(STATUS_CODE.BAD_REQUEST);
    }

    /*
    Test POST /categories with a JSON body with both Title and Description
     */
    @Test
    public void testPostCategoryWithValidTitleAndDescription(){
        String title = "catTest";
        String description = "catDes";
        JSONObject body = new JSONObject();
        body.put("title",title);
        body.put("description",description);
        Utils.buildJSONRequestWithJSONResponse()
                .body(body.toJSONString())
                .post("/categories")
                .then()
                .assertThat().statusCode(STATUS_CODE.CREATED)
                .body("title",equalTo(title))
                .body("description",equalTo(description));
    }

    /*
    Test POST /categories with a JSON body with Title only
     */
    @Test
    public void testPostCategoryWithOnlyValidTitle(){
        String title = "catTest";
        JSONObject body = new JSONObject();
        body.put("title",title);
        Utils.buildJSONRequestWithJSONResponse()
                .body(body.toJSONString())
                .post("/categories")
                .then()
                .assertThat().statusCode(STATUS_CODE.CREATED)
                .body("title",equalTo(title))
                .body("description",equalTo(""));
    }

    /*
    Test POST /categories with a JSON body with Description only
     */
    @Test
    public void testPostCategoryWithOnlyValidDescription(){
        String description = "catTest";
        JSONObject body = new JSONObject();
        body.put("description",description);
        Utils.buildJSONRequest()
                .body(body.toJSONString())
                .post("/categories")
                .then()
                .assertThat().statusCode(STATUS_CODE.BAD_REQUEST);
    }

    /*
    Test POST /categories/:id with a valid ID and valid JSON body
     */
    @Test
    public void testPostCategoryWithValidID(){
        String title = "catTest";
        String description = "catDes";
        JSONObject body = new JSONObject();
        body.put("title",title);
        body.put("description",description);
        int categoryID = createCategory("categoryTest");
        Utils.buildJSONRequestWithJSONResponse()
                .body(body.toJSONString())
                .post("/categories/"+String.valueOf(categoryID))
                .then().assertThat()
                .statusCode(STATUS_CODE.OK)
                .body("title",equalTo("catTest"))
                .body("description",equalTo("catDes"));

    }

    /*
    Test POST /categories/:id with invalid ID and valid JSON body
     */
    @Test
    public void testPostCategoryWithInvalidID(){
        String title = "catTest";
        String description = "catDes";
        JSONObject body = new JSONObject();
        body.put("title",title);
        body.put("description",description);

        int categoryID = createCategory("categoryTest");
        Utils.buildJSONRequest()
                .body(body.toJSONString())
                .get("/category/"+"5")
                .then().assertThat()
                .statusCode(STATUS_CODE.NOT_FOUND);
    }

    /*
    Test POST /categories/:id/projects with valid ID and valid project instance specified in JSON body
     */
    @Test
    public void testPostCategoryRelatedToProjectWithValidID(){
        int projID = createProject("Project1");
        int catID = createCategory("Category1");
        JSONObject body = new JSONObject();
        body.put("id",String.valueOf(projID));

        Utils.buildJSONRequest()
                .body(body.toJSONString())
                .post("/categories/"+String.valueOf(catID)+"/projects")
                .then().assertThat()
                .statusCode(STATUS_CODE.CREATED);
    }

    /*
    Test POST /categories/:id/projects with invalid ID and valid project instance specified in JSON body
     */
    @Test
    public void testPostCategoryRelatedToProjectWithInvalidID(){
        int projID = createProject("Project1");
        createCategory("Category1");
        JSONObject body = new JSONObject();
        body.put("id",String.valueOf(projID));

        Utils.buildJSONRequest()
                .body(body.toJSONString())
                .post("/categories/100/projects")
                .then().assertThat()
                .statusCode(STATUS_CODE.NOT_FOUND);
    }

    /*
    Test POST /categories/:id/to-do with valid ID and valid to-do instance specified in JSON body
     */
    @Test
    public void testPostCategoryRelatedToTodoWithValidID(){
        int todoID = createTodo("Project1");
        int catID = createCategory("Category1");
        JSONObject body = new JSONObject();
        body.put("id",String.valueOf(todoID));

        Utils.buildJSONRequest()
                .body(body.toJSONString())
                .post("/categories/"+catID+"/todos")
                .then().assertThat()
                .statusCode(STATUS_CODE.CREATED);
    }

    /*
    Test POST /categories/:id/to-do with invalid ID and valid to-do instance specified in JSON body
     */
    @Test
    public void testPostCategoryRelatedToTodoWithInvalidID(){
        int todoID = createProject("Project1");
        createCategory("Category1");
        JSONObject body = new JSONObject();
        body.put("id",String.valueOf(todoID));

        Utils.buildJSONRequest()
                .body(body.toJSONString())
                .post("/categories/100/projects")
                .then().assertThat()
                .statusCode(STATUS_CODE.NOT_FOUND);
    }

    /*
    Test HEAD /categories
     */
    @Test
    public void testHeadCategories(){
        assertHEADStatusCode("/categories", STATUS_CODE.OK);
    }

    /*
    Test HEAD /categories/:id with a valid ID
     */
    @Test
    public void testHeadCategoriesWithValidID(){
        int catID = createCategory("catEx");
        Utils.buildJSONRequest()
                .head("/categories/"+String.valueOf(catID))
                .then().assertThat()
                .statusCode(STATUS_CODE.OK);
    }
    /*
    Test HEAD /categories/:id with a invalid ID
     */
    @Test
    public void testHeadCategoriesWithInvalidID(){
        createCategory("catEx");
        Utils.buildJSONRequest()
                .head("/categories/ABC")
                .then().assertThat()
                .statusCode(STATUS_CODE.NOT_FOUND);
    }
    /*
    Test HEAD /categories/:id/projects with valid ID
     */
    @Test
    public void testHeadProjectsOfCategoryWithValidID(){
        int catID = createCategory("catEx");
        int projID = createProject("projEx");
        JSONObject body = new JSONObject();
        body.put("id",String.valueOf(projID));

        Utils.buildJSONRequest()
                .body(body.toJSONString())
                .post("/categories/"+String.valueOf(catID)+"/projects");
        Utils.buildJSONRequest()
                .header("Content-Type", "application/json")
                .header("Accept","application/json")
                .head("/categories/"+String.valueOf(catID)+"/projects")
                .then().assertThat()
                .statusCode(STATUS_CODE.OK);
    }
    /**
     * Bug.
     * Test HEAD /categories/:id/projects with invalid ID (bug?)
     */
    @Test
    public void testHeadProjectsOfCategoryWithInvalidID() {
        assumeTrue(!CI); // skip this test when run on the CI
        int catID = createCategory("catEx");
        int projID = createProject("projEx");
        JSONObject body = new JSONObject();
        body.put("id",String.valueOf(projID));

        Utils.buildJSONRequest()
                .body(body.toJSONString())
                .post("/categories/"+String.valueOf(catID)+"/projects");
        Utils.buildJSONRequest()
                .head("/categories/ABC/projects")
                .then().assertThat()
                .statusCode(STATUS_CODE.NOT_FOUND);
    }

    /*
    Test HEAD /categories/:id/todos with valid ID
     */
    @Test
    public void testHeadTodosOfCategoryWithValidID(){
        int catID = createCategory("catEx");
        int todoID = createTodo("todoEx");
        JSONObject body = new JSONObject();
        body.put("id",String.valueOf(todoID));

        Utils.buildJSONRequest()
                .body(body.toJSONString())
                .post("/categories/"+String.valueOf(catID)+"/projects");
        Utils.buildJSONRequest()
                .head("/categories/"+String.valueOf(catID)+"/projects")
                .then().assertThat()
                .statusCode(STATUS_CODE.OK);
    }

    /**
     * Bug.
     * Test HEAD /categories/:id/todos with invalid ID (bug?)
     */
    @Test
    public void testHeadTodosOfCategoryWithInvalidID() {
        assumeTrue(!CI); // skip this test when run on the CI
        int catID = createCategory("catEx");
        int todoID = createTodo("todoEx");
        JSONObject body = new JSONObject();
        body.put("id",String.valueOf(todoID));

        Utils.buildJSONRequest()
                .body(body.toJSONString())
                .post("/categories/"+String.valueOf(catID)+"/todos");
        Utils.buildJSONRequest()
                .head("/categories/ABC/todos")
                .then().assertThat()
                .statusCode(STATUS_CODE.NOT_FOUND);
    }

    /**
     * Test PUT /categories/:id with valid ID and body with both valid title and description
     */
    @Test
    public void testPutCategoryWithValidIDAndValidTitleDescription(){
        String title = "catEx";
        String description = "catDes";
        JSONObject body = new JSONObject();
        body.put("title",title);
        body.put("description",description);

        int catID = createCategory("initial");
        Utils.buildJSONRequestWithJSONResponse()
                .body(body.toJSONString())
                .put("/categories/"+String.valueOf(catID))
                .then().assertThat()
                .statusCode(STATUS_CODE.OK)
                .body("title",equalTo(title))
                .body("description", equalTo(description));
    }

    /*
    Test PUT /categories/:id with invalid ID and body with both valid title and description
     */
    @Test
    public void testPutCategoryWithInvalidIDAndValidTitleDescription(){
        String title = "catEx";
        String description = "catDes";
        JSONObject body = new JSONObject();
        body.put("title",title);
        body.put("description",description);

        int catID = createCategory("initial");
        Utils.buildJSONRequest()
                .body(body.toJSONString())
                .put("/categories/ABC")
                .then().assertThat()
                .statusCode(STATUS_CODE.NOT_FOUND);
    }

    /*
    Test PUT /categories/:id with valid ID and body with valid title
     */
    @Test
    public void testPutCategoryWithValidIDAndValidTitle(){
        String title = "catEx";
        JSONObject body = new JSONObject();
        body.put("title",title);

        int catID = createCategory("initial");
        Utils.buildJSONRequestWithJSONResponse()
                .body(body.toJSONString())
                .put("/categories/"+String.valueOf(catID))
                .then().assertThat()
                .statusCode(STATUS_CODE.OK)
                .body("title",equalTo(title));
    }

    /*
    Test PUT /categories/:id with valid ID and body with valid description
     */
    @Test
    public void testPutCategoryWithValidIDAndValidDescription(){
        String description = "catDes";
        JSONObject body = new JSONObject();
        body.put("description",description);

        int catID = createCategory("initial");
        Utils.buildJSONRequest()
                .body(body.toJSONString())
                .put("/categories/"+String.valueOf(catID))
                .then().assertThat()
                .statusCode(STATUS_CODE.BAD_REQUEST);
    }

    /*
    Test PUT /categories/:id with valid ID and no body
     */
    @Test
    public void testPutCategoryWithValidIDAndTitleDescription(){
        int catID = createCategory("initial");
        Utils.buildJSONRequest()
                .put("/categories/"+String.valueOf(catID))
                .then().assertThat()
                .statusCode(STATUS_CODE.BAD_REQUEST);
    }

    /*
    Test DELETE /categories/:id with valid ID
     */
    @Test
    public void testDeleteCategoryWithValidID(){
        int catID = createCategory("initial");
        Utils.buildJSONRequest()
                .delete("/categories/"+String.valueOf(catID))
                .then().assertThat()
                .statusCode(STATUS_CODE.OK);
    }

    /*
    Test DELETE /categories/:id with invalid ID
     */
    @Test
    public void testDeleteCategoryWithInvalidID(){
        createCategory("initial");
        Utils.buildJSONRequest()
                .delete("/categories/ABC")
                .then().assertThat()
                .statusCode(STATUS_CODE.NOT_FOUND);
    }

    /*
    Test DELETE /categories/:id/projects/:id with valid ID for both category and project
     */
    @Test
    public void testDeleteCategoryProjectRelationshipValidIDs(){
        int catID = createCategory("catEx");
        int projID = createProject("projEx");
        JSONObject body = new JSONObject();
        body.put("id",String.valueOf(projID));

        Utils.buildJSONRequest()
                .body(body.toJSONString())
                .post("/categories/"+String.valueOf(catID)+"/projects");
        Utils.buildJSONRequest()
                .delete("/categories/"+String.valueOf(catID)+"/projects/"+String.valueOf(projID))
                .then().assertThat()
                .statusCode(STATUS_CODE.OK);
    }
    /*
    Test DELETE /categories/:id/projects/:id with valid Category ID and invalid Project ID
     */
    @Test
    public void testDeleteCategoryProjectRelationshipValidCatIDandInvalidProjID(){
        int catID = createCategory("catEx");
        int projID = createProject("projEx");
        JSONObject body = new JSONObject();
        body.put("id",String.valueOf(projID));

        Utils.buildJSONRequest()
                .body(body.toJSONString())
                .post("/categories/"+String.valueOf(catID)+"/projects");
        Utils.buildJSONRequest()
                .delete("/categories/"+String.valueOf(catID)+"/projects/ABC")
                .then().assertThat()
                .statusCode(STATUS_CODE.NOT_FOUND);
    }

    /**
     * Bug.
     * Test DELETE /categories/:id/projects/:id with invalid ID for category and valid ID for project (bug?)
     */
    @Test
    public void testDeleteCategoryProjectRelationshipInvalidCatIDAndValidProjID(){
        assumeTrue(!CI); // skip this test when run on the CI
        int catID = createCategory("catEx");
        int projID = createProject("projEx");
        JSONObject body = new JSONObject();
        body.put("id",String.valueOf(projID));

        Utils.buildJSONRequest()
                .body(body.toJSONString())
                .post("/categories/"+String.valueOf(catID)+"/projects");
        Utils.buildJSONRequest()
                .delete("/categories/ABC/projects/"+String.valueOf(projID))
                .then().assertThat()
                .statusCode(STATUS_CODE.NOT_FOUND);
    }

    /*
    Test DELETE /categories/:id/projects/:id with invalid ID for both category and project
     */
    @Test
    public void testDeleteCategoryProjectRelationshipInValidIDs(){
        int catID = createCategory("catEx");
        int projID = createProject("projEx");
        JSONObject body = new JSONObject();
        body.put("id",String.valueOf(projID));

        Utils.buildJSONRequest()
                .body(body.toJSONString())
                .post("/categories/"+String.valueOf(catID)+"/projects");
        Utils.buildJSONRequest()
                .delete("/categories/ABC/projects/ABC")
                .then().assertThat()
                .statusCode(STATUS_CODE.NOT_FOUND);
    }

    /*
    Test DELETE /categories/:id/todos/:id with valid ID for both category and todo
     */
    @Test
    public void testDeleteCategoryTodoRelationshipValidIDs(){
        int catID = createCategory("catEx");
        int todoID = createTodo("todoEx");
        JSONObject body = new JSONObject();
        body.put("id",String.valueOf(todoID));

        Utils.buildJSONRequest()
                .body(body.toJSONString())
                .post("/categories/"+catID+"/todos");
        Utils.buildJSONRequest()
                .delete("/categories/"+catID+"/todos/"+todoID)
                .then().assertThat()
                .statusCode(STATUS_CODE.OK);
    }
    /*
    Test DELETE /categories/:id/todos/:id with valid Category ID and invalid To-do ID
     */
    @Test
    public void testDeleteCategoryTodoRelationshipValidCatIDandInvalidTodoID(){
        int catID = createCategory("catEx");
        int todoID = createTodo("todoEx");
        JSONObject body = new JSONObject();
        body.put("id",String.valueOf(todoID));

        Utils.buildJSONRequest()
                .body(body.toJSONString())
                .post("/categories/"+String.valueOf(catID)+"/projects");
        Utils.buildJSONRequest()
                .delete("/categories/"+String.valueOf(catID)+"/projects/ABC")
                .then().assertThat()
                .statusCode(STATUS_CODE.NOT_FOUND);
    }

    /**
     * Test DELETE /categories/:id/projects/:id with invalid ID for category and valid ID for to-do
     */
    @Test
    public void testDeleteCategoryTodoRelationshipInvalidCatIDAndValidTodoID(){
        int catID = createCategory("catEx");
        int todoID = createTodo("todoEx");
        JSONObject body = new JSONObject();
        body.put("id",String.valueOf(todoID));

        Utils.buildJSONRequestWithJSONResponse()
                .body(body.toJSONString())
                .post("/categories/" + String.valueOf(catID) + "/projects");
        Response response = Utils.buildJSONRequestWithJSONResponse()
                .delete("/categories/ABC/projects/"+String.valueOf(todoID));
        response.then().assertThat()
                .statusCode(STATUS_CODE.NOT_FOUND);
    }

    /*
    Test DELETE /categories/:id/projects/:id with invalid ID for both category and to-do
     */
    @Test
    public void testDeleteCategoryTodoRelationshipInValidIDs(){
        int catID = createCategory("catEx");
        int todoID = createProject("todoEx");
        JSONObject body = new JSONObject();
        body.put("id",String.valueOf(todoID));

        Utils.buildJSONRequest()
                .body(body.toJSONString())
                .post("/categories/"+String.valueOf(catID)+"/projects");
        Utils.buildJSONRequest()
                .delete("/categories/ABC/projects/ABC")
                .then().assertThat()
                .statusCode(STATUS_CODE.NOT_FOUND);
    }

    /**
     * Test POST /categories using XML payload
     */
    @Test
    public void testPostCategoriesUsingXML() {
        StringBuilder payload = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        payload.append("<todo>").append("<title>" + "titleText" + "</title>").append("</todo>");

        given()
                .header("Content-Type", "application/xml")
                .header("Accept", "application/xml")
                .body(payload.toString()).when()
                .post("/categories")
                .then().assertThat()
                .statusCode(STATUS_CODE.CREATED);
    }

    /**
     * Test POST /categories using malformedXML payload
     */
    @Test
    public void testPostCategoriesUsingMalformedXML() {
        StringBuilder payload = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        payload.append("<todo>").append("<title>>" + "titleText" + "<</title>").append("</todo>");

        Utils.buildJSONRequest()
                .body(payload.toString()).when()
                .post("/categories")
                .then().assertThat()
                .statusCode(STATUS_CODE.BAD_REQUEST);
    }
}
