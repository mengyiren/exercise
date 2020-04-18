package com.exercise.cn.sliding.window;

import java.util.Arrays;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author yiren.meng
 * @date 2019/12/20
 */
public class SlotBaseCounter {
    private int slotSize;

    private LongAdder[] slotCounter;

    public SlotBaseCounter(int slotSize) {
        this.slotSize = slotSize;
        this.slotCounter = new LongAdder[slotSize];
        for (int i = 0; i < slotSize; i++) {
            slotCounter[i] = new LongAdder();
        }
    }

    public void increaseSlot(int index) {
        slotCounter[index].increment();
    }

    public void wipeSlot(int index) {
        slotCounter[index].reset();
    }

    public long totalCount() {
        return Arrays.stream(slotCounter).mapToLong(LongAdder::sum).sum();
    }

    @Override
    public String toString() {
        return "SlotBaseCounter{" +
                "slotSize=" + slotSize +
                ", slotCounter=" + Arrays.toString(slotCounter) +
                '}';
    }
}
