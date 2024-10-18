package operations;

import main.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;

class CompareOperationTest {

    Stack<Integer> stack;
    SymbolTable table;
    Push push5;
    Push push10;
    Push push5again;
    Push push15;

    @BeforeEach
    void setUp() {
       stack = new Stack<>();
       table = new SymbolTable();
       assertTrue(stack.empty());
       push5 = new Push("5");
       push10 = new Push("10");
       push5again = new Push("5");
       push15 = new Push("15");
    }

    @AfterEach
    void tearDown() {
        stack.clear();
    }

    @Test
    void testCompareEq() {
        assertEquals(1, push5.execute(0, stack, table));
        assertEquals(2, push10.execute(1, stack, table));
        EQ equals = new EQ();
        assertEquals(3, equals.execute(2, stack, table));
        assertEquals(0, stack.pop());
        assertEquals(4, push5.execute(3, stack, table));
        assertEquals(5, push5again.execute(4, stack, table));
        assertEquals(6, equals.execute(5, stack, table));
        assertEquals(1, stack.pop());
    }

    @Test
    void testCompareNeq() {
        assertEquals(1, push5.execute(0, stack, table));
        assertEquals(2, push10.execute(1, stack, table));
        NEQ notEquals = new NEQ();
        assertEquals(3, notEquals.execute(2, stack, table));
        assertEquals(1, stack.pop());
        assertEquals(4, push5.execute(3, stack, table));
        assertEquals(5, push5again.execute(4, stack, table));
        assertEquals(6, notEquals.execute(5, stack, table));
        assertEquals(0, stack.pop());
    }

    @Test
    void testCompareLt() {
        assertEquals(1, push5.execute(0, stack, table));
        assertEquals(2, push10.execute(1, stack, table));
        LT lessThan = new LT();
        assertEquals(3, lessThan.execute(2, stack, table));
        assertEquals(1, stack.pop());
        assertEquals(4, push5.execute(3, stack, table));
        assertEquals(5, push5again.execute(4, stack, table));
        assertEquals(6, lessThan.execute(5, stack, table));
        assertEquals(0, stack.pop());
    }
}