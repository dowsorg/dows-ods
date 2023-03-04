package org.dows.ods.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 学校应用数据库创建对象
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SchoolAppCreateVO {
    @ApiModelProperty(value = "应用ID",required = true)
    private String appId;

    @ApiModelProperty(value = "学校ID",required = true)
    private String schoolId;

    @ApiModelProperty(value = "学校名称",notes = "不填写则后端获取")
    private String schoolName;

    @ApiModelProperty(value = "应用名称",required = true)
    private String appName;

    @ApiModelProperty("应用主页")
    private String appHome;

    @ApiModelProperty("描述")
    private String descr;

    @ApiModelProperty("应用供应商")
    private String appProvider;
}
