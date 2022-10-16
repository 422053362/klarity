package com.klarity.common.integration.event;

import com.royee.common.utils.share.enums.ErrorCodeBaseEnum;

/**
 * @author: pengcheng091
 */

public enum IntegrationErrorCodeEnum implements ErrorCodeBaseEnum {
    /**
     * 集成三方系统的错误信息
     */
    CREATE_VPN_CHANNEL("100001", "创建VPN通道"),
    ;


    IntegrationErrorCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    private final String code;

    private final String message;

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
