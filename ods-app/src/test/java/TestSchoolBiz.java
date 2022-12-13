import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.Method;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.jayway.jsonpath.JsonPath;
import org.dows.ods.biz.SchoolInstanceBiz;
import org.dows.ods.biz.SchoolTokenBiz;
import org.dows.ods.biz.exception.SchoolInstanceException;
import org.dows.ods.biz.util.*;
import org.dows.ods.entity.SchoolToken;
import org.dows.ods.vo.SchoolInstanceCreateVO;
import org.dows.ods.vo.SchoolTokenCreateVO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ResourceUtils;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest(classes = { org.dows.ods.OdsApplication.class })
public class TestSchoolBiz {
    @Resource
    private SchoolInstanceBiz schoolInstanceBiz;
    @Resource
    private SchoolTokenBiz schoolTokenBiz;

    @Test
    public void context() throws Exception {
        //      testSchoolInstance();
        //      insertSchoolApi();
        //      getToken();
        //        testText();
        //        testReadJsonText();
        //        testSchoolDb();
        Map<String, Map<String, Object>> stringMapMap = testParseRule();
        System.out.println(stringMapMap);
    }

    public void testSchoolInstance() {
        SchoolInstanceCreateVO createVO = new SchoolInstanceCreateVO();
        createVO.setSchoolCode("baidu");
        createVO.setSchoolName("百度学院");
        schoolInstanceBiz.createSchoolInstance(createVO);
    }

    // 插入一条学校获取token的信息
    public void insertBaiduApi() {
        SchoolTokenCreateVO createVO = new SchoolTokenCreateVO();
        createVO.setSchoolId(String.valueOf(1600696191920644100L));
        createVO.setJsonParam(
            "{\"grant_type\":\"client_credentials\",\"client_id\":\"591yF8rMe76IFeNcUgSMOxbc\",\"client_secret\":\"fE7thYEYqrUtrKrhUvyAW6BCjCQSjGec\"}");
        createVO.setTokenApi("https://aip.baidubce.com/oauth/2.0/token");
        schoolTokenBiz.createSchoolToken(createVO);
    }

    // 获取某一学校的token来获取最新token
    public void getToken() {
        List<SchoolToken> schoolTokenBySchool = schoolTokenBiz
            .getSchoolTokensBySchool(String.valueOf(1600696191920644100L));
        if (schoolTokenBySchool.isEmpty()) {
            throw new SchoolInstanceException("使用的学校无token 获取方式");
        }
        SchoolToken schoolToken = schoolTokenBySchool.get(0);

        HttpResponse response = HttpManager.requestForResponse(Method.POST.name(), schoolToken.getTokenApi(),
            JSONUtil.toBean(schoolToken.getJsonParam(), HashMap.class), 3000);
        System.out.println(response.body());
    }

    public void testText() {
        JSONObject json = JSONUtil.createObj();
        json.putOnce("text", "你好");
        HttpRequest request = HttpRequest
            .post(
                "https://aip.baidubce.com/rpc/2.0/nlp/v1/sentiment_classify?charset=UTF-8&access_token=24.414722e7cbcba105b798230b50a8c381.2592000.1673080230.282335-26345533")
            .body(json.toString()).timeout(20000);
        String result = request.execute().body();
        String jsonPath$Rule = "$.items|User[]";
        net.minidev.json.JSONArray sentiment = JsonPath.read(result, "$.items");
        for (Object o : sentiment) {
            LinkedHashMap map = (LinkedHashMap) o;
            System.out.println(map);
        }
        System.out.println(sentiment);
    }

    public void testReadJsonText() {
        try {
            File file = ResourceUtils.getFile("classpath:jsonPath$StudentRule.txt");
            FileInputStream in = new FileInputStream(file);
            String jsonPath$StudentRule = new String(in.readAllBytes());
            file = ResourceUtils.getFile("classpath:studentJson.json");
            in = new FileInputStream(file);
            String studentSource = new String(in.readAllBytes());
            Map<String, Map<String, Object>> stringMapMap = JsonParseHelper
                .parseJson2TableFieldValueMap(studentSource, jsonPath$StudentRule);
            // 输入map
            Map<String, List<List<Object>>> sqlParamListMap = SqlUtil.generateSqlMapByTableFieldValueMap(stringMapMap);
            Connection connection = testSchoolDb();
            DataSourceManager.batchExecute(connection, sqlParamListMap);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Connection testSchoolDb() throws SQLException {
        String host = "192.168.1.93";
        String port = "";
        String dbName = "ae_test";
        String userName = "root";
        String password = "123456";
        Connection connection = DataSourceManager
            .getConnection(SchoolDbUtil.generateDbUrlBySchoolDbInstance(host, port, dbName), userName, password);
        return connection;
    }

    public Map<String, Map<String, Object>> testParseRule() {
        try {
            File file = ResourceUtils.getFile("classpath:JsonParse.json");
            FileInputStream in = new FileInputStream(file);
            String jsonPath$TableRule = new String(in.readAllBytes());

            file = ResourceUtils.getFile("classpath:studentJson.json");
            in = new FileInputStream(file);
            String studentSource = new String(in.readAllBytes());
            Map<String, Map<String, Object>> stringMapMap = JsonParseHelper
                .parseJson2TableFieldValueMap(studentSource, jsonPath$TableRule);
            // 输入map
            Map<String, List<List<Object>>> stringListMap = SqlUtil.generateSqlMapByTableFieldValueMap(stringMapMap);
            System.out.println(stringListMap);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
