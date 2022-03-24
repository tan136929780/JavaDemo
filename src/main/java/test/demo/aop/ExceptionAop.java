package test.demo.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import test.demo.model.ReturnResult;
import test.demo.utils.ResponseUtil;

import java.io.IOException;
import java.sql.SQLException;


@Component
@Aspect
public class ExceptionAop {
    public static final Logger logger = LoggerFactory.getLogger("exceptionLog");

    @Pointcut("execution(* test.demo.controller.*.*(..))")
    public void pointCut() {
    }

    @Around("pointCut()")
    public ReturnResult exceptionHandler(ProceedingJoinPoint jointPoint) throws Throwable {
        ReturnResult returnResult = null;
        try {
            returnResult = (ReturnResult) jointPoint.proceed(jointPoint.getArgs());
        } catch (IOException ioException) {
            logger.error("I/O Exception:", ioException);
        } catch (SQLException sqlException) {
            logger.error("SQL Exception:", sqlException);
        } catch (Exception exception) {
            logger.error("Exception:", exception);
            returnResult = ResponseUtil.failed(exception.getMessage());
        }
        return returnResult;
    }
}
