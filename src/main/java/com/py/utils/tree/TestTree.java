package com.py.utils.tree;

import cn.hutool.json.JSONUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * description：测试
 *
 * @author budingxie
 * @version 1.0.0
 * @date 2021/1/5
 */
public class TestTree {

    public static void main(String[] args) {
        List<MenuTreeDTO> base = new ArrayList<>();
        base.add(new MenuTreeDTO(1, "CMS", -1, 1, "", ""));
        base.add(new MenuTreeDTO(2, "内容管理", 1, 1, "", ""));
        base.add(new MenuTreeDTO(3, "专题管理", 1, 2, "", ""));
        base.add(new MenuTreeDTO(4, "菜单管理", 1, 3, "", ""));
        base.add(new MenuTreeDTO(5, "菜谱管理", 1, 4, "", ""));
        base.add(new MenuTreeDTO(6, "广告管理", 1, 5, "", ""));
        base.add(new MenuTreeDTO(7, "B2C商城设置", 2, 1, "", ""));
        base.add(new MenuTreeDTO(8, "标签和说明设置", 7, 1, "", ""));
        base.add(new MenuTreeDTO(9, "门店显示设置", 7, 2, "", ""));
        base.add(new MenuTreeDTO(10, "热搜词统计", 2, 2, "", ""));

        // 转树结构
        List<MenuTreeDTO> list = MenuTreeDTO.LIST2TREE.listToTree(base);
        String jsonStr = JSONUtil.toJsonStr(list);
        System.out.println(jsonStr);
    }

}