import main.SymbolTable;
import operations.*;
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
        Push po = new Push("13");
        assertEquals(1, po.execute(0, testStack, table));
        assertEquals(13, testStack.pop());
        assertTrue(testStack.empty());
    }

    @Test
    void testPop() {
        assertTrue(testStack.empty());
        Pop pop = new Pop("x");
        Push push = new Push("123");
        assertEquals(1, push.execute(0, testStack, table));
        assertEquals(2, pop.execute(1, testStack, table));
    }

    @Test
    void testAdd() {
        assertTrue(testStack.empty());
        Push push5 = new Push("5");
        Push push6 = new Push("6");
        assertEquals(1, push5.execute(0, testStack, table));
        assertEquals(1, push6.execute(0, testStack, table));
        Addition add = new Addition();
        assertEquals(6, testStack.peek());
        assertEquals(1, add.execute(0, testStack, table));
        assertEquals(11, testStack.pop());
    }

    @Test
    void testSubtract() {
        assertTrue(testStack.empty());
        Push push25 = new Push("25");
        Push push7 = new Push("7");
        assertEquals(1, push25.execute(0, testStack, table));
        assertEquals(1, push7.execute(0, testStack, table));
        Subtraction sub = new Subtraction();
        assertEquals(7, testStack.peek());
        assertEquals(5, sub.execute(4, testStack, table));
        assertEquals(18, testStack.pop());
    }

    @Test
    void testMultiply() {
        assertTrue(testStack.empty());
        Push push3 = new Push("3");
        Push push4 = new Push("4");
        assertEquals(1, push3.execute(0, testStack, table));
        assertEquals(2, push4.execute(1, testStack, table));
        Multiplication mult = new Multiplication();
        assertEquals(4, testStack.peek());
        assertEquals(3, mult.execute(2, testStack, table));
        assertEquals(12, testStack.pop());
    }
}