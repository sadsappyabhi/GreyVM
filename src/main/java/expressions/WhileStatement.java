package expressions;

public class WhileStatement extends AbstractStatement {
    private AbstractExpression condition;
    private AbstractStatement body;

    public WhileStatement(AbstractExpression condition, AbstractStatement body) {
        this.condition = condition;
        this.body = body;
    }

    @Override
    public void accept(Visitor v) {
        v.preVisit(this);
        condition.accept(v);
        v.preBodyVisit(this);
        body.accept(v);
        v.postVisit(this);
    }
}
