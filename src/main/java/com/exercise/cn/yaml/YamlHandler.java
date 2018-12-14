package com.exercise.cn.yaml;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author mengyiren
 */
public class YamlHandler implements ApplicationContextAware {
    private ApplicationContext applicationContext;

    public static void main(String[] args) {
        Yaml yaml = new Yaml();
        try {
            Map<String, Object> data = yaml.load(new FileInputStream(new File("D:/IdeaProjects/exercise/src/main/resources/application.yml")));
            if (data != null) {
                Object feign = data.get("feign");
                if (feign instanceof Map) {
                    Object client = ((Map) feign).get("client");
                    if (client instanceof Map) {
                        ((Map) client).forEach((key, value) -> {
                            if (value instanceof Map) {
                                FeignConfig config = new FeignConfig();
                                BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(FeignConfig.class);
                                ((Map) value).forEach((item, time) -> {
                                    String str = getKey(item);
                                    builder.addPropertyValue(str, time);

                                });
                                AbstractBeanDefinition beanDefinition = builder.getBeanDefinition();
                                System.out.println("aaa");
                            }
                            //System.out.println("aaa");
                        });
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Pattern PATTERN = Pattern.compile("_\\w");

    private static String getKey(Object item) {
        StringBuffer buffer = new StringBuffer();
        Matcher matcher = PATTERN.matcher(((String) item).toLowerCase());
        while (matcher.find()) {
            matcher.appendReplacement(buffer, matcher.group(0).substring(1).toUpperCase());
        }
        matcher.appendTail(buffer);
        return buffer.toString();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

}
