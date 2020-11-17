package ca.mcgill.ecse.group14.acceptance;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import io.cucumber.junit.Cucumber;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.junit.runner.RunWith;

import java.util.List;

import static ca.mcgill.ecse.group14.Utils.buildJSONRequest;
import static ca.mcgill.ecse.group14.Utils.getFirstId;

@RunWith(Cucumber.class)
public class CategorizeTaskPriorityStepDefinitions extends BaseStepDefinitions {
    @Given("the following categories exist in the system:")
    public void the_following_categories_exist_in_the_system(DataTable dataTable) {
        List<List<String>> rows = dataTable.asLists(String.class);
        boolean titles = true;
        for (List<String> columns : rows) {
            if (!titles) {
                JSONObject requestBody = new JSONObject();
                requestBody.put("title", Boolean.valueOf(columns.get(0)));
                requestBody.put("description", Boolean.valueOf(columns.get(1)));
                buildJSONRequest().body(requestBody.toJSONString()).post("/categories");
            }
            titles = false;
        }
    }

    @When("the user attempts to assign priority {string} to todo {string}")
    public void the_user_attempts_to_assign_priority_to_todo(String priority, String todoTitle) {
        int priorityId = getFirstId(priority,"categories");
        int todoId = getFirstId(todoTitle, "todos");
        JSONObject requestBody = new JSONObject();
        requestBody.put("id", Integer.valueOf(priorityId));
        Response response = buildJSONRequest().body(requestBody.toJSONString()).post("/todos/"+
                todoId+"/categories");
    }

    @Then("the todo {string} will be have priority {string}")
    public void the_todo_requirements_will_be_have_priority(String todoTitle, String priority) {

    }

    @When("the user attempts to assign priority {string} to todo Quality Assurance")
    public void the_user_attempts_to_assign_priority_to_todo_quality_assurance(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("the todo Quality Assurance will be have priority {string}")
    public void the_todo_quality_assurance_will_be_have_priority(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Given("the todo Requirements is assigned priority {string}")
    public void the_todo_requirements_is_assigned_priority(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("the priority of todo {string} is updated from {string} to {string}")
    public void the_priority_of_todo_is_updated_from_to(String string, String string2, String string3) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Given("the todo Quality Assurance is assigned priority {string}")
    public void the_todo_quality_assurance_is_assigned_priority(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
}
