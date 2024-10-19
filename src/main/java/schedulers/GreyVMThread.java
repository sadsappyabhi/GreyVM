package schedulers;

import main.SymbolTable;
import operations.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class GreyVMThread {
    Stack<Integer> stack;
    private SymbolTable varTable;
    private SymbolTable branchTable;
    private List<Operation> operationList;


    /**
     * TODO: put a better description here
     */
    public GreyVMThread(Scanner scanner, SymbolTable varTable, SymbolTable branchTable) throws IOException {
        stack = new Stack<>();
        this.varTable = varTable;
        this.branchTable = branchTable;
        List<String> commands = new ArrayList<>();
        operationList = new ArrayList<>();

        // Convert the text program into a list of Operation objects
        while (scanner.hasNextLine()) {
            commands.add(scanner.nextLine().trim());
        }

        // Extracts labels from command strings and saves them in variable table. Updates command string.
        for (int i = 0; i < commands.size(); i++) {
            String command = commands.get(i);
            if (command.contains(":")) {
                String branchLabel = command.substring(0, command.indexOf(":"));
                if (branchLabel.isEmpty()) {
                    throw new RuntimeException("Branch label cannot be empty!");
                }
                if (branchTable.contains(branchLabel)) {
                    throw new RuntimeException("Branch label must be unique!");
                }
                branchTable.setValue(branchLabel, i);
                command = command.substring(command.indexOf(":") + 1).trim();
                commands.set(i, command);
            }
        }

        for (String command : commands) {
            String[] tokens = command.split(" ");
            createOperation(tokens);
        }

        for (Operation operation : operationList) {
            if (operation instanceof Pop) {
                if (stack.empty()) {
                    continue;
                }
            }
            if (operation instanceof Branch) {
                operation.execute(0, stack, branchTable);
            }
            else operation.execute(0, stack, varTable);

        }
    }

    /**
     * Runs the loaded program.
     */
    public void run() {
        // Execute the program
        int index = 0;
        while (index < operationList.size()) {
            Operation current = operationList.get(index);
            if (current instanceof Pop) {
                if (stack.empty()) {
                    throw new RuntimeException("Cannot pop from an empty stack!");
                }
            }
            if (current instanceof Branch) {
                index = current.execute(index, stack, branchTable);
            }
            else index = current.execute(index, stack, varTable);
        }
    }

    /**
     * Returns the value of the given variable.
     *
     * @param name The variable name
     * @return The value of the specified variable
     */
    public int getValue(String name) {
        return varTable.getValue(name);
    }

    /**
     * Helper method for constructor to be able to create new Operation objects
     * while parsing the input commands.
     */
    private void createOperation(String[] tokens) {
        switch (tokens[0]) {
            case "push":
                operationList.add(new Push(tokens[1]));
                break;
            case "pop":
                operationList.add(new Pop(tokens[1]));
                break;
            case "add":
                operationList.add(new Addition());
                break;
            case "subtract":
                operationList.add(new Subtraction());
                break;
            case "multiply":
                operationList.add(new Multiplication());
                break;
            case "divide":
                operationList.add(new Division());
                break;
            case "compareEQ":
                operationList.add(new EQ());
                break;
            case "compareNEQ":
                operationList.add(new NEQ());
                break;
            case "compareLT":
                operationList.add(new LT());
                break;
            case "compareLTE":
                operationList.add(new LTE());
                break;
            case "compareGT":
                operationList.add(new GT());
                break;
            case "compareGTE":
                operationList.add(new GTE());
                break;
            case "branch":
                operationList.add(new Branch(tokens[1]));
                break;
            case "branchT":
                operationList.add(new BranchTrue(tokens[1]));
                break;
            case "nop":
                operationList.add(new NOP());
                break;
            default:
                throw new IllegalArgumentException("Command not recognized, please check input!");
        }
    }
}
