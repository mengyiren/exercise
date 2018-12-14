package com.exercise.cn.yaml;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author mengyiren
 */

public class YamlHandler implements ImportBeanDefinitionRegistrar, ApplicationContextAware {
    private ApplicationContext applicationContext;

    public void execute(BeanDefinitionRegistry registry) {
        Yaml yaml = new Yaml();
        try {
            Map<String, Object> data = yaml.load(new FileInputStream(new File("D:/IdeaProjects/exercise/src/main/resources/application.yml")));
            if (data != null) {
                Object feign = data.get("feign");
                if (feign instanceof Map) {
                    Object client = ((Map) feign).get("client");
                    if (client instanceof Map) {
                        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(FeignProperties.class);
                        Map<String, FeignConfig> config = new HashMap<>();
                        ((Map) client).forEach((key, value) -> {
                            if (value instanceof Map) {
                                FeignConfig feignConfig = new FeignConfig();
                                ((Map) value).forEach((item, time) -> {
                                    String str = getKey(item);
                                    try {
                                        Field declaredField = feignConfig.getClass().getDeclaredField(str);
                                        declaredField.setAccessible(true);
                                        declaredField.set(feignConfig, String.valueOf(time));
                                    } catch (NoSuchFieldException | IllegalAccessException e) {

                                    }
                                });
                                config.put((String) key, feignConfig);
                            }
                        });
                        builder.addPropertyValue("config", config);
                        if (registry instanceof DefaultListableBeanFactory) {
                            ((DefaultListableBeanFactory) registry).setAllowBeanDefinitionOverriding(true);
                        }
                        registry.registerBeanDefinition("feignConfig", builder.getBeanDefinition());
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Pattern PATTERN = Pattern.compile("_\\w");

    private String getKey(Object item) {
        StringBuffer buffer = new StringBuffer();
        Matcher matcher = PATTERN.matcher((String) item);
        while (matcher.find()) {
            matcher.appendReplacement(buffer, matcher.group(0).substring(1).toUpperCase());
        }
        matcher.appendTail(buffer);
        return buffer.toString();
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {
        execute(beanDefinitionRegistry);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
