package com.client.service;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.client.contract.hello.HelloGrpc;
import com.client.contract.hello.HelloRequest;
import com.client.contract.hello.HelloResponse;
import io.grpc.Channel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.stereotype.Service;

@Service
public class HelloService {
    @NacosInjected
    NamingService namingService;

    public HelloResponse hello(String name) throws NacosException {
        Instance helloInstance = namingService.selectOneHealthyInstance("java-service");
        Channel channel = ManagedChannelBuilder.forAddress(helloInstance.getIp(), helloInstance.getPort()).usePlaintext().build();
        HelloGrpc.HelloBlockingStub helloBlockingStub = HelloGrpc.newBlockingStub(channel);
        HelloRequest helloRequest = HelloRequest.newBuilder().setName(name).build();
        return helloBlockingStub.hello(helloRequest);
    }
}
