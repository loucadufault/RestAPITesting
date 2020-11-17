package ca.mcgill.ecse.group14.acceptance;
import ca.mcgill.ecse.group14.Resources;
import ca.mcgill.ecse.group14.Utils;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import io.cucumber.junit.Cucumber;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.junit.runner.RunWith;

import java.util.List;
import java.util.Map;

import static ca.mcgill.ecse.group14.Resources.BASE_URL;
import static org.junit.Assert.*;

@RunWith(Cucumber.class)
public class RemoveTaskStepDefinitions extends BaseStepDefinitions{
    @Given("the following projects exist in the system")
    public void the_following_projects_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) {
        List<List<String>> rows = dataTable.asLists(String.class);
        boolean titles = true;
        for (List<String> columns:rows) {
            if (!titles) {
                JSONObject requestBody = new JSONObject();
                requestBody.put("title", columns.get(0));
                Utils.buildJSONRequest().body(requestBody.toJSONString()).post(BASE_URL+"/projects");
            }
            titles = false;
        }
    }

    @Given("the following todos are assigned to the project {string}")
    public void the_following_todos_are_assigned_to_the_project(String projTitle, io.cucumber.datatable.DataTable dataTable) {
        int projId = Utils.getFirstId(projTitle, "projects");
        List<List<String>> rows = dataTable.asLists(String.class);
        boolean titles = true;
        for (List<String> columns:rows) {
            if (!titles) {
                int todoID = Utils.getFirstId(columns.get(0), "todos");
                JSONObject fields = new JSONObject();
                fields.put("id", todoID);
                RequestSpecification request = Utils.buildJSONRequest().body(fields.toJSONString());
                request.post(BASE_URL+"/projects/" + projId + "/tasks");
            }
            titles = false;
        }
    }

    @When("the user attempts to remove todo with title {string} from the system")
    public void the_user_attempts_to_remove_todo_with_title_from_the_system(String title) {
        todoCount = Utils.countTodos();
        Utils.removeTodo(title);
    }

    @Then("todo with title {string} shall be removed from the system")
    public void todo_with_title_shall_be_removed_from_the_system(String title) {
        assertFalse(Utils.existsTodo(title));
    }

    @Given("the todo with title {string} is assigned to a project with title {string}")
    public void the_todo_with_title_is_assigned_to_a_project_with_title(String todoTitle, String projectTitle) {
        int projId = Utils.getFirstId(projectTitle, "projects");
        Response response = Utils.buildJSONRequestWithJSONResponse().when().get(BASE_URL + "/projects/" + projId + "/tasks");
        List<Map<String, String>> things = response.jsonPath().getList("todos");
        for (Map<String, String> thing : things) {
            if (thing.get("title").equals(todoTitle)) {
                return;
            }
        }

        int todoID = Utils.getFirstId(todoTitle, "todos");
        JSONObject fields = new JSONObject();
        fields.put("id", todoID);
        RequestSpecification request = Utils.buildJSONRequest().body(fields.toJSONString());
        request.post(BASE_URL+"/projects/" + projId + "/tasks");
    }

    @When("the user attempts to remove the todo with title {string} from the project with title {string}")
    public void the_user_attempts_to_remove_the_todo_with_title_from_the_project_with_title(String todoTitle, String projTitle) {
        todoCount = Utils.countTodos();
        int todoID = Utils.getFirstId(todoTitle, "todos");
        int projID = Utils.getFirstId(projTitle, "projects");
        Utils.buildJSONRequestWithJSONResponse().when().delete(BASE_URL + "/projects/" + projID + "/tasks/" + todoID);
    }

    @Then("todo with title {string} shall no longer be associated with the project with title {string}")
    public void todo_with_title_shall_no_longer_be_associated_with_the_project_with_title(String todoTitle, String projTitle) {
        int projID = Utils.getFirstId(projTitle, "projects");
        Response response = Utils.buildJSONRequestWithJSONResponse().when().get(BASE_URL + "/projects/" + projID + "/tasks");
        List<Map<String, String>> todos = response.jsonPath().getList("todos"); //
        for (Map<String, String> todo : todos) {
            if (todo.get("title").equals(todoTitle)) {
                fail();
            }
        }
        return;
    }

    @Then("there shall be the same number of todos in the system")
    public void there_shall_be_the_same_number_of_todos_in_the_system() {
        assertEquals(todoCount,Utils.countTodos());
    }

    @Then("there shall be one less todo in the system")
    public void there_shall_be_one_less_todo_in_the_system() {
        assertEquals(todoCount - 1,Utils.countTodos());
    }

}