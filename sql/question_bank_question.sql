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

 Date: 21/12/2024 00:42:51
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

-- ----------------------------
-- Records of question_bank_question
-- ----------------------------
INSERT INTO `question_bank_question` VALUES (16, 1, 1);
INSERT INTO `question_bank_question` VALUES (18, 1, 8);
INSERT INTO `question_bank_question` VALUES (19, 1, 9);
INSERT INTO `question_bank_question` VALUES (20, 1, 10);
INSERT INTO `question_bank_question` VALUES (21, 1, 11);
INSERT INTO `question_bank_question` VALUES (17, 1, 16);
INSERT INTO `question_bank_question` VALUES (77, 1, 65);
INSERT INTO `question_bank_question` VALUES (78, 1, 66);
INSERT INTO `question_bank_question` VALUES (79, 1, 67);
INSERT INTO `question_bank_question` VALUES (80, 1, 68);
INSERT INTO `question_bank_question` VALUES (81, 1, 69);
INSERT INTO `question_bank_question` VALUES (82, 1, 70);
INSERT INTO `question_bank_question` VALUES (83, 1, 71);
INSERT INTO `question_bank_question` VALUES (84, 1, 72);
INSERT INTO `question_bank_question` VALUES (85, 1, 73);
INSERT INTO `question_bank_question` VALUES (86, 1, 74);
INSERT INTO `question_bank_question` VALUES (87, 1, 75);
INSERT INTO `question_bank_question` VALUES (88, 1, 76);
INSERT INTO `question_bank_question` VALUES (89, 1, 77);
INSERT INTO `question_bank_question` VALUES (90, 1, 78);
INSERT INTO `question_bank_question` VALUES (91, 1, 79);
INSERT INTO `question_bank_question` VALUES (92, 1, 80);
INSERT INTO `question_bank_question` VALUES (93, 1, 81);
INSERT INTO `question_bank_question` VALUES (94, 1, 82);
INSERT INTO `question_bank_question` VALUES (95, 1, 83);
INSERT INTO `question_bank_question` VALUES (96, 1, 84);
INSERT INTO `question_bank_question` VALUES (97, 1, 85);
INSERT INTO `question_bank_question` VALUES (98, 1, 86);
INSERT INTO `question_bank_question` VALUES (22, 2, 2);
INSERT INTO `question_bank_question` VALUES (24, 2, 12);
INSERT INTO `question_bank_question` VALUES (25, 2, 13);
INSERT INTO `question_bank_question` VALUES (26, 2, 14);
INSERT INTO `question_bank_question` VALUES (27, 2, 15);
INSERT INTO `question_bank_question` VALUES (23, 2, 17);
INSERT INTO `question_bank_question` VALUES (28, 3, 35);
INSERT INTO `question_bank_question` VALUES (29, 3, 36);
INSERT INTO `question_bank_question` VALUES (30, 3, 37);
INSERT INTO `question_bank_question` VALUES (31, 3, 38);
INSERT INTO `question_bank_question` VALUES (32, 3, 39);
INSERT INTO `question_bank_question` VALUES (33, 3, 40);
INSERT INTO `question_bank_question` VALUES (34, 4, 4);
INSERT INTO `question_bank_question` VALUES (35, 4, 19);
INSERT INTO `question_bank_question` VALUES (36, 4, 41);
INSERT INTO `question_bank_question` VALUES (37, 4, 42);
INSERT INTO `question_bank_question` VALUES (38, 4, 43);
INSERT INTO `question_bank_question` VALUES (39, 4, 44);
INSERT INTO `question_bank_question` VALUES (40, 4, 45);
INSERT INTO `question_bank_question` VALUES (41, 4, 46);
INSERT INTO `question_bank_question` VALUES (42, 5, 5);
INSERT INTO `question_bank_question` VALUES (43, 5, 20);
INSERT INTO `question_bank_question` VALUES (44, 5, 47);
INSERT INTO `question_bank_question` VALUES (45, 5, 48);
INSERT INTO `question_bank_question` VALUES (46, 5, 49);
INSERT INTO `question_bank_question` VALUES (47, 5, 50);
INSERT INTO `question_bank_question` VALUES (48, 5, 51);
INSERT INTO `question_bank_question` VALUES (49, 5, 52);
INSERT INTO `question_bank_question` VALUES (50, 6, 6);
INSERT INTO `question_bank_question` VALUES (51, 6, 23);
INSERT INTO `question_bank_question` VALUES (52, 6, 24);
INSERT INTO `question_bank_question` VALUES (53, 6, 25);
INSERT INTO `question_bank_question` VALUES (54, 6, 26);
INSERT INTO `question_bank_question` VALUES (55, 6, 27);
INSERT INTO `question_bank_question` VALUES (56, 6, 28);
INSERT INTO `question_bank_question` VALUES (57, 7, 7);
INSERT INTO `question_bank_question` VALUES (58, 7, 22);
INSERT INTO `question_bank_question` VALUES (59, 7, 29);
INSERT INTO `question_bank_question` VALUES (60, 7, 30);
INSERT INTO `question_bank_question` VALUES (61, 7, 31);
INSERT INTO `question_bank_question` VALUES (62, 7, 32);
INSERT INTO `question_bank_question` VALUES (63, 7, 33);
INSERT INTO `question_bank_question` VALUES (64, 7, 34);
INSERT INTO `question_bank_question` VALUES (65, 8, 53);
INSERT INTO `question_bank_question` VALUES (66, 8, 54);
INSERT INTO `question_bank_question` VALUES (67, 8, 55);
INSERT INTO `question_bank_question` VALUES (68, 8, 56);
INSERT INTO `question_bank_question` VALUES (69, 8, 57);
INSERT INTO `question_bank_question` VALUES (70, 8, 58);
INSERT INTO `question_bank_question` VALUES (71, 9, 59);
INSERT INTO `question_bank_question` VALUES (72, 9, 60);
INSERT INTO `question_bank_question` VALUES (73, 9, 61);
INSERT INTO `question_bank_question` VALUES (74, 9, 62);
INSERT INTO `question_bank_question` VALUES (75, 9, 63);
INSERT INTO `question_bank_question` VALUES (76, 9, 64);

SET FOREIGN_KEY_CHECKS = 1;
