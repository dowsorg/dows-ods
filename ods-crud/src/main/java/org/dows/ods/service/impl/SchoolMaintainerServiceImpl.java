package org.dows.ods.service.impl;

import org.dows.framework.crud.mybatis.MybatisCrudServiceImpl;
import org.dows.ods.mapper.SchoolMaintainerMapper;
import org.dows.ods.entity.SchoolMaintainer;
import org.dows.ods.service.SchoolMaintainerService;
import org.springframework.stereotype.Service;


/**
 * 学校维护人员(SchoolMaintainer)表服务实现类
 *
 * @author lait.zhang
 * @since 2022-12-06 15:52:15
 */
@Service("schoolMaintainerService")
public class SchoolMaintainerServiceImpl extends MybatisCrudServiceImpl<SchoolMaintainerMapper, SchoolMaintainer> implements SchoolMaintainerService {

}

