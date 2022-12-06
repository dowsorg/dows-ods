package org.dows.ods.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.dows.framework.crud.mybatis.MybatisCrudMapper;
import org.dows.ods.entity.SchoolTable;

/**
 * 应用数据表(SchoolTable)表数据库访问层
 *
 * @author lait.zhang
 * @since 2022-12-06 15:40:58
 */
@Mapper
public interface SchoolTableMapper extends MybatisCrudMapper<SchoolTable> {

}

