package ca.mcgill.ecse.group14.acceptance;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
public class RemoveTodoListForClassStepDefinitions {
    @Given("the project with title {string} exists in the system")
    public void the_project_with_title_exists_in_the_system(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Given("the project with title {string} has no related tasks")
    public void the_project_with_title_has_no_related_tasks(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("the user attempts to delete the project with title {string}")
    public void the_user_attempts_to_delete_the_project_with_title(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("the project with title {string} shall be removed from the system")
    public void the_project_with_title_shall_be_removed_from_the_system(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("there shall be {int} less project in the system")
    public void there_shall_be_less_project_in_the_system(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("the the number of tasks in the system shall remain the same")
    public void the_the_number_of_tasks_in_the_system_shall_remain_the_same() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Given("the project with title {string} has the related todo with title {string} and done status {string} and description {string}")
    public void the_project_with_title_has_the_related_todo_with_title_and_done_status_and_description(String string, String string2, String string3, String string4) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("the user attempts to remove the project with title {string}")
    public void the_user_attempts_to_remove_the_project_with_title(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("the task with title {string} shall be removed from the system")
    public void the_task_with_title_shall_be_removed_from_the_system(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("there shall be {int} less todo in the system")
    public void there_shall_be_less_todo_in_the_system(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Given("the project with title {string} does not exist in the system")
    public void the_project_with_title_does_not_exist_in_the_system(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("the system shall report {string}")
    public void the_system_shall_report(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
}
