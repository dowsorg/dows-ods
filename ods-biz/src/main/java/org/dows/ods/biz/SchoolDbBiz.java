package org.dows.ods.biz;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.ods.service.SchoolDbService;
import org.dows.ods.vo.SchoolDbCreateVO;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SchoolDbBiz {
    private final SchoolDbService schoolDbService;

    /**保存学校数据库信息*/
    public void createSchoolDb(SchoolDbCreateVO createVO){

    }

}
