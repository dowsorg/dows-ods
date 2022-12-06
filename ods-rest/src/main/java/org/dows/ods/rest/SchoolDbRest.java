package org.dows.ods.rest;


import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.framework.crud.mybatis.MybatisCrudRest;
import org.dows.ods.entity.SchoolDb;
import org.dows.ods.form.SchoolDbForm;
import org.dows.ods.service.SchoolDbService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 学校db(SchoolDb)表控制层
 *
 * @author lait.zhang
 * @since 2022-12-06 15:53:31
 */
@Api(tags = "学校db")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("schoolDb")
public class SchoolDbRest implements MybatisCrudRest<SchoolDbForm, SchoolDb, SchoolDbService> {

    //private final SchoolDbBiz schoolDbBiz;

}

