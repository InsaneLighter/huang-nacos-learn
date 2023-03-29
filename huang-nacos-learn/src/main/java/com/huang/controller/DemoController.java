package com.huang.controller;

import com.alibaba.fastjson.JSONObject;
import com.huang.config.ConfigProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Time 2023-03-28 20:49
 * Created by Huang
 * className: DemoController
 * Description:
 */
@RestController
@RequestMapping
public class DemoController {
    @Autowired
    public ConfigProperties configProperties;
    @Value("${temp.nacos.key}")
    private String key;

    @GetMapping("/propertiesConfigChange")
    public String propertiesConfigChange(){
        return configProperties.toString();
    }

    @GetMapping("/valueConfigChange")
    public String valueConfigChange(){
        return key;
    }
}