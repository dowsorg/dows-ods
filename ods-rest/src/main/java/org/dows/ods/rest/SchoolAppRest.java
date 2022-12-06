package org.dows.ods.rest;


import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.framework.crud.mybatis.MybatisCrudRest;
import org.dows.ods.entity.SchoolApp;
import org.dows.ods.form.SchoolAppForm;
import org.dows.ods.service.SchoolAppService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 学校应用(SchoolApp)表控制层
 *
 * @author lait.zhang
 * @since 2022-12-06 15:53:31
 */
@Api(tags = "学校应用")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("schoolApp")
public class SchoolAppRest implements MybatisCrudRest<SchoolAppForm, SchoolApp, SchoolAppService> {

    //private final SchoolAppBiz schoolAppBiz;

}

