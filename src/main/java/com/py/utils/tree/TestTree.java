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
        /**
         *  cms
         *      一级导航
         *          二级导航
         *      内容管理
         *          B2C商城设置
         *          二级导航-v
         *  cms-1
         */
        List<MenuTreeDTO> base = new ArrayList<>();

        MenuTreeDTO root = new MenuTreeDTO(1, "CMS", -1, 1, "");
        MenuTreeDTO root0 = new MenuTreeDTO(2, "CMS-1", -1, 1, "");

        MenuTreeDTO root_1 = new MenuTreeDTO(2, "一级导航", 1, 2, "");
        MenuTreeDTO root_1_1 = new MenuTreeDTO(6, "二级导航", 2, 1, "");

        MenuTreeDTO root_2 = new MenuTreeDTO(3, "内容管理", 1, 2, "");
        MenuTreeDTO root_2_1 = new MenuTreeDTO(4, "B2C商城设置", 3, 1, "");
        MenuTreeDTO root_2_2 = new MenuTreeDTO(5, "二级导航-v", 3, 2, "");

        base.add(root);
        base.add(root0);
        base.add(root_1);
        base.add(root_1_1);
        base.add(root_2);
        base.add(root_2_1);
        base.add(root_2_2);

        // 转树结构
        List<MenuTreeDTO> list = MenuTreeDTO.LIST2TREE.listToTree(base);
        String jsonStr = JSONUtil.toJsonStr(list);
        System.out.println(jsonStr);
    }
}
