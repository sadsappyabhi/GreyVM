package operations;

import main.SymbolTable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;

class PushPopTest {

    private Stack<Integer> stack;
    private SymbolTable table;

    @BeforeEach
    void setUp() {
        stack = new Stack<>();
        table = new SymbolTable();
    }

    @Test
    void testPushPop() {
        assertTrue(stack.empty());
        Push push5 = new Push("5");
        Push push7 = new Push("7");

        push5.execute(0, stack, table);
        assertEquals(5, stack.peek());

        push7.execute(0, stack, table);
        assertEquals(7, stack.peek());

        Pop popToX = new Pop("x");
        Pop popToY = new Pop("y");

        popToX.execute(0, stack, table);
        assertEquals(7, table.getValue("x"));
        popToY.execute(0, stack, table);
        assertEquals(5, table.getValue("y"));
    }
}