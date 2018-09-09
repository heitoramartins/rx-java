
SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for budget
-- ----------------------------
DROP TABLE IF EXISTS `budget`;
CREATE TABLE `budget` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dtInclusion` datetime DEFAULT NULL,
  `total` decimal(19,2) DEFAULT NULL,
  `fk_cliente` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_foreign_cli` (`fk_cliente`),
  CONSTRAINT `fk_foreign_cli` FOREIGN KEY (`fk_cliente`) REFERENCES `cliente` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of budget
-- ----------------------------

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `active` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES ('1', 'eletronico', '1');

-- ----------------------------
-- Table structure for cliente
-- ----------------------------
DROP TABLE IF EXISTS `cliente`;
CREATE TABLE `cliente` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `document` varchar(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of cliente
-- ----------------------------

-- ----------------------------
-- Table structure for items
-- ----------------------------
DROP TABLE IF EXISTS `items`;
CREATE TABLE `items` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `quantity` int(11) DEFAULT NULL,
  `fk_budget` int(11) DEFAULT NULL,
  `fk_product` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_foreign_pro` (`fk_product`),
  KEY `fk_foreign_bud` (`fk_budget`),
  CONSTRAINT `fk_foreign_bud` FOREIGN KEY (`fk_budget`) REFERENCES `budget` (`id`),
  CONSTRAINT `fk_foreign_pro` FOREIGN KEY (`fk_product`) REFERENCES `product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of items
-- ----------------------------

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES ('1', 'USER');

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `fk_category` int(11) NOT NULL,
  `active` tinyint(4) DEFAULT NULL,
  `storeId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_category` (`fk_category`),
  CONSTRAINT `product_ibfk_1` FOREIGN KEY (`fk_category`) REFERENCES `category` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of product
-- ----------------------------

-- ----------------------------
-- Table structure for profile
-- ----------------------------
DROP TABLE IF EXISTS `profile`;
CREATE TABLE `profile` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of profile
-- ----------------------------
INSERT INTO `profile` VALUES ('1', 'ADM');

-- ----------------------------
-- Table structure for profile_permission
-- ----------------------------
DROP TABLE IF EXISTS `profile_permission`;
CREATE TABLE `profile_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `fk_permission` bigint(20) DEFAULT NULL,
  `fk_profile` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_foreign_perm_1` (`fk_permission`),
  KEY `fk_foreign_prof_1` (`fk_profile`),
  CONSTRAINT `fk_foreign_perm_1` FOREIGN KEY (`fk_permission`) REFERENCES `permission` (`id`),
  CONSTRAINT `fk_foreign_prof_1` FOREIGN KEY (`fk_profile`) REFERENCES `profile` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of profile_permission
-- ----------------------------
INSERT INTO `profile_permission` VALUES ('1', '1', '1');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `fk_profile` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_prof_2` (`fk_profile`),
  CONSTRAINT `fk_prof_2` FOREIGN KEY (`fk_profile`) REFERENCES `profile` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
