package operations;

import java.util.Stack;

public class Multiplication extends ArithmeticOperation {
    public Multiplication() {}

    @Override
    public void performOperation(Stack<Integer> stack) {
        stack.push(this.a * this.b);
    }
}
