package ca.mcgill.ecse.group14.acceptance;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import io.cucumber.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)

public class AddingTaskToCourseStepDefinition {
    @Given("^the Todo Manager Rest API server is running$")
    public void theTodoManagerRestAPIServerIsRunning() throws Throwable {

    }

    @Given("^there exists a todo in the system with title (.+)$")
    public void thereExistsATodoInTheSystemWithTitle(String todotitle) throws Throwable {

    }

    @Given("^there exists a project in the system with title (.+)$")
    public void thereExistsAProjectInTheSystemWithTitle(String projecttitle) throws Throwable {

    }

    @When("^I request to add todo (.+) to project (.+)$")
    public void iRequestToAddTodoToProject(String todotitle, String projecttitle) throws Throwable {

    }

    @When("^I request to create a todo with title (.+) done status (.+) and description (.+)$")
    public void iRequestToCreateATodoWithTitleDoneStatusAndDescription(String todotitle, String status, String description) throws Throwable {

    }

    @Then("^the todo (.+) is added to project (.+) todo list$")
    public void theTodoIsAddedToProjectTodoList(String todotitle, String projecttitle) throws Throwable {

    }

    @Then("^I receive an error code (.+)$")
    public void iReceiveAnErrorCode(String errorcode) throws Throwable {

    }

    @Then("^a todo is created with title (.+) done status (.+) and description (.+)$")
    public void aTodoIsCreatedWithTitleDoneStatusAndDescription(String todotitle, String status, String description) throws Throwable {

    }

    @And("^I am a student$")
    public void iAmAStudent() throws Throwable {

    }

    @And("^the following todos exist in the system:$")
    public void theFollowingTodosExistInTheSystem() throws Throwable {

    }

    @And("^the following projects exist in the system:$")
    public void theFollowingProjectsExistInTheSystem() throws Throwable {

    }

    @And("^there does not exist a project in the system with title (.+)$")
    public void thereDoesNotExistAProjectInTheSystemWithTitle(String projecttitle) throws Throwable {

    }

    @And("^there does not exist a todo in the system with title (.+)$")
    public void thereDoesNotExistATodoInTheSystemWithTitle(String todotitle) throws Throwable {

    }

}
