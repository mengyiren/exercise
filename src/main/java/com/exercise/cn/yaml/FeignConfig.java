package com.exercise.cn.yaml;

import lombok.Data;

/**
 * @author mengyiren
 */
@Data
public class FeignConfig {
    private String readTimeout;

    private String connectTimeout;
}
