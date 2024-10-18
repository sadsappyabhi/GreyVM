package expressions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CompileVisitorTest extends AbstractVisitorTest {

    @Test
    public void testSimpleExpressions() {
        CompileVisitor v = new CompileVisitor();
        constEx.accept(v);
        assertEquals("push 5\n", v.getString());

        v = new CompileVisitor();
        varEx.accept(v);
        assertEquals("push x\n", v.getString());

        v = new CompileVisitor();
        simpleBinaryEx.accept(v);
        assertEquals(
                "push x\n" +
                        "push 5\n" +
                        "add\n",
                v.getString()
        );
    }

    @Test
    public void testAssignStatement() {
        CompileVisitor v = new CompileVisitor();
        assignStmt.accept(v);
        assertEquals(
                "push 5\n"
                + "pop x\n",
                v.getString()
        );

        v = new CompileVisitor();
        AssignStatement as = new AssignStatement(
                varEx,
                simpleBinaryEx
        );
        as.accept(v);
        assertEquals(
                "push x\n"
                + "push 5\n"
                + "add\n"
                + "pop x\n",
                v.getString()
        );
    }

    @Test
    public void testWhileStatement() {
        CompileVisitor v = new CompileVisitor();
        BinaryExpression conditional = new BinaryExpression(
                varEx,
                Operator.LT,
                constEx
        );
        BinaryExpression body = new BinaryExpression(
                varEx,
                Operator.MULT,
                new ConstantExpression(3)
        );
        AssignStatement as = new AssignStatement(
                varEx,
                body
        );
        WhileStatement ws = new WhileStatement(
                conditional,
                as
        );
        ws.accept(v);
        assertEquals(
                "while0: nop\n"
                + "push x\n"
                + "push 5\n"
                + "LT\n"
                + "branchF endwhile0\n"
                + "push x\n"
                + "push 3\n"
                + "multiply\n"
                + "pop x\n"
                + "branch while0\n"
                + "endwhile0: nop\n",
                v.getString()
        );
    }

    @Test
    public void testIfStatement() {
        CompileVisitor v = new CompileVisitor();
        BinaryExpression condition =
                new BinaryExpression(
                        varEx,
                        Operator.LT,
                        constEx
                );
        AssignStatement thenBlock =
                new AssignStatement(
                        varEx,
                        new BinaryExpression(
                                varEx,
                                Operator.MULT,
                                constEx
                        )
                );
        IfStatement is = new IfStatement(condition, thenBlock);
        is.accept(v);
        assertEquals(
                "push x\n"
                + "push 5\n"
                + "LT\n"
                + "branchF endif0\n"
                + "push x\n"
                + "push 5\n"
                + "multiply\n"
                + "pop x\n"
                + "endif0: nop\n",
                v.getString()
        );

        v = new CompileVisitor();
        IfStatement is2 = new IfStatement(condition, thenBlock, thenBlock);
        is2.accept(v);
        assertEquals(
                "push x\n"
                + "push 5\n"
                + "LT\n"
                + "branchF else0\n"
                + "push x\n"
                + "push 5\n"
                + "multiply\n"
                + "pop x\n"
                + "branch endif0\n"
                + "else0: nop\n"
                + "push x\n"
                + "push 5\n"
                + "multiply\n"
                + "pop x\n"
                + "endif0: nop\n",
                v.getString()
        );
    }

    @Test
    public void testNestedWhile() {
        BinaryExpression condition = new BinaryExpression(
                varEx,
                Operator.LT,
                constEx
        );
        AssignStatement as = new AssignStatement(varEx, condition);
        WhileStatement innerWhile = new WhileStatement(condition, as);
        WhileStatement outerWhile = new WhileStatement(condition, innerWhile);

        CompileVisitor cv = new CompileVisitor();
        outerWhile.accept(cv);
        assertEquals(
                "while0: nop\n"
                        + "push x\n"
                        + "push 5\n"
                        + "LT\n"
                        + "branchF endwhile0\n"
                        + "while1: nop\n"
                        + "push x\n"
                        + "push 5\n"
                        + "LT\n"
                        + "branchF endwhile1\n"
                        + "push x\n"
                        + "push 5\n"
                        + "LT\n"
                        + "pop x\n"
                        + "branch while1\n"
                        + "endwhile1: nop\n"
                        + "branch while0\n"
                        + "endwhile0: nop\n",
                cv.getString()
        );
    }
}