package com.huang.config;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Time 2023-03-21 15:45
 * Created by Huang
 * className: ConfigProperties
 * Description:
 */
@Data
@ToString
@Component
@ConfigurationProperties("temp.nacos")
public class ConfigProperties {
    private String key;
}
