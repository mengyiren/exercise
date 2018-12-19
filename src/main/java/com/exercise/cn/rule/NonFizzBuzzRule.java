package com.exercise.cn.rule;

import lombok.Setter;
import org.easyrules.annotation.Action;
import org.easyrules.annotation.Condition;
import org.easyrules.annotation.Priority;
import org.easyrules.annotation.Rule;

/**
 * @author mengyiren
 */
@Rule
public class NonFizzBuzzRule {
    @Setter
    private int input;

    @Condition
    public boolean isNonFizzBuzz() {
        return input % 5 != 0 || input % 7 != 0;
    }

    @Action
    public void printInput() {
        System.out.print(input);
    }

    @Priority
    public int getPriority() {
        return 3;
    }
}
