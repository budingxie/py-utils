package com.py.utils.tree;

import java.io.Serializable;
import java.util.List;

/**
 * description：菜单类，用来传输
 *
 * @author budingxie
 * @version 1.0.0
 * @date 2021/1/5
 */
public class MenuTreeDTO implements Serializable {

    private static final long serialVersionUID = -829834569865586546L;

    public MenuTreeDTO() {
    }

    public MenuTreeDTO(Integer id, String name, Integer pid, Integer order, String url, String desc) {
        this.id = id;
        this.name = name;
        this.pid = pid;
        this.order = order;
        this.url = url;
        this.desc = desc;
    }

    /**
     * 实现转树模板方法
     */
    public static final AbstractList2Tree<MenuTreeDTO> LIST2TREE = new AbstractList2Tree<MenuTreeDTO>() {
        @Override
        protected String getKey(MenuTreeDTO node) {
            return node.getId().toString();
        }

        @Override
        protected String getParentId(MenuTreeDTO node) {
            return node.getPid().toString();
        }

        @Override
        protected List<MenuTreeDTO> getChildren(MenuTreeDTO node) {
            return node.getChildren();
        }

        @Override
        protected void setChildren(List<MenuTreeDTO> nodes, MenuTreeDTO node) {
            node.setChildren(nodes);
        }
    };


    /**
     * 菜单主键
     */
    private Integer id;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 父菜单主键，顶级为-1
     */
    private Integer pid;

    /**
     * 排序字段
     */
    private Integer order;

    /**
     * 前端跳转url
     */
    private String url;

    /**
     * 描述
     */
    private String desc;

    /**
     * 子菜单
     */
    private List<MenuTreeDTO> children;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<MenuTreeDTO> getChildren() {
        return children;
    }

    public void setChildren(List<MenuTreeDTO> children) {
        this.children = children;
    }


    @Override
    public String toString() {
        return "MenuTreeDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pid=" + pid +
                ", children=" + children +
                '}';
    }
}
