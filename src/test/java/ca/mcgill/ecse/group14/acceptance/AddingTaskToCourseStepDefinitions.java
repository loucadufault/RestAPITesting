package ca.mcgill.ecse.group14.acceptance;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import io.cucumber.junit.Cucumber;
import org.json.simple.JSONObject;
import org.junit.runner.RunWith;
import io.cucumber.datatable.DataTable;

import ca.mcgill.ecse.group14.Utils;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.core.IsEqual.*;
import static ca.mcgill.ecse.group14.Resources.*;
import static ca.mcgill.ecse.group14.Utils.*;
@RunWith(Cucumber.class)

public class AddingTaskToCourseStepDefinitions extends BaseStepDefinitions{
    @Given("the following todos exist in the system:")
    public void the_following_todos_exist_in_the_system(DataTable dataTable) {
        List<List<String>> rows = dataTable.asLists(String.class);
        boolean titles = true;
        for (List<String> columns : rows) {
            if (!titles) {
                JSONObject requestBody = new JSONObject();
                requestBody.put("title", columns.get(0));
                requestBody.put("doneStatus", columns.get(1));
                requestBody.put("description", columns.get(2));
                buildJSONRequest().body(requestBody.toJSONString()).post(BASE_URL+"/todos");
            }
            titles = false;
        }
    }

    @Given("the following projects exist in the system:")
    public void the_following_projects_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) {
        List<List<String>> rows = dataTable.asLists(String.class);
        boolean titles = true;
        for (List<String> columns : rows) {
            if (!titles) {
                JSONObject requestBody = new JSONObject();
                requestBody.put("title", columns.get(0));
                requestBody.put("completed", columns.get(1));
                requestBody.put("active", columns.get(2));
                requestBody.put("description", columns.get(3));
                buildJSONRequest().body(requestBody.toJSONString()).post(BASE_URL+"/projects");
            }
            titles = false;
        }
    }

    @Given("there exists a todo with title {string} in the system")
    public void there_exists_a_todo_with_title_in_the_system(String string) {
        if (existsTodo)
    }

    @When("the user attempts to add todo with title {string} to project with title {string}")
    public void the_user_attempts_to_add_todo_with_title_to_project_with_title(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("the todo with title Requirements is added to project with title {string} todo list")
    public void the_todo_with_title_requirements_is_added_to_project_with_title_todo_list(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("the todo with title Development is added to project with title {string} todo list")
    public void the_todo_with_title_development_is_added_to_project_with_title_todo_list(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Given("there does not exist a todo with title {string} in the system")
    public void there_does_not_exist_a_todo_with_title_in_the_system(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("the user attempts to create a todo with title {string} and done status {string} and description {string}")
    public void the_user_attempts_to_create_a_todo_with_title_and_done_status_and_description(String string, String string2, String string3) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("the user attempts to add todo {string} to project {string}")
    public void the_user_attempts_to_add_todo_to_project(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("a todo is created with title {string} and done status false and description {string}")
    public void a_todo_is_created_with_title_and_done_status_false_and_description(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("the todo with title {string} is added to project with title {string} todo list")
    public void the_todo_with_title_is_added_to_project_with_title_todo_list(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("the user attempts to add todo with title {string} to project with title COMP100")
    public void the_user_attempts_to_add_todo_with_title_to_project_with_title_comp100(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("the system shall report the error code {string}")
    public void the_system_shall_report_the_error_code(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("the user attempts to add todo with title {string} to project with title FACC100")
    public void the_user_attempts_to_add_todo_with_title_to_project_with_title_facc100(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
}
