package ca.mcgill.ecse.group14.performance;

import ca.mcgill.ecse.group14.Resources;
import ca.mcgill.ecse.group14.Utils;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class TodosTest extends BaseTest {
    @Test
    public void test_AddTodoWithNoOtherObject() {
        Utils.clearData();
        logTransactionTime(Utils.createPopulatedTodo(), Resources.RequestMethod.POST);
    }

    @Test
    public void test_AddTodoWithOneOtherObject() {
        Utils.clearData();
        logTransactionTime(Utils.createPopulatedTodo(), Resources.RequestMethod.POST);
    }

    @Test
    public void test_AddTodosWhileIncreasingNumberOfTodos() {
        Utils.clearData();
        for (int i = 0; i< Resources.OBJECT_COUNT_INCREASE_PROGRESSION.length; i++) {
            Utils.createPopulatedTodos(Resources.OBJECT_COUNT_INCREASE_PROGRESSION[i]);
            logTransactionTime(Utils.createPopulatedTodo(), Resources.RequestMethod.POST);
        }
    }

    @Test
    public void test_DeleteTodoWithNoOtherObject() {
        Utils.clearData();
        logTransactionTime(Utils.deletePopulatedTodo(), Resources.RequestMethod.DELETE);
    }

    @Test
    public void test_DeleteTodoWithOneOtherObject() {
        Utils.clearData();
        Utils.createPopulatedTodo();
        logTransactionTime(Utils.deletePopulatedTodo(), Resources.RequestMethod.DELETE);
    }

    @Test
    public void test_DeleteTodoWhileIncreasingNumberOfTodos() {
        Utils.clearData();
        for (int i = 0; i< Resources.OBJECT_COUNT_INCREASE_PROGRESSION.length; i++) {
            Utils.createPopulatedTodos(Resources.OBJECT_COUNT_INCREASE_PROGRESSION[i]);
            logTransactionTime(Utils.deletePopulatedTodo(),  Resources.RequestMethod.DELETE);
        }
    }

    @Test
    public void test_ChangeTodoWithNoOtherObject() {
        Utils.clearData();
        logTransactionTime(Utils.changePopulatedTodo(), Resources.RequestMethod.PUT);
    }

    @Test
    public void test_ChangeTodoWithOneOtherObject() {
        Utils.clearData();
        Utils.createPopulatedTodo();
        logTransactionTime(Utils.changePopulatedTodo(), Resources.RequestMethod.PUT);
    }

    @Test
    public void test_ChangeTodoWhileIncreasingNumberOfTodos() {
        Utils.clearData();
        for (int i = 0; i< Resources.OBJECT_COUNT_INCREASE_PROGRESSION.length; i++) {
            Utils.createPopulatedTodos(Resources.OBJECT_COUNT_INCREASE_PROGRESSION[i]);
            logTransactionTime(Utils.changePopulatedTodo(), Resources.RequestMethod.PUT);
        }
    }
}
