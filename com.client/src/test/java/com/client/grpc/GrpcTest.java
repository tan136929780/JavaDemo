package com.client.grpc;

import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.client.contract.hello.HelloGrpc;
import com.client.contract.hello.HelloRequest;
import com.client.contract.hello.HelloResponse;
import com.client.service.HelloService;
import io.grpc.Channel;
import io.grpc.ManagedChannelBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vfile.FileServiceGrpc;
import vfile.Vfile;

@SpringBootTest
public class GrpcTest {
    @Autowired
    HelloService helloService;

    @Test
    public void test() throws NacosException {
        HelloResponse helloResponse = helloService.hello("Lyn");
        System.out.println(helloResponse);
    }

    @org.junit.Test
    public void testVfile() {
        Channel channel = ManagedChannelBuilder.forAddress("localhost", 8200).usePlaintext().build();
        FileServiceGrpc.FileServiceBlockingStub fileServiceBlockingStub = FileServiceGrpc.newBlockingStub(channel);
        Vfile.ServiceInfoRequest serviceInfoRequest = Vfile.ServiceInfoRequest.newBuilder().build();
        System.out.println(fileServiceBlockingStub.serviceInfo(serviceInfoRequest));
    }
}
