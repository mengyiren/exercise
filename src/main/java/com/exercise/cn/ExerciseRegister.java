package com.exercise.cn;

import com.exercise.cn.annotion.Exercise;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.util.ClassUtils;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * @author mengyiren
 */
@Slf4j
public class ExerciseRegister implements ImportBeanDefinitionRegistrar, ResourceLoaderAware, BeanClassLoaderAware
        , EnvironmentAware {
    private ClassLoader classLoader;

    private Environment environment;

    private ResourceLoader resourceLoader;

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata metadata, BeanDefinitionRegistry registry) {
        registerExercise(metadata, registry);
    }

    private void registerExercise(AnnotationMetadata metadata, BeanDefinitionRegistry registry) {
        ClassPathScanningCandidateComponentProvider scanner = getScanner();
        AnnotationTypeFilter annotationTypeFilter = new AnnotationTypeFilter(Exercise.class);
        scanner.addIncludeFilter(annotationTypeFilter);
        String basePackage = getBasePackage(metadata);
        Set<BeanDefinition> components = scanner.findCandidateComponents(basePackage);
        for (BeanDefinition component : components) {
            if (component instanceof AnnotatedBeanDefinition) {
                AnnotatedBeanDefinition beanDefinition = (AnnotatedBeanDefinition) component;
                AnnotationMetadata annotationMetadata = beanDefinition.getMetadata();
                Map<String, Object> attributesMap = annotationMetadata.getAnnotationAttributes(Exercise.class.getCanonicalName());
                if (!CollectionUtils.isEmpty(attributesMap)) {
                    doRegisterExercise(registry, annotationMetadata, attributesMap);
                }
            }

        }
    }

    private void doRegisterExercise(BeanDefinitionRegistry registry, AnnotationMetadata metadata, Map<String, Object> attributesMap) {
        String className = metadata.getClassName();
        BeanDefinitionBuilder definition = BeanDefinitionBuilder.genericBeanDefinition(className);
        Object value = attributesMap.get("value");
        List<String> nameList = new ArrayList<>();
        if (value instanceof String) {
            nameList.add(value.toString());
        } else if (value instanceof String[]) {
            String[] strings = (String[]) value;
            nameList.addAll(Arrays.asList(strings));
        }
        definition.addPropertyValue("exerciseName", nameList);
        List<String> stringList = new ArrayList<>();
        Object destation = attributesMap.get("destation");
        if (destation instanceof String[]) {
            String[] strings = (String[]) destation;
            stringList.addAll(Arrays.asList(strings));
        }
        definition.addPropertyValue("destation", stringList);
        definition.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_TYPE);
        AbstractBeanDefinition beanDefinition = definition.getBeanDefinition();
        registry.registerBeanDefinition(className + ".ding", beanDefinition);
    }

    private String getBasePackage(AnnotationMetadata metadata) {
        StackTraceElement[] stackTraces = new RuntimeException().getStackTrace();
        for (StackTraceElement stackTrace : stackTraces) {
            if ("main".equals(stackTrace.getMethodName())) {
                try {
                    if (stackTrace.getClassName().contains("junit")) {
                        return ClassUtils.getPackageName(metadata.getClassName());
                    } else {
                        return Class.forName(stackTrace.getClassName()).getPackage().getName();
                    }
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException("Can't get default package");
                }
            }
        }
        return null;
    }

    private ClassPathScanningCandidateComponentProvider getScanner() {
        return new ClassPathScanningCandidateComponentProvider(false, environment) /*{
            @Override
            protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
                if (beanDefinition.getMetadata().isIndependent()) {
                    if (beanDefinition.getMetadata().isInterface() && beanDefinition.getMetadata().getInterfaceNames().length == 1
                            && Annotation.class.getName().equals(beanDefinition.getMetadata().getInterfaceNames()[0])) {
                        try {
                            Class<?> target = ClassUtils.forName(beanDefinition.getMetadata().getClassName(), ExerciseRegister.this.classLoader);
                            return !target.isAnnotation();
                        } catch (Exception e) {
                            log.info("Could not load class: " + beanDefinition.getMetadata().getClassName());
                        }
                    }
                    return true;
                }
                return false;
            }
        }*/;
    }
}
