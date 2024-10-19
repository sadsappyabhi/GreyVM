package expressions;

public class PrintVisitor implements Visitor {
    private int stmtIndentLevel;
    private int bracketIndentLevel;
    private StringBuffer sb = new StringBuffer();

    public void visit(ConstantExpression expr) {
        sb.append(expr.getValue());
    }

    public void visit(VariableExpression expr) {
        sb.append(expr.getName());
    }

    public void preVisit(BinaryExpression expr) {
        sb.append("(");
    }

    public void visit(BinaryExpression expr) {
        sb.append(expr.getOperator());
    }

    public void postVisit(BinaryExpression expr) {
        sb.append(")");
    }

    public void preVisit(AssignStatement stmt) {
        sb.append("  ".repeat(stmtIndentLevel));
        sb.append(stmt.getVariable().getName());
        sb.append(" = ");
    }

    public void postVisit(AssignStatement stmt) {
        sb.append(";\n");
    }

    public void preVisit(WhileStatement stmt) {
        sb.append("  ".repeat(stmtIndentLevel));
        sb.append("while ");
        stmtIndentLevel++;
        bracketIndentLevel++;
    }

    public void preBodyVisit(WhileStatement stmt) {
        sb.append("\n");
        sb.append("  ".repeat(bracketIndentLevel - 1));
        sb.append("{");
        sb.append("\n");
    }

    public void postVisit(WhileStatement stmt) {
        sb.append("  ".repeat(bracketIndentLevel - 1));
        sb.append("}\n");
        stmtIndentLevel--;
        bracketIndentLevel--;

    }

    public void preVisit(IfStatement stmt) {
        sb.append("  ".repeat(stmtIndentLevel++));
        sb.append("if ");
        bracketIndentLevel++;
    }

    public void preThenVisit(IfStatement stmt) {
        sb.append("\n");
        sb.append("  ".repeat(bracketIndentLevel - 1));
        sb.append("{");
        sb.append("\n");
    }

    public void preElseVisit(IfStatement stmt) {
        if (bracketIndentLevel > 0) {
            sb.append("  ".repeat(bracketIndentLevel - 1));
        }
        sb.append("}");
        sb.append("\n");
        if (bracketIndentLevel > 0) {
            sb.append("  ".repeat(bracketIndentLevel - 1));
        }
        sb.append("else");
        sb.append("\n");
        if (bracketIndentLevel > 0) {
            sb.append("  ".repeat(bracketIndentLevel - 1));
        }
        sb.append("{\n");
    }

    public void postVisit(IfStatement stmt) {
        sb.append("  ".repeat(--bracketIndentLevel));
        sb.append("}\n");
        stmtIndentLevel--;
    }

    public String getString() {
        return sb.toString();
    }
}
