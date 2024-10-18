package expressions;

import java.util.HashMap;

public class CompileVisitor implements Visitor {
    private HashMap<WhileStatement, Integer> whileMap = new HashMap<>();
    private HashMap<IfStatement, Integer> ifMap = new HashMap<>();
    private int whileCounter;
    private int ifCounter;
    StringBuffer sb = new StringBuffer();

    public void visit(ConstantExpression expr) {
        sb.append("push ");
        sb.append(expr.getValue());
        sb.append("\n");
    }

    public void visit(VariableExpression expr) {
        sb.append("push ");
        sb.append(expr.getName());
        sb.append("\n");
    }

    public void preVisit(BinaryExpression expr) {}
    public void visit(BinaryExpression expr) {}

    public void postVisit(BinaryExpression expr) {
        sb.append(expr.getOperator().getInstruction());
    }

    public void preVisit(AssignStatement stmt) {}
    public void postVisit(AssignStatement stmt) {
        sb.append("pop ");
        sb.append(stmt.getVariable().getName());
        sb.append("\n");
    }

    public void preVisit(WhileStatement stmt) {
        whileMap.put(stmt, whileCounter++);
        sb.append("while" + whileMap.get(stmt));
        sb.append(": nop");
        sb.append("\n");
    }

    public void preBodyVisit(WhileStatement stmt) {
        sb.append("branchF ");
        sb.append("endwhile" + whileMap.get(stmt));
        sb.append("\n");
    }

    public void postVisit(WhileStatement stmt) {
        sb.append("branch ");
        sb.append("while" + whileMap.get(stmt));
        sb.append("\n");
        sb.append("endwhile" + whileMap.get(stmt) + ": ");
        sb.append("nop\n");
    }

    public void preVisit(IfStatement stmt) {
        ifMap.put(stmt, ifCounter++);
    }

    public void preThenVisit(IfStatement stmt) {
        sb.append("branchF ");
        if (stmt.getElseBlock() == null) {
            sb.append("endif" + ifMap.get(stmt));
        }
        else sb.append("else" + ifMap.get(stmt));
        sb.append("\n");
    }

    public void preElseVisit(IfStatement stmt) {
        sb.append("branch ");
        sb.append("endif" + ifMap.get(stmt));
        sb.append("\n");
        sb.append("else" + ifMap.get(stmt));
        sb.append(": nop\n");
    }

    public void postVisit(IfStatement stmt) {
        sb.append("endif" + ifMap.get(stmt) + ": ");
        sb.append("nop\n");
    }

    /**
     * Returns the compiled code
     *
     * @return The compiled code as a String
     */
    public String getString() {
        return sb.toString();
    }
}
