package org.dows.ods.service.impl;

import org.dows.framework.crud.mybatis.MybatisCrudServiceImpl;
import org.dows.ods.mapper.SchoolTagMapper;
import org.dows.ods.entity.SchoolTag;
import org.dows.ods.service.SchoolTagService;
import org.springframework.stereotype.Service;


/**
 * 学校标签(SchoolTag)表服务实现类
 *
 * @author lait.zhang
 * @since 2022-12-06 15:52:15
 */
@Service("schoolTagService")
public class SchoolTagServiceImpl extends MybatisCrudServiceImpl<SchoolTagMapper, SchoolTag> implements SchoolTagService {

}

