package operations;
import main.SymbolTable;

import java.util.Stack;

public class PushOperation implements Operation {
    private int value;
    private String variable;

    public PushOperation(String variable) {this.variable = variable;}

    @Override
    public int execute(int programCounter, Stack<Integer> stack, SymbolTable table) {
        try {
            value = Integer.parseInt(variable);
        }
        catch (NumberFormatException e) {
            if (table.contains(variable)) {
                value = table.getValue(variable);
            }
        }
        stack.push(value);
        return ++programCounter;
    }
}
