package com.libgolang.util;

import com.sun.jna.Structure;

import java.util.Arrays;
import java.util.List;

public class HelloReturn extends Structure {
    public GoString call1;
    public GoString call2;

    public HelloReturn() {

    }

    public HelloReturn(GoString call1, GoString call2) {
        this.call1 = call1;
        this.call2 = call2;
    }

    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList("call1", "call2");
    }

    public static class ByValue extends HelloReturn implements Structure.ByValue {}

    public static class ByReference extends HelloReturn implements Structure.ByReference {}
}
