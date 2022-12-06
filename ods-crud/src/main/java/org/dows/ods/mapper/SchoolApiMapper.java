package org.dows.ods.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.dows.framework.crud.mybatis.MybatisCrudMapper;
import org.dows.ods.entity.SchoolApi;

/**
 * 学校接口(SchoolApi)表数据库访问层
 *
 * @author lait.zhang
 * @since 2022-12-06 15:40:55
 */
@Mapper
public interface SchoolApiMapper extends MybatisCrudMapper<SchoolApi> {

}

