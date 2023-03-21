package com.huang.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Time 2023-03-21 15:45
 * Created by Huang
 * className: ConfigProperties
 * Description:
 */
@Component
@ConfigurationProperties("temp.nacos")
public class ConfigProperties {
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
