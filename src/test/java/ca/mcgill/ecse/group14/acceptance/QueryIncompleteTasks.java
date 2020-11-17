package ca.mcgill.ecse.group14.acceptance;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import io.cucumber.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
public class QueryIncompleteTasks extends BaseStepDefinitions {

    @When("the user attempts to query the incomplete tasks of the project with title {string}")
    public void the_user_attempts_to_query_the_incomplete_tasks_of_the_project_with_title(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
}
