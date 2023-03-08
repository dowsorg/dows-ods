package org.dows.ods.api;

import java.lang.reflect.Field;
import java.util.Map;

public interface ChannelApi {
    String getApiUriByMethodName(String name);

    Map<String, Field>  getFieldMap();
}
