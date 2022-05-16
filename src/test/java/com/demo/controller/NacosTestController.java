package com.demo.controller;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;
import com.demo.component.NacosConfigComponent;
import com.google.protobuf.InvalidProtocolBufferException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class NacosTestController {
    @Resource
    NacosConfigComponent nacosConfigComponent;

//    @NacosInjected
//    ConfigService configService;

    @Test
    public void nacosConfiguration() throws InvalidProtocolBufferException, NacosException {
        // 发布配置
//        String content = "env=1111";
//        configService.publishConfig("java", "DEFAULT_GROUP", content);
        System.out.println(nacosConfigComponent.getEnv());
        System.out.println(nacosConfigComponent.getServiceIp());
        System.out.println(nacosConfigComponent.getServicePort());
    }
}
