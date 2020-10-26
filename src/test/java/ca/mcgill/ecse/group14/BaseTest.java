package ca.mcgill.ecse.group14;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class BaseTest {
    @Test
    public void test_NumberOfCircuitsFor2017Season_ShouldBe20() {

        given().
                when().
                get("http://ergast.com/api/f1/2017/circuits.json").
                then().
                assertThat().
                body("MRData.CircuitTable.Circuits.circuitId",hasSize(20));
    }

    @Before
    public void startServer() {
        Server.start();

        boolean started = false;
        while (!started) {
            try {
                Server.check();
                started = true;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @After
    public void stopServer() throws InterruptedException {
        Server.stop();
        Thread.sleep(500);
    }
}
