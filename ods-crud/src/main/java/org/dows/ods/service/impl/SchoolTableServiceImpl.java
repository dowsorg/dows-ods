package org.dows.ods.service.impl;

import org.dows.framework.crud.mybatis.MybatisCrudServiceImpl;
import org.dows.ods.mapper.SchoolTableMapper;
import org.dows.ods.entity.SchoolTable;
import org.dows.ods.service.SchoolTableService;
import org.springframework.stereotype.Service;


/**
 * 应用数据表(SchoolTable)表服务实现类
 *
 * @author lait.zhang
 * @since 2022-12-06 15:52:15
 */
@Service("schoolTableService")
public class SchoolTableServiceImpl extends MybatisCrudServiceImpl<SchoolTableMapper, SchoolTable> implements SchoolTableService {

}

