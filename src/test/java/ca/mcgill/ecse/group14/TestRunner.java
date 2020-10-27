package ca.mcgill.ecse.group14;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({BaseTest.class, TodosTest.class, ProjectsTest.class, CategoriesTest.class, DocsTest.class, ShutDownTest.class})
public class TestRunner {
    @BeforeClass
    public static void setup() {
        Server.start();
        Server.check();
    }

    @AfterClass
    public static void teardown() throws InterruptedException {
        Server.shutdown();
        Thread.sleep(500);
    }
}
