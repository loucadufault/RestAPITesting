package ca.mcgill.ecse.group14.unit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import static org.junit.Assert.fail;

@RunWith(Suite.class)
@Suite.SuiteClasses({BaseTest.class, TodosTest.class, ProjectsTest.class, CategoriesTest.class, DocsTest.class, ShutDownTest.class})
public class TestRunner {
    @BeforeClass
    public static void setup() {
        Server.start();
        if (Server.check() != 0) {
            fail();
        }
    }

    @AfterClass
    public static void teardown() throws InterruptedException {
        Server.shutdown();
        Thread.sleep(500);
    }
}
