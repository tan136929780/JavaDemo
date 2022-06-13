package com.libgolang.so;

import com.libgolang.libStruct.ServiceInfoRequest;
import com.libgolang.libStruct.ServiceInfoResponse;
import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;

public interface Libvfile extends Library {
    Libvfile INSTANCE = Native.load("/Users/tanxianchen/coding/go/src/visionvera/vfile_client/libvfile.so", Libvfile.class);

    ServiceInfoResponse.ByValue ServiceInfo(ServiceInfoRequest serviceInfoRequest);

    Pointer Upload(Pointer in);

    Pointer Download(Pointer in);
}