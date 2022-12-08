package org.dows.ods.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 学校实例的分页查询请求
 */
@Data
public class SchoolInstancePageReq extends SearchPageRequest {
    @ApiModelProperty(value = "学校名称", notes = "一般用于模糊匹配", required = true)
    private String schoolName;

    @ApiModelProperty(value = "学校code", notes = "一般用于精准匹配", required = true)
    private String schoolCode;
}
