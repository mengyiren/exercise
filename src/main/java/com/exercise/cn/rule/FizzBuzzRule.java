package com.exercise.cn.rule;

import org.easyrules.core.CompositeRule;

/**
 * @author mengyiren
 */
public class FizzBuzzRule extends CompositeRule {
    public FizzBuzzRule(Object... rules) {
        for (Object rule : rules) {
            addRule(rule);
        }
    }

    @Override
    public int getPriority() {
        return 0;
    }
}
