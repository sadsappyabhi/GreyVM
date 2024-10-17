package main;

import operations.Addition;
import operations.Operation;
import operations.Pop;
import operations.Push;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;

class SymbolTableTest {

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
    void testConstructor() {
        assertNotNull(table);
    }

    @Test
    void getAndSetValue() {
        table.setValue("x", 0);
        assertEquals(0, table.getValue("x"));
        table.setValue("x", 2);
        assertEquals(2, table.getValue("x"));
    }

    @Test
    void testException() {
        table.setValue("x", 0);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            table.getValue("y");
        });
    }


    @Test
    void testPop() {
        List<Operation> operations = new ArrayList<>();
        operations.add(new Push("5"));
        operations.add(new Push("7"));
        operations.add(new Addition());
        operations.add(new Pop("x"));
        operations.add(new Push("x"));

        for (Operation operation : operations) {
            operation.execute(0, testStack, table);
        }

        assertEquals(12, testStack.pop());
    }
    @Test
    void contains() {
        table.setValue("x", 23);
        assertTrue(table.contains("x"));
        assertFalse(table.contains("y"));
        assertEquals(23, table.getValue("x"));
    }
}