package com.demo.controller;

import com.demo.component.NacosComponent;
import com.google.protobuf.InvalidProtocolBufferException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class NacosTestController {
    @Resource
    NacosComponent nacosComponent;

    @Test
    public void nacosConfiguration() throws InvalidProtocolBufferException {
        System.out.println(nacosComponent.getEnv());
        System.out.println(nacosComponent.getServiceIp());
        System.out.println(nacosComponent.getServicePort());
    }
}
