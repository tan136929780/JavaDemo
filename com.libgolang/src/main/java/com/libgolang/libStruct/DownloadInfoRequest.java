package com.libgolang.libStruct;

import com.sun.jna.Structure;

import java.util.Arrays;
import java.util.List;

public class DownloadInfoRequest extends Structure {
    public HostInfo hostInfo;
    public DownloadRequest downloadRequest;

    public DownloadInfoRequest() {
        super();
    }

    public DownloadInfoRequest(HostInfo hostInfo, DownloadRequest downloadRequest) {
        this.hostInfo = hostInfo;
        this.downloadRequest = downloadRequest;
    }

    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList("hostInfo", "DownloadRequest");
    }

    public static class ByValue extends DownloadInfoRequest implements Structure.ByValue {
        public ByValue() {
        }
    }

    public static class ByReference extends DownloadInfoRequest implements Structure.ByReference {
        public ByReference() {
        }
    }
}
