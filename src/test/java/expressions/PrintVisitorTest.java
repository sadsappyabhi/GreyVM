package expressions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PrintVisitorTest extends AbstractVisitorTest {
    @Test
    public void testSimpleExpressions()
    {
        PrintVisitor v = new PrintVisitor();
        constEx.accept(v);
        assertEquals("5", v.getString());

        v = new PrintVisitor();
        varEx.accept(v);
        assertEquals("x", v.getString());

        v = new PrintVisitor();
        simpleBinaryEx.accept(v);
        assertEquals("(x+5)", v.getString());
    }

    @Test
    public void testAssignStatement() {
        PrintVisitor v = new PrintVisitor();
        assignStmt.accept(v);
        assertEquals("x = 5;\n", v.getString());

        v = new PrintVisitor();
        AssignStatement as = new AssignStatement(
                varEx,
                simpleBinaryEx
        );
        as.accept(v);
        assertEquals("x = (x+5);\n", v.getString());
    }

    @Test
    public void testWhileStatement() {
        PrintVisitor v = new PrintVisitor();
        BinaryExpression conditional = new BinaryExpression(
                varEx,
                Operator.LT,
                constEx);
        BinaryExpression body = new BinaryExpression(
                varEx,
                Operator.MULT,
                new ConstantExpression(3));
        AssignStatement as = new AssignStatement(
                varEx,
                body);
        WhileStatement ws = new WhileStatement(
                conditional,
                as);
        ws.accept(v);
        assertEquals(
                "while (x<5)\n"
                        + "{\n"
                        + "  x = (x*3);\n"
                        + "}\n",
                v.getString());
    }

    @Test
    public void testIfStatement() {
        PrintVisitor v = new PrintVisitor();
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
                "if (x<5)\n"
                        + "{\n"
                        + "  x = (x*5);\n"
                        + "}\n",
                v.getString()
        );

        v = new PrintVisitor();
        IfStatement is2 = new IfStatement(condition, thenBlock, thenBlock);
        is2.accept(v);
        assertEquals(
                "if (x<5)\n"
                        + "{\n"
                        + "  x = (x*5);\n"
                        + "}\n"
                        + "else\n"
                        + "{\n"
                        + "  x = (x*5);\n"
                        + "}\n",
                v.getString()
        );
    }

    @Test
    public void testNesting() {

        PrintVisitor v = new PrintVisitor();

        BinaryExpression condition1 = new BinaryExpression(varEx, Operator.LT,
                constEx);
        AssignStatement as = new AssignStatement(varEx, constEx);
        CompoundStatement body = new CompoundStatement();
        body.addStatement(as);
        body.addStatement(as);
        body.addStatement(as);
        WhileStatement innerWhile = new WhileStatement(condition1, body);
        WhileStatement outerWhile = new WhileStatement(condition1, innerWhile);
        outerWhile.accept(v);
        assertEquals(
                "while (x<5)\n"
                        + "{\n"
                        + "  while (x<5)\n"
                        + "  {\n"
                        + "    x = 5;\n"
                        + "    x = 5;\n"
                        + "    x = 5;\n"
                        + "  }\n"
                        + "}\n",
                v.getString()

        );
    }

    @Test
    public void testNestingIfAndWhile() {
        PrintVisitor v = new PrintVisitor();
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
        IfStatement is2 = new IfStatement(condition, thenBlock, thenBlock);
        WhileStatement ws = new WhileStatement(condition, is2);
        ws.accept(v);
        assertEquals(
                "while (x<5)\n"
                        + "{\n"
                        + "  if (x<5)\n"
                        + "  {\n"
                        + "    x = (x*5);\n"
                        + "  }\n"
                        + "  else\n"
                        + "  {\n"
                        + "    x = (x*5);\n"
                        + "  }\n"
                        + "}\n",
                v.getString()
        );
    }

    @Test
    public void testingMoreIfWhile() {
        PrintVisitor v = new PrintVisitor();
        BinaryExpression condition = new BinaryExpression(varEx, Operator.LT, new ConstantExpression(10));
        AssignStatement as1 = new AssignStatement(varEx, new BinaryExpression(varEx, Operator.ADD,
                new ConstantExpression(1)));
        AssignStatement as2 = new AssignStatement(varEx, new BinaryExpression(varEx,
                Operator.ADD,new ConstantExpression(5)));
        IfStatement is = new IfStatement(condition, as1, as2);

        WhileStatement ws = new WhileStatement(condition, is);
        ws.accept(v);
        assertEquals(
                "while (x<10)\n"
                        + "{\n"
                        + "  if (x<10)\n"
                        + "  {\n"
                        + "    x = (x+1);\n"
                        + "  }\n"
                        + "  else\n"
                        + "  {\n"
                        + "    x = (x+5);\n"
                        + "  }\n"
                        + "}\n",
                v.getString()
        );

        PrintVisitor pv = new PrintVisitor();
        CompoundStatement body = new CompoundStatement();
        body.addStatement(as1);
        body.addStatement(as2);
        WhileStatement ws1 = new WhileStatement(condition, body);
        WhileStatement ws2 = new WhileStatement(condition, is);
        CompoundStatement twoWhiles = new CompoundStatement();
        twoWhiles.addStatement(ws1);
        twoWhiles.addStatement(ws2);
        WhileStatement outerWhile = new WhileStatement(condition, twoWhiles);
        outerWhile.accept(pv);
        assertEquals(
                "while (x<10)\n"
                        + "{\n"
                        + "  while (x<10)\n"
                        + "  {\n"
                        + "    x = (x+1);\n"
                        + "    x = (x+5);\n"
                        + "  }\n"
                        + "  while (x<10)\n"
                        + "  {\n"
                        + "    if (x<10)\n"
                        + "    {\n"
                        + "      x = (x+1);\n"
                        + "    }\n"
                        + "    else\n"
                        + "    {\n"
                        + "      x = (x+5);\n"
                        + "    }\n"
                        + "  }\n"
                        + "}\n",
                pv.getString()
        );
    }
}