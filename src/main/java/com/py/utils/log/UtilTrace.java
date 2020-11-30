package com.py.utils.log;

import cn.hutool.core.util.IdUtil;

/**
 * description：
 *
 * @author pengyou
 * @version 1.0.0
 * @date 2020/11/12
 */
public class UtilTrace {

    public static final String MDC_TRACE_ID = "trace_id";

    private static InheritableThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal<>();

    /**
     * 获取traceId
     *
     * @return
     */
    public static String get() {
        String traceId = inheritableThreadLocal.get();
        if (traceId == null) {
            traceId = IdUtil.simpleUUID();
            inheritableThreadLocal.set(traceId);
        }
        return traceId;
    }

    public static void set(String traceId) {
        inheritableThreadLocal.set(traceId);
    }

    public static void remove() {
        inheritableThreadLocal.remove();
    }
}