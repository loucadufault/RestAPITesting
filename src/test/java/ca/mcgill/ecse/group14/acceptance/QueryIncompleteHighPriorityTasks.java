package ca.mcgill.ecse.group14.acceptance;

import ca.mcgill.ecse.group14.Utils;
import ca.mcgill.ecse.group14.Resources;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import io.cucumber.junit.Cucumber;
import org.json.simple.JSONObject;
import org.junit.runner.RunWith;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

@RunWith(Cucumber.class)
public class QueryIncompleteHighPriorityTasks extends BaseStepDefinitions {


    @Given("the following priorities are in the system")
    public void the_following_priorities_are_in_the_system(io.cucumber.datatable.DataTable dataTable) {
        List<List<String>> columns = dataTable.asLists(String.class);

        boolean hasSeenTitleRow = false;
        for (List<String> row : columns) {
            if(hasSeenTitleRow) {
                JSONObject requestBody = new JSONObject();
                requestBody.put("title", row.get(0));
                requestBody.put("description", row.get(1));
                Utils.buildJSONRequest().body(requestBody.toJSONString()).post("/categories");
            }
            else{
                hasSeenTitleRow = true;
            }
        }
    }

    @Given("there exists the following projects in the system")
    public void there_exists_the_following_projects_in_the_system(io.cucumber.datatable.DataTable dataTable) {
        List<List<String>> columns = dataTable.asLists(String.class);

        boolean hasSeenTitleRow = false;
        for (List<String> row : columns) {
            if(hasSeenTitleRow) {
                JSONObject requestBody = new JSONObject();
                requestBody.put("title", row.get(0));
                requestBody.put("completed", row.get(1));
                requestBody.put("active", row.get(2));
                requestBody.put("description", row.get(3));
                Utils.buildJSONRequest().body(requestBody.toJSONString()).post("/tasks");
            }
            else{
                hasSeenTitleRow = true;
            }
        }
    }

    @Given("there exists the following todos in the system that are saved under {string}")
    public void there_exists_the_following_todos_in_the_system_that_are_saved_under(String string, io.cucumber.datatable.DataTable dataTable) {
        List<List<String>> columns = dataTable.asLists(String.class);

        boolean hasSeenTitleRow = false;
        for (List<String> row : columns) {
            if(hasSeenTitleRow) {
                JSONObject requestBody = new JSONObject();
                requestBody.put("title", row.get(0));
                requestBody.put("todoDoneStatus", row.get(1));
                requestBody.put("todoDescription", row.get(2));
                if(row.size()==4) {
                    requestBody.put("todoPriority", row.get(3));
                }
                Utils.buildJSONRequest().body(requestBody.toJSONString()).post(Resources.BASE_URL+"/tasks");
            }
            else{
                hasSeenTitleRow = true;
            }
        }
    }

    @When("the user attempts to query the incomplete high priority tasks of the project with title {string}")
    public void the_user_attempts_to_query_the_incomplete_high_priority_tasks_of_the_project_with_title(String title) {
        counter = 0;
        
    }

    @Then("{string} todos will be returned")
    public void todos_will_be_returned(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Given("the project with title {string} has no active tasks")
    public void the_project_with_title_has_no_active_tasks(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Given("there does not exist the project {string} in the system")
    public void there_does_not_exist_the_project_in_the_system(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
}