package com.server.grpc;


import com.client.contract.hello.HelloRequest;
import com.client.contract.hello.HelloResponse;
import com.client.contract.hello.HelloGrpc;
import com.server.utils.JacksonUtil;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
@Slf4j(topic = "grpcRequestLog")
public class HelloGrpcService extends HelloGrpc.HelloImplBase {
    @Override
    public void hello(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {
        log.info(JacksonUtil.toJSONString(request));
        HelloResponse.newBuilder().setGreeting("hello" + request.getName()).build();
        responseObserver.onCompleted();
    }
}
