package com.client.grpc;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.client.contract.hello.HelloResponse;
import com.client.service.HelloService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GrpcTest {
    @Autowired
    HelloService helloService;

    @Test
    public void test() {
        HelloResponse helloResponse = helloService.hello("Lyn");
        System.out.println(helloResponse);
    }
}