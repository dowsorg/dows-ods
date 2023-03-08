package org.dows.ods.api;

import org.dows.framework.api.StatusCode;
import org.dows.framework.api.exceptions.BizException;

/**
 * 学校实例的异常类
 */
public class SchoolInstanceException extends BizException {

    public SchoolInstanceException(int code, String message) {
        super(code, message);
    }

    public SchoolInstanceException(StatusCode code, Object[] args) {
        super(code, args);
    }

    public SchoolInstanceException(String msg) {
        super(msg);
    }

    public SchoolInstanceException(StatusCode responseCode) {
        super(responseCode);
    }
}
