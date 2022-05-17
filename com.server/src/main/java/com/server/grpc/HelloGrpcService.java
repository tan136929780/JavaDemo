package com.server.grpc;


import com.server.contract.hello.HelloRequest;
import com.server.contract.hello.HelloResponse;
import com.server.contract.hello.HelloGrpc;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
@Slf4j(topic = "grpcRequestLog")
public class HelloGrpcService extends HelloGrpc.HelloImplBase {
    @Override
    public void hello(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {
        HelloResponse helloResponse = null;
        try {
            log.info(request.toString());
            helloResponse = HelloResponse.newBuilder().setGreeting("hello " + request.getName()).build();
        } catch (Exception e) {
            log.error(request.toString());
        } finally {
            responseObserver.onNext(helloResponse);
            responseObserver.onCompleted();
        }
    }
}
