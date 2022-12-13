package org.dows.ods.biz;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.ods.vo.SchoolApiCreateVO;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SchoolApiBiz {

    //创建学校Api
    public void createSchoolApi(SchoolApiCreateVO createVO) {
        
        // 该学校内 接口url+请求方式的组合唯一性

    }
}
