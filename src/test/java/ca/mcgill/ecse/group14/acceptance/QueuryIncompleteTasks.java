package ca.mcgill.ecse.group14.acceptance;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import io.cucumber.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
public class QueuryIncompleteTasks {
    @Given("^the Todo Manager Rest API server is running$")
    public void the_todo_manager_rest_api_server_is_running() throws Throwable {

    }

    @Given("^that (.+) is a project in memory$")
    public void that_is_a_project_in_memory(String title) throws Throwable {
        
    }

    @Given("^that (.+) is an unactive project in memory$")
    public void that_is_an_unactive_project_in_memory(String title) throws Throwable {
        
    }

    @Given("^that (.+) is not a project in memory$")
    public void that_is_not_a_project_in_memory(String title) throws Throwable {
        
    }

    @When("^I request the incomplete High priority tasks of the project titled (.+)$")
    public void i_request_the_incomplete_high_priority_tasks_of_the_project_titled(String projecttitle) throws Throwable {
        
    }

    @When("^I request the incomplete tasks of the project titled (.+)$")
    public void i_request_the_incomplete_tasks_of_the_project_titled(String projecttitle) throws Throwable {
        
    }

    @Then("^(.+) todos will be returned$")
    public void todos_will_be_returned(String n) throws Throwable {
        
    }

    @And("^the following projects are in memory$")
    public void the_following_projects_are_in_memory() throws Throwable {
        
    }

    @And("^the following todos are saved under 'Class A'$")
    public void the_following_todos_are_saved_under_class_a() throws Throwable {
        
    }

    @And("^the following todos are saved under 'Class B1'$")
    public void the_following_todos_are_saved_under_class_b1() throws Throwable {
        
    }

    @And("^the following todos are saved under 'Class B2'$")
    public void the_following_todos_are_saved_under_class_b2() throws Throwable {
        
    }

    @And("^the following todos are saved under 'Class C'$")
    public void the_following_todos_are_saved_under_class_c() throws Throwable {
        
    }

    @And("^the following todos are saved under 'Class D'$")
    public void the_following_todos_are_saved_under_class_d() throws Throwable {
        
    }

    @And("^that (.+) has active tasks$")
    public void that_has_active_tasks(String title) throws Throwable {
        
    }

    @And("^that (.+) has no active tasks$")
    public void that_has_no_active_tasks(String title) throws Throwable {
        
    }

    @And("^that (.+) has no tasks$")
    public void that_has_no_tasks(String title) throws Throwable {
        
    }

}
