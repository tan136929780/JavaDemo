package com.libgolang.libStruct;

import com.sun.jna.Structure;
import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.List;

public class DownloadRequest extends Structure {
    public String  uri;
    public DownloadOption downloadOption;

    public DownloadRequest() {
        super();
    }
    public DownloadRequest(String uri, DownloadOption downloadOption) {
        this.uri = uri;
        this.downloadOption = downloadOption;
    }

    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList("uri", "downloadOption");
    }

    public static class ByValue extends DownloadRequest implements Structure.ByValue {
        public ByValue() {
        }
    }

    public static class ByReference extends DownloadRequest implements Structure.ByReference {
        public ByReference() {
        }
    }
}
