package org.dows.ods.biz;

import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.framework.api.exceptions.BizException;
import org.dows.ods.biz.util.*;
import org.dows.ods.bo.JsonConfig;
import org.dows.ods.entity.SchoolApi;
import org.dows.ods.entity.SchoolDb;
import org.dows.ods.entity.SchoolInstance;
import org.dows.ods.entity.SchoolToken;
import org.dows.ods.service.SchoolApiService;
import org.dows.ods.service.SchoolDbService;
import org.dows.ods.service.SchoolInstanceService;
import org.dows.ods.vo.SchoolApiCreateVO;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class SchoolApiBiz {
    private final SchoolApiService schoolApiService;
    private final SchoolInstanceService schoolInstanceService;
    private final SchoolDbService schoolDbService;
    private final SchoolTokenBiz schoolTokenBiz;
    private final SchoolDbBiz schoolDbBiz;

    //创建学校Api
    public void createSchoolApi(SchoolApiCreateVO createVO) {
        SchoolApiUtil.validate(createVO);
        // 该学校内 接口url+请求方式的组合唯一性
        long count = schoolApiService.lambdaQuery()
            .eq(SchoolApi::getSchoolId, createVO.getSchoolId())
            .eq(SchoolApi::getHttpMethod, createVO.getHttpMethod())
            .eq(SchoolApi::getApiUrl, createVO.getApiUrl()).count();
        if (count > 0) {
            throw new BizException("当前学校已存在该接口信息");
        }
        SchoolApi schoolApi = new SchoolApi();
        BeanUtils.copyProperties(createVO, schoolApi);
        schoolApiService.save(schoolApi);
    }

    /**
     *<h1>指定某一api请求数据并同步进应用数据库</h1>
     * <p></p>
     * */
    public void workWithSchoolApi(String schoolApiId, Set<String> dbIdSet) {
        SchoolApi schoolApi = this.getSchoolApiById(schoolApiId);
        String schoolId = schoolApi.getSchoolId();
        String authId = schoolApi.getAuthId();
        // 准备前置信息
        // 学校实例
        SchoolInstance schoolInstance = schoolInstanceService.getById(schoolId);
        SchoolToken schoolToken = schoolTokenBiz.getSchoolTokenById(authId);
        List<SchoolDb> schoolDbs = schoolDbService.listByIds(dbIdSet);
        SingleSchoolApiWorker singleSchoolApiWorker = new SingleSchoolApiWorker();
        singleSchoolApiWorker.setSchoolDbs(schoolDbs);
        singleSchoolApiWorker.setSchoolApi(schoolApi);
        singleSchoolApiWorker.setSchoolInstance(schoolInstance);
        singleSchoolApiWorker.setSchoolToken(schoolToken);
        singleSchoolApiWorker.run();
    }

    // 根据Id查询学校Api
    public SchoolApi getSchoolApiById(String id) {
        SchoolApi schoolApi = schoolApiService.getById(id);
        if (Objects.isNull(schoolApi)) {
            throw new BizException("学校Api接口信息不存在");
        }
        return schoolApi;
    }

    /**
     * 单个学校API的工作者
     */
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    class SingleSchoolApiWorker {
        // 学校信息
        private SchoolInstance schoolInstance;
        // 学校auth token信息
        private SchoolToken schoolToken;
        // 接口规则
        private SchoolApi schoolApi;
        // 需要同步的数据库
        private List<SchoolDb> schoolDbs;

        public void run(){
            // 先根据 学校获取token的方式获取到最新token
            String appKey = schoolToken.getAppKey();
            String secretKey = schoolToken.getSecretKey();
            String jsonParam = schoolToken.getJsonParam();
            HttpResponse tokenResponse = HttpManager.requestForResponse(HttpMethod.POST.name(), schoolToken.getTokenApi(),null, jsonParam, 1000);
            String token = JSONUtil.getByPath(JSONUtil.parse( tokenResponse.body()), schoolToken.getTokenJsonPath(), "");
            Map<String,Object>headerParamMap = new HashMap<>();
            headerParamMap.put("token",Arrays.asList(token));

            String jsonParseStr = schoolApi.getExtractKey();
            String apiUrl = schoolApi.getApiUrl();
            String httpMethod = schoolApi.getHttpMethod();

            HttpResponse jsonResponse = HttpManager.requestForResponse(httpMethod, apiUrl, JSONUtil.toJsonStr(headerParamMap),schoolApi.getJsonParams(), 1000);
            String jsonStr = jsonResponse.body();
            JsonConfig jsonConfig = JsonHelper.parse$ValidateJsonConfig(jsonParseStr);
            Map<String, Map<String, Object>> tableFieldValueMap = JsonHelper.parseJson2TableFieldValueMap(jsonStr, jsonConfig);
            Map<String, List<List<Object>>> sqlParamListMap = SqlUtil.generateSqlMapByTableFieldValueMap(tableFieldValueMap);
            for (SchoolDb schoolDb : schoolDbs) {
                String dbUrl = SchoolDbUtil.generateDbUrlBySchoolDbInstance(schoolDb);
                try {
                    Connection connection = DataSourceManager.getConnection(dbUrl, schoolDb.getDbUser(), schoolDb.getDbPwd());
                    DataSourceManager.batchExecute(connection,sqlParamListMap);
                } catch (SQLException throwable) {
                    throwable.printStackTrace();
                }
            }
        }
    }
}
