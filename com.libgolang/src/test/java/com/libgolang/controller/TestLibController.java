package com.libgolang.controller;

import com.libgolang.so.LibHello;
import com.libgolang.util.GoString;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestLibController {
    @Test
    public void test() {
        System.out.println(LibHello.INSTANCE.Hello(new GoString.ByValue("Test")).str);
//        byte download = 1;
//        System.out.println(LibHello.INSTANCE.Download(download));
    }
}
