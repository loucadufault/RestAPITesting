package ca.mcgill.ecse.group14.unit;

import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static ca.mcgill.ecse.group14.Resources.BASE_URL;
import static ca.mcgill.ecse.group14.Resources.STATUS_CODE;
import static io.restassured.RestAssured.given;
import static io.restassured.path.xml.XmlPath.CompatibilityMode.HTML;


public class DocsTest extends BaseTest {
    private static final String EXPECTED = "API Documentation";

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
