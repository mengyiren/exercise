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
public class FizzRule {
    @Setter
    private int input;

    @Condition
    public boolean isFizz() {
        return input % 5 == 0;
    }

    @Action
    public void printFizz() {
        System.out.print("fizz");
    }

    @Priority
    public int getPriority() {
        return 1;
    }
}
