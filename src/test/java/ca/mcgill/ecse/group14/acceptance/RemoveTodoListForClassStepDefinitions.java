package ca.mcgill.ecse.group14.acceptance;

import ca.mcgill.ecse.group14.Utils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import io.restassured.response.Response;
import org.junit.runner.RunWith;

import java.util.List;
import java.util.Map;

import static ca.mcgill.ecse.group14.Resources.BASE_URL;
import static org.junit.Assert.assertTrue;

@RunWith(Cucumber.class)
public class RemoveTodoListForClassStepDefinitions extends BaseStepDefinitions {
    @Given("the project with title {string} exists in the system")
    public void the_project_with_title_exists_in_the_system(String title) {
        Utils.createProject(title);
    }

    @Given("the project with title {string} has no related tasks")
    public void the_project_with_title_has_no_related_tasks(String title) {
        int id = Utils.getFirstId(title, "projects");
        Response response = Utils.buildJSONRequestWithJSONResponse().when().get("/projects/" + String.valueOf(id) + "/tasks");
        List<Map<String, String>> tasks = response.jsonPath().getList("tasks");
        if (tasks == null) {
            return;
        }
        for (Map<String, String> task: tasks) {
            Utils.deleteTask(Integer.parseInt(task.get("id")));
        }
//        System.out.println(Utils.buildJSONRequestWithJSONResponse().when().get("/projects/" + String.valueOf(id) + "/tasks").jsonPath().get("tasks").toString());
    }

    @When("the user attempts to delete the project with title {string}")
    public void the_user_attempts_to_delete_the_project_with_title(String title) {
        counter = Utils.countProjects();
        Utils.removeProject(title);
    }

    @Then("the project with title {string} shall be removed from the system")
    public void the_project_with_title_shall_be_removed_from_the_system(String title) {
        assertTrue(!Utils.existsProject(title));
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
