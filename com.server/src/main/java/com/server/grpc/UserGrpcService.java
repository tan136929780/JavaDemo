package com.server.grpc;

import com.client.contract.user.ResponseResult;
import com.client.contract.user.UserRequest;
import com.client.contract.user.UserServiceGrpc;
import com.server.utils.JacksonUtil;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
@Slf4j(topic = "grpcRequestLog")
public class UserGrpcService extends UserServiceGrpc.UserServiceImplBase {
    @Override
    public void getUserInfo(UserRequest request, StreamObserver<ResponseResult> responseObserver) {
        log.info(JacksonUtil.toJSONString(request));
        ResponseResult.newBuilder().setCode(200).setMsg("user grpc service").build();
        responseObserver.onCompleted();
    }
}
