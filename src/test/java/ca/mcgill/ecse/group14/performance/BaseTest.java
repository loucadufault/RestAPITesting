package ca.mcgill.ecse.group14.performance;

import ca.mcgill.ecse.group14.Resources;
import ca.mcgill.ecse.group14.Server;
import ca.mcgill.ecse.group14.Utils;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import java.util.*;

public class BaseTest {
    protected static final Logger logger = LogManager.getLogger();
    protected static final List<int[]> transactionTimeByObjectCount = new ArrayList<int[]>();
    protected static int objectCount = 0;

    @Rule
    public TestWatcher watchman = new TestWatcher() {
        @Override
        protected void starting(Description description) {
            logger.trace("Starting Test '{}' ...", description.getMethodName());
        }
        @Override
        protected void failed(Throwable e, Description description) {
            logger.error("'{}' Test '{}' failed with '{}': '{}'.", description.getClassName(), description.getMethodName(), e.getClass().getSimpleName(), e.getMessage().replaceAll("\\R", " ↵ "));
        }
    };

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
        for (int[] entry : transactionTimeByObjectCount) {
            String s = Arrays.toString(entry);
            s = s.substring(1,s.length()-1);
            System.out.println(s);
        }
        Utils.clearData();
    }

    protected static void logTransactionTime(Response response) {
//        int objectCount = Utils.countAllObjects(); // very expensive
        int transactionTime = (int) response.getTime();
        transactionTimeByObjectCount.add(new int[] {objectCount, transactionTime});
        logger.info("transaction took {} ms with {} total objects.", transactionTime, objectCount);
    }

    protected void clearObjects() {
        Utils.clearData();
        objectCount = 0;
    }
}
