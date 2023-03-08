package org.dows.ods.demo;

import lombok.extern.slf4j.Slf4j;
import org.dows.ods.api.OdsResponse;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class DemoBiz {


    public OdsResponse completeExp(Object parmas){
        String stuId="";
        String expId="";
        // params 包含了学生ID 实验ID
        Map<String,Object> bizJson = new HashMap<>();
        bizJson.put("stuId",stuId);
        bizJson.put("expId",expId);


        // log.
        log.info("咨询业务逻辑 保存数据...");

        OdsResponse odsResponse = new OdsResponse();
        odsResponse.setBizJson(bizJson.toString());


        return odsResponse;
    }
}
