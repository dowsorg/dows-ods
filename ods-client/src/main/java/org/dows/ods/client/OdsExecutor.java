package org.dows.ods.client;

import cn.hutool.core.util.ReflectUtil;
import cn.hutool.extra.spring.SpringUtil;
import cn.hutool.json.JSONUtil;
import org.dows.framework.api.Response;
import org.dows.ods.api.*;
import org.dows.ods.channel.hnilab.HnilabConfig;
import org.springframework.web.client.RestTemplate;

import java.sql.Connection;
import java.util.Map;

public class OdsExecutor {


    public static Response exec(ChannelProperties.Method method,ChannelApi channelApi, OdsResponse odsResponse) {

        String formMethod = method.getFormMethod();
        String channelClient = formMethod.substring(0, formMethod.indexOf(":"));
        String methodName = formMethod.split("://")[1];
        Object bean = SpringUtil.getBean(channelClient);

        ReflectUtil.invoke(bean,methodName,null);
        // 动态根据环境获取对应的接口对象，包含了环境对应的api
        // 获取接口uri 有可能是http 也有可能是 jdbc
        //String apiUri = channelApi.getApiUriByMethodName(odsResponse.getMethod());
        //String type = channelConfig.getDatasourceType();

        String apiUri = channelApi.getApiUriByMethodName(method.getName());
        // todo 执行jdbc 方式
//        if (type.equals("jdbc")) {
//            // todo 根据apiUri 动态创建数据源,并执行
//            // todo 根据apiUri 执行http 方式
//            if(apiUri.startsWith("sql://")){
//                //Connection connection = DataSourceManager.getConnection();
//                //throw new RuntimeException("协议错误");
//                //JSONUtil.toBean(odsResponse.getBizJson());
//                //
//               String sql =  apiUri.split("sql://")[1];
//               // todo 执行form 方法
//
//
//               // todo 执行to 方法
//
//            }
////            DataSourceManager.getConnection();
//            if(!apiUri.startsWith("table://")){
//                throw new RuntimeException("协议错误");
//            }
//
//
//
//
//        } else if (type.equals("http")) {
//            // todo 根据apiUri 执行http 方式
//            if(!apiUri.startsWith("http://") || !apiUri.startsWith("https://")){
//                throw new RuntimeException("协议错误");
//            }
//            // todo 此处从容器获取
//            RestTemplate restTemplate = new RestTemplate();
//            String url = null;
//            if(apiUri.startsWith("get:")){
//                url = apiUri.substring(4);
//                restTemplate.getForEntity(url,null);
//            } else if(apiUri.startsWith("post:")){
//                url = apiUri.substring(5);
//                restTemplate.postForEntity(url,null,String.class);
//            } else if(apiUri.startsWith("delete:")){
//                url = apiUri.substring(7);
//                //restTemplate.delete();
//            } else if(apiUri.startsWith("put:")){
//                url = apiUri.substring(4);
////                restTemplate.put();
//            } else {
//                // 默认都是post
//                restTemplate.postForEntity(apiUri,null,String.class);
//            }
//        }
        return Response.ok();
    }

    public static void post(ChannelProperties.Method method, String url, Object result) {
    }
}
