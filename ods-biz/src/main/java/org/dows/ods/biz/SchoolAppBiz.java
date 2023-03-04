package org.dows.ods.biz;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.framework.api.exceptions.BizException;
import org.dows.ods.biz.util.SchoolAppUtil;
import org.dows.ods.entity.SchoolApp;
import org.dows.ods.entity.SchoolInstance;
import org.dows.ods.service.SchoolAppService;
import org.dows.ods.vo.SchoolAppCreateVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class SchoolAppBiz {
    private final SchoolAppService schoolAppService;
    private final SchoolInstanceBiz schoolInstanceBiz;

    // 创建学校应用
    public void createSchoolApp(SchoolAppCreateVO createVO) {
        SchoolAppUtil.validate(createVO);
        if (!StringUtils.hasText(createVO.getSchoolName())) {
            SchoolInstance schoolInstanceById = schoolInstanceBiz.getSchoolInstanceById(createVO.getSchoolId());
            createVO.setSchoolName(schoolInstanceById.getSchoolName());
        }
        // bean的属性复制
        SchoolApp app = new SchoolApp();
        BeanUtils.copyProperties(createVO, app);
        schoolAppService.save(app);
    }

    // 查询某一个应用
    public SchoolApp getSchoolAppById(String id) {
        SchoolApp app = schoolAppService.getById(id);
        if (Objects.isNull(app)) {
            throw new BizException("学校应用不存在");
        }
        return app;
    }

    // 查询某一学校的所有应用
    public List<SchoolApp> getAppsBySchoolId(String schoolId) {
        return schoolAppService.lambdaQuery()
            .eq(SchoolApp::getSchoolId, schoolId)
            .list();
    }
}
