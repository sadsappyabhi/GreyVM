package schedulers;

public class ThreadState {
    public enum State {
        Running,
        Yielded,
        Sleeping,
        Complete
    }

    private State state;
    private int nextInstruction;
    private int ticksToSleep;

    /**
     * Returns the current state
     *
     * @return the state
     */
    public State getState() {
        return state;
    }

    /**
     * Returns the index of the next instruction to execute.
     * @return the index of the next instruction
     */
    public int getNextInstruction() {
        return nextInstruction;
    }

    /**
     * Returns the number of ticks to sleep
     */
    private int getTicksToSleep() {
        return ticksToSleep;
    }

    /**
     * Creates a ThreadState
     * @param currentState the state
     * @param nextInstruction the next instruction
     * @param ticks the number of ticks to sleep
     */
    public ThreadState(State currentState, int nextInstruction, int ticks) {
        this.state = currentState;
        this.nextInstruction = nextInstruction;
        this.ticksToSleep = ticks;
    }
}
