package org.dows.ods.rest;


import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.framework.crud.mybatis.MybatisCrudRest;
import org.dows.ods.entity.SchoolInstance;
import org.dows.ods.form.SchoolInstanceForm;
import org.dows.ods.service.SchoolInstanceService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 学校(SchoolInstance)表控制层
 *
 * @author lait.zhang
 * @since 2022-12-06 15:53:31
 */
@Api(tags = "学校")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("schoolInstance")
public class SchoolInstanceRest implements MybatisCrudRest<SchoolInstanceForm, SchoolInstance, SchoolInstanceService> {

    //private final SchoolInstanceBiz schoolInstanceBiz;

}

