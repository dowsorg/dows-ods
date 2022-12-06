package org.dows.ods.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.dows.framework.crud.mybatis.MybatisCrudMapper;
import org.dows.ods.entity.SchoolDb;

/**
 * 学校db(SchoolDb)表数据库访问层
 *
 * @author lait.zhang
 * @since 2022-12-06 15:40:56
 */
@Mapper
public interface SchoolDbMapper extends MybatisCrudMapper<SchoolDb> {

}

