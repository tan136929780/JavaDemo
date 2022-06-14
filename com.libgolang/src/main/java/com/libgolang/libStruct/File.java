package com.libgolang.libStruct;

import com.sun.jna.Structure;
import lombok.AllArgsConstructor;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import java.util.List;

public class File extends Structure {
    public byte[] content = new byte[1024*1024*10];

    public File() {
        super();
    }

    public File(byte[] content) {
        this.content = content;
    }

    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList("content");
    }

    public static class ByValue extends File implements Structure.ByValue {
        public ByValue() {
        }
    }

    public static class ByReference extends File implements Structure.ByReference {
        public ByReference() {
        }
    }
}
