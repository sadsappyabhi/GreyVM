package main;
import java.util.HashMap;
import java.util.Map;

/**
 * Used to manage and store variables.
 * @author Abhilasha Chebolu
 * @version Summer/Fall 2024
 */
public class SymbolTable {
    private Map<String, Integer> table;
    /**
     * Instantiates an empty SymbolTable
     */
    public SymbolTable()  {
        this.table = new HashMap<>();
    }

    /**
     * Sets the value of a variable
     *
     * @param name The name
     * @param value The value
     */
    public void setValue(String name, int value) {
        this.table.put(name, value);
    }

    /**
     * Returns the value of a variable
     *
     * @param name The name
     * @return The value associated with the variable
     * @throws RuntimeException if the variable has not been defined
     */
    public int getValue(String name) {
        if (!table.containsKey(name)) {
            throw new RuntimeException("Variable not found in table!");
        }
        return table.get(name);
    }

    /**
     * Checks if the variable has been defined
     *
     * @param name The name of the variable to check
     * @return True if the variable exists in the table, False if it does not
     */
    public boolean contains(String name) {
        return table.containsKey(name);
    }
}
