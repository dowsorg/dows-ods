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
 * 学校Token(SchoolToken)实体类
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
@ApiModel(value = "SchoolToken对象", description = "学校Token")
public class SchoolToken implements CrudEntity {
    private static final long serialVersionUID = 784917787592800564L;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty("主键ID")
    private Long id;

    @ApiModelProperty("学校ID")
    private String schoolId;

    @ApiModelProperty("账号名")
    private String appKey;

    @ApiModelProperty("密钥")
    private String secretKey;

    @ApiModelProperty("tokenApi")
    private String tokenApi;

    @ApiModelProperty(value = "json参数",notes = "可以转换成 Map<String,Object> 类型的 map")
    private String jsonParam;

    @ApiModelProperty("时间戳")
    private Date dt;

    // TODO 解析结果集的规则字段
    @ApiModelProperty(value = "从tokenApi请求结果解析出token的jsonPath")
    private String tokenJsonPath;

    @JsonIgnore
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty("逻辑删除")
    private Boolean deleted;

}

