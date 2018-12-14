package com.exercise.cn;

import com.exercise.cn.annotion.Exercise;

/**
 * @author mengyiren
 */
@Exercise(value = "exercise-one", destation = {"111", "222"})
public class ExerciseOne extends AbstractExercise {

    @Override
    public String getName() {
        return "name:" + this.exerciseName.toString() + " destation:" + destation.toString();
    }
}
