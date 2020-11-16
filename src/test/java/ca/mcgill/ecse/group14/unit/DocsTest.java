package ca.mcgill.ecse.group14.unit;

import io.restassured.http.ContentType;
import org.junit.*;

import static ca.mcgill.ecse.group14.Resources.*;

import org.junit.Test;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

import static io.restassured.path.xml.XmlPath.CompatibilityMode.HTML;

import static io.restassured.RestAssured.*;


public class DocsTest {
    private static final String EXPECTED = "API Documentation";

    @BeforeClass
    public static void setup() {
        Server.start();
        Server.check();
    }

    @AfterClass
    public static void teardown() throws InterruptedException {
        Server.stop();
        Thread.sleep(500);
    }

    /*
    Test /docs loads the documentation
     */
    @Test
    public void testDocumentationLoaded(){
        given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .get(BASE_URL+"/docs")
                .then().assertThat()
                .statusCode(STATUS_CODE.OK);
    }

    /*
    Test that the body of the documentation is HTML
     */
    @Test
    public void testDocumentationHTML(){
        given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json");
        Response r = given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .get(BASE_URL+"/docs")
                .then()
                .contentType(ContentType.HTML)
                .extract()
                .response();
        XmlPath path = new XmlPath(HTML, r.getBody().asString());
        Assert.assertEquals(path.getString("html.head.title"), EXPECTED);
    }

}
