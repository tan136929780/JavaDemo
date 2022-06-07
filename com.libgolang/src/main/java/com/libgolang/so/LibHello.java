package com.libgolang.so;

import com.libgolang.util.GoString;
import com.sun.jna.Library;
import com.sun.jna.Native;

public interface LibHello extends Library {
    LibHello INSTANCE = Native.loadLibrary("/home/tan/www/go/src/goService/golib/libhello.so", LibHello.class);

    GoString.ByValue Hello(GoString.ByValue call);
    byte[] Download(byte[] call, long len);
}