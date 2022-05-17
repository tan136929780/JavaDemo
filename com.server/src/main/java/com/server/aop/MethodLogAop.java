package com.server.aop;

import com.server.utils.JacksonUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j(topic = "methodLog")
public class MethodLogAop {
    @Pointcut("@annotation(com.server.annotation.MethodLog)")
    public void pointCut() {

    }

    @Around("pointCut()")
    public Object methodHandler(ProceedingJoinPoint jointPoint) throws Throwable {
        Object object = jointPoint.proceed(jointPoint.getArgs());
        log.info("{} args:{} result:{}", jointPoint.getSignature().toString(), JacksonUtil.toJSONString(jointPoint.getArgs()), JacksonUtil.toJSONString(object));
        return object;
    }
}
