package ca.mcgill.ecse.group14.acceptance;
import ca.mcgill.ecse.group14.Resources;
import ca.mcgill.ecse.group14.Utils;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import io.cucumber.junit.Cucumber;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.junit.runner.RunWith;

import java.util.List;

@RunWith(Cucumber.class)
public class RemoveTaskStepDefinitions extends BaseStepDefinitions{

    @Given("I am a student")
    public void i_am_a_student() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Given("the following projects exist in the system")
    public void the_following_projects_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
        // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
        // Double, Byte, Short, Long, BigInteger or BigDecimal.
        //
        // For other transformations you can register a DataTableType.
        throw new io.cucumber.java.PendingException();
    }

    @Given("the following todos are assigned to the project {string}")
    public void the_following_todos_are_assigned_to_the_project(String string, io.cucumber.datatable.DataTable dataTable) {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
        // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
        // Double, Byte, Short, Long, BigInteger or BigDecimal.
        //
        // For other transformations you can register a DataTableType.
        throw new io.cucumber.java.PendingException();
    }

    @When("the user attempts to remove todo with title {string} from the system")
    public void the_user_attempts_to_remove_todo_with_title_from_the_system(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("todo with title {string} shall be removed from the system")
    public void todo_with_title_shall_be_removed_from_the_system(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Given("the todo with title {string} is assigned to a project with title {string}")
    public void the_todo_with_title_is_assigned_to_a_project_with_title(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("I request to remove the todo with title {string} from the project with title {string}")
    public void i_request_to_remove_the_todo_with_title_from_the_project_with_title(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("the user attempts to remove the todo with title {string} from the project with title {string}")
    public void the_user_attempts_to_remove_the_todo_with_title_from_the_project_with_title(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("there shall be the same number of todos in the system")
    public void there_shall_be_the_same_number_of_todos_in_the_system() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

}