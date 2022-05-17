package com.server.grpc;

import com.server.contract.user.ResponseResult;
import com.server.contract.user.UserRequest;
import com.server.contract.user.UserServiceGrpc;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
@Slf4j(topic = "grpcRequestLog")
public class UserGrpcService extends UserServiceGrpc.UserServiceImplBase {
    @Override
    public void getUserInfo(UserRequest request, StreamObserver<ResponseResult> responseObserver) {
        ResponseResult responseResult = null;
        try {
            log.info(request.toString());
            responseResult = ResponseResult.newBuilder().setCode(200).setMsg("user grpc service").build();
        } catch (Exception e) {
            log.error(request.toString());
        } finally {
            responseObserver.onNext(responseResult);
            responseObserver.onCompleted();
        }
    }
}
