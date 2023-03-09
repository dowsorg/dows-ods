package org.dows.ods.channel.hnilab.client;

import cn.hutool.core.util.StrUtil;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.List;

public class UrlInterceptor implements Interceptor {
 
    @Override
    public Response intercept(Chain chain) throws IOException {
        //获取request
        Request request = chain.request();
        //从request中获取原有的HttpUrl实例oldHttpUrl
        HttpUrl oldHttpUrl = request.url();

        List<String> headerValues = request.headers("domain");
        //获取request的创建者builder
        Request.Builder builder = request.newBuilder();
        //从request中获取headers，通过给定的键url_name
        //如果有这个header，先将配置的header删除，因此header仅用作app和okhttp之间使用
        builder.removeHeader("domain");
        //匹配获得新的BaseUrl
        int domainType = 0;// 0：动态 1：固定
        for (int i = 0; i < headerValues.size(); i++) {
            if (headerValues.get(i).equals("fixed")) {
                domainType = 1;
            }
        }

        //根据业务逻辑，动态获取的服务器地址
        String baseUrl = "";
 
        if (StrUtil.isNotBlank(baseUrl)) {
            HttpUrl newBaseUrl = null;
            newBaseUrl = HttpUrl.parse(baseUrl);
 
            //重建新的HttpUrl，修改需要修改的url部分
            HttpUrl newFullUrl = oldHttpUrl
                    .newBuilder()
                    .scheme(newBaseUrl.scheme())
                    .host(newBaseUrl.host())//更换主机名
                    .port(newBaseUrl.port())//更换端口
                    .build();
            //重建这个request，通过builder.url(newFullUrl).build()；
            // 然后返回一个response至此结束修改
            return chain.proceed(builder.url(newFullUrl).build());
        }
        return chain.proceed(chain.request());
    }
}
