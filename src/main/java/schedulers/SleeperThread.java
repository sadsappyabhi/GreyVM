package schedulers;

public class SleeperThread {
    private GreyVMThread thread;
    private int wakeupTime;

    public SleeperThread(GreyVMThread thread, int wakeupTime) {
        this.thread = thread;
        setWakeupTime(wakeupTime);
    }

    public int getWakeupTime() {
        return this.wakeupTime;
    }

    public void setWakeupTime(int time) {
        if (time <= 0) {
            throw new IllegalArgumentException("Time cannot be negative!");
        }
        wakeupTime = time;
    }

    public GreyVMThread getThread() {
        return this.thread;
    }
}
