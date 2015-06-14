-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.1.50-community


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema hotel
--

CREATE DATABASE IF NOT EXISTS hotel;
USE hotel;

--
-- Definition of table `chats`
--

DROP TABLE IF EXISTS `chats`;
CREATE TABLE `chats` (
  `cid` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) DEFAULT NULL,
  `Use_uid` int(11) DEFAULT NULL,
  `context` text NOT NULL,
  `cfid` varchar(100) NOT NULL,
  `uchmark` text,
  PRIMARY KEY (`cid`),
  KEY `FK_firstperson` (`Use_uid`),
  KEY `FK_secondperson` (`uid`),
  CONSTRAINT `FK_secondperson` FOREIGN KEY (`uid`) REFERENCES `users` (`uid`),
  CONSTRAINT `FK_firstperson` FOREIGN KEY (`Use_uid`) REFERENCES `users` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `chats`
--

/*!40000 ALTER TABLE `chats` DISABLE KEYS */;
/*!40000 ALTER TABLE `chats` ENABLE KEYS */;


--
-- Definition of table `friends`
--

DROP TABLE IF EXISTS `friends`;
CREATE TABLE `friends` (
  `fsid` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) DEFAULT NULL,
  `Use_uid` int(11) DEFAULT NULL,
  PRIMARY KEY (`fsid`),
  KEY `FK_fid` (`uid`),
  KEY `FK_uid` (`Use_uid`),
  CONSTRAINT `FK_uid` FOREIGN KEY (`Use_uid`) REFERENCES `users` (`uid`),
  CONSTRAINT `FK_fid` FOREIGN KEY (`uid`) REFERENCES `users` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `friends`
--

/*!40000 ALTER TABLE `friends` DISABLE KEYS */;
/*!40000 ALTER TABLE `friends` ENABLE KEYS */;


--
-- Definition of table `pets`
--

DROP TABLE IF EXISTS `pets`;
CREATE TABLE `pets` (
  `pid` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) DEFAULT NULL,
  `pnickname` varchar(20) NOT NULL,
  `ptype` varchar(20) DEFAULT NULL,
  `pvariety` varchar(20) DEFAULT NULL,
  `pbirthday` date NOT NULL,
  `pgender` int(11) DEFAULT NULL,
  `pfid` varchar(100) NOT NULL,
  `pmark` text,
  PRIMARY KEY (`pid`),
  KEY `FK_belonged` (`uid`),
  CONSTRAINT `FK_belonged` FOREIGN KEY (`uid`) REFERENCES `users` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `pets`
--

/*!40000 ALTER TABLE `pets` DISABLE KEYS */;
/*!40000 ALTER TABLE `pets` ENABLE KEYS */;


--
-- Definition of table `posts`
--

DROP TABLE IF EXISTS `posts`;
CREATE TABLE `posts` (
  `poid` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL,
  `retypeid` int(11) DEFAULT NULL,
  `pofilelist` varchar(100) DEFAULT NULL,
  `potype` varchar(20) NOT NULL,
  `appreciate` int(11) DEFAULT NULL,
  `pettype` varchar(20) DEFAULT NULL,
  `pobegintime` date DEFAULT NULL,
  `poendtime` date DEFAULT NULL,
  `pocontext` text NOT NULL,
  `potime` datetime NOT NULL,
  `pomark` text,
  PRIMARY KEY (`poid`),
  KEY `FK_poster` (`uid`),
  CONSTRAINT `FK_poster` FOREIGN KEY (`uid`) REFERENCES `users` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `posts`
--

/*!40000 ALTER TABLE `posts` DISABLE KEYS */;
/*!40000 ALTER TABLE `posts` ENABLE KEYS */;


--
-- Definition of table `resources`
--

DROP TABLE IF EXISTS `resources`;
CREATE TABLE `resources` (
  `rid` int(11) NOT NULL AUTO_INCREMENT,
  `rtyp` varchar(10) NOT NULL,
  `raddress` varchar(200) NOT NULL,
  `updatetime` datetime NOT NULL,
  PRIMARY KEY (`rid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `resources`
--

/*!40000 ALTER TABLE `resources` DISABLE KEYS */;
/*!40000 ALTER TABLE `resources` ENABLE KEYS */;


--
-- Definition of table `trades`
--

DROP TABLE IF EXISTS `trades`;
CREATE TABLE `trades` (
  `tid` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) DEFAULT NULL,
  `pid` int(11) DEFAULT NULL,
  `Use_uid` int(11) DEFAULT NULL,
  `tbegindate` date NOT NULL,
  `tenddate` date DEFAULT NULL,
  `tstatus` varchar(20) NOT NULL,
  `tpostlist` varchar(100) DEFAULT NULL,
  `tmark` text,
  PRIMARY KEY (`tid`),
  KEY `FK_adopt` (`uid`),
  KEY `FK_befoster` (`pid`),
  KEY `FK_foster` (`Use_uid`),
  CONSTRAINT `FK_foster` FOREIGN KEY (`Use_uid`) REFERENCES `users` (`uid`),
  CONSTRAINT `FK_adopt` FOREIGN KEY (`uid`) REFERENCES `users` (`uid`),
  CONSTRAINT `FK_befoster` FOREIGN KEY (`pid`) REFERENCES `pets` (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `trades`
--

/*!40000 ALTER TABLE `trades` DISABLE KEYS */;
/*!40000 ALTER TABLE `trades` ENABLE KEYS */;


--
-- Definition of table `unuseuserinfo`
--

DROP TABLE IF EXISTS `unuseuserinfo`;
CREATE TABLE `unuseuserinfo` (
  `unuseid` int(11) NOT NULL,
  `uid` int(11) DEFAULT NULL,
  `userRealName` varchar(50) DEFAULT NULL,
  `id` varchar(20) NOT NULL,
  `date` date NOT NULL,
  `ucmark` text,
  PRIMARY KEY (`unuseid`),
  KEY `FK_including2` (`uid`),
  CONSTRAINT `FK_including2` FOREIGN KEY (`uid`) REFERENCES `users` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `unuseuserinfo`
--

/*!40000 ALTER TABLE `unuseuserinfo` DISABLE KEYS */;
/*!40000 ALTER TABLE `unuseuserinfo` ENABLE KEYS */;


--
-- Definition of table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `unuseid` int(11) DEFAULT NULL,
  `uname` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `province` varchar(20) NOT NULL,
  `city` varchar(20) NOT NULL,
  `district` varchar(20) NOT NULL,
  `address` varchar(100) DEFAULT NULL,
  `ufid` varchar(100) DEFAULT NULL,
  `reputation` int(11) NOT NULL,
  `isAdopt` tinyint(1) NOT NULL,
  `QQ` varchar(10) DEFAULT NULL,
  `email` varchar(50) NOT NULL,
  `tel` varchar(20) NOT NULL,
  `headportrait` varchar(50) NOT NULL,
  `umark` text,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `users`
--

/*!40000 ALTER TABLE `users` DISABLE KEYS */;
/*!40000 ALTER TABLE `users` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
