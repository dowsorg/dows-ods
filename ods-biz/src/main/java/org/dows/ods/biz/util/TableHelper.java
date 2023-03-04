package org.dows.ods.biz.util;

import org.dows.framework.api.exceptions.BizException;
import org.dows.ods.bo.FieldConfig;
import org.dows.ods.bo.TableConfig;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

/*table解析类的辅助类*/
public class TableHelper {

    public static void validate(TableConfig tableConfig)throws BizException{
        /*数据表的名称和字段的配置都不能为空*/
        if(!StringUtils.hasText(tableConfig.getTableName())){
            throw new BizException("数据表名称必须填写");
        }
        List<FieldConfig> fieldConfigs = tableConfig.getFieldConfigs();
        if(CollectionUtils.isEmpty(fieldConfigs)){
            throw new BizException("表的字段解析规则必须填写");
        }
        // 表中必须存在被标记为主键的字段解析至少有一个
        long count = fieldConfigs.stream().filter(e -> Boolean.TRUE.equals(e.getPrimaryKeyTag())).count();
        if(count==0){
            throw new BizException("数据表解析字段中至少指定一个主键字段");
        }
        // 调用field的规则验证
        for (FieldConfig fieldConfig : fieldConfigs) {
            FieldHelper.validate(fieldConfig);
        }
    }

}
