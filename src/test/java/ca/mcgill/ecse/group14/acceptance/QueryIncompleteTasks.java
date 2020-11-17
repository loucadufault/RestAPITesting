package ca.mcgill.ecse.group14.acceptance;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import io.cucumber.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
public class QueryIncompleteTasks {
    @Given("^there exists a project with title (.+) in the system$")
    public void there_exists_a_project_with_title_in_the_system(String string) throws Throwable {
        
    }

    @Given("^the project with title \"([^\"]*)\" has active tasks$")
    public void the_project_with_title_something_has_active_tasks(String title, String strArg1) throws Throwable {
        
    }

    @Given("^the project with title \"([^\"]*)\" has no active tasks$")
    public void the_project_with_title_something_has_no_active_tasks(String title, String strArg1) throws Throwable {
        
    }

    @Given("^there does not exist a project with title (.+) in the system$")
    public void there_does_not_exist_a_project_with_title_in_the_system(String string) throws Throwable {
        
    }

    @When("^the user attempts to query the incomplete high priority tasks of the project with title \"([^\"]*)\"$")
    public void the_user_attempts_to_query_the_incomplete_high_priority_tasks_of_the_project_with_title_something(String title, String strArg1) throws Throwable {
        
    }

    @Then("^\"([^\"]*)\" todos will be returned$")
    public void something_todos_will_be_returned(String n, String strArg1) throws Throwable {
        
    }

    @And("^the following projects are in memory$")
    public void the_following_projects_are_in_memory() throws Throwable {
        
    }

    @And("^the following todos are saved under \"([^\"]*)\"$")
    public void the_following_todos_are_saved_under_something(String strArg1) throws Throwable {
        
    }

}
