package expressions;

public class Operator {
    private String op;
    private String instruction;

    /**
     * Creates an operator.
     *
     * @param op The operator
     * @param instruction The opcode to execute the operator
     */
    private Operator(String op, String instruction) {
        this.op = op;
        this.instruction = instruction;
    }

    @Override
    public String toString() {
        return op;
    }

    public String getInstruction() {
        return instruction;
    }

    public static final Operator ADD = new Operator("+", "add\n");
    public static final Operator SUB = new Operator("-", "subtract\n");
    public static final Operator MULT = new Operator("*", "multiply\n");
    public static final Operator DIV = new Operator("/", "divide\n");
    public static final Operator EQ = new Operator("==", "EQ\n");
    public static final Operator NEQ = new Operator("!=", "NEQ\n");
    public static final Operator LT = new Operator("<", "LT\n");
    public static final Operator LTE = new Operator("<=", "LTE\n");
    public static final Operator GT = new Operator(">", "GT\n");
    public static final Operator GTE = new Operator(">=", "GTE\n");
}
