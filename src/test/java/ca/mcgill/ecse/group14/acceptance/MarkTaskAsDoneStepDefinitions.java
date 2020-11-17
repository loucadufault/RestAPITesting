package ca.mcgill.ecse.group14.acceptance;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import io.cucumber.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
public class MarkTaskAsDoneStepDefinitions {

    @Given("the following todos exist in the system:")
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

    @Given("there exists a todo with title {string} and done status {string} in the system")
    public void there_exists_a_todo_with_title_and_done_status_in_the_system(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("user attempts to set done status to {string} for the todo with title {string}")
    public void user_attempts_to_set_done_status_to_for_the_todo_with_title(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("the todo with title {string} shall have done status {string}")
    public void the_todo_with_title_shall_have_done_status(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("the user attempts to set done status to {string} for the todo with title {string}")
    public void the_user_attempts_to_set_done_status_to_for_the_todo_with_title(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("todo with title {string} shall have done status {string}")
    public void todo_with_title_shall_have_done_status(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("the system shall report thee error code {string}")
    public void the_system_shall_report_thee_error_code(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

}