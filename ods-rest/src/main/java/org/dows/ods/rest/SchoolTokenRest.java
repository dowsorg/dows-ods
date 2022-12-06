package org.dows.ods.rest;


import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.framework.crud.mybatis.MybatisCrudRest;
import org.dows.ods.entity.SchoolToken;
import org.dows.ods.form.SchoolTokenForm;
import org.dows.ods.service.SchoolTokenService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 学校Token(SchoolToken)表控制层
 *
 * @author lait.zhang
 * @since 2022-12-06 15:53:32
 */
@Api(tags = "学校Token")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("schoolToken")
public class SchoolTokenRest implements MybatisCrudRest<SchoolTokenForm, SchoolToken, SchoolTokenService> {

    //private final SchoolTokenBiz schoolTokenBiz;

}

