import main.SymbolTable;
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
}