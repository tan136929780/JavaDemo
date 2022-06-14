package com.libgolang.so;

import com.libgolang.libStruct.*;
import com.sun.jna.Library;
import com.sun.jna.Native;

public interface Libvfile extends Library {
    Libvfile INSTANCE = Native.load("/Users/tanxianchen/coding/go/src/file-service/vfile_client/libvfile.so", Libvfile.class);

    ServiceInfoResponse.ByValue ServiceInfo(ServiceInfoRequest serviceInfoRequest);

    UploadResponse Upload(UploadInfoRequest uploadInfoRequest);

     DownloadResponse Download(DownloadInfoRequest downloadInfoRequest);
}