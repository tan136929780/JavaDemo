package com.libtest.controller;

import com.libtest.so.LibHello;
import com.libtest.util.GoString;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestLibController {
    @Test
    public void test() {
        System.out.println(LibHello.INSTANCE.Hello(new GoString.ByValue("Test")).str);
    }
}
