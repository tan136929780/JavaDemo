package com.libgolang.libStruct;

import com.sun.jna.Structure;
import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
public class ServiceInfoResponse extends Structure {
    public int Version;
    public String  Os;
    public String Hostname;
    public String AcceptProtocol;

    public ServiceInfoResponse() {
        super();
    }

    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList("Version", "Os", "Hostname", "AcceptProtocol");
    }

    public static class ByValue extends ServiceInfoResponse implements Structure.ByValue {
        public ByValue() {
        }
    }

    public static class ByReference extends ServiceInfoResponse implements Structure.ByReference {
        public ByReference() {
        }
    }
}
