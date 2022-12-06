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
 * 学校应用(SchoolApp)实体类
 *
 * @author lait.zhang
 * @since 2022-12-06 15:44:50
 */
@SuppressWarnings("serial")
@Data
@ToString
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "SchoolApp对象", description = "学校应用")
public class SchoolApp implements CrudEntity {
    private static final long serialVersionUID = 152030882814131108L;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty("主键ID")
    private Long id;

    @ApiModelProperty("应用ID")
    private String appId;

    @ApiModelProperty("学校ID")
    private String schoolId;

    @ApiModelProperty("学校名称")
    private String schoolName;

    @ApiModelProperty("应用名称")
    private String appName;

    @ApiModelProperty("应用主页")
    private String appHome;

    @ApiModelProperty("描述")
    private String descr;

    @ApiModelProperty("应用供应商")
    private String appProvider;

    @ApiModelProperty("时间戳")
    private Date dt;

    @JsonIgnore
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty("逻辑删除")
    private Boolean deleted;

}

