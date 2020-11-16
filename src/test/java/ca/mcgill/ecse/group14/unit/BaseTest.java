package ca.mcgill.ecse.group14.unit;

import org.junit.*;

import static io.restassured.RestAssured.*;

import static ca.mcgill.ecse.group14.unit.Resources.*;

public class BaseTest {
    @Test
    public void test_ServerIsRunning() {
        given().
                when().
                get(BASE_URL).
                then().
                assertThat().statusCode(STATUS_CODE.OK);
    }
}
