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
    public void test() throws InvalidProtocolBufferException {
        HelloResponse helloResponse = helloService.hello("Lyn");
//        com.google.protobuf.util.JsonFormat.printer().includingDefaultValueFields().print(helloResponse);
        System.out.println(helloResponse);
    }
}
