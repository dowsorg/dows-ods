package org.dows.ods.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 学校创建传输数据对象(接受前端传输)
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SchoolInstanceCreateVO {

    @ApiModelProperty(value = "学校名称", required = true)
    private String schoolName;

    @ApiModelProperty(value = "学校code", required = true)
    private String schoolCode;

    @ApiModelProperty(value = "学校官网", required = false)
    private String schoolHome;
}
