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
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.junit.runner.RunWith;

import java.util.List;
import java.util.Map;

import static ca.mcgill.ecse.group14.Resources.BASE_URL;
import static ca.mcgill.ecse.group14.Utils.*;
import static org.junit.Assert.assertEquals;

@RunWith(Cucumber.class)
public class UpdateTaskPriorityStepDefinitions {
<<<<<<< HEAD
=======
    @When("the user attempts to update the priority to priority {string} of the todo with title {string}")
    public void the_user_attempts_to_update_the_priority_to_priority_of_the_todo_with_title(String priority, String todoTitle) {
//        the_user_attempts_to_assign_priority_to_todo(priority,todoTitle);
    }

    @Then("the todo with title {string} has priority {string}")
    public void the_todo_with_title_has_priority(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
>>>>>>> 750b84bd63559373a088a37099f87d8090572489

    @Given("the todo with title {string} is not assigned to a priority")
    public void the_todo_with_title_is_not_assigned_to_a_priority(String todoId) {
        List<Map<String,String>> prior = buildJSONRequest()
                .get("/todos/"+todoId+"/categories")
                .jsonPath()
                .getList("categories");
        if (prior != null){
            for (Map<String,String> p : prior) {
                buildJSONRequest().delete("/todos/" + todoId + "/categories/"+p.get("id"));
            }
        }
    }

}
