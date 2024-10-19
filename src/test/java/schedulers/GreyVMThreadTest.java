package schedulers;

import main.SymbolTable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.StringReader;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class GreyVMThreadTest {
    GreyVMThread vm;
    Scanner scanner;
    SymbolTable varTable;
    SymbolTable branchTable;

    @BeforeEach
    void setUp() {

        varTable = new SymbolTable();
        branchTable = new SymbolTable();
    }

    @Test
    void simpleTest() throws IOException {
        scanner = new Scanner(new StringReader(
                "push 5\n"
                        + "push 7\n"
                        + "add\n"
                        + "pop x\n"
                        + "push x\n"
                        + "push 3\n"
                        + "add\n"
                        + "pop y\n"
        ));
        vm = new GreyVMThread(scanner, varTable, branchTable);
        vm.run();
        assertEquals(12, vm.getValue("x"));
        assertEquals(15, vm.getValue("y"));
    }

    @Test
    void testComplicatedExample() throws IOException {
        scanner = new Scanner(new StringReader(
                "push 0\n" +
                        "pop sum\n" +
                        "push 0\n" +
                        "pop count\n" +
                        "loop: push count\n" +
                        "push 100\n" +
                        "compareGT\n" +
                        "branchT end\n" +
                        "push sum\n" +
                        "push count\n" +
                        "add\n" +
                        "pop sum\n" +
                        "push count\n" +
                        "push 1\n" +
                        "add\n" +
                        "pop count\n" +
                        "branch loop\n" +
                        "end: nop\n"
        ));

        vm = new GreyVMThread(scanner, varTable, branchTable);
        vm.run();
    }

    @Test
    void testPushPop() throws Exception {
        Scanner reader = new Scanner(new StringReader(
                "push 15\n"
                        + "push 7\n"
                        + "pop variable\n"
                        + "pop y\n"
        ));

        GreyVMThread vm = new GreyVMThread(reader, varTable, branchTable);
        vm.run();
        assertEquals(7, vm.getValue("variable"));
        assertEquals(15, vm.getValue("y"));
    }

    @Test
    public void testPushPopGiven()
    {
        /** #score(4) */
        GreyVMThread svm = null;
        try
        {
            svm = new GreyVMThread(
                    new Scanner(
                            "push 12\n" +
                                    "pop a\n" +
                                    "push 1\n" +
                                    "push 2\n" +
                                    "push 3\n" +
                                    "pop d\n" +
                                    "pop c\n" +
                                    "pop b\n" +
                                    "push 13\n" +
                                    "pop longVar\n"),
                    varTable,
                    branchTable);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            fail("Exception thrown by constructor: " + e);
        }
        svm.run();
        assertEquals(12, svm.getValue("a"));
        assertEquals(1, svm.getValue("b"));
        assertEquals(2, svm.getValue("c"));
        assertEquals(3, svm.getValue("d"));
        assertEquals(13, svm.getValue("longVar"));
    }
}