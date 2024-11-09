package schedulers;

import java.util.Comparator;
import java.util.List;
import java.util.Queue;

public class AbstractScheduler implements Scheduler {
    protected Queue<GreyVMThread> queue;
    protected PQ<SleeperThread> sleepQueue;
    protected int time;

    public AbstractScheduler(List<GreyVMThread> list) {
        sleepQueue = new PQ<>(new Comparator<SleeperThread>() {
            @Override
            public int compare(SleeperThread o1, SleeperThread o2) {
                // these are reversed to make PQ a min-heap for the sleepQueue
                return Integer.compare(o2.getWakeupTime(), o1.getWakeupTime());
            }
        });
    }

    /**
     * Returns the thread that will be executed in the next time slice.
     *
     * @return the current thread
     */
    @Override
    public GreyVMThread getCurrentThread() {
        return queue.peek();
    }

    /**
     * Executes the given number of instructions
     *
     * @param numInstructions
     */
    @Override
    public void run(int numInstructions) {
        for (int i = 0; i < numInstructions; i++) {
            tick();
            wakeSleepingThreads();
        }
    }

    protected void tick() {
        time++;
        if (queue.isEmpty()) {
            return;
        }

        ThreadState state =  getCurrentThread().run();

        switch (state.getState()) {
            case Running -> {
                break;
            }
            case Yielded -> {
                queue.offer(queue.poll());
                break;
            }
            case Sleeping -> {
                GreyVMThread sleeper = queue.poll();
                sleepQueue.offer(new SleeperThread(sleeper, time + state.getTicksToSleep()));
                break;
            }
            case Complete -> {

            }
            default -> {
                queue.poll();
                break;
            }
        }
        tickHook(state.getState());
    }

    public void tickHook(ThreadState.State state) {
        // Use for overriding
    }

    protected void wakeSleepingThreads() {
        if (!sleepQueue.isEmpty() && time >= sleepQueue.peek().getWakeupTime()) {
            GreyVMThread wokeThread = sleepQueue.poll().getThread();
            queue.offer(wokeThread);
        }
    }
}
