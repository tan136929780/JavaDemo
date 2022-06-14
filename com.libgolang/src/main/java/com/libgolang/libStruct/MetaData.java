package com.libgolang.libStruct;

import com.sun.jna.Structure;

import java.util.Arrays;
import java.util.List;

public class MetaData extends Structure {
    public String fileName;
    public String uri;
    public String fileType;
    public String hash;
    public long fileSize;

    public MetaData() {
        super();
    }

    public MetaData(String fileName, String uri, String fileType, String hash, long fileSize) {
        this.fileName = fileName;
        this.uri = uri;
        this.fileType = fileType;
        this.hash = hash;
        this.fileSize = fileSize;
    }

    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList("fileName", "uri", "fileType", "hash", "fileSize");
    }

    public static class ByValue extends MetaData implements Structure.ByValue {
        public ByValue() {
        }
    }

    public static class ByReference extends MetaData implements Structure.ByReference {
        public ByReference() {
        }
    }
}
