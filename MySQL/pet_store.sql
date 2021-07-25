-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 26, 2021 at 01:22 AM
-- Server version: 10.4.18-MariaDB
-- PHP Version: 7.4.18

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `pet_store`
--

-- --------------------------------------------------------

--
-- Table structure for table `bills`
--

CREATE TABLE `bills` (
  `id` int(11) NOT NULL,
  `sales_personal` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `time` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `bill_details`
--

CREATE TABLE `bill_details` (
  `bill_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `quantity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `categories`
--

CREATE TABLE `categories` (
  `id` int(11) NOT NULL,
  `name` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `is_deleted` tinyint(1) NOT NULL DEFAULT 0,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `categories`
--

INSERT INTO `categories` (`id`, `name`, `is_deleted`, `created_at`, `updated_at`, `deleted_at`) VALUES
(1, 'Thức ăn cho chó', 0, '2021-06-19 02:21:53', '2021-06-19 02:21:53', NULL),
(2, 'Thức ăn cho mèo', 0, '2021-06-19 02:22:06', '2021-06-19 02:22:06', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `producers`
--

CREATE TABLE `producers` (
  `id` int(11) NOT NULL,
  `name` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `is_deleted` tinyint(1) NOT NULL DEFAULT 0,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `producers`
--

INSERT INTO `producers` (`id`, `name`, `is_deleted`, `created_at`, `updated_at`, `deleted_at`) VALUES
(1, 'Whiskas', 0, '2021-06-19 02:22:53', '2021-06-19 02:22:53', NULL),
(2, 'Pedigree', 0, '2021-06-19 02:24:31', '2021-06-19 02:24:31', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE `products` (
  `id` int(11) NOT NULL,
  `code` varchar(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `producer_id` int(11) NOT NULL,
  `category_id` int(11) NOT NULL,
  `name` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `price` float(10,3) NOT NULL,
  `quantity_stock` int(11) NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT 0,
  `is_deleted` tinyint(1) NOT NULL DEFAULT 0,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`id`, `code`, `producer_id`, `category_id`, `name`, `price`, `quantity_stock`, `status`, `is_deleted`, `created_at`, `updated_at`, `deleted_at`) VALUES
(1, 'SP.01', 1, 2, 'Thức ăn cho mèo lớn Whiskas vị cá biển túi 1.2kg', 115.000, 10, 0, 0, '2021-06-19 02:28:58', '2021-06-19 02:28:58', NULL),
(2, 'SP.02', 1, 2, 'Thức ăn cho mèo lớn Whiskas vị cá biển túi 3kg', 260.000, 5, 0, 0, '2021-06-19 02:28:58', '2021-06-19 02:28:58', NULL),
(3, 'SP.03', 1, 2, 'Thức ăn cho mèo con Whiskas vị cá biển túi 1.1kg', 107.000, 7, 0, 0, '2021-06-19 02:29:31', '2021-06-19 02:29:31', NULL),
(4, 'SP.04', 1, 2, 'Thức ăn cho mèo con Whiskas vị cá biển túi 450g', 50.000, 2, 0, 0, '2021-06-19 02:29:31', '2021-06-19 02:29:31', NULL),
(5, 'SP.05', 1, 2, 'Thức ăn cho mèo Whiskas vị cá biển lon 400g', 36.000, 20, 0, 0, '2021-06-19 02:37:12', '2021-06-19 02:37:12', NULL),
(6, 'SP.06', 2, 1, 'Thức ăn cho chó lớn Pedigree vị bò và rau củ túi 500g', 38.500, 0, 0, 0, '2021-06-19 02:38:06', '2021-06-19 02:38:06', NULL),
(7, 'SP.07', 2, 1, 'Thức ăn cho chó lớn Pedigree vị bò và rau củ túi 3kg', 180.000, 0, 0, 0, '2021-06-19 02:38:14', '2021-06-19 02:38:14', NULL),
(8, 'SP.08', 2, 1, 'Thức ăn cho chó con Pedigree vị gà, trứng và sữa túi 1.3kg', 107.000, 6, 0, 0, '2021-06-19 02:40:46', '2021-06-19 02:40:46', NULL),
(9, 'SP.09', 2, 1, 'Bánh xương chăm sóc răng cho chó trung Pedigree túi 98g', 35.000, 2, 0, 0, '2021-06-19 02:40:46', '2021-06-19 02:40:46', NULL),
(10, 'SP.10', 2, 1, 'Sốt cho chó lớn Pedigree vị gan nướng và rau túi 130g', 16.000, 4, 0, 0, '2021-06-19 02:41:56', '2021-06-19 02:41:56', NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bills`
--
ALTER TABLE `bills`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `bill_details`
--
ALTER TABLE `bill_details`
  ADD KEY `bill_id` (`bill_id`),
  ADD KEY `product_id` (`product_id`);

--
-- Indexes for table `categories`
--
ALTER TABLE `categories`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `producers`
--
ALTER TABLE `producers`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`id`),
  ADD KEY `category_id` (`category_id`),
  ADD KEY `producer_id` (`producer_id`),
  ADD KEY `code` (`code`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `bills`
--
ALTER TABLE `bills`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `categories`
--
ALTER TABLE `categories`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `producers`
--
ALTER TABLE `producers`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `products`
--
ALTER TABLE `products`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `bill_details`
--
ALTER TABLE `bill_details`
  ADD CONSTRAINT `bill_details_ibfk_1` FOREIGN KEY (`bill_id`) REFERENCES `bills` (`id`),
  ADD CONSTRAINT `bill_details_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`);

--
-- Constraints for table `products`
--
ALTER TABLE `products`
  ADD CONSTRAINT `products_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`),
  ADD CONSTRAINT `products_ibfk_2` FOREIGN KEY (`producer_id`) REFERENCES `producers` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
