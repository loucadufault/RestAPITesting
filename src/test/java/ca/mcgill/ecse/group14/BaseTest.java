package ca.mcgill.ecse.group14;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class BaseTest {
    @Before
    public void setup() {
        Server.start();
        Server.check();
    }

    @After
    public void teardown() throws InterruptedException {
        Server.stop();
        Thread.sleep(500);
    }

    @Test
    public void test_ServerIsRunning() {

        given().
                when().
                get(Server.BASE_URL).
                then().
                assertThat().statusCode(200);
    }
}
