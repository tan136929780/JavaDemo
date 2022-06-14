package com.libgolang.libStruct;

import com.sun.jna.Structure;
import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.List;

public class DownloadOption extends Structure {
    public long sliceSize;
    public long timeout;
    public long rangeStart;
    public long rangeEnd;

    public DownloadOption() {
        super();
    }

    public DownloadOption(long sliceSize, long timeout, long rangeStart, long rangeEnd) {
        this.sliceSize = sliceSize;
        this.timeout = timeout;
        this.rangeStart = rangeStart;
        this.rangeEnd = rangeEnd;
    }

    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList("sliceSize", "timeout", "rangeStart", "rangeEnd");
    }

    public static class ByValue extends DownloadOption implements Structure.ByValue {
        public ByValue() {
        }
    }

    public static class ByReference extends DownloadOption implements Structure.ByReference {
        public ByReference() {
        }
    }
}
