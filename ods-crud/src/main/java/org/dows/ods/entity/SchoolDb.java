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
 * 学校db(SchoolDb)实体类
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
@ApiModel(value = "SchoolDb对象", description = "学校db")
public class SchoolDb implements CrudEntity {
    private static final long serialVersionUID = 512939737049007110L;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty("主键ID")
    private Long id;

    @ApiModelProperty("数据库ID")
    private String dbId;

    @ApiModelProperty("应用ID")
    private String appId;

    @ApiModelProperty("学习ID")
    private String schoolId;

    @ApiModelProperty("应用名")
    private String appName;

    @ApiModelProperty("数据库名")
    private String dbName;

    @ApiModelProperty("数据库用户名")
    private String dbUser;

    @ApiModelProperty("数据库密码")
    private String dbPwd;

    @ApiModelProperty("IP地址")
    private String host;

    @ApiModelProperty("端口")
    private String port;

    @ApiModelProperty("时间戳")
    private Date dt;

    @JsonIgnore
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty("逻辑删除")
    private Boolean deleted;

}

