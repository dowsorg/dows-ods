package org.dows.ods.api;

public interface ChannelConfig {
    ChannelApi getChannelApi();

    ChannelProperties getChannelProperties();

    String getChannelName();

    // 获取数据源类型
    //String getDatasourceType();
}
