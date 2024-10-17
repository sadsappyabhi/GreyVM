package operations;

import main.SymbolTable;

import java.util.Stack;

public class BranchTrue extends Branch {
    public BranchTrue(String label) {
        super(label);
    }

    @Override
    public int execute(int programCounter, Stack<Integer> stack, SymbolTable table) {
        return stack.pop() == 1 ? table.getValue(super.label) : ++programCounter;
    }
}
