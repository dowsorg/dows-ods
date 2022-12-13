package org.dows.ods.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 字段的值
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValueConfig {
    /*结果集中的字段的值*/
    private Object jsonFieldValue;
    /*对应表中的字段的值*/
    private Object tableFieldValue;
}
