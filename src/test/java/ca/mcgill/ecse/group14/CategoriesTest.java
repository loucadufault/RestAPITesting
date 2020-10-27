package ca.mcgill.ecse.group14;
import io.restassured.specification.RequestSpecification;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.json.simple.JSONObject;

import static io.restassured.RestAssured.*;
import static org.hamcrest.core.IsEqual.*;
import static ca.mcgill.ecse.group14.Resources.*;
import static ca.mcgill.ecse.group14.TestUtils.*;

public class CategoriesTest {
    @Before
    public void setup() {
        Server.start();
        Server.check();
    }

    @After
    public void teardown() throws InterruptedException {
        Server.stop();
        Thread.sleep(500);
    }
    /*
    Test GET /categories
     */
    @Test
    public void testGetCategories() {
        TestUtils.assertGETStatusCode(BASE_URL + "/categories", STATUS_CODE.OK);
    }

    /*
    Test GET /categories/:id with a valid ID
     */
    @Test
    public void testGetCategoryWithValidID(){
        int catID = createCategoryHelper("catEx");
        given()
                .header("Content-Type", "application/json")
                .header("Accept","application/json")
                .get(BASE_URL+"/categories/"+String.valueOf(catID))
                .then().assertThat()
                .statusCode(STATUS_CODE.OK)
                .body("categories[0].title",equalTo("catEx"));
    }

    /*
    Test GET /categories/:id with an invalid ID
     */
    @Test
    public void testGetCategoryWithInvalidID(){
        int catID = createCategoryHelper("catEx");
        given()
                .header("Content-Type", "application/json")
                .header("Accept","application/json")
                .get(BASE_URL+"/categories/10")
                .then().assertThat()
                .statusCode(STATUS_CODE.NOT_FOUND);
    }

    /*
    Test GET /categories/:id/projects with valid ID
     */
    @Test
    public void testGetProjectsOfCategoryWithValidID(){
        int catID = createCategoryHelper("catEx");
        int projID = createProjectHelper("projEx");
        JSONObject body = new JSONObject();
        body.put("id",String.valueOf(projID));

        given()
                .header("Content-Type", "application/json")
                .header("Accept","application/json")
                .body(body.toJSONString())
                .post(BASE_URL+"/categories/"+String.valueOf(catID)+"/projects");
        given()
                .header("Content-Type", "application/json")
                .header("Accept","application/json")
                .get(BASE_URL+"/categories/"+String.valueOf(catID)+"/projects")
                .then().assertThat()
                .statusCode(STATUS_CODE.OK);
    }
    /*
    Test GET /categories/:id/projects with invalid ID (bug?)
    */
    @Test
    public void testGetProjectsOfCategoryWithInvalidID(){
        int catID = createCategoryHelper("catEx");
        int projID = createProjectHelper("projEx");
        JSONObject body = new JSONObject();
        body.put("id",String.valueOf(projID));

        given()
                .header("Content-Type", "application/json")
                .header("Accept","application/json")
                .body(body.toJSONString())
                .post(BASE_URL+"/categories/"+String.valueOf(catID)+"/projects");
        given()
                .header("Content-Type", "application/json")
                .header("Accept","application/json")
                .get(BASE_URL+"/categories/100/projects")
                .then().assertThat()
                .statusCode(STATUS_CODE.NOT_FOUND);
    }
    /*
    Test GET /categories/:id/todos with valid ID
     */
    @Test
    public void testGetTodosOfCategoryWithValidID(){
        int catID = createCategoryHelper("catEx");
        int todoID = createTodoHelper("todoEx");
        JSONObject body = new JSONObject();
        body.put("id",String.valueOf(todoID));

        given()
                .header("Content-Type", "application/json")
                .header("Accept","application/json")
                .body(body.toJSONString())
                .post(BASE_URL+"/categories/"+String.valueOf(catID)+"/projects");
        given()
                .header("Content-Type", "application/json")
                .header("Accept","application/json")
                .get(BASE_URL+"/categories/"+String.valueOf(catID)+"/projects")
                .then().assertThat()
                .statusCode(STATUS_CODE.OK);
    }

    /*
    Test GET /categories/:id/todos with invalid ID (bug?)
     */
    @Test
    public void testGetTodosOfCategoryWithInvalidID(){
        int catID = createCategoryHelper("catEx");
        int todoID = createTodoHelper("todoEx");
        JSONObject body = new JSONObject();
        body.put("id",String.valueOf(todoID));

        given()
                .header("Content-Type", "application/json")
                .header("Accept","application/json")
                .body(body.toJSONString())
                .post(BASE_URL+"/categories/"+String.valueOf(catID)+"/projects");
        given()
                .header("Content-Type", "application/json")
                .header("Accept","application/json")
                .get(BASE_URL+"/categories/ABC/projects")
                .then().assertThat()
                .statusCode(STATUS_CODE.NOT_FOUND);
    }

