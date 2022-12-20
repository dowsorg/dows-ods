package org.dows.ods.biz;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.framework.api.exceptions.BizException;
import org.dows.ods.biz.util.SchoolDbUtil;
import org.dows.ods.entity.SchoolApp;
import org.dows.ods.entity.SchoolDb;
import org.dows.ods.service.SchoolDbService;
import org.dows.ods.vo.SchoolDbCreateVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class SchoolDbBiz {
    private final SchoolDbService schoolDbService;
    private final SchoolAppBiz schoolAppBiz;

    /**保存学校数据库信息*/
    public void createSchoolDb(SchoolDbCreateVO createVO){
        SchoolDbUtil.validate(createVO);
        if(!StringUtils.hasText(createVO.getAppName())){
            String appId = createVO.getAppId();
            SchoolApp schoolAppById = schoolAppBiz.getSchoolAppById(appId);
            createVO.setAppName(schoolAppById.getAppName());
        }
        SchoolDb db = new SchoolDb();
        BeanUtils.copyProperties(createVO,db);
        schoolDbService.save(db);
        return;
    }

    public SchoolDb getSchoolDbById(String id){
        SchoolDb db = schoolDbService.getById(id);
        if (Objects.isNull(db)) {
            throw new BizException("学校数据库信息不存在");
        }
        return db;
    }

}
