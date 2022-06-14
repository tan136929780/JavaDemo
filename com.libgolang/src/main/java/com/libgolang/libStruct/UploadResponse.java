package com.libgolang.libStruct;

import com.sun.jna.Structure;

import java.util.Arrays;
import java.util.List;

public class UploadResponse extends Structure {
    public int code;
    public String message;
    public String uri;

    public UploadResponse() {
        super();
    }

    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList("code", "message", "uri");
    }

    public static class ByValue extends UploadResponse implements Structure.ByValue {
        public ByValue() {
        }
    }

    public static class ByReference extends UploadResponse implements Structure.ByReference {
        public ByReference() {
        }
    }
}
