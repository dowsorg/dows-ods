//package org.dows.ods.client;
//
//import lombok.Data;
//import org.dows.ods.api.OdsResponse;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//
//import javax.annotation.PostConstruct;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@Data
//@ConfigurationProperties(prefix = "dows.ods")
//public class OdsPointcutProperties {
//
//    private List<Endpoint> endpoints;
//    private List<Pointcut> pointcuts;
//
//
//    private final Map<String,Endpoint> endpointMap = new HashMap<>();
//
//    @PostConstruct
//    public void init(){
//        for (OdsPointcutProperties.Pointcut pointcut : pointcuts) {
//            List<Method> methods = pointcut.getMethods();
//            for (Method method : methods) {
//                for (Endpoint endpoint : endpoints) {
//                    if(method.getEndpointId().equals(endpoint.getId())){
//                        String key = pointcut.getClazz()+"#"+method.getName();
//                        endpointMap.put(key,endpoint);
//                    }
//                }
//            }
//        }
//    }
//
//    public Endpoint getEndpoint(String classMethod){
//        return endpointMap.get(classMethod);
//    }
//
//    @Data
//    public static class Pointcut {
//        private Class clazz;
//        private List<Method> methods;
//    }
//
//
//    /**
//     *           - enable:
//     *             name: dd
//     *             endpointId: ff
//     */
//    @Data
//    public static class Method {
//        private String endpointId;
//        private String name;
//        private Boolean enable;
//    }
//
//    @Data
//    public static class Endpoint {
//        private String id;
//        private String type;
//        private Boolean enable;
//        private Host datasource;
//    }
//
//    /**
//     * http://ods.findsoft.com/upload
//     */
//    @Data
//    public static class Host{
//        private String host;
//        private String port;
//        private String username;
//        private String pwd;
//    }
//}
