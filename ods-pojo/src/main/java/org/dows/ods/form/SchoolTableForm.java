package org.dows.ods.form;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 应用数据表(SchoolTable)表单
 *
 * @author lait.zhang
 * @since 2022-12-06 15:52:56
 */
@SuppressWarnings("serial")
@Data
@ToString
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "SchoolTableForm 表单对象", description = "应用数据表")
public class SchoolTableForm implements Serializable {
    private static final long serialVersionUID = 907501504286035226L;
    @JsonIgnore
    private Long id;

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

    @JsonIgnore
    private Date dt;

    @JsonIgnore
    private Boolean deleted;


}

