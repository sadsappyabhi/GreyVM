import main.SymbolTable;
import operations.PopOperation;
import operations.PushOperation;
import org.junit.jupiter.api.*;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;

class ArithmeticOperationTest {
    Stack<Integer> testStack;
    SymbolTable table;

    @BeforeEach
    void setUp() {
        testStack = new Stack<>();
        table = new SymbolTable();
    }

    @AfterEach
    void tearDown() {
        testStack.clear();
    }

    @Test
    void testPush() {
        assertTrue(testStack.empty());
        PushOperation po = new PushOperation("13");
        assertEquals(1, po.execute(0, testStack, table));
        assertEquals(13, testStack.pop());
        assertTrue(testStack.empty());
    }

    @Test
    void testPop() {
        assertTrue(testStack.empty());
        PopOperation pop = new PopOperation("x");
        PushOperation push = new PushOperation("123");
        assertEquals(1, push.execute(0, testStack, table));
        assertEquals(2, pop.execute(1, testStack, table));
    }
}