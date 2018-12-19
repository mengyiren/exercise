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
public class BuzzRule {
    @Setter
    private int input;

    @Condition
    public boolean isBuzz() {
        return input % 7 == 0;
    }

    @Action
    public void printBuzz() {
        System.out.print("buzz");
    }

    @Priority
    public int getPriority() {
        return 2;
    }
}
