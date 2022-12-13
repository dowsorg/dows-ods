package org.dows.ods.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 数据表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TableConfig {
    /*数据表的名称*/
    private String tableName;
    /*字段的配置*/
    private List<FieldConfig> fieldConfigs;
}
