package org.dows.ods.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 学校数据表创建对象
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SchoolTableCreateVO {

    @ApiModelProperty("数据库ID")
    private String dbId;

    @ApiModelProperty("应用ID")
    private String appId;

    @ApiModelProperty("数据库名")
    private String dbName;

    @ApiModelProperty("表名")
    private String tableName;

    @ApiModelProperty("描述")
    private String tableDescr;
}
