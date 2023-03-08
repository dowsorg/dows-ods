import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.http.Method;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.jayway.jsonpath.JsonPath;
import org.dows.ods.biz.*;
import org.dows.ods.biz.SchoolInstanceException;
import org.dows.ods.biz.util.*;
import org.dows.ods.bo.JsonConfig;
import org.dows.ods.entity.*;
import org.dows.ods.vo.SchoolInstanceCreateVO;
import org.dows.ods.vo.SchoolTokenCreateVO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

@SpringBootTest(classes = { org.dows.ods.OdsApplication.class })
public class TestSchoolBiz {
    @Resource
    private SchoolInstanceBiz schoolInstanceBiz;
    @Resource
    private SchoolTokenBiz schoolTokenBiz;
    @Resource
    private SchoolAppBiz schoolAppBiz;
    @Resource
    private SchoolDbBiz schoolDbBiz;
    @Resource
    private SchoolApiBiz schoolApiBiz;
    @Resource
    private SchoolTableBiz schoolTableBiz;

    @Test
    public void context() throws Exception {
        //      testSchoolInstance();
        //      insertSchoolApi();
        //      getToken();
        //        testText();
        //        testReadJsonText();
        //        testSchoolDb();
        //        Map<String, Map<String, Object>> stringMapMap = testParseRule();
        //        System.out.println(stringMapMap);
//        initData();
        doWorker();
    }

