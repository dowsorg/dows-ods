package org.dows.ods.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.dows.framework.crud.mybatis.MybatisCrudMapper;
import org.dows.ods.entity.SchoolToken;

/**
 * 学校Token(SchoolToken)表数据库访问层
 *
 * @author lait.zhang
 * @since 2022-12-06 15:41:00
 */
@Mapper
public interface SchoolTokenMapper extends MybatisCrudMapper<SchoolToken> {

}

