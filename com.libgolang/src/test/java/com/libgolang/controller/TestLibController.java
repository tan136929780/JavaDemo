package com.libgolang.controller;

import com.libgolang.libStruct.HostInfo;
import com.libgolang.libStruct.ServiceInfoRequest;
import com.libgolang.so.Libvfile;
import com.libgolang.libStruct.ServiceInfoResponse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestLibController {
    @Test
    public void test() {
        HostInfo hostInfo = new HostInfo( "127.0.0.1", 8200);
        ServiceInfoRequest serviceInfoRequest = new ServiceInfoRequest(hostInfo);
        ServiceInfoResponse serviceInfo = Libvfile.INSTANCE.ServiceInfo(serviceInfoRequest);
        System.out.println(serviceInfo);
    }
}
