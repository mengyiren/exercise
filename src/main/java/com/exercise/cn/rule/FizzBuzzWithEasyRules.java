package com.exercise.cn.rule;

import org.easyrules.api.RulesEngine;
import org.easyrules.core.RulesEngineBuilder;

/**
 * @author mengyiren
 */
public class FizzBuzzWithEasyRules {
    public static void main(String[] args) {
        RulesEngine rulesEngine = RulesEngineBuilder.aNewRulesEngine().withSkipOnFirstAppliedRule(true).withSilentMode(true).build();
        FizzRule fizzRule = new FizzRule();
        BuzzRule buzzRule = new BuzzRule();
        NonFizzBuzzRule nonFizzBuzzRule = new NonFizzBuzzRule();
        rulesEngine.registerRule(fizzRule);
        rulesEngine.registerRule(buzzRule);
        rulesEngine.registerRule(nonFizzBuzzRule);
        for (int i = 1; i <= 100; i++) {
            fizzRule.setInput(i);
            buzzRule.setInput(i);
            nonFizzBuzzRule.setInput(i);
            rulesEngine.fireRules();
            System.out.println();
        }
    }
}
