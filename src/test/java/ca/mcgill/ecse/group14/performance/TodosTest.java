package ca.mcgill.ecse.group14.performance;

import ca.mcgill.ecse.group14.Resources;
import ca.mcgill.ecse.group14.Utils;
import org.junit.Test;

public class TodosTest extends BaseTest {
    @Test
    public void test_AddTodoWithNoOtherObject() {
        clearObjects();
        logTransactionTime(Utils.createPopulatedTodo());
        objectCount += 1;
    }

    @Test
    public void test_AddTodoWithOneOtherObject() {
        clearObjects();
        Utils.createPopulatedTodo();
        objectCount += 1;
        logTransactionTime(Utils.createPopulatedTodo());
        objectCount += 1;
    }

    @Test
    public void test_AddTodosWhileIncreasingNumberOfTodos() {
        clearObjects();
        for (int i = 0; i< Resources.OBJECT_COUNT_INCREASE_PROGRESSION.length; i++) {
            Utils.createPopulatedTodos(Resources.OBJECT_COUNT_INCREASE_PROGRESSION[i]);
            objectCount += Resources.OBJECT_COUNT_INCREASE_PROGRESSION[i];
            logTransactionTime(Utils.createPopulatedTodo());
            objectCount += 1;
        }
    }

    @Test
    public void test_DeleteTodoWithNoOtherObject() {
        clearObjects();
        logTransactionTime(Utils.deletePopulatedTodo());
        objectCount -= 1;
    }

    @Test
    public void test_DeleteTodoWithOneOtherObject() {
        clearObjects();
        Utils.createPopulatedTodo();
        objectCount += 1;
        logTransactionTime(Utils.deletePopulatedTodo());
        objectCount -= 1;
    }

    @Test
    public void test_DeleteTodoWhileIncreasingNumberOfTodos() {
        clearObjects();
        for (int i = 0; i< Resources.OBJECT_COUNT_INCREASE_PROGRESSION.length; i++) {
            Utils.createPopulatedTodos(Resources.OBJECT_COUNT_INCREASE_PROGRESSION[i]);
            objectCount += Resources.OBJECT_COUNT_INCREASE_PROGRESSION[i];
            logTransactionTime(Utils.deletePopulatedTodo());
            objectCount -= 1;
        }
    }

    @Test
    public void test_ChangeTodoWithNoOtherObject() {
        clearObjects();
        logTransactionTime(Utils.changePopulatedTodo());
    }

    @Test
    public void test_ChangeTodoWithOneOtherObject() {
        clearObjects();
        Utils.createPopulatedTodo();
        objectCount += 1;
        logTransactionTime(Utils.changePopulatedTodo());
    }

    @Test
    public void test_ChangeTodoWhileIncreasingNumberOfTodos() {
        clearObjects();
        for (int i = 0; i< Resources.OBJECT_COUNT_INCREASE_PROGRESSION.length; i++) {
            Utils.createPopulatedTodos(Resources.OBJECT_COUNT_INCREASE_PROGRESSION[i]);
            objectCount += Resources.OBJECT_COUNT_INCREASE_PROGRESSION[i];
            logTransactionTime(Utils.changePopulatedTodo());
            objectCount += 1;
        }
    }
}