    /*
    Test POST /categories with no body
     */
    @Test
    public void testPostCategoryNoBody(){
        given()
                .header("Content-Type", "application/json")
                .header("Accept","application/json")
                .post(BASE_URL+"/categories")
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
        given()
                .header("Content-Type", "application/json")
                .header("Accept","application/json")
                .body(body.toJSONString())
                .post(BASE_URL+"/categories")
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
        given()
                .header("Content-Type", "application/json")
                .header("Accept","application/json")
                .body(body.toJSONString())
                .post(BASE_URL+"/categories")
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
        given()
                .header("Content-Type", "application/json")
                .header("Accept","application/json")
                .body(body.toJSONString())
                .post(BASE_URL+"/categories")
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
        int categoryID = createCategoryHelper("categoryTest");
        given()
                .header("Content-Type", "application/json")
                .header("Accept","application/json")
                .body(body.toJSONString())
                .post(BASE_URL+"/categories/"+String.valueOf(categoryID))
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

        int categoryID = createCategoryHelper("categoryTest");
        given()
                .header("Content-Type", "application/json")
                .header("Accept","application/json")
                .body(body.toJSONString())
                .get(BASE_URL+"/category/"+"5")
                .then().assertThat()
                .statusCode(STATUS_CODE.NOT_FOUND);
    }

    /*
    Test POST /categories/:id/projects with valid ID and valid project instance specified in JSON body
     */
    @Test
    public void testPostCategoryRelatedToProjectWithValidID(){
        int projID = createProjectHelper("Project1");
        int catID = createCategoryHelper("Category1");
        JSONObject body = new JSONObject();
        body.put("id",String.valueOf(projID));

        given()
                .header("Content-Type", "application/json")
                .header("Accept","application/json")
                .body(body.toJSONString())
                .post(BASE_URL+"/categories/"+String.valueOf(catID)+"/projects")
                .then().assertThat()
                .statusCode(STATUS_CODE.CREATED);
    }

    /*
    Test POST /categories/:id/projects with invalid ID and valid project instance specified in JSON body
     */
    @Test
    public void testPostCategoryRelatedToProjectWithInvalidID(){
        int projID = createProjectHelper("Project1");
        int catID = createCategoryHelper("Category1");
        JSONObject body = new JSONObject();
        body.put("id",String.valueOf(projID));

        given()
                .header("Content-Type", "application/json")
                .header("Accept","application/json")
                .body(body.toJSONString())
                .post(BASE_URL+"/categories/100/projects")
                .then().assertThat()
                .statusCode(STATUS_CODE.NOT_FOUND);
    }

    /*
    Test POST /categories/:id/to-do with valid ID and valid to-do instance specified in JSON body
     */
    @Test
    public void testPostCategoryRelatedToTodoWithValidID(){
        int todoID = createProjectHelper("Project1");
        int catID = createCategoryHelper("Category1");
        JSONObject body = new JSONObject();
        body.put("id",String.valueOf(todoID));

        given()
                .header("Content-Type", "application/json")
                .header("Accept","application/json")
                .body(body.toJSONString())
                .post(BASE_URL+"/categories/"+String.valueOf(catID)+"/projects")
                .then().assertThat()
                .statusCode(STATUS_CODE.CREATED);
    }

    /*
    Test POST /categories/:id/to-do with invalid ID and valid to-do instance specified in JSON body
     */
    @Test
    public void testPostCategoryRelatedToTodoWithInvalidID(){
        int todoID = createProjectHelper("Project1");
        int catID = createCategoryHelper("Category1");
        JSONObject body = new JSONObject();
        body.put("id",String.valueOf(todoID));

        given()
                .header("Content-Type", "application/json")
                .header("Accept","application/json")
                .body(body.toJSONString())
                .post(BASE_URL+"/categories/100/projects")
                .then().assertThat()
                .statusCode(STATUS_CODE.NOT_FOUND);
    }

    /*
    Test HEAD /categories
     */
    @Test
    public void testHeadCategories(){
        assertHEADStatusCode(BASE_URL+"/categories", STATUS_CODE.OK);
    }

