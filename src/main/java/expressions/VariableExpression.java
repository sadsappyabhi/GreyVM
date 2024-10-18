package expressions;

public class VariableExpression extends AbstractExpression {
    String label;

    public VariableExpression(String name) {
        label = name;
    }

    public String getName() {
        return label;
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}
