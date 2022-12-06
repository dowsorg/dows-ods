package org.dows.ods.service.impl;

import org.dows.framework.crud.mybatis.MybatisCrudServiceImpl;
import org.dows.ods.mapper.SchoolAppMapper;
import org.dows.ods.entity.SchoolApp;
import org.dows.ods.service.SchoolAppService;
import org.springframework.stereotype.Service;


/**
 * 学校应用(SchoolApp)表服务实现类
 *
 * @author lait.zhang
 * @since 2022-12-06 15:52:14
 */
@Service("schoolAppService")
public class SchoolAppServiceImpl extends MybatisCrudServiceImpl<SchoolAppMapper, SchoolApp> implements SchoolAppService {

}

