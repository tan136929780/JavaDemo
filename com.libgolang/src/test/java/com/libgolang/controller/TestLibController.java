package com.libgolang.controller;

import com.libgolang.so.Libvfile;
import com.libgolang.libStruct.ServiceInfoResponse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestLibController {
    @Test
    public void test() {
        ServiceInfoResponse serviceInfo = Libvfile.INSTANCE.ServiceInfo();
        System.out.println(serviceInfo.AcceptProtocol.str);
    }
}
