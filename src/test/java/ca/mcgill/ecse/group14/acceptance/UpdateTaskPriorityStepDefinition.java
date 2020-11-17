package ca.mcgill.ecse.group14.acceptance;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import io.cucumber.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
public class UpdateTaskPriorityStepDefinition {
    @Given("^the Todo Manager Rest API server is running$")
    public void theTodoManagerRestAPIServerIsRunning() throws Throwable {

    }

    @Given("^there exists a todo in the system with title (.+)$")
    public void thereExistsATodoInTheSystemWithTitle(String title) throws Throwable {

    }

    @Given("^there does not exist a todo in the system with title (.+)$")
    public void thereDoesNotExistATodoInTheSystemWithTitle(String title) throws Throwable {

    }

    @When("^I request to update todo (.+) priority to priority (.+)$")
    public void iRequestToUpdateTodoPriorityToPriority(String title, String priority2) throws Throwable {

    }

    @Then("^the priority of todo (.+) is updated from (.+) to (.+)$")
    public void thePriorityOfTodoIsUpdatedFromTo(String title, String priority1, String priority2) throws Throwable {

    }

    @Then("^I recieve an error code (.+)$")
    public void iRecieveAnErrorCode(String errorcode) throws Throwable {

    }

    @Then("^the todo (.+) will be assigned priority (.+)$")
    public void theTodoIsAssignedPriority(String title, String priority) throws Throwable {

    }

    @And("^I am a student$")
    public void iAmAStudent() throws Throwable {

    }

    @And("^the following todos exist in the system:$")
    public void theFollowingTodosExistInTheSystem() throws Throwable {

    }

    @And("^the todo (.+) is not assigned to a priority$")
    public void theTodoIsNotAssignedToAPriority(String title) throws Throwable {

    }
}
