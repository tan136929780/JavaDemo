package com.libgolang.so;

import com.libgolang.util.GoString;
import com.libgolang.util.HelloReturn;
import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;

public interface LibHello extends Library {
    LibHello INSTANCE = Native.loadLibrary("/home/tan/www/go/src/goService/golib/libhello.so", LibHello.class);

    GoString.ByValue Hello1(GoString.ByValue call);
    HelloReturn Hello2(GoString.ByValue call);
    Pointer Hello3(byte[] call, long len);
}