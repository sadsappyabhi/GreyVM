package operations;

import main.SymbolTable;

import java.util.Stack;

public class PopOperation implements Operation {
    private final String variable;

    public PopOperation(String variable) {
        this.variable = variable;
    }

    @Override
    public int execute(int programCounter, Stack<Integer> stack, SymbolTable table) {
        int value = stack.pop();
        table.setValue(variable, value);
        return ++programCounter;
    }
}
