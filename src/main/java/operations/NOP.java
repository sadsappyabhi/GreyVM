package operations;

import main.SymbolTable;

import java.util.Stack;

public class NOP implements Operation {
    public NOP() {}

    @Override
    public int execute(int programCounter, Stack<Integer> stack, SymbolTable table) {
        return ++programCounter;
    }
}
