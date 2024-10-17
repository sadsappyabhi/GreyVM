package operations;

import java.util.Stack;

public class Addition extends ArithmeticOperation {
    public Addition() {}

    @Override
    public void performOperation(Stack<Integer> stack) {
        stack.push(this.a + this.b);
    }
}
