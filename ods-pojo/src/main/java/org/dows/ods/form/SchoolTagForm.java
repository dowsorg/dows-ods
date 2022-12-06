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
 * 学校标签(SchoolTag)表单
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
@ApiModel(value = "SchoolTagForm 表单对象", description = "学校标签")
public class SchoolTagForm implements Serializable {
    private static final long serialVersionUID = 406132753441317039L;
    @JsonIgnore
    private Long id;

    @ApiModelProperty("学校标签")
    private String schoolTag;

    @JsonIgnore
    private Date dt;

    @JsonIgnore
    private Boolean deleted;


}

