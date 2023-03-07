package org.dows.ods.channel.hnilab;

import lombok.Data;
import org.dows.ods.api.ChannelApi;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@Data
public class HnilabApi implements ChannelApi {
    private static final Map<String, Field> fieldMap = new HashMap<>();

    static {
        Field[] declaredFields = HnilabApi.class.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            fieldMap.put(declaredField.getName(), declaredField);
        }
    }

    private String checkToken;
    private String getToken;
    private String getUserInfo;
    private String saveOperateRecordData;

    public static Map<String, Field> getFieldMap() {
        return fieldMap;
    }

    public String getApiUriByMethodName(String name) {
        Field field = fieldMap.get(name);
        if (field != null) {
            try {
                String o = (String) field.get(this);
                return o;
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

}