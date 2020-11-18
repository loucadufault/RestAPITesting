package ca.mcgill.ecse.group14.acceptance;

import ca.mcgill.ecse.group14.Utils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.junit.runner.RunWith;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(Cucumber.class)
public class RemoveTodoListForClassStepDefinitions extends BaseStepDefinitions {
    @Given("there exists a project with title {string} in the system")
    public void there_exists_a_project_with_title_in_the_system(String title) {
        if (!Utils.existsProject(title)) {
            Utils.createProject(title);
        }
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
            Utils.deleteTodo(Integer.parseInt(task.get("id")));
        }
    }

    @When("the user attempts to delete the project with title {string}")
    public void the_user_attempts_to_delete_the_project_with_title(String title) {
        projectCount = Utils.countProjects();
        todoCount = Utils.countTodos();
        Utils.removeProject(title);
    }

    @Then("the project with title {string} shall be removed from the system")
    public void the_project_with_title_shall_be_removed_from_the_system(String title) {
        assertTrue(!Utils.existsProject(title));
    }

    @Then("there shall be one less project in the system")
    public void there_shall_be_less_project_in_the_system() {
        assertEquals(projectCount - 1, Utils.countProjects());
    }

    @Given("the project with title {string} has the related task with title {string} and done status {string} and description {string}")
    public void the_project_with_title_has_the_related_task_with_title_and_done_status_and_description(String projectTitle, String taskTitle, String taskDoneStatus, String taskDescription) {
        int id = Utils.getFirstId(projectTitle, "projects");
        Response response;
        response = Utils.buildJSONRequestWithJSONResponse().when().get("/projects/" + String.valueOf(id) + "/tasks");
        List<Map<String, String>> projectTasks = response.jsonPath().getList("tasks");
        if (projectTasks != null) {
            for (Map<String, String> task : projectTasks) {
                if (task.get("title").equals(taskTitle) && task.get("description").equals(taskDescription) && task.get("doneStatus").equals(taskDoneStatus)) {
                    return;
                }
            }
        }

        int taskId = -1;
        response = Utils.buildJSONRequestWithJSONResponse().when().get("/" + "todos");
        List<Map<String, String>> tasks = response.jsonPath().getList("todos");
        if (tasks != null) {
            for (Map<String, String> task : tasks) {
                if (task.get("title").equals(taskTitle) && task.get("description").equals(taskDescription) && task.get("doneStatus").equals(taskDoneStatus)) {
                    taskId = Integer.parseInt(task.get("id"));
                }
            }
        }

        if (taskId == -1) {
            JSONObject requestBody = new JSONObject();
            requestBody.put("title", taskTitle);
            requestBody.put("doneStatus", Boolean.valueOf(taskDoneStatus));
            requestBody.put("description", taskDescription);

            response = Utils.buildJSONRequestWithJSONResponse().body(requestBody.toJSONString()).post("/todos");
            taskId = Integer.parseInt(response.jsonPath().get("id"));
        }

        JSONObject requestBody = new JSONObject();
        requestBody.put("id", taskId);
        Utils.buildJSONRequest().body(requestBody.toJSONString()).post();
    }

    @When("the user attempts to remove the project with title {string}")
    public void the_user_attempts_to_remove_the_project_with_title(String title) {
        todoCount = Utils.countTodos();
        projectCount = Utils.countProjects();
        int id = Utils.getFirstId(title, "projects");
        errorCode = Utils.buildJSONRequestWithJSONResponse().when().delete("/projects/" + id).getStatusCode();
    }

    @Then("the todo with title {string} shall not be removed from the system")
    public void the_todo_with_title_shall_not_be_removed_from_the_system(String title) {
        assertTrue(Utils.existsTodo(title));
    }
}
