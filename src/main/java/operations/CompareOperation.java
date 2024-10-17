package operations;

import main.SymbolTable;

import java.util.Stack;

public abstract class CompareOperation implements Operation {
    protected int a, b;

    public enum returnValue {
        TRUE(1),
        FALSE(0);

        private final int value;
        returnValue(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public void populateOperation(Stack<Integer> stack) {
        this.b = stack.pop();
        this.a = stack.pop();
    }

    abstract void performOperation(Stack<Integer> stack);

    @Override
    public int execute(int programCounter, Stack<Integer> stack, SymbolTable table) {
        populateOperation(stack);
        performOperation(stack);
        return ++programCounter;
    }
}
