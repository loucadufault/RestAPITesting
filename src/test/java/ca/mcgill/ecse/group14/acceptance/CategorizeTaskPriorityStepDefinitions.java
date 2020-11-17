package ca.mcgill.ecse.group14.acceptance;

import ca.mcgill.ecse.group14.Utils;
import io.cucumber.datatable.DataTable;
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

import java.util.List;
import java.util.Map;

import static ca.mcgill.ecse.group14.Utils.*;
import static org.junit.Assert.assertEquals;

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
        List<Map<String,String>> prior = buildJSONRequestWithJSONResponse().when()
                .get("/todos/"+todoId+"/categories").jsonPath().getList("categories");
        if (prior != null){
            for (Map<String, String> ity: prior) {
                deleteCategory(Integer.parseInt(ity.get("id")));
            }
        }
        JSONObject requestBody = new JSONObject();
        requestBody.put("id", Integer.valueOf(priorityId));
        Response response = buildJSONRequest().body(requestBody.toJSONString()).post("/todos/"+
                todoId+"/categories");
    }

    @Then("the todo {string} will be have priority {string}")
    public void the_todo_will_be_have_priority(String todoTitle, String priority) {
        int todoID = getFirstId(todoTitle, "todos");
        List<Map<String,String>> priorList = buildJSONRequestWithJSONResponse().when()
                .get("/todos/"+todoID+"/categories")
                .jsonPath()
                .getList("categories");
        String prior = priorList.get(0).get("title");
        assertEquals(priority, prior);
    }

    @Given("the todo {string} is assigned priority {string}")
    public void the_todo_is_assigned_priority(String todoTitle, String priority) {
        int priorityId = getFirstId(priority,"categories");
        int todoId = getFirstId(todoTitle, "todos");
        JSONObject requestBody = new JSONObject();
        requestBody.put("id", Integer.valueOf(priorityId));
        Response response = buildJSONRequest().body(requestBody.toJSONString()).post("/todos/"+
                todoId+"/categories");
    }

    @Then("the priority of todo {string} is updated from {string} to {string}")
    public void the_priority_of_todo_is_updated_from_to(String todoTitle, String priority1, String priority2) {
        int todoID = getFirstId(todoTitle, "todos");
        List<Map<String,String>> priorList = buildJSONRequestWithJSONResponse().when()
                .get("/todos/"+todoID+"/categories")
                .jsonPath()
                .getList("categories");
        String prior = priorList.get(0).get("title");
        assertEquals(priority2, prior);
    }
}
