package com.demo.filter;

import com.demo.filter.wrapper.RequestWrapper;
import com.demo.filter.wrapper.ResponseWrapper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import com.demo.utils.JacksonUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;


@Component
@Slf4j
public class RequestFilter implements Filter {
    Logger logger = LoggerFactory.getLogger("requestLog");

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        RequestWrapper requestWrapper  = new RequestWrapper((HttpServletRequest) servletRequest);
        ResponseWrapper responseWrapper = new ResponseWrapper((HttpServletResponse) servletResponse);
        logger.info("Request:Url:{}---Body:{}", requestWrapper.getRequestURL(), JacksonUtil.toJSONString(requestWrapper.getRequestBody()));
        filterChain.doFilter(requestWrapper, responseWrapper);
        logger.info("Response:{}", JacksonUtil.toJSONString(new String(responseWrapper.getBytes(), "UTF-8")));
        OutputStream outputStream = servletResponse.getOutputStream();
        outputStream.write(responseWrapper.getBytes());
        outputStream.flush();
        outputStream.close();
    }
}
