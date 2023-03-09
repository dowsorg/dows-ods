package org.dows.ods.api;

import lombok.Data;
import org.springframework.aop.Pointcut;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * dows:
 * ods:
 * hnilab:
 * appId: 3dfe80096e234a3798d05d3f0bd6610e01a1e53b855a4e0b94c36edec31b5217
 * appSecret: 6CE2715036F894F8F48BD85C1335D74D0083A36133C6282B26610E2DB7ED89A3A08F286AF4FF5E620CAE48B8CCD09B5B3657E364087C2F22285BEDDE00C8A95799C46201162E8808
 * apis:
 * checkToken: /api/OpenData/CheckToken
 * getToken: /api/OpenData/GetToken
 * getUserInfo: /api/OpenData/GetUserInfo
 * saveOperateRecordData: /api/OpenData/SaveOperateRecordData
 * env:
 * - name: test
 * host: https://testweb.hnilab.com/
 * - name: prd
 * host: https://www.hnilab.com/
 */
@Data
@ConfigurationProperties("dows.ods")
public class ChannelProperties {
    private List<Pointcut> pointcuts;
    private List<ChannelSetting> endpoints;

    @Data
    public static class Pointcut {
        private Class clazz;
        private List<Method> methods;

        public Method getMethod(String name){
            for (Method method : methods) {
                if(method.getName().equals(name)){
                    return method;
                }
            }
            return null;
        }
    }

    /**
     *           - enable:
     *             name: dd
     *             endpointId: ff
     */
    @Data
    public static class Method {
        private String endpointId;
        private String name;
        private String formMethod;
        private String toMethod;
        private Boolean enable;
        private String env;

        public String getKey(String methodName){
            String[] split = methodName.split("://");
            return this.getEnv()+":"+split[0];
        }

        public String getVal(String methodName){
            String[] split = methodName.split("://");
            return split[1];
        }

        public String getChannelType(String methodName){
            String[] split = methodName.split("://");
            return split[0];
        }
    }

}
