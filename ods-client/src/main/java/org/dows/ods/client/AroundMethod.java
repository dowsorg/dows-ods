package org.dows.ods.client;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.dows.ods.api.OdsResponse;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
public class AroundMethod implements MethodInterceptor {
    private OdsPointcutProperties odsProperties;
    public AroundMethod(OdsPointcutProperties odsProperties) {
        this.odsProperties = odsProperties;
    }

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        log.info("method  name:" + methodInvocation.getMethod().getName());
        log.info("method  arguments" + Arrays.toString(methodInvocation.getArguments()));
        log.info("around  method : before ");
        try {
            // 获取数据
            Object result = methodInvocation.proceed();
            // todo 上报处理
            Class<?> returnType = methodInvocation.getMethod().getReturnType();
            // 通过该断点上传数据
            String endpoint = odsProperties.getEndpoint();

            log.info("around method : after ");
            return result;
        } catch (IllegalArgumentException e) {
            log.info("around method : throw  an  exception ");
            throw e;
        }
    }
}