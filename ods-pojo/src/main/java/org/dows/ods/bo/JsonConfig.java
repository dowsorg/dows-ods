package org.dows.ods.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 三方接口的json结果集配置
 * */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JsonConfig {
    /*解析三方接口请求结果的jsonPath*/
    private String jsonPath;
    /*三方接口请求结果数据可以解析出的数据表*/
    private List<TableConfig> tableConfigs;
}
