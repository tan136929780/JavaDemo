package com.libgolang.controller;

import com.libgolang.libStruct.*;
import com.libgolang.libStruct.File;
import com.libgolang.so.Libvfile;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.util.Arrays;

@SpringBootTest
public class TestLibController {
    @Test
    public void test() throws IOException {
        HostInfo hostInfo = new HostInfo("127.0.0.1", 8200);
//        ServiceInfoRequest serviceInfoRequest = new ServiceInfoRequest(hostInfo);
//        ServiceInfoResponse serviceInfo = Libvfile.INSTANCE.ServiceInfo(serviceInfoRequest);
//        System.out.println(serviceInfo);
        File file = new File(fileToByte("/Users/tanxianchen/Downloads/test.png"));
        MetaData metaData = new MetaData("vfile:///Users/tanxianchen/Downloads/test.png", "", "txt", "hash", file.content.length);
        UploadRequest uploadRequest = new UploadRequest(metaData, file);
        UploadInfoRequest uploadInfoRequest = new UploadInfoRequest(hostInfo, uploadRequest);
        UploadResponse uploadResponse = Libvfile.INSTANCE.Upload(uploadInfoRequest);
        System.out.println(uploadResponse);

//        DownloadOption downloadOption = new DownloadOption(100, 100, 0, 100);
//        DownloadRequest downloadRequest = new DownloadRequest("uri", downloadOption);
//        DownloadInfoRequest downloadInfoRequest = new DownloadInfoRequest(hostInfo, downloadRequest);
//        DownloadResponse downloadResponse = Libvfile.INSTANCE.Download(downloadInfoRequest);
//        System.out.println(downloadResponse);
    }

    public byte[] readFileByByte(String filePath) throws IOException {
        byte[] buffer = new byte[1024*1024*10];
        java.io.File file = new java.io.File(filePath);
        FileInputStream fis = new FileInputStream(file);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int len = 0;
        while((len = fis.read(buffer)) != -1){
            byteArrayOutputStream.write(buffer,0,len);
        }
        fis.close();
        byteArrayOutputStream.close();
        return buffer;
    }

    public byte[] fileToByte(String filePath) {
        byte[] bytes = new byte[0];
        try {
            java.io.File file = new java.io.File(filePath);
            FileInputStream fileInputStream = new FileInputStream(file);
            bytes = fileInputStream.readAllBytes();
            fileInputStream.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return bytes;
    }
}
