package operations;

import java.util.Stack;

public class Division extends ArithmeticOperation {
    public Division() {}

    @Override
    public void performOperation(Stack<Integer> stack) {
        if (this.b == 0) {
            throw new IllegalArgumentException("Cannot divide by Zero!");
        }
        else stack.push(this.a / this.b);
    }
}
