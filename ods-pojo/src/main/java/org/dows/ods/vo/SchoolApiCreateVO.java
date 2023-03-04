package org.dows.ods.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 学校接口创建
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SchoolApiCreateVO {
    @ApiModelProperty("学校ID")
    private String schoolId;

    @ApiModelProperty(value = "账号ID", notes = "school_token表的主键ID")
    private String authId;

    @ApiModelProperty("http请求方法")
    private String httpMethod;

    @ApiModelProperty("学校API")
    private String apiUrl;

    @ApiModelProperty("json参数")
    private String jsonParams;

    @ApiModelProperty("接口描述")
    private String apiDescr;

    @ApiModelProperty("提取的key[key1,key2,key3]")
    private String extractKey;
}