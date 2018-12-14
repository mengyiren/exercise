package com.exercise.cn.yaml;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author mengyiren
 */
@Configuration
@Import(YamlHandler.class)
public class YamlConfig {
}
