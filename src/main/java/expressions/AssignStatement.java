package expressions;

public class AssignStatement extends AbstractStatement {
    private AbstractExpression expr;
    private VariableExpression var;

    public AssignStatement(VariableExpression var, AbstractExpression value) {
        this.var = var;
        this.expr = value;
    }

    public VariableExpression getVariable() {
        return var;
    }

    @Override
    public void accept(Visitor v) {
        v.preVisit(this);
        expr.accept(v);
        v.postVisit(this);
    }
}