    public void initData() {
        SchoolInstance schoolInstance = schoolInstanceBiz.getSchoolInstanceById("1600696191920644100");
        // 创建学校应用
        //        SchoolAppCreateVO schoolAppCreateVO = new SchoolAppCreateVO();
        //        schoolAppCreateVO.setSchoolId(String.valueOf(schoolInstanceById.getId()));
        //        schoolAppCreateVO.setSchoolName(schoolInstanceById.getSchoolName());
        //        schoolAppCreateVO.setAppName("hm");
        //        schoolAppBiz.createSchoolApp(schoolAppCreateVO);
        // 创建学校db
        List<SchoolApp> appsBySchoolId = schoolAppBiz.getAppsBySchoolId(String.valueOf(schoolInstance.getId()));
        SchoolApp app = appsBySchoolId.get(0);
        //        SchoolDbCreateVO schoolDbCreateVO = new SchoolDbCreateVO();
        //        schoolDbCreateVO.setSchoolId(String.valueOf(schoolInstanceById.getId()));
        //        schoolDbCreateVO.setAppName(app.getAppName());
        //        schoolDbCreateVO.setAppId(app.getId().toString());
        //        schoolDbCreateVO.setDbName(app.getAppName());
        //        schoolDbCreateVO.setDbUser("root");
        //        schoolDbCreateVO.setDbPwd("123456");
        //        schoolDbCreateVO.setHost("192.168.1.93");
        //        schoolDbCreateVO.setPort("3306");
        //        schoolDbBiz.createSchoolDb(schoolDbCreateVO);
        SchoolDb schoolDb = schoolDbBiz.getSchoolDbById("1");
        // 创建token
        //        SchoolTokenCreateVO createVO = new SchoolTokenCreateVO();
        //        createVO.setSchoolId(schoolInstanceById.getId().toString());
        //        createVO.setTokenApi("http://192.168.1.147:7040/pmcp/api/user/login");
        //        createVO.setJsonParam("{\"cas\":false,\"code\":\"admin\",\"password\":\"123456\"}");
        //        schoolTokenBiz.createSchoolToken(createVO);
        SchoolToken schoolToken = schoolTokenBiz.getSchoolTokenById("2");
        HttpResponse response = HttpManager
            .requestForResponse(Method.POST.name(), null,schoolToken.getTokenApi(), schoolToken.getJsonParam(), 1000);
        String token = JSONUtil.getByPath(JSONUtil.parse(response.body()), schoolToken.getTokenJsonPath(), "");

        // 创建API
        //        SchoolApiCreateVO createVO = new SchoolApiCreateVO();
        //        createVO.setSchoolId(schoolInstance.getId().toString());
        //        createVO.setAuthId(schoolToken.getId().toString());
        //        createVO.setHttpMethod("POST");
        //        createVO.setJsonParams("{\"pageNo\":1,\"pageSize\":20,\"queryParam\":{\"role\":0}}");
        //        createVO.setExtractKey("{\"jsonPath\":\"$.data.list\",\"tables\":[{\"tableName\":\"user\",\"fields\":[{\"jsonFieldName\":\"code\",\"tableFieldName\":\"code\",\"values\":[],\"defaultValue\":0,\"primaryKeyTag\":true},{\"jsonFieldName\":\"name\",\"tableFieldName\":\"name\",\"values\":[],\"defaultValue\":\"\"},{\"jsonFieldName\":\"sex\",\"tableFieldName\":\"sex\",\"values\":[{\"jsonFieldValue\":1,\"tableFieldValue\":1},{\"jsonFieldValue\":0,\"tableFieldValue\":0}],\"defaultValue\":0,\"primaryKeyTag\":false},{\"jsonFieldName\":\"\",\"tableFieldName\":\"admin\",\"values\":[],\"defaultValue\":0,\"primaryKeyTag\":false},{\"jsonFieldName\":\"\",\"tableFieldName\":\"teacher\",\"values\":[],\"defaultValue\":0,\"primaryKeyTag\":false},{\"jsonFieldName\":\"\",\"tableFieldName\":\"student\",\"values\":[],\"defaultValue\":1,\"primaryKeyTag\":false,\"111\":11}]},{\"tableName\":\"user_auth\",\"fields\":[{\"jsonFieldName\":\"code\",\"tableFieldName\":\"userCode\",\"defaultValue\":0,\"primaryKeyTag\":true},{\"jsonFieldName\":\"\",\"tableFieldName\":\"password\",\"values\":[],\"defaultValue\":123456,\"encryptValueTag\":true,\"crypto\":{\"cryptoType\":\"md5\"}}]}]}");
        //        createVO.setApiUrl("http://192.168.1.147:7040/pmcp/api/user/queryUserList");
        //        schoolApiBiz.createSchoolApi(createVO);

        // 查询API
        SchoolApi schoolApi = schoolApiBiz.getSchoolApiById("1");

        HttpRequest request = HttpUtil.createRequest(Method.POST, schoolApi.getApiUrl())
            .body(schoolApi.getJsonParams()).header("token",token)
            .timeout(1000);
        String resJsonStr = HttpManager.requestForResponse(request).body();
        JsonConfig jsonConfig = JsonHelper.parse$ValidateJsonConfig(schoolApi.getExtractKey());

        Map<String, Map<String, Object>> tableFieldValueMap = JsonHelper.parseJson2TableFieldValueMap(resJsonStr, jsonConfig);

        Map<String, List<List<Object>>> sqlMapByTableFieldValueMap = SqlUtil.generateSqlMapByTableFieldValueMap(tableFieldValueMap);
        System.out.println(sqlMapByTableFieldValueMap);
        String dbUrl = SchoolDbUtil.generateDbUrlBySchoolDbInstance(schoolDb);

        try {
            Connection connection = DataSourceManager.getConnection(dbUrl, schoolDb.getDbUser(), schoolDb.getDbPwd());
            DataSourceManager.batchExecute(connection,sqlMapByTableFieldValueMap);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

    }

    public void doWorker() {
        String schoolApiId = "1";
        String dbId = "1";
        Set<String> dbIds = new HashSet<>(Arrays.asList(dbId));
        schoolApiBiz.workWithSchoolApi(schoolApiId,dbIds);
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

        HttpResponse response = HttpManager.requestForResponse(Method.POST.name(), schoolToken.getTokenApi(),null,
            schoolToken.getJsonParam(), 3000);
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

    //    public void testReadJsonText() {
    //        try {
    //            File file = ResourceUtils.getFile("classpath:jsonPath$StudentRule.txt");
    //            FileInputStream in = new FileInputStream(file);
    //            String jsonPath$StudentRule = new String(in.readAllBytes());
    //            file = ResourceUtils.getFile("classpath:studentJson.json");
    //            in = new FileInputStream(file);
    //            String studentSource = new String(in.readAllBytes());
    //            Map<String, Map<String, Object>> stringMapMap = JsonHelper.parseJson2TableFieldValueMap(studentSource, jsonPath$StudentRule);
    //            // 输入map
    //            Map<String, List<List<Object>>> sqlParamListMap = SqlUtil.generateSqlMapByTableFieldValueMap(stringMapMap);
    //            Connection connection = testSchoolDb();
    //            DataSourceManager.batchExecute(connection, sqlParamListMap);
    //
    //        } catch (FileNotFoundException e) {
    //            e.printStackTrace();
    //        } catch (IOException e) {
    //            e.printStackTrace();
    //        } catch (SQLException throwables) {
    //            throwables.printStackTrace();
    //        }
    //    }

    public Connection testSchoolDb() throws SQLException {
        String host = "192.168.1.93";
        String port = "";
        String dbName = "ae_test";
        String userName = "root";
        String password = "123456";
        Connection connection = DataSourceManager
            .getConnection(DbUtil.generateDbUrlStrByParam(host, port, dbName), userName, password);
        return connection;
    }

    //    public Map<String, Map<String, Object>> testParseRule() {
    //        try {
    //            File file = ResourceUtils.getFile("classpath:JsonConfig.json");
    //            FileInputStream in = new FileInputStream(file);
    //            String jsonPath$TableRule = new String(in.readAllBytes());
    //
    //            file = ResourceUtils.getFile("classpath:studentJson.json");
    //            in = new FileInputStream(file);
    //            String studentSource = new String(in.readAllBytes());
    //            Map<String, Map<String, Object>> stringMapMap = JsonHelper.parseJson2TableFieldValueMap(studentSource, jsonPath$TableRule);
    //            // 输入map
    //            Map<String, List<List<Object>>> stringListMap = SqlUtil.generateSqlMapByTableFieldValueMap(stringMapMap);
    //            System.out.println(stringListMap);
    //
    //        } catch (FileNotFoundException e) {
    //            e.printStackTrace();
    //        } catch (IOException e) {
    //            e.printStackTrace();
    //        }
    //        return null;
    //    }

}
