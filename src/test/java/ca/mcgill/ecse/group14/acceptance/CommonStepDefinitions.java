package ca.mcgill.ecse.group14.acceptance;

import ca.mcgill.ecse.group14.Server;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.junit.Cucumber;
import org.junit.runner.RunWith;

import java.net.ConnectException;

import static org.junit.Assert.assertEquals;

@RunWith(Cucumber.class)
public class CommonStepDefinitions extends BaseStepDefinitions {
    @Before
    public static void before() {
        setup();
    }

    @After
    public static void after() throws InterruptedException {
        teardown();
    }

    @Given("the Todo Manager Rest API server is running")
    public void the_todo_manager_rest_api_server_is_running() {
        assertEquals(0, Server.ping());
    }
}
