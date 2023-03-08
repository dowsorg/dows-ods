package org.dows.ods.client;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.dows.ods.api.*;
import org.dows.ods.channel.hnilab.HnilabConfig;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class AroundMethod implements MethodInterceptor {
    private Map<String, ChannelConfig> channelConfigMap;
    private Map<String, ChannelProperties.Pointcut> pointcutMap;
    private Map<String, ChannelApi> channelEnvApiMap;

    private Map<String, ChannelSetting> channelSettingMap;
    public void setChannelConfig(Map<String, ChannelConfig> channelConfigMap) {
        this.channelConfigMap = channelConfigMap;
    }

    public void setPointcut(Map<String, ChannelProperties.Pointcut> pointcutMap) {
        this.pointcutMap = pointcutMap;
    }

    public void setChannelEnvApiMap(Map<String, ChannelApi> channelEnvApiMap) {
        this.channelEnvApiMap = channelEnvApiMap;
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

            Class<?> declaringClass = methodInvocation.getMethod().getDeclaringClass();
            String methodName = methodInvocation.getMethod().getName();
            String fullMethodName = declaringClass+"."+methodName;
            ChannelProperties.Pointcut pointcut = pointcutMap.get(fullMethodName);
            ChannelProperties.Method method = pointcut.getMethod(methodName);


            String formMethodKey = method.getKey(method.getFormMethod());
            String toMethodKey = method.getKey(method.getToMethod());

            ChannelApi formChannelApi = channelEnvApiMap.get(formMethodKey);
            ChannelApi toChannelApi = channelEnvApiMap.get(toMethodKey);

            String formUrl = formChannelApi.getApiUriByMethodName(method.getVal(method.getFormMethod()));
            String toUrl = toChannelApi.getApiUriByMethodName(method.getVal(method.getToMethod()));

            // // todo 通过该端点上传数据 该端点可能是 jdbc 也可能是 http
            //String channel = method.getEndpointId();
            // todo 后期可异步，先实现channelProperties
            //ChannelConfig channelConfig = channelConfigMap.get(channel);

            //ChannelSetting channelSetting = channelSettingMap.get(channel);

            //ChannelApi channelApi = channelEnvApiMap.get(channelSetting.getEnv() + ":" + channelSetting.getId());

            //OdsExecutor.exec(method,channelApi,odsResponse);
            log.info("around method : after ");
            return result;
        } catch (IllegalArgumentException e) {
            log.info("around method : throw  an  exception ");
            throw e;
        }
    }



}