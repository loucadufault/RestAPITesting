package ca.mcgill.ecse.group14.performance;

import ca.mcgill.ecse.group14.Resources;
import ca.mcgill.ecse.group14.Utils;
import ca.mcgill.ecse.group14.acceptance.BaseStepDefinitions;
import org.junit.Test;

public class ProjectsTest extends BaseTest {

    @Test
    public void test_AddProjectsWhileIncreasingNumberOfProjects() {
        clearObjects();
        for (int i = 0; i< Resources.OBJECT_COUNT_INCREASE_PROGRESSION.length; i++) {
            Utils.createPopulatedProjects(Resources.OBJECT_COUNT_INCREASE_PROGRESSION[i]);
            objectCount += Resources.OBJECT_COUNT_INCREASE_PROGRESSION[i];
            logTransactionTime(Utils.createPopulatedProject());
            objectCount += 1;
        }
    }

    @Test
    public void test_DeleteProjectWhileIncreasingNumberOfProjects() {
        clearObjects();
        for (int i = 0; i< Resources.OBJECT_COUNT_INCREASE_PROGRESSION.length; i++) {
            Utils.createPopulatedProjects(Resources.OBJECT_COUNT_INCREASE_PROGRESSION[i]);
            objectCount += Resources.OBJECT_COUNT_INCREASE_PROGRESSION[i];
            logTransactionTime(Utils.deletePopulatedProject());
            objectCount -= 1;
        }
    }

    @Test
    public void test_ChangeProjectWhileIncreasingNumberOfProjects() {
        clearObjects();
        for (int i = 0; i< Resources.OBJECT_COUNT_INCREASE_PROGRESSION.length; i++) {
            Utils.createPopulatedProjects(Resources.OBJECT_COUNT_INCREASE_PROGRESSION[i]);
            objectCount += Resources.OBJECT_COUNT_INCREASE_PROGRESSION[i];
            logTransactionTime(Utils.changePopulateProject());
            objectCount += 1;
        }
    }
}
