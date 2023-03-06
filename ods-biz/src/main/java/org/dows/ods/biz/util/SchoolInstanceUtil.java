package org.dows.ods.biz.util;

import org.dows.ods.api.exception.SchoolInstanceException;
import org.dows.ods.entity.SchoolInstance;
import org.dows.ods.vo.SchoolInstanceCreateVO;
import org.springframework.util.StringUtils;

/**
 * 学校实例的工具类
 */
public class SchoolInstanceUtil {
    /**
     * <h1>创建学校时参数验证</h1>
     * <h3>能接受的学校名称：</h3>
     * <p>创建时不能为null 或''或 ' '</p>
     */
    public static void validate(SchoolInstanceCreateVO instanceCreateVO) {
        if (!StringUtils.hasText(instanceCreateVO.getSchoolName())) {
            throw new SchoolInstanceException("使用学校名称非法：不能为null 或''或 ' '");
        }
        if (!StringUtils.hasText(instanceCreateVO.getSchoolCode())) {
            throw new SchoolInstanceException("使用学校编号非法：不能为null 或''或 ' '");
        }
    }

    /**
     * copy 静态代码将 SchoolInstanceCreateVO 实例 转换成 SchoolInstance 实例
     */
    public static SchoolInstance convert(SchoolInstanceCreateVO createVO) {
        SchoolInstance bean = new SchoolInstance();
        bean.setSchoolCode(createVO.getSchoolCode())
            .setSchoolHome(createVO.getSchoolHome())
            .setSchoolName(createVO.getSchoolName());
        return bean;
    }
}
