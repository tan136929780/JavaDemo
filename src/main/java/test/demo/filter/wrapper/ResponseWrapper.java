package test.demo.filter.wrapper;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.*;


public class ResponseWrapper extends HttpServletResponseWrapper {

    private final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    private final HttpServletResponse   response;
    private       PrintWriter           printWriter;

    public ResponseWrapper(HttpServletResponse response) {
        super(response);
        this.response = response;
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        // 将数据写到 byte 中
        return new MyServletOutputStream(byteArrayOutputStream);
    }

    /**
     * 重写父类的 getWriter() 方法，将响应数据缓存在 PrintWriter 中
     */
    @Override
    public PrintWriter getWriter() throws IOException {
        try {
            printWriter = new PrintWriter(new OutputStreamWriter(byteArrayOutputStream, "utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return printWriter;
    }

    /**
     * 获取缓存在 PrintWriter 中的响应数据
     *
     * @return
     */
    public byte[] getBytes() {
        if (null != printWriter) {
            printWriter.close();
            return byteArrayOutputStream.toByteArray();
        }

        try {
            byteArrayOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteArrayOutputStream.toByteArray();
    }

    static class MyServletOutputStream extends ServletOutputStream {
        private final ByteArrayOutputStream byteArrayOutputStream;

        public MyServletOutputStream(ByteArrayOutputStream byteArrayOutputStream) {
            this.byteArrayOutputStream = byteArrayOutputStream;
        }

        @Override
        public void write(int b) throws IOException {
            byteArrayOutputStream.write(b);
        }

        @Override
        public boolean isReady() {
            return false;
        }

        @Override
        public void setWriteListener(WriteListener listener) {

        }
    }
}