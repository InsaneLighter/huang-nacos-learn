package com.huang.controller;

import com.alibaba.fastjson.JSONObject;
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
    @Value("${key}")
    public String key;

    @GetMapping("/configChange")
    public JSONObject configChange(){
        return new JSONObject(){{
            put("key",key);
        }};
    }
}