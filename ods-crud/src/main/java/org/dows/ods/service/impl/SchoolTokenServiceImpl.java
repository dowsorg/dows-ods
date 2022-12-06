package org.dows.ods.service.impl;

import org.dows.framework.crud.mybatis.MybatisCrudServiceImpl;
import org.dows.ods.mapper.SchoolTokenMapper;
import org.dows.ods.entity.SchoolToken;
import org.dows.ods.service.SchoolTokenService;
import org.springframework.stereotype.Service;


/**
 * 学校Token(SchoolToken)表服务实现类
 *
 * @author lait.zhang
 * @since 2022-12-06 15:52:15
 */
@Service("schoolTokenService")
public class SchoolTokenServiceImpl extends MybatisCrudServiceImpl<SchoolTokenMapper, SchoolToken> implements SchoolTokenService {

}

