package org.dows.ods.biz;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.framework.api.exceptions.BizException;
import org.dows.ods.api.exception.SchoolInstanceException;
import org.dows.ods.biz.util.SchoolInstanceUtil;
import org.dows.ods.entity.SchoolInstance;
import org.dows.ods.service.SchoolInstanceService;
import org.dows.ods.vo.SchoolInstanceCreateVO;
import org.dows.ods.vo.SchoolInstancePageReq;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Objects;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class SchoolInstanceBiz {
    private final SchoolInstanceService schoolInstanceService;

    /**
     * 保存学校实例
     */
    public void createSchoolInstance(SchoolInstanceCreateVO createVO) throws BizException {
        // 创建请求参数的验证业务
        SchoolInstanceUtil.validate(createVO);
        SchoolInstance newSchool = SchoolInstanceUtil.convert(createVO);
        // 根据名称或者编号进行学校判重
        long count = schoolInstanceService.lambdaQuery()
            .eq(SchoolInstance::getSchoolCode, newSchool.getSchoolCode())
            .or()
            .eq(SchoolInstance::getSchoolName, newSchool.getSchoolName())
            .count();
        if (count > 0) {
            throw new SchoolInstanceException("当前学校编号或学校名称已存在");
        }
        // todo 待确认
        //  newSchool.setSchoolId(IdWorker.getIdStr());
        schoolInstanceService.save(newSchool);
        return;
    }

    /*更新学校实例*/

    /**
     * 学校的分页查询
     *
     * @return Page<SchoolInstance>
     */
    public Page<SchoolInstance> querySchoolInstancePage(SchoolInstancePageReq pageReq) {
        int pageNo = pageReq.getPageNo() == 0 ? SchoolInstancePageReq.PAGE_NO_DEFAULT : pageReq.getPageNo();
        int pageSize = pageReq.getPageSize() == 0 ? SchoolInstancePageReq.PAGE_SIZE_DEFAULT : pageReq.getPageSize();
        String name = pageReq.getSchoolName();
        String code = pageReq.getSchoolCode();
        // 拼接分页查询条件
        LambdaQueryWrapper<SchoolInstance> queryWrapper = new LambdaQueryWrapper<>();
        // 名称
        if (StringUtils.hasText(name)) {
            queryWrapper.like(SchoolInstance::getSchoolName, name);
        }
        // 编号
        if (StringUtils.hasText(code)) {
            queryWrapper.eq(SchoolInstance::getSchoolCode, code);
        }
        return schoolInstanceService.page(new Page<>(pageNo, pageSize), queryWrapper);
    }

    // 根据自增Id批量删除
    @Transactional(rollbackFor = Exception.class)
    public void batchRemoveSchoolByIds(Set<Long> ids) {
        schoolInstanceService.removeByIds(ids);
    }

    /**
     * 根据ID查询学校实例
     */
    public SchoolInstance getSchoolInstanceById(String id) {
        SchoolInstance schoolInstance = schoolInstanceService.getById(id);
        if (Objects.isNull(schoolInstance)) {
            throw new SchoolInstanceException("使用的学校实例不存在");
        }
        return schoolInstance;
    }
}
