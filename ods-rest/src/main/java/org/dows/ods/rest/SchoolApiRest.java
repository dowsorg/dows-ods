package org.dows.ods.rest;


import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.framework.crud.mybatis.MybatisCrudRest;
import org.dows.ods.entity.SchoolApi;
import org.dows.ods.form.SchoolApiForm;
import org.dows.ods.service.SchoolApiService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 学校接口(SchoolApi)表控制层
 *
 * @author lait.zhang
 * @since 2022-12-06 15:53:31
 */
@Api(tags = "学校接口")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("schoolApi")
public class SchoolApiRest implements MybatisCrudRest<SchoolApiForm, SchoolApi, SchoolApiService> {

    //private final SchoolApiBiz schoolApiBiz;

}

