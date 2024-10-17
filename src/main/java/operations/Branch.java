package operations;

import main.SymbolTable;

import java.util.Stack;

public class Branch implements Operation {
    protected String label;

    public Branch(String label) throws IllegalArgumentException {
        if (label == null || label.isEmpty()) {
            throw new IllegalArgumentException("Branch operation must have a label!");
        }
        this.label = label;
    }

    @Override
    public int execute(int programCounter, Stack<Integer> stack, SymbolTable table) {
        return table.getValue(label);
    }
}
