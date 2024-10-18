package operations;

import main.SymbolTable;
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

    }
}