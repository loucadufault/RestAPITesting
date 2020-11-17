package ca.mcgill.ecse.group14.acceptance;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import io.cucumber.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
public class CategorizeTaskPriorityStepDefinition {
    @Given("^the Todo Manager Rest API server is running$")
    public void theTodoManagerRestAPIServerIsRunning() throws Throwable {

    }

    @Given("^there exists a todo in the system with title (.+)$")
    public void thereExistsATodoInTheSystemWithTitle(String title) throws Throwable {

    }

    @Given("^there does not exist a todo in the system with title (.+)$")
    public void thereDoesNotExistATodoInTheSystemWithTitle(String title) throws Throwable {

    }

    @When("^I request to assign priority (.+) to todo (.+)$")
    public void iRequestToAssignPriorityToTodo(String priority, String title) throws Throwable {

    }

    @Then("^the todo (.+) will be assigned priority (.+)$")
    public void theTodoWillBeAssignedPriority(String title, String priority) throws Throwable {

    }

    @Then("^I receive an error code (.+)$")
    public void iReceiveAnErrorCode(String errorcode) throws Throwable {

    }

    @Then("^the priority of todo (.+) is updated from (.+) to (.+)$")
    public void thePriorityOfTodoIsUpdatedFromTo(String title, String priority1, String priority2) throws Throwable {

    }

    @And("^I am a student$")
    public void iAmAStudent() throws Throwable {

    }

    @And("^the following todos exist in the system:$")
    public void theFollowingTodosExistInTheSystem() throws Throwable {

    }

    @And("^the todo (.+) is assigned priority (.+)$")
    public void theTodoIsAssignedPriority(String title, String priority1) throws Throwable {

    }
}
