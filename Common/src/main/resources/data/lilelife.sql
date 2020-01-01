-- --------------------------------------------------------
-- 主机:                           47.105.48.220
-- 服务器版本:                        5.5.64-MariaDB - MariaDB Server
-- 服务器操作系统:                      Linux
-- HeidiSQL 版本:                  9.3.0.4984
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 导出 lilelife 的数据库结构
CREATE DATABASE IF NOT EXISTS `lilelife` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `lilelife`;


-- 导出  表 lilelife.book 结构
CREATE TABLE IF NOT EXISTS `book` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `user_id` int(10) NOT NULL COMMENT '用户id',
  `name` varchar(200) NOT NULL COMMENT '书名',
  `percent` int(2) NOT NULL DEFAULT '0' COMMENT '阅读进度 0-100%',
  `author` varchar(200) DEFAULT NULL COMMENT '作者',
  `book_cover` varchar(500) DEFAULT NULL COMMENT '封面',
  `introduction` varchar(500) DEFAULT NULL COMMENT '简介',
  `status` int(1) NOT NULL DEFAULT '0' COMMENT '状态：0 待读；1在读  每用户一本在读；2完成；3删除',
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `book_user` (`user_id`),
  CONSTRAINT `book_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='书单表  包含一个读者的所有书';

-- 正在导出表  lilelife.book 的数据：~0 rows (大约)
DELETE FROM `book`;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
/*!40000 ALTER TABLE `book` ENABLE KEYS */;


-- 导出  表 lilelife.todo 结构
CREATE TABLE IF NOT EXISTS `todo` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `user_id` int(10) NOT NULL COMMENT '用户id',
  `title` varchar(200) NOT NULL DEFAULT '' COMMENT '代办主题',
  `content` varchar(500) DEFAULT '' COMMENT '内容',
  `status` int(1) NOT NULL DEFAULT '0' COMMENT '事项状态：0 代办；1完成；2取消',
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='代办事';

-- 正在导出表  lilelife.todo 的数据：~0 rows (大约)
DELETE FROM `todo`;
/*!40000 ALTER TABLE `todo` DISABLE KEYS */;
/*!40000 ALTER TABLE `todo` ENABLE KEYS */;


-- 导出  表 lilelife.user 结构
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `phone` varchar(11) NOT NULL,
  `password` varchar(200) NOT NULL,
  `salt` varchar(100) NOT NULL COMMENT '加盐',
  `photo` varchar(200) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `phone` (`phone`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=gbk;

-- 正在导出表  lilelife.user 的数据：~4 rows (大约)
DELETE FROM `user`;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`id`, `name`, `phone`, `password`, `salt`, `photo`, `create_time`, `update_time`) VALUES
	(1, 's', '15934862384', '11', '', NULL, '0000-00-00 00:00:00', '2019-12-17 14:36:15'),
	(2, '李乐', '14567778645', 'liasdg', '', NULL, '0000-00-00 00:00:00', '2019-12-17 14:36:15'),
	(3, '张三', '12345678907', 'sdagas', '', NULL, '0000-00-00 00:00:00', '2019-12-17 14:36:33'),
	(4, '吴', '15934862385', '8b43d1b7cc9f4e88ccb1028245d5de57df07651cf287a5307f683fc51dd3c94b', 'gwaDOEVcfKhCSYelUdLbeNNoSNTctfTIPfePijElBqlYHTXJOAUlCrWpTeoMCVAd', NULL, '2019-12-17 15:58:09', '2019-12-30 16:21:03');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
