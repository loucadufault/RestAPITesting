package ca.mcgill.ecse.group14.acceptance;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import io.cucumber.junit.Cucumber;
import org.junit.runner.RunWith;

import java.util.List;
import java.util.Map;

import static ca.mcgill.ecse.group14.Utils.*;

@RunWith(Cucumber.class)
public class QueryIncompleteTasks extends BaseStepDefinitions {

    @When("the user attempts to query the incomplete tasks of the project with title {string}")
    public void the_user_attempts_to_query_the_incomplete_tasks_of_the_project_with_title(String title) {
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
                            counter++;


                }

            }
        }
        return;
    }
}
