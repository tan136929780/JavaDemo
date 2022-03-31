package test.demo.filter;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import test.demo.utils.JacksonUtil;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;


@Component
@Slf4j
public class RequestFilter implements Filter {
    Logger logger = LoggerFactory.getLogger("requestLog");

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = new HttpServletRequestWrapper((HttpServletRequest)servletRequest);
        logger.info("Request:{}", JacksonUtil.toJSONString(request.getParameterMap()));
        filterChain.doFilter(request, servletResponse);
        logger.info("Response:{}", "");
    }
}
