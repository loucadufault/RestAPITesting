package ca.mcgill.ecse.group14.acceptance;

import ca.mcgill.ecse.group14.Utils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import io.cucumber.java.en.Then;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.junit.runner.RunWith;

import java.util.List;
import java.util.Map;

import static ca.mcgill.ecse.group14.Resources.BASE_URL;
import static org.junit.Assert.*;

@RunWith(Cucumber.class)
public class CreateTodoListForClassStepDefinitions extends BaseStepDefinitions {
    @Given("there does not exist a project with title {string} in the system")
    public void there_does_not_exist_a_project_with_title_in_the_system(String title) {
        Utils.removeProject(title);
    }

    @When("the user attempts to create a new project with title {string} and description {string} and completed status {string} and active status {string}")
    public void the_user_attempts_to_create_a_new_project_with_title_and_description_and_completed_status_and_active_status(String title, String description, String completed, String active) {
        counter = Utils.countProjects();
        Utils.createProject(title, description, completed, active);
    }

    @Then("there shall be one more project in the system")
    public void there_shall_be_one_more_project_in_the_system() {
        assertEquals(counter + 1, Utils.countProjects());
    }

    @When("the user attempts to create a new project without specifying any fields")
    public void the_user_attempts_to_create_a_new_project_without_specifying_any_fields() {
        Utils.createProject();
    }

    @When("the user attempts to create a new project with title {string} and id {string}")
    public void the_user_attempts_to_create_a_new_project_with_title_and_id(String title, String id) {
        JSONObject requestBody = new JSONObject();
        requestBody.put("title", title);
        requestBody.put("id", Integer.parseInt(id));

        Utils.buildJSONRequestWithJSONResponse().body(requestBody.toJSONString()).post(BASE_URL + "/projects");
    }

    @Then("the project with title {string} and id {string} shall not be created in the system")
    public void the_project_with_title_and_id_shall_not_be_created_in_the_system(String title, String id) {
        assertTrue(!Utils.existsProject(title));
        assertTrue(!Utils.existsProject(id));
    }

    @Then("there shall be the same number of projects in the system")
    public void there_shall_be_the_same_number_of_projects_in_the_system() {
        assertEquals(counter, Utils.countProjects());
    }

    @Then("the project with title {string} and description {string} and completed status {string} and active status {string} shall be created in the system")
    public void the_project_with_title_and_description_and_completed_status_and_active_status_shall_be_created_in_the_system(String title, String description, String completed, String active) {
        Response response = Utils.buildJSONRequestWithJSONResponse().when().get(BASE_URL + "/projects");
        List<Map<String, String>> projects = response.jsonPath().getList("projects");
        for (Map<String, String> project : projects) {
            if (project.get("title").equals(title) && project.get("description").equals(description) && project.get("completed").equals(completed) && project.get("active").equals(active)) {
                return;
            }
        }
        fail();
    }
}
