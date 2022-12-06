package org.dows.ods.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.dows.framework.crud.mybatis.MybatisCrudMapper;
import org.dows.ods.entity.SchoolApp;

/**
 * 学校应用(SchoolApp)表数据库访问层
 *
 * @author lait.zhang
 * @since 2022-12-06 15:40:55
 */
@Mapper
public interface SchoolAppMapper extends MybatisCrudMapper<SchoolApp> {

}

