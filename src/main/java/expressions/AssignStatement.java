package expressions;

public class AssignStatement extends AbstractStatement {
    private AbstractExpression expr;
    private VariableExpression var;

    public AssignStatement(VariableExpression var, AbstractExpression value) {
        this.var = var;
        this.expr = value;
    }
}
