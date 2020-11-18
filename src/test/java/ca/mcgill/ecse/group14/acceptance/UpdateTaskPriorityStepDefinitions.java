package ca.mcgill.ecse.group14.acceptance;

import io.cucumber.java.en.Given;
import io.cucumber.junit.Cucumber;
import org.junit.runner.RunWith;

import java.util.List;
import java.util.Map;

import static ca.mcgill.ecse.group14.Utils.buildJSONRequest;

@RunWith(Cucumber.class)
public class UpdateTaskPriorityStepDefinitions {
    @Given("the todo with title {string} is not assigned to a priority")
    public void the_todo_with_title_is_not_assigned_to_a_priority(String todoId) {
        List<Map<String,String>> prior = buildJSONRequest()
                .get("/todos/"+todoId+"/categories")
                .jsonPath()
                .getList("categories");
        if (prior != null){
            for (Map<String,String> p : prior) {
                buildJSONRequest().delete("/todos/" + todoId + "/categories/"+p.get("id"));
            }
        }
    }

}
