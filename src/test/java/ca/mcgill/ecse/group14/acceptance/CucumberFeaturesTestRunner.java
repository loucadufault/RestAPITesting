package ca.mcgill.ecse.group14.acceptance;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = "pretty",
        features = "src/test/resources",
        glue = "ca.mcgill.ecse.group14.acceptance"
)
public class CucumberFeaturesTestRunner {
}
