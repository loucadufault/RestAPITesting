package ca.mcgill.ecse.group14.performance;

import ca.mcgill.ecse.group14.Resources;
import ca.mcgill.ecse.group14.Utils;
import org.junit.Test;

public class CategoriesTest extends BaseTest {
    @Test
    public void test_AddCategoryWithNoOtherObject() {
        clearObjects();
        logTransactionTime(Utils.createPopulatedCategory());
        objectCount += 1;
    }

    @Test
    public void test_AddCategoryWithOneOtherObject() {
        clearObjects();
        Utils.createPopulatedCategory();
        objectCount += 1;
        logTransactionTime(Utils.createPopulatedCategory());
        objectCount += 1;
    }

    @Test
    public void test_AddCategoryWhileIncreasingNumberOfCategories() {
        clearObjects();
        for (int i = 0; i< Resources.OBJECT_COUNT_INCREASE_PROGRESSION.length; i++) {
            Utils.createPopulatedCategories(Resources.OBJECT_COUNT_INCREASE_PROGRESSION[i]);
            objectCount += Resources.OBJECT_COUNT_INCREASE_PROGRESSION[i];
            logTransactionTime(Utils.createPopulatedCategory());
            objectCount += 1;
        }
    }

    @Test
    public void test_DeleteCategoryWithNoOtherObject() {
        clearObjects();
        logTransactionTime(Utils.deletePopulateCategory());
        objectCount -= 1;
    }

    @Test
    public void test_DeleteCategoryWithOneOtherObject() {
        clearObjects();
        Utils.createPopulatedCategory();
        objectCount += 1;
        logTransactionTime(Utils.deletePopulateCategory());
        objectCount -= 1;
    }

    @Test
    public void test_DeleteCategoryWhileIncreasingNumberOfCategories() {
        clearObjects();
        for (int i = 0; i< Resources.OBJECT_COUNT_INCREASE_PROGRESSION.length; i++) {
            Utils.createPopulatedCategories(Resources.OBJECT_COUNT_INCREASE_PROGRESSION[i]);
            objectCount += Resources.OBJECT_COUNT_INCREASE_PROGRESSION[i];
            logTransactionTime(Utils.deletePopulateCategory());
            objectCount -= 1;
        }
    }

    @Test
    public void test_ChangeCategoryWithNoOtherObject() {
        clearObjects();
        logTransactionTime(Utils.changePopulatedCategory());
    }

    @Test
    public void test_ChangeCategoryWithOneOtherObject() {
        clearObjects();
        Utils.createPopulatedCategory();
        objectCount += 1;
        logTransactionTime(Utils.changePopulatedCategory());
    }

    @Test
    public void test_ChangeCategoryWhileIncreasingNumberOfCategories() {
        clearObjects();
        for (int i = 0; i< Resources.OBJECT_COUNT_INCREASE_PROGRESSION.length; i++) {
            Utils.createPopulatedCategories(Resources.OBJECT_COUNT_INCREASE_PROGRESSION[i]);
            objectCount += Resources.OBJECT_COUNT_INCREASE_PROGRESSION[i];
            logTransactionTime(Utils.changePopulatedCategory());
            objectCount += 1;
        }
    }
}
