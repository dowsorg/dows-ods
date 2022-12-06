package org.dows.ods.rest;


import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.framework.crud.mybatis.MybatisCrudRest;
import org.dows.ods.entity.SchoolMaintainerRecord;
import org.dows.ods.form.SchoolMaintainerRecordForm;
import org.dows.ods.service.SchoolMaintainerRecordService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 维护记录(SchoolMaintainerRecord)表控制层
 *
 * @author lait.zhang
 * @since 2022-12-06 15:53:31
 */
@Api(tags = "维护记录")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("schoolMaintainerRecord")
public class SchoolMaintainerRecordRest implements MybatisCrudRest<SchoolMaintainerRecordForm, SchoolMaintainerRecord, SchoolMaintainerRecordService> {

    //private final SchoolMaintainerRecordBiz schoolMaintainerRecordBiz;

}

