package com.client.service;

import com.client.contract.hello.HelloGrpc;
import com.client.contract.hello.HelloRequest;
import com.client.contract.hello.HelloResponse;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
public class HelloService {
    @GrpcClient("java-client")
    private HelloGrpc.HelloBlockingStub helloBlockingStub;

    public HelloResponse hello(String name) {
        HelloRequest helloRequest = HelloRequest.newBuilder().setName(name).build();
        return helloBlockingStub.hello(helloRequest);
    }
}
