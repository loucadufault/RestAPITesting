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

    @Before
    public static void before() {
        setup();
    }

    @After
    public static void after() throws InterruptedException {
        teardown();
    }

    @Given("the following priorities are in memory")
    public void the_following_priorities_are_in_memory(io.cucumber.datatable.DataTable dataTable) {
        List<List<String>> columns = dataTable.asLists(String.class);

        boolean hasSeenTitleRow = false;
        for (List<String> row : columns) {
            if(hasSeenTitleRow) {
                JSONObject requestBody = new JSONObject();
                requestBody.put("title", row.get(0));
                requestBody.put("description", row.get(1));
                Utils.buildJSONRequest().body(requestBody.toJSONString()).post(Resources.BASE_URL+"/categories");
            }
            else{
                hasSeenTitleRow = true;
            }
        }
    }

    @Given("the following projects are in memory")
    public void the_following_projects_are_in_memory(io.cucumber.datatable.DataTable dataTable) {
        List<List<String>> columns = dataTable.asLists(String.class);

        boolean hasSeenTitleRow = false;
        for (List<String> row : columns) {
            if(hasSeenTitleRow) {
                JSONObject requestBody = new JSONObject();
                requestBody.put("title", row.get(0));
                requestBody.put("completed", row.get(1));
                requestBody.put("active", row.get(2));
                requestBody.put("description", row.get(3));
                Utils.buildJSONRequest().body(requestBody.toJSONString()).post(Resources.BASE_URL+"/todos");
            }
            else{
                hasSeenTitleRow = true;
            }
        }
    }

    @Given("the following todos are saved under {string}")
    public void the_following_todos_are_saved_under(String string, io.cucumber.datatable.DataTable dataTable) {
        List<List<String>> columns = dataTable.asLists(String.class);

        boolean hasSeenTitleRow = false;
        for (List<String> row : columns) {
            if(hasSeenTitleRow) {
                JSONObject requestBody = new JSONObject();
                requestBody.put("todoTitle", row.get(0));
                requestBody.put("todoDoneStatus", row.get(1));
                requestBody.put("todoDescription", row.get(2));
                requestBody.put("todoPriority", row.get(3));
                Utils.buildJSONRequest().body(requestBody.toJSONString()).post(Resources.BASE_URL+"/todos");
            }
            else{
                hasSeenTitleRow = true;
            }
        }
    }

    @Given("there exists a project with title {string} in the system")
    public void there_exists_a_project_with_title_in_the_system(String title) {
        Utils.existsCategory(title);
    }

    @Given("the project with title {string} has active tasks")
    public void the_project_with_title_has_active_tasks(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("the user attempts to query the incomplete high priority tasks of the project with title {string}")
    public void the_user_attempts_to_query_the_incomplete_high_priority_tasks_of_the_project_with_title(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("\"\"{int}\"\" todos will be returned")
    public void todos_will_be_returned(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Given("the project with title {string} is inactive")
    public void the_project_with_title_is_inactive(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Given("the project with title {string} has no tasks")
    public void the_project_with_title_has_no_tasks(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

}