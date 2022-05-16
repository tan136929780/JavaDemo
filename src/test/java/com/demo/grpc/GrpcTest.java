package com.demo.grpc;

import com.demo.contract.hello.HelloResponse;
import com.demo.service.HelloService;
import com.google.protobuf.InvalidProtocolBufferException;
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
