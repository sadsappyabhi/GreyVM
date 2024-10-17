import java.util.Stack;
/**
 * An object that implements this interface will represent a single statement
 * of the program
 *
 * @Author Abhilasha Chebolu
 * @Version Summer/Fall 2024
 */
public interface Operation {
    /**
     * Executes a single operation
     *
     * @param programCounter Index of the Operation
     * @param stack The Execution Stack
     * @param symbolTable The Symbol Table
     */
    int execute(int programCounter, Stack<Integer> stack, SymbolTable symbolTable);
}
