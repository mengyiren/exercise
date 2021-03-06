package com.exercise.cn;

import com.exercise.cn.yaml.FeignProperties;
import com.exercise.cn.yaml.YamlConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author mengyiren
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {YamlConfig.class})
public class TestDefinition {
    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void test() {
        FeignProperties bean = applicationContext.getBean(FeignProperties.class);
        System.out.println(bean);
    }
}
