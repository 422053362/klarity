package com.klarity.common.util.exception;

import com.royee.common.utils.share.exception.BusinessException;
import lombok.Data;

/**
 * @author: pengcheng091
 * @createDate: 2022-10-16 15:05
 */
@Data
public class KlarityException extends BusinessException {
    String code;
    String message;

    Throwable throwable;

    public KlarityException(KlarityExceptionEnum exceptionEnum) {
        this.code = exceptionEnum.getCode();
        this.message = exceptionEnum.getMessage();
    }

    public KlarityException(KlarityExceptionEnum exceptionEnum, Throwable throwable) {
        this.code = exceptionEnum.getCode();
        this.message = exceptionEnum.getMessage();
        this.throwable = throwable;
    }
}
