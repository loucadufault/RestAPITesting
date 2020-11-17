package ca.mcgill.ecse.group14.acceptance;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import io.cucumber.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
public class ChangeTaskDescriptionStepDefinition {

    @Given("^the todo manager rest API is running$")
    public void the_todo_manager_rest_api_is_running() throws Throwable {

    }

    @Given("^todo with title (.+) exists$")
    public void todo_with_title_exists(String title) throws Throwable {

    }

    @Given("^todo with title (.+) does not exist$")
    public void todo_with_title_does_not_exist(String title) throws Throwable {

    }

    @When("^I request to add a description (.+) to the todo with title (.+)$")
    public void i_request_to_add_a_description_to_the_todo_with_title(String newdescription, String title) throws Throwable {

    }

    @When("^I request to change the description of todo with title (.+) from (.+) to (.+)$")
    public void i_request_to_change_the_description_of_todo_with_title_from_to(String title, String description1, String description2) throws Throwable {

    }

    @Then("^the description (.+) is added to the todo with title (.+)$")
    public void the_description_is_added_to_the_todo_with_title(String newdescription, String title) throws Throwable {

    }

    @Then("^the description of todo with title (.+) is changed to (.+)$")
    public void the_description_of_todo_with_title_is_changed_to(String title, String description2) throws Throwable {

    }

    @Then("^I receive an error code (.+)$")
    public void i_receive_an_error_code(String errorcode) throws Throwable {

    }

    @And("^I am a student$")
    public void i_am_a_student() throws Throwable {

    }

    @And("^the following todos exist in the system$")
    public void the_following_todos_exist_in_the_system() throws Throwable {

    }

    @And("^todo with title (.+) does not have a description$")
    public void todo_with_title_does_not_have_a_description(String title) throws Throwable {

    }

    @And("^todo with title (.+) has a description (.+)$")
    public void todo_with_title_has_a_description(String title, String description1) throws Throwable {

    }

}