/*
 Navicat Premium Data Transfer

 Source Server         : class
 Source Server Type    : MySQL
 Source Server Version : 80035
 Source Host           : localhost:3306
 Source Schema         : setrain

 Target Server Type    : MySQL
 Target Server Version : 80035
 File Encoding         : 65001

 Date: 21/12/2024 00:09:41
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for question_bank
-- ----------------------------
DROP TABLE IF EXISTS `question_bank`;
CREATE TABLE `question_bank`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `title` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '标题',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_title`(`title` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '题库' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of question_bank
-- ----------------------------
INSERT INTO `question_bank` VALUES (3, 'C++高级特性');
INSERT INTO `question_bank` VALUES (1, 'Java编程基础');
INSERT INTO `question_bank` VALUES (2, 'Python编程实践');
INSERT INTO `question_bank` VALUES (7, '人工智能前沿');
INSERT INTO `question_bank` VALUES (8, '操作系统原理');
INSERT INTO `question_bank` VALUES (6, '数据库技术精粹');
INSERT INTO `question_bank` VALUES (5, '测试技术大全');
INSERT INTO `question_bank` VALUES (9, '计算机网络基础');
INSERT INTO `question_bank` VALUES (4, '项目管理技巧');

SET FOREIGN_KEY_CHECKS = 1;
