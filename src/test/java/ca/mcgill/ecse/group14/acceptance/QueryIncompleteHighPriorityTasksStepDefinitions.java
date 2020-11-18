package ca.mcgill.ecse.group14.acceptance;

import ca.mcgill.ecse.group14.Resources;
import ca.mcgill.ecse.group14.Utils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import org.json.simple.JSONObject;
import org.junit.runner.RunWith;

import java.util.List;
import java.util.Map;

import static ca.mcgill.ecse.group14.Utils.*;
import static org.junit.Assert.assertEquals;

@RunWith(Cucumber.class)
public class QueryIncompleteHighPriorityTasksStepDefinitions extends BaseStepDefinitions {
    @Given("the following priorities are in the system")
    public void the_following_priorities_are_in_the_system(io.cucumber.datatable.DataTable dataTable) {
        List<List<String>> rows = dataTable.asLists(String.class);

        boolean hasSeenTitleRow = false;
        for (List<String> row : rows) {
            if(hasSeenTitleRow) {
                JSONObject requestBody = new JSONObject();
                requestBody.put("title", row.get(0));
                requestBody.put("description", row.get(1));
                buildJSONRequest().body(requestBody.toJSONString()).post("/categories");
            }
            else{
                hasSeenTitleRow = true;
            }
        }
    }

    @Given("there exists the following projects in the system")
    public void there_exists_the_following_projects_in_the_system(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps();

        for (Map<String, String> row : rows) {
            JSONObject requestBody = new JSONObject();
            requestBody.put("title", row.get("title"));
            requestBody.put("completed", Boolean.valueOf(row.get("completed")));
            requestBody.put("active", Boolean.valueOf(row.get("active")));
            requestBody.put("description", row.get("description"));
            buildJSONRequest().body(requestBody.toJSONString()).post("/projects");
        }
    }

    @Given("there exists the following todos in the system that are saved under {string}")
    public void there_exists_the_following_todos_in_the_system_that_are_saved_under(String title, io.cucumber.datatable.DataTable dataTable) {
        List<List<String>> rows = dataTable.asLists(String.class);
        int projectId = getFirstId(title, "projects");
        boolean hasSeenTitleRow = false;
        for (List<String> row : rows) {
            if(hasSeenTitleRow) {
                JSONObject requestBody = new JSONObject();
                requestBody.put("title", row.get(0));
                requestBody.put("doneStatus", Boolean.valueOf(row.get(1)));
                requestBody.put("description", row.get(2));
                buildJSONRequest().body(requestBody.toJSONString()).post(Resources.BASE_URL+"/todos");

                int todoId = getFirstId(row.get(0), "todos");

                if(row.size()==4) {
                    requestBody = new JSONObject();
                    int categoryId = getFirstId(row.get(3), "categories");
                    requestBody.put("id",Integer.toString(categoryId));
                    buildJSONRequest().body(requestBody.toJSONString()).post(Resources.BASE_URL+"/todos/"+todoId+"/categories");
                }

                requestBody = new JSONObject();
                requestBody.put("id",Integer.toString(projectId));
                buildJSONRequest().body(requestBody.toJSONString()).post(Resources.BASE_URL+"/todos/"+todoId+"/tasksof");
            }
            else{
                hasSeenTitleRow = true;
            }
        }
    }

    @When("the user attempts to query the incomplete high priority tasks of the project with title {string}")
    public void the_user_attempts_to_query_the_incomplete_high_priority_tasks_of_the_project_with_title(String title) {
        counter = 0;

        int projectId = getFirstId(title, "projects");
        List<Map<String,String>> taskList = buildJSONRequestWithJSONResponse().when()
                .get("/projects/"+projectId+"/tasks")
                .jsonPath()
                .getList("todos");
        if (taskList == null)
            return;
        List<Map<String, String>> projectInfo = buildJSONRequest().when().get("/projects/"+projectId).jsonPath().getList("projects");
        if (projectInfo == null)
            return;

        if(projectInfo.get(0).get("active").equals("true")) {
            for (Map<String, String> thing : taskList) {
                if (thing.get("doneStatus").equals("false")) {
                    int todoId = getFirstId(thing.get("title"), "todos");
                    List<Map<String, String>> priorityList = buildJSONRequestWithJSONResponse().when()
                            .get("/todos/" + todoId + "/categories")
                            .jsonPath()
                            .getList("categories");
                    for (Map<String, String> prios : priorityList) {
                        if (prios.get("title").equals("HIGH")) {
                            counter++;
                        }
                    }
                }

            }
        }
    }

    @Then("{string} todos will be returned")
    public void todos_will_be_returned(String number) {
        assertEquals(Integer.parseInt(number), counter);
    }

    @Given("the project with title {string} has no active tasks")
    public void the_project_with_title_has_no_active_tasks(String title) {
        int id = getFirstId(title, "projects");
        List<Map<String,String>> taskList = buildJSONRequestWithJSONResponse().when()
                .get("/projects/"+id+"/tasks")
                .jsonPath()
                .getList("categories");

        if (taskList == null) {
            return;
        }
        for (Map<String, String> thing : taskList) {
            if (thing.get("todoDoneStatus").equals("true")) {
                Utils.removeTodo(thing.get("title"));
            }
        }
    }

    @Given("there does not exist the project {string} in the system")
    public void there_does_not_exist_the_project_in_the_system(String title) {
        if(existsProject(title)) {
            Utils.removeProject(title);
        }
    }
}