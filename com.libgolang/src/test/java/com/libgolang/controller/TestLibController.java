package com.libgolang.controller;

import com.libgolang.so.LibHello;
import com.libgolang.util.GoString;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@SpringBootTest
public class TestLibController {
    @Test
    public void test() {
        System.out.println(LibHello.INSTANCE.Hello(new GoString.ByValue("Test")).str);
        byte[] download = "Test".getBytes();
        System.out.println(Arrays.toString(download));
        System.out.println(Arrays.toString(LibHello.INSTANCE.Download(download, download.length)));
    }
}
