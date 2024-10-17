package operations;

import main.SymbolTable;

import java.util.Stack;

/**
 * Represents a simple arithmetic operation
 *
 * @author Abhilasha Chebolu
 * @version Summer/Fall 2024
 */
public abstract class ArithmeticOperation implements Operation {
    protected int a, b;

    public ArithmeticOperation() {}

    public void populateOperation(Stack<Integer> stack) {
        b = stack.pop();
        a = stack.pop();
    }

    abstract void performOperation(Stack<Integer> stack);

    @Override
    public int execute(int programCounter, Stack<Integer> stack, SymbolTable table) {
        populateOperation(stack);
        performOperation(stack);
        return ++programCounter;
    }
}
