package com.exercise.cn;

import com.exercise.cn.sliding.window.Loops;
import com.exercise.cn.sliding.window.SlidingWindowCounter;
import org.junit.Test;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author yiren.meng
 * @date 2019/12/20
 */
public class SlidingWindowCounterTest {
    private ExecutorService es = Executors.newCachedThreadPool();


    private ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();

    @Test
    public void testNWindow() throws IOException {
        SlidingWindowCounter swc = new SlidingWindowCounter(4);
        ses.scheduleAtFixedRate(() -> Loops.fixLoop(swc::increase, new Random().nextInt(10))
                , 1, 1, TimeUnit.SECONDS);
        ses.scheduleAtFixedRate(() -> {
            System.out.println(swc);
            swc.advance();
        }, 1, 2, TimeUnit.SECONDS);
        System.in.read();
    }

    @Test
    public void test4Window() {
        SlidingWindowCounter swc = new SlidingWindowCounter(4);
        System.out.println(swc);
        swc.increase();
        swc.increase();
        System.out.println(swc);
        swc.advance();
        System.out.println(swc);
        swc.increase();
        System.out.println(swc);
        swc.advance();
        System.out.println(swc);
        swc.increase();
        swc.increase();
        swc.increase();
        System.out.println(swc);

    }

    @Test
    public void testMove() {
        System.out.println(1 << 29);
        System.out.println(Integer.toBinaryString(~((1 << 29) - 1)));
    }

    @Test
    public void testWait() {
        long timeMillis = System.currentTimeMillis();
        System.out.println(timeMillis % 15000);
    }

}
