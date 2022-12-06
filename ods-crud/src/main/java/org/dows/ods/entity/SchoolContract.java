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
 * 学校联系信息(SchoolContract)实体类
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
@ApiModel(value = "SchoolContract对象", description = "学校联系信息")
public class SchoolContract implements CrudEntity {
    private static final long serialVersionUID = 566888739735884361L;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty("主键ID")
    private Long id;

    @ApiModelProperty("学校ID")
    private String schoolId;

    @ApiModelProperty("联系人")
    private String contracter;

    @ApiModelProperty("联系电话")
    private String phone;

    @ApiModelProperty("电话")
    private String telphone;

    @ApiModelProperty("时间戳")
    private Date dt;

    @JsonIgnore
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty("逻辑删除")
    private Boolean deleted;

}

