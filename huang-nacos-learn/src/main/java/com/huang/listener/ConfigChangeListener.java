package com.huang.listener;

import com.alibaba.cloud.nacos.NacosConfigProperties;
import com.alibaba.cloud.nacos.NacosPropertySourceRepository;
import com.alibaba.cloud.nacos.client.NacosPropertySource;
import com.alibaba.cloud.nacos.refresh.NacosContextRefresher;
import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.*;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.client.config.listener.impl.AbstractConfigChangeListener;
import com.huang.event.NacosConfigUpdateEvent;
import com.huang.postprocess.NacosConfigRefreshAnnotationPostProcess;
import com.huang.utils.NacosConfigParserUtils;
import com.huang.utils.PlaceholderUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.InjectionMetadata;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ReflectionUtils;
import org.yaml.snakeyaml.Yaml;

import javax.annotation.PostConstruct;
import java.io.StringReader;
import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

/**
 * @Time 2023-03-28 17:38
 * Created by Huang
 * className: ConfigChangeListener
 * Description:
 */
@Slf4j
//@Component
public class ConfigChangeListener extends AbstractConfigChangeListener {
    ConfigService configService;

    @Autowired
    NacosContextRefresher nacosContextRefresher;

    @Autowired
    NacosConfigProperties nacosConfigProperties;

    @Autowired
    ApplicationContext applicationContext;

    @PostConstruct
    public void init() throws NacosException {
        //手动获取configService
        configService = NacosFactory.createConfigService(nacosConfigProperties.assembleConfigServiceProperties());
        //手动注册监听器
        for (NacosPropertySource propertySource : NacosPropertySourceRepository
                .getAll()) {
            if (!propertySource.isRefreshable()) {
                continue;
            }
            String dataId = propertySource.getDataId();
            registerNacosListener(propertySource.getGroup(), dataId);
        }
    }

    private void registerNacosListener(final String groupKey, final String dataKey) {
        try {
            configService.addListener(dataKey, groupKey, this);
        }
        catch (NacosException e) {
            log.warn(String.format(
                    "register fail for nacos listener ,dataId=[%s],group=[%s]", dataKey,
                    groupKey), e);
        }
    }

    @Override
    public void receiveConfigChange(ConfigChangeEvent event) {
        event.getChangeItems().forEach(item -> {
            String key = item.getKey();
            String oldValue = item.getOldValue();
            String newValue = item.getNewValue();
            log.info("ConfigChangeListener receiveConfigChange key:{}",key);
            log.info("ConfigChangeListener receiveConfigChange oldValue:{}",oldValue);
            log.info("ConfigChangeListener receiveConfigChange newValue:{}",newValue);
        });
    }

    @Override
    public void receiveConfigInfo(String configInfo) {
        NacosConfigUpdateEvent nacosConfigUpdateEvent = new NacosConfigUpdateEvent(configInfo);
        applicationContext.publishEvent(nacosConfigUpdateEvent);
        log.info("ConfigChangeListener receiveConfigInfo configInfo:{}",configInfo);
    }
}
