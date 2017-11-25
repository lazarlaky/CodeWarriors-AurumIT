-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Nov 25, 2017 at 01:04 PM
-- Server version: 5.7.19
-- PHP Version: 5.6.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `snapit`
--

-- --------------------------------------------------------

--
-- Table structure for table `slike`
--

DROP TABLE IF EXISTS `slike`;
CREATE TABLE IF NOT EXISTS `slike` (
  `androidID` varchar(30) NOT NULL,
  `naziv` varchar(30) NOT NULL,
  `putanja` varchar(30) NOT NULL,
  PRIMARY KEY (`naziv`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `verifikacijakorisnika`
--

DROP TABLE IF EXISTS `verifikacijakorisnika`;
CREATE TABLE IF NOT EXISTS `verifikacijakorisnika` (
  `kod` varchar(20) NOT NULL,
  `androidID` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`kod`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `verifikacijakorisnika`
--

INSERT INTO `verifikacijakorisnika` (`kod`, `androidID`) VALUES
('195', NULL),
('292', NULL),
('389', NULL),
('486', NULL),
('583', NULL),
('680', NULL);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
