package org.dows.ods.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.dows.framework.crud.mybatis.MybatisCrudMapper;
import org.dows.ods.entity.SchoolMaintainerRecord;

/**
 * 维护记录(SchoolMaintainerRecord)表数据库访问层
 *
 * @author lait.zhang
 * @since 2022-12-06 15:40:57
 */
@Mapper
public interface SchoolMaintainerRecordMapper extends MybatisCrudMapper<SchoolMaintainerRecord> {

}

