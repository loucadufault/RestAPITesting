package ca.mcgill.ecse.group14;

import io.restassured.RestAssured;
import io.restassured.response.*;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.junit.Test;

import static  ca.mcgill.ecse.group14.TestUtils.*;
import static ca.mcgill.ecse.group14.Resources.*;
import static ca.mcgill.ecse.group14.TestUtils.assertGETStatusCode;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ProjectsTest {
    private static boolean DEFAULT_COMPLETED = false;
    private static boolean DEFAULT_ACTIVE = false;
    private static String DEFAULT_DESCRIPTION = "";

    public ProjectsTest() {
        RestAssured.baseURI = BASE_URL + "/projects";
    }

    ////////////////////////////////////////////////////////////////////////////////
    // POST
    ////////////////////////////////////////////////////////////////////////////////

    @Test
    public void test_CreateProject_ValidValues() {
        String title = "test title";

        JSONObject requestParams = new JSONObject();
        requestParams.put("title", title);

        Response response = given().body(requestParams.toJSONString()).when().post();
//        System.out.println(when().post().asString());

        assertHasDefaultValues(response);
        assertHasTitle(response, title);
    }

    @Test
    public void test_CreateProject_NoValues() {
        Response response = buildJSONRequest().when().post();
        assertHasDefaultValues(response);
        assertHasTitle(response, "");
    }

    @Test
    public void test_CreateProject_IntegerTitle() {
        float title = 4.0f;

        JSONObject requestParams = new JSONObject();
        requestParams.put("title", title);

        Response response = buildJSONRequest().body(requestParams.toJSONString()).when().post();
        response.then().assertThat().statusCode(STATUS_CODE.CREATED);
        assertHasDefaultValues(response);
        assertHasTitle(response, String.valueOf(title));
    }

    @Test
    public void test_CreateProject_StringCompleted() {
        String completed = "true";

        JSONObject requestParams = new JSONObject();
        requestParams.put("completed", completed);
        Response response = buildJSONRequest().body(requestParams.toJSONString()).when().post();
        response.then().assertThat().statusCode(STATUS_CODE.BAD_REQUEST);
        assertHasErrorMessage(response, "Failed Validation: completed should be BOOLEAN");
    }

    @Test
    public void test_CreateProject_StringActive() {
        String active = "true";

        JSONObject requestParams = new JSONObject();
        requestParams.put("active", active);
        Response response = buildJSONRequest().body(requestParams.toJSONString()).when().post();
        response.then().assertThat().statusCode(STATUS_CODE.BAD_REQUEST);
        assertHasErrorMessage(response, "Failed Validation: completed should be BOOLEAN");
    }

    ////////////////////////////////////////////////////////////////////////////////
    // GET
    ////////////////////////////////////////////////////////////////////////////////

    @Test
    public void test_GetAllProjects_StatusCodeOK() {
        assertGETStatusCode(BASE_URL + "/projects", STATUS_CODE.OK);
    }

    @Test
    public void test_GetAllProjects_NewProjects() {
        String title1 = "test 1";
        String title2 = "test 2";
        createProject(title1);
        createProject(title2);

        Response response = buildJSONRequestWithJSONResponse().when().get()
                .then().assertThat().statusCode(STATUS_CODE.OK).extract().response();
    }



    ////////////////////////////////////////////////////////////////////////////////
    // Utils
    ////////////////////////////////////////////////////////////////////////////////

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
}