    /*
    Test HEAD /categories/:id with a valid ID
     */
    @Test
    public void testHeadCategoriesWithValidID(){
        int catID = createCategoryHelper("catEx");
        given()
                .header("Content-Type", "application/json")
                .header("Accept","application/json")
                .head(BASE_URL+"/categories/"+String.valueOf(catID))
                .then().assertThat()
                .statusCode(STATUS_CODE.OK);
    }
    /*
    Test HEAD /categories/:id with a invalid ID
     */
    @Test
    public void testHeadCategoriesWithInvalidID(){
        int catID = createCategoryHelper("catEx");
        given()
                .header("Content-Type", "application/json")
                .header("Accept","application/json")
                .head(BASE_URL+"/categories/ABC")
                .then().assertThat()
                .statusCode(STATUS_CODE.NOT_FOUND);
    }
    /*
    Test HEAD /categories/:id/projects with valid ID
     */
    @Test
    public void testHeadProjectsOfCategoryWithValidID(){
        int catID = createCategoryHelper("catEx");
        int projID = createProjectHelper("projEx");
        JSONObject body = new JSONObject();
        body.put("id",String.valueOf(projID));

        given()
                .header("Content-Type", "application/json")
                .header("Accept","application/json")
                .body(body.toJSONString())
                .post(BASE_URL+"/categories/"+String.valueOf(catID)+"/projects");
        given()
                .header("Content-Type", "application/json")
                .header("Accept","application/json")
                .head(BASE_URL+"/categories/"+String.valueOf(catID)+"/projects")
                .then().assertThat()
                .statusCode(STATUS_CODE.OK);
    }
    /*
    Test HEAD /categories/:id/projects with invalid ID (bug?)
     */
    @Test
    public void testHeadProjectsOfCategoryWithInvalidID(){
        int catID = createCategoryHelper("catEx");
        int projID = createProjectHelper("projEx");
        JSONObject body = new JSONObject();
        body.put("id",String.valueOf(projID));

        given()
                .header("Content-Type", "application/json")
                .header("Accept","application/json")
                .body(body.toJSONString())
                .post(BASE_URL+"/categories/"+String.valueOf(catID)+"/projects");
        given()
                .header("Content-Type", "application/json")
                .header("Accept","application/json")
                .head(BASE_URL+"/categories/ABC/projects")
                .then().assertThat()
                .statusCode(STATUS_CODE.NOT_FOUND);
    }

    /*
    Test HEAD /categories/:id/todos with valid ID
     */
    @Test
    public void testHeadTodosOfCategoryWithValidID(){
        int catID = createCategoryHelper("catEx");
        int todoID = createTodoHelper("todoEx");
        JSONObject body = new JSONObject();
        body.put("id",String.valueOf(todoID));

        given()
                .header("Content-Type", "application/json")
                .header("Accept","application/json")
                .body(body.toJSONString())
                .post(BASE_URL+"/categories/"+String.valueOf(catID)+"/projects");
        given()
                .header("Content-Type", "application/json")
                .header("Accept","application/json")
                .head(BASE_URL+"/categories/"+String.valueOf(catID)+"/projects")
                .then().assertThat()
                .statusCode(STATUS_CODE.OK);
    }

    /*
    Test HEAD /categories/:id/todos with invalid ID (bug?)
     */
    @Test
    public void testHeadTodosOfCategoryWithInvalidID(){
        int catID = createCategoryHelper("catEx");
        int todoID = createTodoHelper("todoEx");
        JSONObject body = new JSONObject();
        body.put("id",String.valueOf(todoID));

        given()
                .header("Content-Type", "application/json")
                .header("Accept","application/json")
                .body(body.toJSONString())
                .post(BASE_URL+"/categories/"+String.valueOf(catID)+"/projects");
        given()
                .header("Content-Type", "application/json")
                .header("Accept","application/json")
                .head(BASE_URL+"/categories/ABC/projects")
                .then().assertThat()
                .statusCode(STATUS_CODE.NOT_FOUND);
    }

    /*
    Test PUT /categories/:id with valid ID and body with both valid title and description
     */
    @Test
    public void testPutCategoryWithValidIDAndValidTitleDescription(){
        String title = "catEx";
        String description = "catDes";
        JSONObject body = new JSONObject();
        body.put("title",title);
        body.put("description",description);

        int catID = createCategoryHelper("initial");
        given()
                .header("Content-Type", "application/json")
                .header("Accept","application/json")
                .body(body.toJSONString())
                .put(BASE_URL+"/categories/"+String.valueOf(catID))
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

        int catID = createCategoryHelper("initial");
        given()
                .header("Content-Type", "application/json")
                .header("Accept","application/json")
                .body(body.toJSONString())
                .put(BASE_URL+"/categories/ABC")
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

        int catID = createCategoryHelper("initial");
        given()
                .header("Content-Type", "application/json")
                .header("Accept","application/json")
                .body(body.toJSONString())
                .put(BASE_URL+"/categories/"+String.valueOf(catID))
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

        int catID = createCategoryHelper("initial");
        given()
                .header("Content-Type", "application/json")
                .header("Accept","application/json")
                .body(body.toJSONString())
                .put(BASE_URL+"/categories/"+String.valueOf(catID))
                .then().assertThat()
                .statusCode(STATUS_CODE.BAD_REQUEST);
    }

