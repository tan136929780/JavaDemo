package com.server.aop;

import com.server.model.ReturnResult;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import com.server.utils.ResponseUtil;

import java.io.IOException;
import java.sql.SQLException;


@Component
@Aspect
@Slf4j(topic = "exceptionLog")
public class ExceptionAop {
    @Pointcut("execution(* com.server.controller.*.*(..))")
    public void pointCut() {
    }

    @Around("pointCut()")
    public ReturnResult exceptionHandler(ProceedingJoinPoint jointPoint) throws Throwable {
        ReturnResult returnResult = null;
        try {
            returnResult = (ReturnResult) jointPoint.proceed(jointPoint.getArgs());
        } catch (IOException ioException) {
            log.error("I/O Exception:", ioException);
        } catch (SQLException sqlException) {
            log.error("SQL Exception:", sqlException);
        } catch (Exception exception) {
            log.error("Exception:", exception);
            returnResult = ResponseUtil.failed(exception.getMessage());
        }
        return returnResult;
    }
}
