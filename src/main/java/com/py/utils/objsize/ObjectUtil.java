package com.py.utils.objsize;

import org.apache.lucene.util.RamUsageEstimator;

import java.util.Map;
import java.util.TreeMap;

/**
 * description
 *
 * @author pengyou@xiaomi.com
 * @version 1.0.0
 * @date 2020/11/13
 */
public class ObjectUtil {

    public static Map<String, Object> size(Object o) {
        Map<String, Object> resMap = new TreeMap<>();

        //计算指定对象本身在堆空间的大小，单位字节
        long shallowSize = RamUsageEstimator.shallowSizeOf(o);
        //计算指定对象及其引用树上的所有对象的综合大小，单位字节
        long size = RamUsageEstimator.sizeOf(o);
        //计算指定对象及其引用树上的所有对象的综合大小，返回可读的结果，如：2KB
        String humanSize = RamUsageEstimator.humanSizeOf(o);

        resMap.put("occupied-heap-size", shallowSize);
        resMap.put("object-reference-size", size);
        resMap.put("total-size", humanSize);
        return resMap;
    }

}