    /*
    Test PUT /categories/:id with valid ID and no body
     */
    @Test
    public void testPutCategoryWithValidIDAndTitleDescription(){
        int catID = createCategoryHelper("initial");
        given()
                .header("Content-Type", "application/json")
                .header("Accept","application/json")
                .put(BASE_URL+"/categories/"+String.valueOf(catID))
                .then().assertThat()
                .statusCode(STATUS_CODE.BAD_REQUEST);
    }

    /*
    Test DELETE /categories/:id with valid ID
     */
    @Test
    public void testDeleteCategoryWithValidID(){
        int catID = createCategoryHelper("initial");
        given()
                .header("Content-Type", "application/json")
                .header("Accept","application/json")
                .delete(BASE_URL+"/categories/"+String.valueOf(catID))
                .then().assertThat()
                .statusCode(STATUS_CODE.OK);
    }

    /*
    Test DELETE /categories/:id with invalid ID
     */
    @Test
    public void testDeleteCategoryWithInvalidID(){
        int catID = createCategoryHelper("initial");
        given()
                .header("Content-Type", "application/json")
                .header("Accept","application/json")
                .delete(BASE_URL+"/categories/ABC")
                .then().assertThat()
                .statusCode(STATUS_CODE.NOT_FOUND);
    }

    /*
    Test DELETE /categories/:id/projects/:id with valid ID for both category and project
     */
    @Test
    public void testDeleteCategoryProjectRelationshipValidIDs(){
        int catID = createCategoryHelper("catEx");
        int projID = createProjectHelper("projEx");
        JSONObject body = new JSONObject();
        body.put("id",String.valueOf(projID));

        given()
                .header("Content-Type", "application/json")
                .header("Accept","application/json")
                .body(body.toJSONString())
                .post(BASE_URL+"/categories/"+String.valueOf(catID)+"/projects");
        given()
                .header("Content-Type", "application/json")
                .header("Accept","application/json")
                .delete(BASE_URL+"/categories/"+String.valueOf(catID)+"/projects/"+String.valueOf(projID))
                .then().assertThat()
                .statusCode(STATUS_CODE.OK);
    }
    /*
    Test DELETE /categories/:id/projects/:id with valid Category ID and invalid Project ID
     */
    @Test
    public void testDeleteCategoryProjectRelationshipValidCatIDandInvalidProjID(){
        int catID = createCategoryHelper("catEx");
        int projID = createProjectHelper("projEx");
        JSONObject body = new JSONObject();
        body.put("id",String.valueOf(projID));

        given()
                .header("Content-Type", "application/json")
                .header("Accept","application/json")
                .body(body.toJSONString())
                .post(BASE_URL+"/categories/"+String.valueOf(catID)+"/projects");
        given()
                .header("Content-Type", "application/json")
                .header("Accept","application/json")
                .delete(BASE_URL+"/categories/"+String.valueOf(catID)+"/projects/ABC")
                .then().assertThat()
                .statusCode(STATUS_CODE.NOT_FOUND);
    }

    /*
    Test DELETE /categories/:id/projects/:id with invalid ID for category and valid ID for project (bug?)
     */
    @Test
    public void testDeleteCategoryProjectRelationshipInvalidCatIDandValidProjID(){
        int catID = createCategoryHelper("catEx");
        int projID = createProjectHelper("projEx");
        JSONObject body = new JSONObject();
        body.put("id",String.valueOf(projID));

        given()
                .header("Content-Type", "application/json")
                .header("Accept","application/json")
                .body(body.toJSONString())
                .post(BASE_URL+"/categories/"+String.valueOf(catID)+"/projects");
        given()
                .header("Content-Type", "application/json")
                .header("Accept","application/json")
                .delete(BASE_URL+"/categories/ABC/projects/"+String.valueOf(projID))
                .then().assertThat()
                .statusCode(STATUS_CODE.NOT_FOUND);
    }

