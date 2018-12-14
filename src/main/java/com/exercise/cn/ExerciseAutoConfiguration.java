package com.exercise.cn;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author mengyiren
 */
@Configuration
@Import(ExerciseRegister.class)
public class ExerciseAutoConfiguration {
}
