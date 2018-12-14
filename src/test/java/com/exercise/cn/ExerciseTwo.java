package com.exercise.cn;

import com.exercise.cn.annotion.Exercise;

/**
 * @author mengyiren
 */
@Exercise(value = "exercise-two", destation = "333")
public class ExerciseTwo extends AbstractExercise {

    @Override
    public String getName() {
        return "name:" + this.exerciseName.toString() + " destation:" + destation.toString();
    }
}
