package org.dows.ods.rest;


import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.framework.crud.mybatis.MybatisCrudRest;
import org.dows.ods.entity.SchoolTable;
import org.dows.ods.form.SchoolTableForm;
import org.dows.ods.service.SchoolTableService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 应用数据表(SchoolTable)表控制层
 *
 * @author lait.zhang
 * @since 2022-12-06 15:53:31
 */
@Api(tags = "应用数据表")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("schoolTable")
public class SchoolTableRest implements MybatisCrudRest<SchoolTableForm, SchoolTable, SchoolTableService> {

    //private final SchoolTableBiz schoolTableBiz;

}

