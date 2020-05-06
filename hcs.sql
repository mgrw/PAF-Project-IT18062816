-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 06, 2020 at 02:51 PM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.2.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `hcs`
--

-- --------------------------------------------------------

--
-- Table structure for table `labtest`
--

CREATE TABLE `labtest` (
  `LabTestID` int(11) NOT NULL,
  `PatientID` int(10) NOT NULL,
  `TestName` varchar(100) CHARACTER SET latin1 NOT NULL,
  `TestType` varchar(100) CHARACTER SET latin1 NOT NULL,
  `TestDescription` varchar(300) CHARACTER SET latin1 NOT NULL,
  `LabDate` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `labtest`
--

INSERT INTO `labtest` (`LabTestID`, `PatientID`, `TestName`, `TestType`, `TestDescription`, `LabDate`) VALUES
(25, 1, '1-st-Test', 'Blood', 'Pass', '2020-05-01'),
(26, 2, '1-st-Test', 'Suger', 'Fail', '2020-05-02'),
(27, 3, '2-nd-Test', 'Blood', 'Fail', '2020-05-03'),
(28, 4, '1-st-Test', 'Blood', 'Pass', '2020-05-04'),
(29, 5, '2-nd-Test', 'Suger', 'Pass', '2020-05-05');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `labtest`
--
ALTER TABLE `labtest`
  ADD PRIMARY KEY (`LabTestID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `labtest`
--
ALTER TABLE `labtest`
  MODIFY `LabTestID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
