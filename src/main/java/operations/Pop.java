package operations;

import main.SymbolTable;

import java.util.Stack;

public class Pop implements Operation {
    private final String variable;

    public Pop(String variable) {
        this.variable = variable;
    }

    @Override
    public int execute(int programCounter, Stack<Integer> stack, SymbolTable table) {
        int value = stack.pop();
        table.setValue(variable, value);
        return ++programCounter;
    }
}
