package org.dows.ods.service.impl;

import org.dows.framework.crud.mybatis.MybatisCrudServiceImpl;
import org.dows.ods.mapper.SchoolInstanceMapper;
import org.dows.ods.entity.SchoolInstance;
import org.dows.ods.service.SchoolInstanceService;
import org.springframework.stereotype.Service;


/**
 * 学校(SchoolInstance)表服务实现类
 *
 * @author lait.zhang
 * @since 2022-12-06 15:52:15
 */
@Service("schoolInstanceService")
public class SchoolInstanceServiceImpl extends MybatisCrudServiceImpl<SchoolInstanceMapper, SchoolInstance> implements SchoolInstanceService {

}

