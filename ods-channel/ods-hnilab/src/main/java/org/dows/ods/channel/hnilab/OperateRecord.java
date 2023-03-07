package org.dows.ods.channel.hnilab;

import lombok.Data;

/**
 * "appId": "平台分配的 appid",
 * "ticket": "平台生成的 ticket",
 * "signature": "签名 MD5(appid+secret+ticket)",
 * "projectName": "项目名称",
 * "userAccount": "操作人账号",
 * "userName": "操作人姓名",
 * "startTime": 开始操作时间 时间戳,
 * "endTime": 结束操作时间 时间戳,
 * "operateTime": 操作时长 秒,
 * "isFinish": 是否完成 0 否 1 是,
 * "score": 分数 百分制,
 * "projectReport": {
 * "reportData": "实验报告文档 文件需转换为 byte[]",
 * "fileName": "实验报告名称"
 * },
 * "steps": [
 * {
 * "stepCode": "步骤号",
 * "stepName": "步骤名称",
 * "satrtTime": 开始操作时间 时间戳,
 * "endTime": 结束操作时间 时间戳,
 * "usedTime": 操作时长 秒,
 * "experctTime": 合理时间 秒,
 * "maxScore": 满分 百分制,
 * "score": 得分 百分制,
 * "repatCount": 重复次数,
 * "evaluation": "评价",
 */
@Data
public class OperateRecord {
    //接入平台分配的唯一编号 String 是
    private String appid;
    // 做实验平台生成的 ticket String 是
    private String ticket;
    // 签名 string 是 MD5(appid+secret+ticket)
    private String signature;
    //项目名称 string 是
    private String projectName;
    // 用户账号 string 是
    private String userAccount;
    //用户姓名 string 是
    private String userName;
    //开始操作时间 int 是 时间戳
    private String startTime;
    //结束操作时间 int 是 时间戳
    private String endTime;
    // 操作时长 Int 是 单位：秒
    private Integer operateTime;
    // 是否完成 Int 是 0 否 1 是
    private Integer isFinish;
    // 分数 int 是 百分制
    private Integer score;
    //实验报告 非必填一次操作只有一个实验报告，实验报告说明见下方实验报告
    private Integer projectReport;
    // 实验步骤 是 见下方实验步骤
    private ExperimentStep steps;
}
