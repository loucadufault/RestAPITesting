package ca.mcgill.ecse.group14.acceptance;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import io.cucumber.junit.Cucumber;

import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.junit.runner.RunWith;
import io.cucumber.datatable.DataTable;

import ca.mcgill.ecse.group14.Utils;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.core.IsEqual.*;
import static ca.mcgill.ecse.group14.Resources.*;
import static ca.mcgill.ecse.group14.Utils.*;
import static org.junit.Assert.assertEquals;

@RunWith(Cucumber.class)
public class AddingTaskToCourseStepDefinitions extends BaseStepDefinitions{
    @Given("the following todos exist in the system:")
    public void the_following_todos_exist_in_the_system(DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps();
        for (Map<String, String> columns : rows) {
            JSONObject requestBody = new JSONObject();
            requestBody.put("title", columns.get("title"));
            requestBody.put("doneStatus", Boolean.valueOf(columns.get("doneStatus")));
            requestBody.put("description", columns.get("description"));
            buildJSONRequest().body(requestBody.toJSONString()).post("/todos");
        }
    }

    @Given("the following projects exist in the system:")
    public void the_following_projects_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps();
        for (Map<String, String> columns : rows) {
            JSONObject requestBody = new JSONObject();
            requestBody.put("title", columns.get("title"));
            requestBody.put("completed", Boolean.valueOf(columns.get("completed")));
            requestBody.put("active", columns.get("active"));
            requestBody.put("description", columns.get("description"));

            buildJSONRequest().body(requestBody.toJSONString()).post("/projects");
        }
    }

    @Given("there exists a todo with title {string} in the system")
    public void there_exists_a_todo_with_title_in_the_system(String title) {
        if (!existsTodo(title)){
            createTodo(title);
        }
    }

    @When("the user attempts to add todo with title {string} to project with title {string}")
    public void the_user_attempts_to_add_todo_with_title_to_project_with_title(String todoTitle, String projTitle) {
        int todoId = getFirstId(todoTitle,"todos");
        int projId = getFirstId(projTitle, "projects");
        JSONObject requestBody = new JSONObject();
        requestBody.put("id", Integer.valueOf(todoId));
        Response response = buildJSONRequest().body(requestBody.toJSONString()).post("/projects/"+
                projId+"/tasks");
        errorCode = response.getStatusCode();
        System.out.println(errorCode);
    }

    @Then("the todo with title {string} is added to project with title {string} todo list")
    public void the_todo_with_title_is_added_to_project_with_title_todo_list(String todoTitle, String projTitle) {
        int projID = getFirstId(projTitle, "projects");
        List<Map<String,String>> things = buildJSONRequestWithJSONResponse().when()
                .get("/projects/"+projID+"/tasks")
                .jsonPath()
                .getList("todos");
        for (Map<String, String> thing : things) {
            if (thing.get("title").equals(todoTitle)) {
                return;
            }
        }
    }

    @Given("there does not exist a todo with title {string} in the system")
    public void there_does_not_exist_a_todo_with_title_in_the_system(String title) {
        if(existsTodo(title)){
            removeTodo(title);
        }
    }

    @When("the user attempts to create a todo with title {string} and done status {string} and description {string}")
    public void the_user_attempts_to_create_a_todo_with_title_and_done_status_and_description(String title, String status, String description) {
        JSONObject requestBody = new JSONObject();
        requestBody.put("title",title);
        requestBody.put("doneStatus",status);
        requestBody.put("description",description);
        buildJSONRequest().body(requestBody.toJSONString()).post("/todos");
    }

    @Then("a todo is created with title {string} and done status {string} and description {string}")
    public void a_todo_is_created_with_title_and_done_status_and_description(String title, String status, String description) {
        existsTodo(title);
    }

    @Then("the system shall report the error code {string}")
    public void the_system_shall_report_the_error_code(String code) {
        assertEquals(Integer.parseInt(code), errorCode);
    }
}
