package test;

import com.net.protocol.factory.CustomProtocolFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class VnosProtocolClient {
    public static void main(String[] args) {
        BufferedReader reader = null;
        try {
            // 配置协议处理器查找规则一
//            if (StringUtils.isNullOrBlank(System.getProperty("java.protocol.handler.pkgs"))) {
//                // 设置各个协议包所在的父包路径
//                System.setProperty("java.protocol.handler.pkgs", "com.net.protocol.custom");
//            }
            /*
             * 配置协议处理器查找规则二
             * 这种方式在整个应用范围之内只能被执行一次。
             * 如果多于一次则会出现"java.lang.Error: factory already defined"这样的错误。但不会受规则一的限制.
             */
            URL.setURLStreamHandlerFactory(new CustomProtocolFactory());
            URL url = new URL("vnos://127.0.0.1/");
            reader = new BufferedReader(new InputStreamReader(url.openConnection().getInputStream()));
            String result = "";
            while ((result = reader.readLine()) != null)
                System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null)
                    reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}