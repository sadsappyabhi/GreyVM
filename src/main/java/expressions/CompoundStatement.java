package expressions;

import java.util.ArrayList;

public class CompoundStatement extends AbstractStatement {
    private ArrayList<AbstractStatement> body = new ArrayList<>();

    /**
     * Adds a statement to the body of this CompoundStatement
     * @param stmt
     */
    public void addStatement(AbstractStatement stmt) {
        body.add(stmt);
    }

    /**
     * Accepts a Visitor
     *
     * @param v The visitor
     */
    @Override
    public void accept(Visitor v) {
        for (AbstractStatement stmt : body) {
            stmt.accept(v);
        }
    }
}
