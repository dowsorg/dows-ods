package org.dows.ods.biz;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.framework.api.exceptions.BizException;
import org.dows.ods.biz.exception.SchoolInstanceException;
import org.dows.ods.entity.SchoolInstance;
import org.dows.ods.entity.SchoolToken;
import org.dows.ods.service.SchoolInstanceService;
import org.dows.ods.service.SchoolTokenService;
import org.dows.ods.vo.SchoolTokenCreateVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class SchoolTokenBiz {
    private final SchoolTokenService schoolTokenService;
    private final SchoolInstanceService schoolInstanceService;

    //创建学校获取token的途径
    public void createSchoolToken(SchoolTokenCreateVO createVO) throws BizException {
        SchoolInstance schoolInstance = schoolInstanceService.getById(createVO.getSchoolId());
        if (Objects.isNull(schoolInstance)) {
            throw new SchoolInstanceException("使用的学校实例不存在");
        }
        SchoolToken token = new SchoolToken();
        BeanUtils.copyProperties(createVO, token);
        schoolTokenService.save(token);
    }

    // 查询学校下所有Token
    public List<SchoolToken> getSchoolTokenBySchool(String schoolId) {
        return schoolTokenService.lambdaQuery()
            .eq(SchoolToken::getSchoolId, schoolId)
            .list();
    }

    // 根据ID删除某一批Token获取方式
    @Transactional(rollbackFor = Exception.class)
    public void batchDeleteSchoolTokenByIds(Set<Long> ids) {
        schoolTokenService.removeByIds(ids);
    }

    // 批量删除某一批学校的Token
    @Transactional(rollbackFor = Exception.class)
    public void batchDeleteSchoolTokenBySchoolIds(Set<Long> schoolIds) {
        schoolTokenService.remove(new LambdaQueryWrapper<SchoolToken>().in(SchoolToken::getSchoolId, schoolIds));
    }
}