package org.dows.ods.channel.hnilab;


import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@EnableConfigurationProperties(HnIlabProperties.class)
@Configuration
public class HnilabConfig {

    private final HnIlabProperties hnIlabProperties;



    public void init(){
        Field[] declaredFields = HnilabApi.class.getDeclaredFields();
        List<String> fields = new ArrayList<>();
        for (Field declaredField : declaredFields) {
            fields.add(declaredField.getName());
        }
        HnIlabProperties.Env env = hnIlabProperties.getEnv();
        HnilabApi hnilabApi = hnIlabProperties.getHnilabApi();
        String appId = hnIlabProperties.getAppId();
        String appSecret = hnIlabProperties.getAppSecret();
        if (env.getName().equals("test")) {
            for (String field : fields) {

            }
        }
        if(env.getName().equals("prd")){

        }
    }

}
