import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.Method;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.jayway.jsonpath.JsonPath;
import org.dows.ods.biz.SchoolInstanceBiz;
import org.dows.ods.biz.SchoolTokenBiz;
import org.dows.ods.biz.exception.SchoolInstanceException;
import org.dows.ods.biz.util.HttpManager;
import org.dows.ods.biz.util.JsonParseUtil;
import org.dows.ods.biz.util.TableConf;
import org.dows.ods.biz.util.TableDataSqlUtil;
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
import java.util.*;
import java.util.stream.Collectors;

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
        testReadJsonText();
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
            .getSchoolTokenBySchool(String.valueOf(1600696191920644100L));
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
            Map<String, Map<String, Object>> stringMapMap = JsonParseUtil
                .parseJson2TableFieldValueMap(studentSource, jsonPath$StudentRule);
            Map<String, List<String>> stringListMap = TableDataSqlUtil.generateSqlMapByTableFieldValueMap(stringMapMap);
            System.out.println(stringListMap);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //        String studentSource = "{\"code\":94,\"data\":{\"records\":[{\"XH\":\"minim Ut\",\"XM\":\"mollit sed velit\",\"XB\":\"cillum aliqua\",\"BH\":\"in nostrud velit ut\",\"BJMC\":\"Ut aliqua\",\"ZYH\":\"quis dolore consequat commodo\",\"ZYMC\":\"dolor labore\",\"DWH\":\"incididunt eu officia ullamco esse\",\"DWMC\":\"nisi enim\",\"XSLB\":\"nulla ut\",\"RXNY\":\"ad in proident sint irure\"}],\"total\":57,\"page\":75,\"pages\":36,\"rows\":15},\"success\":false,\"msg\":\"do labore sunt irure\"}";
        //        String jsonPath$StudentRule = "$.data.records|[{\"table\":\"user\",\"field\":{\"XH\":\"code\",\"XM\":\"name\",\"XB\":\"sex\"},\"primary_key\":[\"code\"]},{\"table\":\"class_member\",\"field\":{\"XH\":\"userId\",\"BH\":\"classId\"},\"primary_key\":[\"userId\",\"classId\"]}]";
    }

    public void testReadJsonText1() throws Exception {
        String teacherSource = "{\"code\":82,\"data\":{\"records\":[{\"GH\":\"commodo\",\"XM\":\"nisi occaecat proident anim sit\",\"XBMC\":\"labore aliquip proident commodo\",\"SZDW\":\"in enim tempor mollit sint\",\"RYZTMC\":\"Lorem id dolor sunt sint\",\"GJMC\":\"ut ex ut pariatur\"}],\"page\":41,\"pages\":40,\"rows\":92,\"total\":77},\"msg\":\"voluptate incididunt Duis in adipisicing\",\"success\":false}";
        String jsonPath$TeacherRule = "$.data.records|[{\"table\":\"user\",\"field\":{\"GH\":\"code\",\"XM\":\"name\",\"XBMC\":\"sex\"},\"primary_key\":[\"code\"]}]";

        String studentSource = "{\"code\":94,\"data\":{\"records\":[{\"XH\":\"minim Ut\",\"XM\":\"mollit sed velit\",\"XB\":\"cillum aliqua\",\"BH\":\"in nostrud velit ut\",\"BJMC\":\"Ut aliqua\",\"ZYH\":\"quis dolore consequat commodo\",\"ZYMC\":\"dolor labore\",\"DWH\":\"incididunt eu officia ullamco esse\",\"DWMC\":\"nisi enim\",\"XSLB\":\"nulla ut\",\"RXNY\":\"ad in proident sint irure\"}],\"total\":57,\"page\":75,\"pages\":36,\"rows\":15},\"success\":false,\"msg\":\"do labore sunt irure\"}";
        String jsonPath$StudentRule = "$.data.records|[{\"table\":\"user\",\"field\":{\"XH\":\"code\",\"XM\":\"name\",\"XB\":\"sex\"},\"primary_key\":[\"code\"]},{\"table\":\"class_member\",\"field\":{\"XH\":\"userId\",\"BH\":\"classId\"},\"primary_key\":[\"userId\",\"classId\"]}]";

        String regex = "\\|";
        String[] splitStrs = jsonPath$StudentRule.split(regex);

        String jsonPath = splitStrs[0];
        JSONArray records = JSONUtil.getByPath(JSONUtil.parse(studentSource), jsonPath, new JSONArray());

        // key:table_组合id(中间表)
        Map<String, Map<String, Object>> tableEntityIdFieldValueMap = new HashMap<>();
        JSONArray rules = JSONUtil.parseArray(splitStrs[1]);
        //一个接口的返回结果可能需要多个表的解析规则
        ListIterator<Object> recordIterator = records.listIterator();
        while (recordIterator.hasNext()) {
            LinkedHashMap<String, Object> sourceData = (LinkedHashMap) recordIterator.next();
            // 该条记录根据N张表的解析配置
            for (int i = 0; i < rules.size(); i++) {
                JSONObject tableConf = rules.getJSONObject(i);
                String tableName = tableConf.getStr(TableConf.TABLE.getName());
                // 字段配置
                JSONObject fieldConf = tableConf.getJSONObject(TableConf.FIELD.getName());
                Set<Object> primaryKeySet = tableConf.getJSONArray(TableConf.PRIMARY_KEY.getName()).stream().collect(
                    Collectors.toSet());
                if (Objects.isNull(fieldConf) || fieldConf.isEmpty()) {
                    // 没有配置field规则或者字段规则为空
                    //报错还是跳过，最终选择了跳过
                    continue;
                }
                // 处理每条数据记录
                Map<String, Object> targetFieldValueMap = new HashMap<>();
                Iterator<Map.Entry<String, Object>> fieldValueIterator = sourceData.entrySet().iterator();
                while (fieldValueIterator.hasNext()) {
                    Map.Entry<String, Object> fieldValue = fieldValueIterator.next();
                    // 根据源数据中的字段名获得即将存入的目标字段名
                    // 假设
                    String targetFieldName = fieldConf.getStr(fieldValue.getKey());
                    if (StrUtil.isNotBlank(targetFieldName)) {
                        // 如果该源字段名在字段解析规则中存在，就需要新字段名和对应字段值的加入
                        // 获取实际字段的值，并保存进
                        Object value = fieldValue.getValue();
                        targetFieldValueMap.put(targetFieldName, value);
                    }
                }

                // 组合ID
                StringJoiner combineIdBuilder = new StringJoiner("_");
                // 表名开头
                combineIdBuilder.add(tableName);
                for (Object primaryKey : primaryKeySet) {
                    // 将 primaryKey对应的字段值获取
                    combineIdBuilder.add(String.valueOf(targetFieldValueMap.get(String.valueOf(primaryKey))));
                }
                Map<String, Object> fieldValueMap = tableEntityIdFieldValueMap
                    .computeIfAbsent(combineIdBuilder.toString(), s -> new HashMap<>());
                // 重叠的字段对应值覆盖，新的字段就新增
                fieldValueMap.putAll(targetFieldValueMap);
            }
        }
    }

}
