package expressions;

import jdk.incubator.vector.VectorOperators;

public class BinaryExpression extends AbstractExpression {
    private AbstractExpression leftChild;
    private AbstractExpression rightChild;
    Operator op;

    public BinaryExpression(AbstractExpression left, Operator op, AbstractExpression right) {
        this.leftChild = left;
        this.op = op;
        this.rightChild = right;
    }

    public Operator getOperator() {
        return op;
    }

    @Override
    public void accept(Visitor v) {
        v.preVisit(this);
        leftChild.accept(v);
        v.visit(this);
        rightChild.accept(v);
        v.postVisit(this);
    }
}
