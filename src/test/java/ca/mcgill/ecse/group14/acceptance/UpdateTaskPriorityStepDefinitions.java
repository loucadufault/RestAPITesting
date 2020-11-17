package ca.mcgill.ecse.group14.acceptance;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import io.cucumber.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
public class UpdateTaskPriorityStepDefinitions {
    @When("the user attempts to update the priority to priority {string} of the todo with title {string}")
    public void the_user_attempts_to_update_the_priority_to_priority_of_the_todo_with_title(String priority, String todoTitle) {
        the_user_attempts_to_assign_priority_to_todo(priority,todoTitle);
    }

    @Then("the todo with title {string} has priority {string}")
    public void the_todo_with_title_has_priority(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Given("the todo with title {string} is not assigned to a priority")
    public void the_todo_with_title_is_not_assigned_to_a_priority(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

}
