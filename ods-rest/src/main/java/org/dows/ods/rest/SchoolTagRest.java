package org.dows.ods.rest;


import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.framework.crud.mybatis.MybatisCrudRest;
import org.dows.ods.entity.SchoolTag;
import org.dows.ods.form.SchoolTagForm;
import org.dows.ods.service.SchoolTagService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 学校标签(SchoolTag)表控制层
 *
 * @author lait.zhang
 * @since 2022-12-06 15:53:31
 */
@Api(tags = "学校标签")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("schoolTag")
public class SchoolTagRest implements MybatisCrudRest<SchoolTagForm, SchoolTag, SchoolTagService> {

    //private final SchoolTagBiz schoolTagBiz;

}

