package org.dows.ods.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import org.dows.framework.crud.mybatis.CrudEntity;

import java.util.Date;

/**
 * 应用数据表(SchoolTable)实体类
 *
 * @author lait.zhang
 * @since 2022-12-06 15:44:51
 */
@SuppressWarnings("serial")
@Data
@ToString
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "SchoolTable对象", description = "应用数据表")
public class SchoolTable implements CrudEntity {
    private static final long serialVersionUID = -96655099010510232L;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty("主键ID")
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

    @ApiModelProperty("时间戳")
    private Date dt;

    @JsonIgnore
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty("逻辑删除")
    private Boolean deleted;

}

