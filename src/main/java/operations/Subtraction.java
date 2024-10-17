package operations;

import java.util.Stack;

public class Subtraction extends ArithmeticOperation {
    public Subtraction() {}

    @Override
    public void performOperation(Stack<Integer> stack) {
        stack.push(this.a - this.b);
    }
}
