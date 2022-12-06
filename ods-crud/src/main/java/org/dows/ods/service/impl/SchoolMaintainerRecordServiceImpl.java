package org.dows.ods.service.impl;

import org.dows.framework.crud.mybatis.MybatisCrudServiceImpl;
import org.dows.ods.mapper.SchoolMaintainerRecordMapper;
import org.dows.ods.entity.SchoolMaintainerRecord;
import org.dows.ods.service.SchoolMaintainerRecordService;
import org.springframework.stereotype.Service;


/**
 * 维护记录(SchoolMaintainerRecord)表服务实现类
 *
 * @author lait.zhang
 * @since 2022-12-06 15:52:15
 */
@Service("schoolMaintainerRecordService")
public class SchoolMaintainerRecordServiceImpl extends MybatisCrudServiceImpl<SchoolMaintainerRecordMapper, SchoolMaintainerRecord> implements SchoolMaintainerRecordService {

}

