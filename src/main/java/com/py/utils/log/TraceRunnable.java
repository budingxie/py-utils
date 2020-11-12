package com.py.utils.log;

import org.slf4j.MDC;

/**
 * description：
 *
 * @author pengyou
 * @version 1.0.0
 * @date 2020/11/12
 */
public class TraceRunnable implements Runnable {

    private String tranceId;

    private Runnable target;

    public TraceRunnable(Runnable target) {
        this.tranceId = TraceUtil.get();
        this.target = target;
    }

    @Override
    public void run() {
        try {
            TraceUtil.set(this.tranceId);
            MDC.put(TraceUtil.MDC_TRACE_ID, TraceUtil.get());
            this.target.run();
        } finally {
            MDC.remove(TraceUtil.MDC_TRACE_ID);
            TraceUtil.remove();
        }
    }

    public static Runnable trace(Runnable target) {
        return new TraceRunnable(target);
    }
}
