package expressions;

public class IfStatement extends AbstractStatement {
    private AbstractExpression condition;
    private AbstractStatement thenBlock;
    private AbstractStatement elseBlock;

    public IfStatement(AbstractExpression condition, AbstractStatement thenBlock) {
        this.condition = condition;
        this.thenBlock = thenBlock;
    }

    public IfStatement(AbstractExpression condition, AbstractStatement thenBlock, AbstractStatement elseBlock) {
        this(condition, thenBlock);
        this.elseBlock = elseBlock;
    }

    public AbstractStatement getElseBlock() {
        return elseBlock;
    }

    @Override
    public void accept(Visitor v) {
        v.preVisit(this);
        condition.accept(v);
        v.preThenVisit(this);
        thenBlock.accept(v);
        if (elseBlock != null) {
            v.preElseVisit(this);
            elseBlock.accept(v);
        }
        v.postVisit(this);
    }
}
