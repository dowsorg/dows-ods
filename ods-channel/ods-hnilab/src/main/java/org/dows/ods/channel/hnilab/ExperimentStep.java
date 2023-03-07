package org.dows.ods.channel.hnilab;

import lombok.Data;

@Data
public class ExperimentStep {
    //步骤号 string 是
    private String stepCode;
    //步骤名称 string 是
    private String stepName;
    // 开始时间 int 是 时间戳
    private Integer satrtTime;
    //结束时间 int 是 时间戳
    private Integer endTime;
    // 用时 int 是 单位：秒
    private Integer usedTime;
    // 合理时间 int 是 单位：秒
    private Integer experctTime;
    //满分 int 是 百分制
    private Integer maxScore;
    // 得分 int 是 百分制
    private Integer score;
    // 重复次数 int 是
    private Integer repatCount;
    //评价 string 非必填
    private String evaluation;
    //赋分模型 string 非必填 如果有，填写每一步的得分点
    private String scoringModel;

}
