package expressions;

import jdk.incubator.vector.VectorOperators;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class AbstractVisitorTest {
    protected ConstantExpression constEx;
    protected VariableExpression varEx;
    protected BinaryExpression simpleBinaryEx;
    protected BinaryExpression complexEx;
    protected AssignStatement assignStmt;
    protected CompoundStatement compStmt;
    protected WhileStatement whileStmt;
    protected IfStatement ifStmt;
    protected IfStatement ifElseStmt;
    protected WhileStatement nestedWhile;

    @BeforeEach
    void setUp() {
        constEx = new ConstantExpression(5);
        varEx = new VariableExpression("x");

        simpleBinaryEx = new BinaryExpression(
                varEx,
                Operator.ADD,
                constEx
        );

        assignStmt = new AssignStatement(
                varEx,
                constEx
        );

        compStmt = new CompoundStatement();
        compStmt.addStatement(assignStmt);
        compStmt.addStatement(assignStmt);
        compStmt.addStatement(assignStmt);
    }

    public void testNothing() {
        int x = 3 + 5;
        assertEquals(8, x, "Can't happen");
    }
}