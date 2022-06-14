package com.libgolang.libStruct;

import com.sun.jna.Structure;

import java.util.Arrays;
import java.util.List;

public class UploadInfoRequest extends Structure {
    public HostInfo hostInfo;
    public UploadRequest uploadRequest;

    public UploadInfoRequest() {
        super();
    }

    public UploadInfoRequest(HostInfo hostInfo, UploadRequest uploadRequest) {
        this.hostInfo = hostInfo;
        this.uploadRequest = uploadRequest;
    }

    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList("hostInfo", "uploadRequest");
    }

    public static class ByValue extends UploadInfoRequest implements Structure.ByValue {
        public ByValue() {
        }
    }

    public static class ByReference extends UploadInfoRequest implements Structure.ByReference {
        public ByReference() {
        }
    }
}
