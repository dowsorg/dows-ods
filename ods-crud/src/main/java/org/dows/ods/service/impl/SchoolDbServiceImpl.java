package org.dows.ods.service.impl;

import org.dows.framework.crud.mybatis.MybatisCrudServiceImpl;
import org.dows.ods.mapper.SchoolDbMapper;
import org.dows.ods.entity.SchoolDb;
import org.dows.ods.service.SchoolDbService;
import org.springframework.stereotype.Service;


/**
 * 学校db(SchoolDb)表服务实现类
 *
 * @author lait.zhang
 * @since 2022-12-06 15:52:15
 */
@Service("schoolDbService")
public class SchoolDbServiceImpl extends MybatisCrudServiceImpl<SchoolDbMapper, SchoolDb> implements SchoolDbService {

}

