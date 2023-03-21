package com.huang.discovery;

import com.alibaba.fastjson.JSONObject;
import com.huang.config.ConfigProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Time 2023-03-21 15:49
 * Created by Huang
 * className: DemoController
 * Description:
 */
@RestController
@RequestMapping("/demo")
public class DemoController {
    @Autowired
    private ConfigProperties properties;

    @GetMapping("/getConfigValue")
    public JSONObject getConfigValue(){
        return new JSONObject(){{
            put("value", properties.getValue());
        }};
    }
}