    /*
    Test DELETE /categories/:id/projects/:id with invalid ID for both category and project
     */
    @Test
    public void testDeleteCategoryProjectRelationshipInValidIDs(){
        int catID = createCategoryHelper("catEx");
        int projID = createProjectHelper("projEx");
        JSONObject body = new JSONObject();
        body.put("id",String.valueOf(projID));

        given()
                .header("Content-Type", "application/json")
                .header("Accept","application/json")
                .body(body.toJSONString())
                .post(BASE_URL+"/categories/"+String.valueOf(catID)+"/projects");
        given()
                .header("Content-Type", "application/json")
                .header("Accept","application/json")
                .delete(BASE_URL+"/categories/ABC/projects/ABC")
                .then().assertThat()
                .statusCode(STATUS_CODE.NOT_FOUND);
    }

    /*
    Test DELETE /categories/:id/todos/:id with valid ID for both category and todo
     */
    @Test
    public void testDeleteCategoryTodoRelationshipValidIDs(){
        int catID = createCategoryHelper("catEx");
        int todoID = createTodoHelper("todoEx");
        JSONObject body = new JSONObject();
        body.put("id",String.valueOf(todoID));

        given()
                .header("Content-Type", "application/json")
                .header("Accept","application/json")
                .body(body.toJSONString())
                .post(BASE_URL+"/categories/"+String.valueOf(catID)+"/projects");
        given()
                .header("Content-Type", "application/json")
                .header("Accept","application/json")
                .delete(BASE_URL+"/categories/"+String.valueOf(catID)+"/projects/"+String.valueOf(todoID))
                .then().assertThat()
                .statusCode(STATUS_CODE.OK);
    }
    /*
    Test DELETE /categories/:id/todos/:id with valid Category ID and invalid To-do ID
     */
    @Test
    public void testDeleteCategoryTodoRelationshipValidCatIDandInvalidTodoID(){
        int catID = createCategoryHelper("catEx");
        int todoID = createTodoHelper("todoEx");
        JSONObject body = new JSONObject();
        body.put("id",String.valueOf(todoID));

        given()
                .header("Content-Type", "application/json")
                .header("Accept","application/json")
                .body(body.toJSONString())
                .post(BASE_URL+"/categories/"+String.valueOf(catID)+"/projects");
        given()
                .header("Content-Type", "application/json")
                .header("Accept","application/json")
                .delete(BASE_URL+"/categories/"+String.valueOf(catID)+"/projects/ABC")
                .then().assertThat()
                .statusCode(STATUS_CODE.NOT_FOUND);
    }

    /*
    Test DELETE /categories/:id/projects/:id with invalid ID for category and valid ID for to-do
     */
    @Test
    public void testDeleteCategoryTodoRelationshipInvalidCatIDandValidTodoID(){
        int catID = createCategoryHelper("catEx");
        int todoID = createTodoHelper("todoEx");
        JSONObject body = new JSONObject();
        body.put("id",String.valueOf(todoID));

        given()
                .header("Content-Type", "application/json")
                .header("Accept","application/json")
                .body(body.toJSONString())
                .post(BASE_URL+"/categories/"+String.valueOf(catID)+"/projects");
        given()
                .header("Content-Type", "application/json")
                .header("Accept","application/json")
                .delete(BASE_URL+"/categories/ABC/projects/"+String.valueOf(todoID))
                .then().assertThat()
                .statusCode(STATUS_CODE.NOT_FOUND);
    }

    /*
    Test DELETE /categories/:id/projects/:id with invalid ID for both category and to-do
     */
    @Test
    public void testDeleteCategoryTodoRelationshipInValidIDs(){
        int catID = createCategoryHelper("catEx");
        int todoID = createProjectHelper("todoEx");
        JSONObject body = new JSONObject();
        body.put("id",String.valueOf(todoID));

        given()
                .header("Content-Type", "application/json")
                .header("Accept","application/json")
                .body(body.toJSONString())
                .post(BASE_URL+"/categories/"+String.valueOf(catID)+"/projects");
        given()
                .header("Content-Type", "application/json")
                .header("Accept","application/json")
                .delete(BASE_URL+"/categories/ABC/projects/ABC")
                .then().assertThat()
                .statusCode(STATUS_CODE.NOT_FOUND);
    }

    /**
     * Test POST /categories using XML payload
     */
    @Test
    public void testPostTodosUsingXML() {
        StringBuilder payload = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        payload.append("<todo>").append("<title>" + "titleText" + "</title>").append("</todo>");

        given()
                .header("Content-Type", "application/xml")
                .header("Accept", "application/xml")
                .body(payload.toString()).when()
                .post(BASE_URL+"/categories")
                .then().assertThat()
                .statusCode(STATUS_CODE.CREATED);
    }
}
