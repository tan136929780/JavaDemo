package com.libgolang.libStruct;

import com.sun.jna.Structure;
import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
public class ServiceInfoRequest extends Structure {
    public HostInfo hostInfo;

    public ServiceInfoRequest() {
        super();
    }

    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList("hostInfo");
    }

    public static class ByValue extends ServiceInfoRequest implements Structure.ByValue {
        public ByValue() {
        }
    }

    public static class ByReference extends ServiceInfoRequest implements Structure.ByReference {
        public ByReference() {
        }
    }
}
