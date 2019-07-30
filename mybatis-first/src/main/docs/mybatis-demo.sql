/*
 Navicat Premium Data Transfer

 Source Server         : 218.28.238.170
 Source Server Type    : MySQL
 Source Server Version : 50543
 Source Host           : 218.28.238.170:3306
 Source Schema         : mybatis-demo

 Target Server Type    : MySQL
 Target Server Version : 50543
 File Encoding         : 65001

 Date: 30/07/2019 19:57:47
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for g_ac_user
-- ----------------------------
DROP TABLE IF EXISTS `g_ac_user`;
CREATE TABLE `g_ac_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '邮箱地址',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '密码',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '姓名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
