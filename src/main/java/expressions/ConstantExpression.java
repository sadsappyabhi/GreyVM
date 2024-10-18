package expressions;

public class ConstantExpression extends AbstractExpression {
    private int val;

    public ConstantExpression(int val) {
        this.val = val;
    }

    public int getValue() {
        return val;
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}
