SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- 菜单表
-- ----------------------------
DROP TABLE IF EXISTS `menus`;
CREATE TABLE `menus`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '菜单主键',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '菜单名称',
  `pid` int(11) NOT NULL DEFAULT 0 COMMENT '父级 ID, 最顶级为 0',
  `order` int(11) NOT NULL COMMENT '排序, 序号越大, 越靠前',
  `url` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '前端跳转',
  `desc` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '描述字段',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Compact;

-- ----------------------------
-- 菜单表，测试数据
-- ----------------------------
INSERT INTO `menus` VALUES (1, 'CMS', -1, 1, '', '');
INSERT INTO `menus` VALUES (2, '一级导航', 1, 2, '', '');
INSERT INTO `menus` VALUES (3, '内容管理', 1, 2, '', '');
INSERT INTO `menus` VALUES (4, 'B2C商城设置', 3, 1, '', '');
INSERT INTO `menus` VALUES (5, '二级导航-v', 3, 2, '', '');
INSERT INTO `menus` VALUES (6, '二级导航', 2, 1, '', '');

SET FOREIGN_KEY_CHECKS = 1;
