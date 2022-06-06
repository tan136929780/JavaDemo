package com.libtest.so;

import com.libtest.util.GoString;
import com.sun.jna.Library;
import com.sun.jna.Native;

public interface LibHello extends Library {
    LibHello INSTANCE = Native.loadLibrary("/home/tan/www/go/src/lib/libhello.so", LibHello.class);

    GoString.ByValue Hello(GoString.ByValue call);
}