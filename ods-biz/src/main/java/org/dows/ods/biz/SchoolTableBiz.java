package org.dows.ods.biz;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.ods.biz.util.SchoolTableUtil;
import org.dows.ods.entity.SchoolDb;
import org.dows.ods.entity.SchoolTable;
import org.dows.ods.service.SchoolDbService;
import org.dows.ods.service.SchoolTableService;
import org.dows.ods.vo.SchoolTableCreateVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**学校数据库业务类*/
@Service
@RequiredArgsConstructor
@Slf4j
public class SchoolTableBiz {
    private final SchoolDbBiz schoolDbBiz;
    private final SchoolTableService schoolTableService;
    private final SchoolDbService schoolDbService;

    /*单个创建数据表信息:手动维护*/
    public void createSchoolTable(SchoolTableCreateVO createVO){
        SchoolTableUtil.validate(createVO);
        if (!StringUtils.hasText(createVO.getDbName())) {
            SchoolDb db = schoolDbBiz.getSchoolDbById(createVO.getDbId());
            createVO.setDbName(db.getDbName());
        }
        SchoolTable table =new SchoolTable();
        BeanUtils.copyProperties(createVO,table);
        schoolTableService.save(table);
        return;
    }

    // 批量创建数据表，一般可以用于指定数据库一键导入表
    @Transactional(rollbackFor = Exception.class)
    public void batchCreateSchoolTable(Collection<SchoolTableCreateVO> createVOs){
        if(!CollectionUtils.isEmpty(createVOs)){
            Set<String> dbIdSet = new HashSet<>();
            for (SchoolTableCreateVO createVO : createVOs) {
                SchoolTableUtil.validate(createVO);
                if (!StringUtils.hasText(createVO.getDbName())) {
                    // 收集没有dbName的记录
                    dbIdSet.add(createVO.getDbId());
                }
            }
            Map<Long, SchoolDb> schoolDbMap=null;
            if(!dbIdSet.isEmpty()){
                List<SchoolDb> schoolDbs = schoolDbService.listByIds(dbIdSet);
                schoolDbMap = schoolDbs.stream().collect(Collectors.toMap(SchoolDb::getId, Function.identity()));
            }
            List<SchoolTable> insertBeans = new ArrayList<>(createVOs.size());
            for (SchoolTableCreateVO createVO : createVOs) {
                if (!StringUtils.hasText(createVO.getDbName())) {
                    SchoolDb schoolDb = schoolDbMap.get(createVO.getDbId());
                    createVO.setDbName(schoolDb.getDbName());
                }
                SchoolTable table =new SchoolTable();
                BeanUtils.copyProperties(createVO,table);
                insertBeans.add(table);
            }
            schoolTableService.saveBatch(insertBeans);
        }
    }
}
