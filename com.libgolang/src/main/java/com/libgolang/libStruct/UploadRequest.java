package com.libgolang.libStruct;

import com.sun.jna.Structure;

import java.util.Arrays;
import java.util.List;

public class UploadRequest extends Structure {
    public MetaData metaData;
    public File file;

    public UploadRequest() {
        super();
    }
    public UploadRequest(MetaData metaData, File file) {
        this.metaData = metaData;
        this.file = file;
    }

    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList("metaData", "file");
    }

    public static class ByValue extends UploadRequest implements Structure.ByValue {
        public ByValue() {
        }
    }

    public static class ByReference extends UploadRequest implements Structure.ByReference {
        public ByReference() {
        }
    }
}
