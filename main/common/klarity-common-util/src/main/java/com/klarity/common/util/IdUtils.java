package com.klarity.common.util;

import com.royee.common.sequence.service.MysqlSequence;
import com.royee.common.starters.template.util.SpringContextHolder;

/**
 * 获取系统全局唯一Id
 *
 * @author: pengcheng091
 */
public class IdUtils {
    public static final String nextVal() {
        MysqlSequence mysqlSequence = SpringContextHolder.getBean(MysqlSequence.class);
        String applicationName = mysqlSequence.getApplicationName();
        return mysqlSequence.nextVal(applicationName);
    }
}
