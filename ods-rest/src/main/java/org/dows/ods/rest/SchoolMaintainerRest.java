package org.dows.ods.rest;


import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.framework.crud.mybatis.MybatisCrudRest;
import org.dows.ods.entity.SchoolMaintainer;
import org.dows.ods.form.SchoolMaintainerForm;
import org.dows.ods.service.SchoolMaintainerService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 学校维护人员(SchoolMaintainer)表控制层
 *
 * @author lait.zhang
 * @since 2022-12-06 15:53:31
 */
@Api(tags = "学校维护人员")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("schoolMaintainer")
public class SchoolMaintainerRest implements MybatisCrudRest<SchoolMaintainerForm, SchoolMaintainer, SchoolMaintainerService> {

    //private final SchoolMaintainerBiz schoolMaintainerBiz;

}

