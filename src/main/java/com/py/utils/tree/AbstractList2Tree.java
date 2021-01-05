package com.py.utils.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * description：list转树模板类
 *
 * @author budingxie
 * @version 1.0.0
 * @date 2021/1/5
 */
public abstract class AbstractList2Tree<N> {

    /**
     * 获取节点唯一标识方法
     *
     * @param node 节点
     * @return 节点id
     */
    protected abstract String getKey(N node);

    /**
     * 获取节点父节点唯一标识方法
     *
     * @param node 节点
     * @return 父节点id
     */
    protected abstract String getParentId(N node);

    /**
     * 获取指定节点的，子节点
     *
     * @param node 节点
     * @return 子节点集合
     */
    protected abstract List<N> getChildren(N node);

    /**
     * 给当某个节点，设置子节
     *
     * @param nodes 子节点
     * @param node  节点
     */
    protected abstract void setChildren(List<N> nodes, N node);

    /**
     * 生成树方法
     *
     * @param oldList 节点集合
     * @return 节点树集合
     */
    public List<N> listToTree(List<N> oldList) {
        Map<String, N> newMap = new HashMap<>(16);
        List<N> newList = new ArrayList<>();
        for (N tree : oldList) {
            newMap.put(getKey(tree), tree);
        }
        for (N tree : oldList) {
            N parent = newMap.get(getParentId(tree));
            if (parent != null) {
                if (getChildren(parent) == null) {
                    List<N> ch = new ArrayList<>();
                    ch.add(tree);
                    setChildren(ch, parent);
                } else {
                    List<N> ch = getChildren(parent);
                    ch.add(tree);
                    setChildren(ch, parent);
                }
            } else {
                newList.add(tree);
            }
        }
        return newList;
    }
}
