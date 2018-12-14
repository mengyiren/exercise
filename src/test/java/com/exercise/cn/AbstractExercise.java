package com.exercise.cn;

import lombok.Data;

import java.util.List;

/**
 * @author mengyiren
 */
@Data
public abstract class AbstractExercise implements IExerciseHandler {
    protected List<String> exerciseName;

    protected List<String> destation;
}
