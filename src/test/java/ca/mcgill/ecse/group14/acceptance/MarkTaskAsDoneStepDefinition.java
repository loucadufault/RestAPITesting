package ca.mcgill.ecse.group14.acceptance;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import io.cucumber.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
public class MarkTaskAsDoneStepDefinition {

    @Given("^the Todo Manager Rest API server is running$")
    public void the_todo_manager_rest_api_server_is_running() throws Throwable {

    }

    @Given("^todo with title (.+) exists$")
    public void todo_with_title_exists(String title) throws Throwable {

    }

    @Given("^todo with title (.+) does not exist$")
    public void todo_with_title_does_not_exist(String title) throws Throwable {

    }

    @When("^I request to set doneStatus of todo with title (.+) to true$")
    public void i_request_to_set_donestatus_of_todo_with_title_to_true(String title) throws Throwable {

    }

    @Then("^doneStatus of todo with title (.+) is set to true$")
    public void donestatus_of_todo_with_title_is_set_to_true(String title) throws Throwable {

    }

    @Then("^todo with title (.+) will not be modified$")
    public void todo_with_title_will_not_be_modified(String title) throws Throwable {

    }

    @Then("^I will receive an error code (.+)$")
    public void i_will_receive_an_error_code(String errorcode) throws Throwable {

    }

    @And("^I am a student$")
    public void i_am_a_student() throws Throwable {

    }

    @And("^the following todos exist in the system:$")
    public void the_following_todos_exist_in_the_system() throws Throwable {

    }

    @And("^todo with title (.+) has doneStatus set to false$")
    public void todo_with_title_has_donestatus_set_to_false(String title) throws Throwable {

    }

    @And("^todo with title (.+) has doneStatus set to true$")
    public void todo_with_title_has_donestatus_set_to_true(String title) throws Throwable {

    }

}