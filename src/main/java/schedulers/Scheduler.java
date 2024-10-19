package schedulers;


/**
 * Interface for scheduler classes
 */
public interface Scheduler {
    /**
     * Returns the thread that will execute an instruction during
     * the next time slice.
     *
     * @return the threaed
     */
    GreyVMThread getCurrentThread();

    /**
     * Executes the given number of instructions.
     *
     * @param numInstructions
     */
    void run(int numInstructions);
}
