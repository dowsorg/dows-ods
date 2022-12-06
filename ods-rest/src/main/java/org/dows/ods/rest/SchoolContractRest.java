package org.dows.ods.rest;


import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.framework.crud.mybatis.MybatisCrudRest;
import org.dows.ods.entity.SchoolContract;
import org.dows.ods.form.SchoolContractForm;
import org.dows.ods.service.SchoolContractService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 学校联系信息(SchoolContract)表控制层
 *
 * @author lait.zhang
 * @since 2022-12-06 15:53:31
 */
@Api(tags = "学校联系信息")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("schoolContract")
public class SchoolContractRest implements MybatisCrudRest<SchoolContractForm, SchoolContract, SchoolContractService> {

    //private final SchoolContractBiz schoolContractBiz;

}

