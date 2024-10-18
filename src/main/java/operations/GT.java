package operations;

import java.util.Stack;

public class GT extends CompareOperation {
    public GT() {}

    @Override
    public void performOperation(Stack<Integer> stack) {
        stack.push((this.a > this.b ? returnValue.TRUE.getValue() : returnValue.FALSE.getValue()));
    }
}
