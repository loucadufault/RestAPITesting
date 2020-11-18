package ca.mcgill.ecse.group14.acceptance;
import ca.mcgill.ecse.group14.Utils;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.junit.Cucumber;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.junit.runner.RunWith;

import java.util.List;
import java.util.Map;

import static ca.mcgill.ecse.group14.Resources.BASE_URL;
import static org.junit.Assert.fail;

@RunWith(Cucumber.class)
public class ChangeTaskDescriptionStepDefinitions extends BaseStepDefinitions {
    @Given("the following todos exist in the system")
    public void the_following_todos_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) {
        List<List<String>> rows = dataTable.asLists(String.class);
        boolean titles = true;
        for (List<String> columns:rows) {
            if (!titles) {
                JSONObject requestBody = new JSONObject();
                requestBody.put("title", columns.get(0));
                requestBody.put("description", columns.get(1));
                Utils.buildJSONRequest().body(requestBody.toJSONString()).post(BASE_URL+"/todos");
            }
            titles = false;
        }
    }

    @Given("there exists a todo with title {string} and description {string} in the system")
    public void there_exists_a_todo_with_title_and_description_in_the_system(String title, String description) {
        if(!Utils.existsTodo(title)){
            JSONObject requestBody = new JSONObject();
            requestBody.put("title", title);
            requestBody.put("description", description);
            Utils.buildJSONRequestWithJSONResponse().body(requestBody.toJSONString()).post(BASE_URL + "/todos");
        }
    }

    @When("the user attempts to change the description to {string} for the todo with title {string}")
    public void the_user_attempts_to_change_the_description_to_for_the_todo_with_title(String newDescription, String title) {
        int todoID = Utils.getFirstId(title,"todos");
        JSONObject requestBody = new JSONObject();
        requestBody.put("title", title);
        requestBody.put("description", newDescription);
        Utils.buildJSONRequestWithJSONResponse().body(requestBody.toJSONString()).put(BASE_URL + "/todos/" + todoID);
    }

    @Then("the todo with title {string} shall have description {string}")
    public void the_todo_with_title_shall_have_description(String title, String description) {
        Response response = Utils.buildJSONRequestWithJSONResponse().when().get(BASE_URL + "/todos");
        List<Map<String, String>> todos = response.jsonPath().getList("todos");
        for (Map<String, String> todo : todos) {
            if (todo.get("title").equals(title) && todo.get("description").equals(description)) {
                return;
            }
        }
        fail();
    }
}