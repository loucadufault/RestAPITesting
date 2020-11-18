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

import static org.junit.Assert.fail;

@RunWith(Cucumber.class)
public class MarkTaskAsDoneStepDefinitions extends BaseStepDefinitions{
    @Given("there exists a todo with title {string} and done status {string} in the system")
    public void there_exists_a_todo_with_title_and_done_status_in_the_system(String title, String doneStatus) {
        if(!Utils.existsTodo(title)){
            JSONObject requestBody = new JSONObject();
            requestBody.put("title", title);
            requestBody.put("doneStatus", Boolean.parseBoolean(doneStatus));
            Utils.buildJSONRequestWithJSONResponse().body(requestBody.toJSONString()).post("/todos");
        }
    }

    @When("the user attempts to set done status to {string} for the todo with title {string}")
    public void the_user_attempts_to_set_done_status_to_for_the_todo_with_title(String newDoneStatus, String title) {
        int todoID = Utils.getFirstId(title,"todos");
        JSONObject requestBody = new JSONObject();
        requestBody.put("title", title);
        requestBody.put("doneStatus", Boolean.parseBoolean(newDoneStatus));
        Response response = Utils.buildJSONRequestWithJSONResponse().body(requestBody.toJSONString()).put("/todos/" + todoID);
        errorCode = response.statusCode();
    }

    @Then("the todo with title {string} shall have done status {string}")
    public void the_todo_with_title_shall_have_done_status(String title, String newDoneStatus) {
        Response response = Utils.buildJSONRequestWithJSONResponse().when().get("/todos");
        List<Map<String, String>> todos = response.jsonPath().getList("todos");
        for (Map<String, String> todo : todos) {
            if (todo.get("title").equals(title) && todo.get("doneStatus").equals(newDoneStatus)) {
                return;
            }
        }
        fail();
    }
}