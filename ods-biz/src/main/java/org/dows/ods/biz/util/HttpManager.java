package org.dows.ods.biz.util;

import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.http.Method;
import org.dows.framework.api.exceptions.BizException;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 三方请求工具类
 */
public class HttpManager {
    /**
     * 保存该项目支持的http类型
     */
    private static final Map<String, Method> SUPPORT_METHODS = new HashMap<>();

    static {
        Method[] values = Method.values();
        for (int i = 0; i < values.length; i++) {
            SUPPORT_METHODS.put(values[i].name(), values[i]);
        }
    }

    public static HttpResponse requestForResponse(String method, String url, Map<String, Object> paramMap,
        int timeout) {
        if (StringUtils.hasText(method) && StringUtils.hasText(url)) {
            if (!SUPPORT_METHODS.keySet().contains(method)) {
                throw new BizException("不支持该请求类型");
            }
            return HttpUtil.createRequest(SUPPORT_METHODS.get(method), url)
                .form(paramMap)
                .timeout(timeout)
                .execute();
        }
        throw new BizException("请求类型或请求URL为空，非法参数");
    }
}
