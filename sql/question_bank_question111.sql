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

 Date: 22/12/2024 11:03:51
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for question_bank_question
-- ----------------------------
DROP TABLE IF EXISTS `question_bank_question`;
CREATE TABLE `question_bank_question`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `questionBankId` bigint NOT NULL COMMENT '题库 id',
  `questionId` bigint NOT NULL COMMENT '题目 id',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `questionBankId`(`questionBankId` ASC, `questionId` ASC) USING BTREE,
  INDEX `questionId`(`questionId` ASC) USING BTREE,
  CONSTRAINT `question_bank_question_ibfk_1` FOREIGN KEY (`questionBankId`) REFERENCES `question_bank` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 98 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '题库题目' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
