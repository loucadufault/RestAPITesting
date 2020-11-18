package ca.mcgill.ecse.group14.acceptance;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.junit.runner.RunWith;

import java.util.List;
import java.util.Map;

import static ca.mcgill.ecse.group14.Utils.*;

@RunWith(Cucumber.class)
public class CategorizeTaskPriorityStepDefinitions extends BaseStepDefinitions {
    @Given("the following categories exist in the system:")
    public void the_following_categories_exist_in_the_system(DataTable dataTable) {
        List<Map<String,String>> rows = dataTable.asMaps();
        boolean titles = true;
        for (Map<String,String> columns : rows) {
            JSONObject requestBody = new JSONObject();
            requestBody.put("title", columns.get("title"));
            requestBody.put("description", columns.get("description"));
            buildJSONRequest().body(requestBody.toJSONString()).post("/categories");
        }
    }

    @When("the user attempts to assign priority {string} to todo {string}")
    public void the_user_attempts_to_assign_priority_to_todo(String priority, String todoTitle) {
        int priorityId = getFirstId(priority,"categories");
        int todoId = getFirstId(todoTitle, "todos");
        List<Map<String,String>> prior = buildJSONRequestWithJSONResponse().when()
                .get("/todos/"+todoId+"/categories").jsonPath().getList("categories");
        if (prior != null){
            for (Map<String,String> p : prior) {
                buildJSONRequest().delete("/todos/" + todoId + "/categories/"+p.get("id"));
            }
        }
        JSONObject requestBody = new JSONObject();
        requestBody.put("id", priorityId);
        Response response = buildJSONRequest().body(requestBody.toJSONString()).post("/todos/"+ todoId+"/categories");
        errorCode = response.getStatusCode();
    }

    @Then("the todo {string} will have priority {string}")
    public void the_todo_will_have_priority(String todoTitle, String priority) {
        int todoId = getFirstId(todoTitle, "todos");
        List<Map<String,String>> priorList = buildJSONRequestWithJSONResponse().when()
                .get("/todos/"+todoId+"/categories")
                .jsonPath()
                .getList("categories");
        for (Map<String, String> thing : priorList) {
            if (thing.get("title").equals(priority)) {
                return;
            }
        }
    }

    @Given("the todo {string} is assigned priority {string}")
    public void the_todo_is_assigned_priority(String todoTitle, String priority) {
        int priorityId = getFirstId(priority,"categories");
        int todoId = getFirstId(todoTitle, "todos");
        JSONObject requestBody = new JSONObject();
        requestBody.put("id", Integer.valueOf(priorityId));
        buildJSONRequest().body(requestBody.toJSONString()).post("/todos/" + todoId + "/categories");
    }

    @Then("the priority of todo {string} is updated from {string} to {string}")
    public void the_priority_of_todo_is_updated_from_to(String todoTitle, String priority1, String priority2) {
        int todoID = getFirstId(todoTitle, "todos");
        List<Map<String,String>> priorList = buildJSONRequestWithJSONResponse().when()
                .get("/todos/"+todoID+"/categories")
                .jsonPath()
                .getList("categories");
        for (Map<String, String> thing : priorList) {
            if (thing.get("title").equals(priority2)) {
                return;
            }
        }
    }
}
