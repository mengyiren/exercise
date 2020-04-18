package com.exercise.cn.sliding.window;

/**
 * @author yiren.meng
 * @date 2019/12/20
 */
public class SlidingWindowCounter {
    private volatile SlotBaseCounter slotBaseCounter;
    private volatile int windowSize;
    private volatile int head;

    public SlidingWindowCounter(int windowSize) {
        resizeWindow(windowSize);
    }

    public synchronized void resizeWindow(int windowSize) {
        this.windowSize = windowSize;
        this.slotBaseCounter = new SlotBaseCounter(windowSize);
        this.head = 0;
    }

    public void increase() {
        slotBaseCounter.increaseSlot(head);
    }

    public long totalAndAdvance() {
        long total = totalCount();
        advance();
        return total;
    }

    public void advance() {
        int tail = (head + 1) % windowSize;
        slotBaseCounter.wipeSlot(tail);
        head = tail;
    }

    private long totalCount() {
        return slotBaseCounter.totalCount();
    }

    @Override
    public String toString() {
        return "SlidingWindowCounter{" +
                "slotBaseCounter=" + slotBaseCounter +
                ", windowSize=" + windowSize +
                ", head=" + head + ", total=" + totalCount() +
                '}';
    }
}
