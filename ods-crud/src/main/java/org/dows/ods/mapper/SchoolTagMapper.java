package org.dows.ods.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.dows.framework.crud.mybatis.MybatisCrudMapper;
import org.dows.ods.entity.SchoolTag;

/**
 * 学校标签(SchoolTag)表数据库访问层
 *
 * @author lait.zhang
 * @since 2022-12-06 15:40:59
 */
@Mapper
public interface SchoolTagMapper extends MybatisCrudMapper<SchoolTag> {

}

