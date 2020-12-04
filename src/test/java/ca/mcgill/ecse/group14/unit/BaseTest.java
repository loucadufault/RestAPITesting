package ca.mcgill.ecse.group14.unit;

import ca.mcgill.ecse.group14.Resources;
import ca.mcgill.ecse.group14.Server;
import ca.mcgill.ecse.group14.Utils;
import io.restassured.RestAssured;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Rule;
import org.junit.rules.MethodRule;
import org.junit.rules.TestWatcher;
import org.junit.rules.TestWatchman;
import org.junit.runner.Description;
import org.junit.runners.model.FrameworkMethod;

public class BaseTest {
    static final boolean CI = Boolean.valueOf(System.getenv("CI"));
    protected static final Logger logger = LogManager.getLogger();
    private static long startTimeMillis;

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = Resources.BASE_URL;
        if (Server.ping() == 0) {
            return;
        }
        Server.start();
        Server.waitUntilReady();
    }

    @AfterClass
    public static void teardown() {
        Utils.clearData();
    }

    private static long getElapsedTime() {
        return System.currentTimeMillis() - startTimeMillis;
    }

    @Rule
    public TestWatcher watchman = new TestWatcher() {
        @Override
        protected void starting(Description description) {
            logger.trace("Starting Test '{}' ...", description.getMethodName());
            startTimeMillis = System.currentTimeMillis();
        }
        @Override
        protected void succeeded(Description description) {
            logger.info("Test '{}' succeeded in {} ms.", description.getMethodName(), getElapsedTime());
        }
        @Override
        protected void failed(Throwable e, Description description) {
            logger.error("Test '{}' failed with '{}': '{}' in {} ms.", description.getMethodName(), e.getClass().getSimpleName(), e.getMessage().replaceAll("\\R", " ↵ "), getElapsedTime());
        }
    };
}
