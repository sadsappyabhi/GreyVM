package operations;

import java.util.Stack;

public class EQ extends CompareOperation {
    public EQ() {}

    @Override
    public void performOperation(Stack<Integer> stack) {
        stack.push((this.a == this.b ? returnValue.TRUE.getValue() : returnValue.FALSE.getValue()));
    }
}
