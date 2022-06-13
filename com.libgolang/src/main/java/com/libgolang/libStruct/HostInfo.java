package com.libgolang.libStruct;

import com.sun.jna.Structure;

import java.util.Arrays;
import java.util.List;

public class HostInfo extends Structure {
    public GoString host;
    public int port;

    public HostInfo() {
        super();
    }
    public HostInfo(GoString host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList("host", "port");
    }

    public static class ByValue extends HostInfo implements Structure.ByValue {
        public ByValue() {
        }
    }

    public static class ByReference extends HostInfo implements Structure.ByReference {
        public ByReference() {
        }
    }
}
