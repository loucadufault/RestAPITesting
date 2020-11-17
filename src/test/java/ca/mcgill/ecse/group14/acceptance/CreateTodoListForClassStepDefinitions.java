package ca.mcgill.ecse.group14.acceptance;

import ca.mcgill.ecse.group14.Utils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.java.en.Then;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
public class CreateTodoListForClassStepDefinitions {
    @Given("there does not exist a project with title {string} in the system")
    public void there_does_not_exist_a_project_with_title_in_the_system(String title) {
        Utils.removeProject(string);
    }

    @When("the user attempts to create a new project with title {string} and description {string} and completed status {string} and active status {string}")
    public void the_user_attempts_to_create_a_new_project_with_title_and_description_and_completed_status_and_active_status(String string, String string2, String string3, String string4) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("the project with title {string} and description {string} and completed status <completedStatus> and active status <activeStatus> shall be created in the system")
    public void the_project_with_title_and_description_and_completed_status_completed_status_and_active_status_active_status_shall_be_created_in_the_system(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("there shall be one more project in the system")
    public void there_shall_be_one_more_project_in_the_system() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Given("there does not exist a project with title COMP {int} in the system")
    public void there_does_not_exist_a_project_with_title_comp_in_the_system(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Given("there does not exist a project with title MATH {int} in the system")
    public void there_does_not_exist_a_project_with_title_math_in_the_system(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("the user attempts to create a new project without specifying any fields")
    public void the_user_attempts_to_create_a_new_project_without_specifying_any_fields() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("the user attempts to create a new project with title {string} and id {string}")
    public void the_user_attempts_to_create_a_new_project_with_title_and_id(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("the project with title {string} and id {string} shall not be created in the system")
    public void the_project_with_title_and_id_shall_not_be_created_in_the_system(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("there shall be the same number of projects in the system")
    public void there_shall_be_the_same_number_of_projects_in_the_system() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
}
