package ca.mcgill.ecse.group14.acceptance;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import io.cucumber.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
public class ChangeTaskDescriptionStepDefinitions {

    @Given("the following todos exist in the system")
    public void the_following_todos_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
        // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
        // Double, Byte, Short, Long, BigInteger or BigDecimal.
        //
        // For other transformations you can register a DataTableType.
        throw new io.cucumber.java.PendingException();
    }

    @Given("there exists a todo with title {string} and description {string} in the system")
    public void there_exists_a_todo_with_title_and_description_in_the_system(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("the user attempts to change the description to \"\"Chapters {int} to {int}\"\" for the todo with title {string}")
    public void the_user_attempts_to_change_the_description_to_chapters_to_for_the_todo_with_title(Integer int1, Integer int2, String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("the todo with title {string} shall have description \"\"Chapters {int} to {int}\"\"")
    public void the_todo_with_title_shall_have_description_chapters_to(String string, Integer int1, Integer int2) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("the user attempts to change the description to \"\"On unit testing\"\" for the todo with title {string}")
    public void the_user_attempts_to_change_the_description_to_on_unit_testing_for_the_todo_with_title(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("the todo with title {string} shall have description \"\"On unit testing\"\"")
    public void the_todo_with_title_shall_have_description_on_unit_testing(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Given("there exists a todo with title {string} and description \"\"Use Gherkin!{string} in the system")
    public void there_exists_a_todo_with_title_and_description_use_gherkin_in_the_system(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("the user attempts to change the description to \"\"Use Cucumber!{string} for the todo with title {string}")
    public void the_user_attempts_to_change_the_description_to_use_cucumber_for_the_todo_with_title(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("the todo with title {string} shall have description \"\"Use Cucumber!{string}")
    public void the_todo_with_title_shall_have_description_use_cucumber(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Given("there exists a todo with title {string} and description \"\"Very hard midterm!{string} in the system")
    public void there_exists_a_todo_with_title_and_description_very_hard_midterm_in_the_system(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("the user attempts to change the description to \"\"Study earlier!{string} for the todo with title {string}")
    public void the_user_attempts_to_change_the_description_to_study_earlier_for_the_todo_with_title(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("the todo with title {string} shall have description \"\"Study earlier!{string}")
    public void the_todo_with_title_shall_have_description_study_earlier(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("the user attempts to change the description to {string} for the todo with title {string}")
    public void the_user_attempts_to_change_the_description_to_for_the_todo_with_title(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
}