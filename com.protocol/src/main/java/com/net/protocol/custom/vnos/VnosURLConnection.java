package com.net.protocol.custom.vnos;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;

import com.util.StringUtils;

/**
 * <p>自定义vnos协议连接器</p>
 *
 */
public class VnosURLConnection extends URLConnection {

    /**
     * vnos协议的默认端口号
     */
    public static final int DEFAULT_PORT = 9527;

    private Socket connection = null;

    public VnosURLConnection(URL url) {
        super(url);
    }

    /**
     * <p>由于父类URLConnection中的getInputStream()方法不提供输入流的获取实现逻辑，因此这里需要重写此方法</p>
     *
     * @return
     * @throws IOException
     */
    @Override
    public synchronized InputStream getInputStream() throws IOException {
        if (!connected)
            this.connect();
        return connection.getInputStream();
    }

    /**
     * <p>vnos协议连接操作</p>
     *
     * @throws IOException
     */
    @Override
    public synchronized void connect() throws IOException {
        if (!connected) {
            int port = url.getPort();
            if (port < 1 || port > 65535)
                port = DEFAULT_PORT;
            this.connection = new Socket(url.getHost(), port);
            connected = true;
            // 连接后立即发送请求
            sendRequest(url);
        }
    }

    /**
     * <p>发送vnos协议请求</p>
     *
     * @param u
     * @throws IOException
     */
    protected void sendRequest(URL u) throws IOException {
        OutputStream outputStream = this.connection.getOutputStream();

        String queryString = u.getQuery();

        /*
         *  将URL的查询参数部分发送给服务器，由服务器负责解析查询后返回结果。
         *  当参数参数部分为空时，则发送一个"?"，表示查询服务器系统环境的所有信息。
         */
        outputStream.write(StringUtils.isNotNullOrBlank(queryString) ? queryString.getBytes() : "?".getBytes());
        outputStream.flush();
    }

}