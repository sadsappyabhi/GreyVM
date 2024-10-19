package schedulers;

import java.util.Queue;

public class AbstractScheduler implements Scheduler {
    protected Queue<GreyVMThread> queue;
    protected PQ<SleeperThread> sleepQueue;
}
