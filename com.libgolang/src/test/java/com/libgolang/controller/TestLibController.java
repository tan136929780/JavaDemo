package com.libgolang.controller;

import com.libgolang.so.LibHello;
import com.libgolang.util.GoString;
import com.libgolang.util.HelloReturn;
import com.sun.jna.Pointer;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@SpringBootTest
public class TestLibController {
    @Test
    public void test() {
        GoString.ByValue str = LibHello.INSTANCE.Hello1(new GoString.ByValue("Test"));
        System.out.println(str.str);
//        HelloReturn helloReturn = LibHello.INSTANCE.Hello2(new GoString.ByValue("Test"));
//        System.out.println(helloReturn.call1.str);
//        System.out.println(helloReturn.call2.str);
        byte[] download = "1111".getBytes();
        Pointer pointer = LibHello.INSTANCE.Hello3(download, download.length);
        System.out.println(new String(pointer.getByteArray(0, 4)));
    }
}
