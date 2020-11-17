package ca.mcgill.ecse.group14.acceptance;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import io.cucumber.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
public class RemoveTaskStepDefinitions {

    /*@Given("^the Todo Manager Rest API server is running$")
    public void the_todo_manager_rest_api_server_is_running() throws Throwable {

    }*/

    @Given("^todo with title (.+) exists$")
    public void todo_with_title_exists(String todotitle) throws Throwable {

    }

    @Given("^project with title (.+) exists$")
    public void project_with_title_exists(String projecttitle) throws Throwable {

    }

    @Given("^todo with title (.+) does not exist$")
    public void todo_with_title_does_not_exist(String todotitle) throws Throwable {

    }

    @When("^I request to remove todo with title (.+) from the system$")
    public void i_request_to_remove_todo_with_title_from_the_system(String todotitle) throws Throwable {

    }

    @When("^I request to remove todo with title (.+) from the project with title (.+)$")
    public void i_request_to_remove_todo_with_title_from_the_project_with_title(String todotitle, String projecttitle) throws Throwable {

    }

    @When("^I request to remove todo with title (.+) from the course$")
    public void i_request_to_remove_todo_with_title_from_the_course(String todotitle) throws Throwable {

    }

    @Then("^todo with title (.+) is removed from the system$")
    public void todo_with_title_is_removed_from_the_system(String todotitle) throws Throwable {

    }

    @Then("^todo with title (.+) is removed from the project$")
    public void todo_with_title_is_removed_from_the_project(String todotitle) throws Throwable {

    }

    @Then("^I receive an error code (.+)$")
    public void i_receive_an_error_code(String errorcode) throws Throwable {

    }

    @And("^I am a student$")
    public void i_am_a_student() throws Throwable {

    }

    @And("^the following projects exist in the system$")
    public void the_following_projects_exist_in_the_system() throws Throwable {

    }

    @And("^the following todos are assigned to the project \"([^\"]*)\"$")
    public void the_following_todos_are_assigned_to_the_project_something(String strArg1) throws Throwable {

    }

    @And("^todo with title (.+) exists and is assigned to a project with title (.+)$")
    public void todo_with_title_exists_and_is_assigned_to_a_project_with_title(String todotitle, String projecttitle) throws Throwable {

    }

    /*@And("^project with title (.+) exists$")
    public void project_with_title_exists(String projecttitle) throws Throwable {

    }*/

}