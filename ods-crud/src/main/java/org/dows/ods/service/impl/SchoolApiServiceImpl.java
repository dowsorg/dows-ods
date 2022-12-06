package org.dows.ods.service.impl;

import org.dows.framework.crud.mybatis.MybatisCrudServiceImpl;
import org.dows.ods.mapper.SchoolApiMapper;
import org.dows.ods.entity.SchoolApi;
import org.dows.ods.service.SchoolApiService;
import org.springframework.stereotype.Service;


/**
 * 学校接口(SchoolApi)表服务实现类
 *
 * @author lait.zhang
 * @since 2022-12-06 15:52:14
 */
@Service("schoolApiService")
public class SchoolApiServiceImpl extends MybatisCrudServiceImpl<SchoolApiMapper, SchoolApi> implements SchoolApiService {

}

