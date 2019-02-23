-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Oct 16, 2018 at 08:32 AM
-- Server version: 10.1.19-MariaDB
-- PHP Version: 5.6.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bus_management`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin_table`
--

CREATE TABLE `admin_table` (
  `id` int(20) NOT NULL,
  `admin_id` int(20) NOT NULL,
  `username` varchar(70) NOT NULL,
  `password` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `admin_table`
--

INSERT INTO `admin_table` (`id`, `admin_id`, `username`, `password`, `email`) VALUES
(1, 1234, 'admin', '81dc9bdb52d04dc20036dbd8313ed055', 'admin@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `bus_table`
--

CREATE TABLE `bus_table` (
  `id` int(20) NOT NULL,
  `bus_id` int(20) NOT NULL,
  `route_id` int(20) NOT NULL,
  `seat` int(20) NOT NULL DEFAULT '60'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bus_table`
--

INSERT INTO `bus_table` (`id`, `bus_id`, `route_id`, `seat`) VALUES
(1, 1, 1, 58),
(2, 2, 2, 60),
(3, 3, 3, 58);

-- --------------------------------------------------------

--
-- Table structure for table `location_table`
--

CREATE TABLE `location_table` (
  `id` int(20) NOT NULL,
  `bus_id` int(20) NOT NULL,
  `lat` float NOT NULL,
  `lan` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `location_table`
--

INSERT INTO `location_table` (`id`, `bus_id`, `lat`, `lan`) VALUES
(1, 1, 22.4624, 91.9739),
(2, 2, 22.3419, 91.8155),
(3, 3, 22.3419, 91.8155);

-- --------------------------------------------------------

--
-- Table structure for table `route_table`
--

CREATE TABLE `route_table` (
  `id` int(20) NOT NULL,
  `route_id` int(20) NOT NULL,
  `route` varchar(200) NOT NULL,
  `start_at` varchar(20) NOT NULL,
  `end_at` varchar(20) NOT NULL,
  `gender` varchar(20) NOT NULL,
  `time` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `route_table`
--

INSERT INTO `route_table` (`id`, `route_id`, `route`, `start_at`, `end_at`, `gender`, `time`) VALUES
(3, 3, 'IIUC,AK KHAN', 'IIUC', 'AK KHAN', 'Female', '12:30 p.m.'),
(4, 11, 'aa', 'aa', 'aa', 'aa', '11:11'),
(5, 11, 'aa', 'aa', 'aa', 'aa', '11:11'),
(6, 11, 'aa', 'aa', 'aa', 'aa', '11:11'),
(7, 11, 'aa', 'aa', 'aa', 'aa', '11:11'),
(8, 11, 'aa', 'aa', 'aa', 'aa', '11:11');

-- --------------------------------------------------------

--
-- Table structure for table `student_table`
--

CREATE TABLE `student_table` (
  `id` int(20) NOT NULL,
  `student_id` varchar(20) NOT NULL,
  `username` varchar(70) NOT NULL,
  `password` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `student_table`
--

INSERT INTO `student_table` (`id`, `student_id`, `username`, `password`, `email`) VALUES
(1, '1404027', 'Tanvir', 'cse', 'cse@gmail.com'),
(2, '1404027', 'Tanvir', 'cse', 'cse@gmail.com'),
(3, 'vcghjcgh', 'Fujbv', '2fafe492c5495cff354d79bec974fe5b', 'fhjh@gmail.com');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin_table`
--
ALTER TABLE `admin_table`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `bus_table`
--
ALTER TABLE `bus_table`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `location_table`
--
ALTER TABLE `location_table`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `route_table`
--
ALTER TABLE `route_table`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `student_table`
--
ALTER TABLE `student_table`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin_table`
--
ALTER TABLE `admin_table`
  MODIFY `id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `bus_table`
--
ALTER TABLE `bus_table`
  MODIFY `id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `location_table`
--
ALTER TABLE `location_table`
  MODIFY `id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `route_table`
--
ALTER TABLE `route_table`
  MODIFY `id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT for table `student_table`
--
ALTER TABLE `student_table`
  MODIFY `id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
