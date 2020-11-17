package ca.mcgill.ecse.group14.acceptance;

import ca.mcgill.ecse.group14.Server;
import io.cucumber.java.en.Given;
import io.cucumber.junit.Cucumber;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(Cucumber.class)
public class CommonStepDefinitions extends BaseStepDefinitions {
    @Given("the Todo Manager Rest API server is running")
    public void the_todo_manager_rest_api_server_is_running() {
        assertEquals(0, Server.check());
    }
}
