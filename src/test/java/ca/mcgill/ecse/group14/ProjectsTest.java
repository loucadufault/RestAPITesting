package ca.mcgill.ecse.group14;

import org.json.simple.JSONObject;
import org.junit.Test;

import static ca.mcgill.ecse.group14.Resources.*;
import static ca.mcgill.ecse.group14.TestUtils.assertGETStatusCode;

public class ProjectsTest {
    @Test
    public void testGetProjectStatusCode()
    {
        assertGETStatusCode(BASE_URL + "/projects", STATUS_CODE.OK);
    }

    JSONObject requestParams = new JSONObject();
}
