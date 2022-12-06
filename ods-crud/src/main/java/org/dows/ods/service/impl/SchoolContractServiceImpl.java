package org.dows.ods.service.impl;

import org.dows.framework.crud.mybatis.MybatisCrudServiceImpl;
import org.dows.ods.mapper.SchoolContractMapper;
import org.dows.ods.entity.SchoolContract;
import org.dows.ods.service.SchoolContractService;
import org.springframework.stereotype.Service;


/**
 * 学校联系信息(SchoolContract)表服务实现类
 *
 * @author lait.zhang
 * @since 2022-12-06 15:52:14
 */
@Service("schoolContractService")
public class SchoolContractServiceImpl extends MybatisCrudServiceImpl<SchoolContractMapper, SchoolContract> implements SchoolContractService {

}

