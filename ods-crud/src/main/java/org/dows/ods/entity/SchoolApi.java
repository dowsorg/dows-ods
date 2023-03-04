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
import org.dows.ods.bo.JsonConfig;

import java.util.Date;

/**
 * 学校接口(SchoolApi)实体类
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
@ApiModel(value = "SchoolApi对象", description = "学校接口")
public class SchoolApi implements CrudEntity {
    private static final long serialVersionUID = 176688725898925687L;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty("主键ID")
    private Long id;

    @ApiModelProperty("学校ID")
    private String schoolId;

    @ApiModelProperty("账号ID")
    private String authId;

    @ApiModelProperty("http请求方法")
    private String httpMethod;

    @ApiModelProperty("学校API")
    private String apiUrl;

    @ApiModelProperty("json参数")
    private String jsonParams;

    @ApiModelProperty("接口描述")
    private String apiDescr;

    /**
     * <p>实际存放的是{@link JsonConfig} 结构的json字符串</p>
     */
    @ApiModelProperty("提取的key[key1,key2,key3]")
    private String extractKey;

    @ApiModelProperty("时间戳")
    private Date dt;

    @JsonIgnore
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty("逻辑删除")
    private Boolean deleted;

}

