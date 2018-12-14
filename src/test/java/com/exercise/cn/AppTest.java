package com.exercise.cn;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Unit test for simple App.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ExerciseAutoConfiguration.class})
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Autowired
    private List<IExerciseHandler> handlers;

    @Test
    public void shouldAnswerWithTrue() {
        handlers.forEach(handler -> System.out.println(handler.getName()));
    }
}
