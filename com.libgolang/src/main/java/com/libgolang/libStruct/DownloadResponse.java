package com.libgolang.libStruct;

import com.sun.jna.Structure;

import java.util.Arrays;
import java.util.List;

public class DownloadResponse extends Structure {
    public long code;
    public String message;
    public MetaData metaData;
    public File file;

    public DownloadResponse() {
        super();
    }

    public DownloadResponse(long code, String message, MetaData metaData, File file) {
        this.code = code;
        this.message = message;
        this.metaData = metaData;
        this.file = file;
    }

    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList("code", "message", "metaData", "file");
    }

    public static class ByValue extends DownloadResponse implements Structure.ByValue {
        public ByValue() {
        }
    }

    public static class ByReference extends DownloadResponse implements Structure.ByReference {
        public ByReference() {
        }
    }
}
