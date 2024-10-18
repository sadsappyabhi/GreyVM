package expressions;

public interface Visitor {
    /**
     * Visit a ConstantExpression
     *
     * @param expr
     */
    void visit(ConstantExpression expr);

    /**
     * Visits a VariableExpression.
     *
     * @param expr
     */
    void visit(VariableExpression expr);

    void preVisit(BinaryExpression expr);

    void visit(BinaryExpression expr);

    void postVisit(BinaryExpression expr);

    void preVisit(AssignStatement stmt);

    void postVisit(AssignStatement stmt);

    void preVisit(WhileStatement stmt);

    void preBodyVisit(WhileStatement stmt);

    void postVisit(WhileStatement stmt);

    void preVisit(IfStatement stmt);

    void preThenVisit(IfStatement stmt);

    void preElseVisit(IfStatement stmt);

    void postVisit(IfStatement stmt);
}
