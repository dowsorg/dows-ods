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
 * 维护记录(SchoolMaintainerRecord)表单
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
@ApiModel(value = "SchoolMaintainerRecordForm 表单对象", description = "维护记录")
public class SchoolMaintainerRecordForm implements Serializable {
    private static final long serialVersionUID = -51020324695870365L;
    @JsonIgnore
    private Long id;

    @ApiModelProperty("学校ID")
    private String schoolId;

    @ApiModelProperty("账号ID")
    private String accountId;

    @ApiModelProperty("账号名")
    private String accountName;

    @ApiModelProperty("维护内容")
    private String content;

    @JsonIgnore
    private Date dt;

    @JsonIgnore
    private Boolean deleted;


}

