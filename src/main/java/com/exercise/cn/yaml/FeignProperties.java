package com.exercise.cn.yaml;

import lombok.Data;

import java.util.Map;

/**
 * @author mengyiren
 */
@Data
public class FeignProperties {
    private Map<String, FeignConfig> config;
}
