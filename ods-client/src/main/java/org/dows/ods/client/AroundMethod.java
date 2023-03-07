package org.dows.ods.client;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.dows.ods.api.OdsResponse;
import org.dows.ods.channel.hnilab.HnilabConfig;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Slf4j
public class AroundMethod implements MethodInterceptor {
    private OdsPointcutProperties odsProperties;
    private HnilabConfig hnilabConfig;
    public AroundMethod(OdsPointcutProperties odsProperties) {
        this.odsProperties = odsProperties;
    }

    public void setHnilabConfig(HnilabConfig hnilabConfig){
        this.hnilabConfig = hnilabConfig;
    }

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        log.info("around method before,method  name:{},method  arguments:{}" ,
                methodInvocation.getMethod().getName()
                ,Arrays.toString(methodInvocation.getArguments()));
        try {
            // 获取数据
            Object result = methodInvocation.proceed();
            // todo 上报处理 OdsResponse 返回值应为该类型，if 判断一下，如果是可强转
            Class<?> returnType = methodInvocation.getMethod().getReturnType();
            OdsResponse odsResponse = (OdsResponse)result;

            // todo 通过该断点上传数据
            String methodName = methodInvocation.getMethod().getName();
            // 该端点可能是 jdbc 也可能是 http
            OdsPointcutProperties.Endpoint endpoint = odsProperties.getEndpoint(methodName);
            if(endpoint != null){
                // todo 后期可异步，先实现
                OdsClient.exec(hnilabConfig.getHnIlabProperties().getEnv(),endpoint,odsResponse);
            }
            log.info("around method : after ");
            return result;
        } catch (IllegalArgumentException e) {
            log.info("around method : throw  an  exception ");
            throw e;
        }
    }
}