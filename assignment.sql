-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 11, 2022 at 10:35 AM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 8.1.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `assignment`
--

-- --------------------------------------------------------

--
-- Table structure for table `categories`
--

CREATE TABLE `categories` (
  `id` int(11) NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `status` varchar(5) COLLATE utf8_unicode_ci DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `categories`
--

INSERT INTO `categories` (`id`, `name`, `status`) VALUES
(5, 'quần đẹp ds', NULL),
(6, 'quan ao mua he', NULL),
(19, 'saasssssssssss', NULL),
(22, 'dsafasdf', NULL),
(23, 'ddddddddddd', NULL),
(26, 'zcxvzx', NULL),
(29, 'Quan ao mua he', NULL),
(34, '     213            213', NULL),
(36, 'ja jddf', NULL),
(37, 'ssssss', NULL),
(38, 'sddddđ', NULL),
(39, 'ao so mi mua he', NULL),
(40, '22222222222', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `order_id` varchar(50) NOT NULL,
  `user_id` int(11) NOT NULL,
  `order_status` int(1) NOT NULL DEFAULT 0 COMMENT '0: Chưa xác nhận\r\n1: Đã xác nhận\r\n3: Đã hủy\r\n4: Đang giao\r\n5: Giao thành công',
  `status` varchar(1) DEFAULT '1',
  `id` int(11) NOT NULL,
  `delivery_address` varchar(225) NOT NULL,
  `phone_number` varchar(15) NOT NULL,
  `total_money` double NOT NULL,
  `note` text DEFAULT NULL,
  `cancel_note` text DEFAULT NULL,
  `ctime` timestamp NOT NULL DEFAULT current_timestamp(),
  `mtime` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`order_id`, `user_id`, `order_status`, `status`, `id`, `delivery_address`, `phone_number`, `total_money`, `note`, `cancel_note`, `ctime`, `mtime`) VALUES
('1649843412801', 8, 2, '1', 31, 'Thụy Quỳnh,Thái Thụy,Thái Bình, Thái Thụy', '0368940942', 0, '1231', '', '2022-04-13 09:50:12', '2022-04-13 09:51:32'),
('1649906606867', 8, 2, '1', 32, 'số nhà 6, ngõ 99/11 phường Phúc Diễn, đường Phúc Diễn, quận Bắc Từ Niêm, Hà Nội', '0368940942', 0, '1231', '', '2022-04-14 03:23:26', '2022-04-14 03:44:12'),
('1649907611519', 8, 1, '1', 33, 'Thụy Quỳnh,Thái Thụy,Thái Bình', '0399772605', 0, '22222', '', '2022-04-14 03:40:11', '2022-04-14 03:44:29'),
('1650850746260', 8, 2, '1', 35, 'Thụy Quỳnh,Thái Thụy,Thái Bình, Thái Thụy', '0399772605', 0, '1231', 'sdddddddd', '2022-04-25 01:39:06', '2022-04-25 03:37:38'),
('1650856252658', 8, 1, '1', 36, 'Thụy Quỳnh,Thái Thụy,Thái Bình, Thái Thụy', '0368940942', 350000, '1231', '', '2022-04-25 03:10:52', NULL),
('1650946069081', 8, 2, '1', 37, 'Thá»¥y Quá»³nh,ThÃ¡i Thá»¥y,ThÃ¡i BÃ¬nh', '0368940942', 400000, '21', NULL, '2022-04-26 04:07:49', '2022-06-09 02:09:44'),
('1654321124137', 8, 0, NULL, 38, 'ha noi', '0456728913', 60000, 'rqwewwwwwwww', NULL, '2022-06-04 05:38:44', NULL),
('1654321714032', 8, 0, NULL, 40, 'thai binh', '02189379821', 859992, '128080joiurwoiur872', NULL, '2022-06-04 05:48:34', NULL),
('1654321802924', 8, 1, NULL, 42, 'tp ho chi minh', '08739728472', 122222, '21', NULL, '2022-06-04 05:50:03', '2022-06-09 02:09:34'),
('1654322709132', 8, 0, NULL, 43, 'fpt polytechnic', '08739728472', 155555, 'ieuriowueo', NULL, '2022-06-04 06:05:09', NULL),
('1654739137087', 8, 0, NULL, 44, 'Thanh xuân Hà Nội', '0873972843', 860000, '', NULL, '2022-06-09 01:45:37', NULL),
('1654739288001', 8, 0, NULL, 45, 'Cao đẳng fpt Polytechnic', '0965544225', 180000, '', NULL, '2022-06-09 01:48:08', NULL),
('1654739465016', 20, 1, NULL, 46, 'Cao đẳng fpt Polytechnic', '0985274196', 1100000, '', NULL, '2022-06-09 01:51:05', '2022-06-09 02:04:31'),
('1654739568639', 20, 2, NULL, 47, 'Thanh xuân Hà Nội', '0985274196', 60000, '', NULL, '2022-06-09 01:52:48', NULL),
('1654739644844', 20, 0, NULL, 48, 'Nam từ Niêm Hà Nội', '0873972847', 99999, '', NULL, '2022-06-09 01:54:04', NULL),
('1654753373702', 20, 1, NULL, 49, 'Cao đẳng fpt Polytechnic', '0985274196', 300000, '', NULL, '2022-06-09 05:42:53', '2022-06-09 05:44:28');

-- --------------------------------------------------------

--
-- Table structure for table `order_detail`
--

CREATE TABLE `order_detail` (
  `amount` int(11) NOT NULL,
  `price` float NOT NULL,
  `order_id` int(11) NOT NULL,
  `id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `order_detail`
--

INSERT INTO `order_detail` (`amount`, `price`, `order_id`, `id`, `product_id`) VALUES
(4, 100000, 31, 12, 1),
(4, 50000, 31, 13, 2),
(1, 100000, 32, 14, 1),
(3, 100000, 33, 15, 1),
(4, 100000, 34, 16, 1),
(1, 50000, 35, 17, 2),
(5, 50000, 36, 18, 2),
(1, 100000, 36, 19, 1),
(4, 100000, 37, 20, 1),
(1, 100000, 43, 21, 1),
(1, 55555, 43, 22, 4),
(8, 100000, 44, 23, 1),
(1, 60000, 44, 24, 2),
(3, 60000, 45, 25, 2),
(11, 100000, 46, 26, 1),
(1, 60000, 47, 27, 2),
(1, 99999, 48, 28, 3),
(5, 60000, 49, 29, 2);

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE `products` (
  `id` int(11) NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `stock` int(11) NOT NULL DEFAULT 0,
  `price` int(11) NOT NULL DEFAULT 0,
  `color` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `size` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `image` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `category_id` int(11) NOT NULL,
  `status` varchar(1) COLLATE utf8_unicode_ci DEFAULT '1',
  `description` varchar(225) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`id`, `name`, `stock`, `price`, `color`, `size`, `image`, `category_id`, `status`, `description`) VALUES
(1, 'Áo Thun Nam Cổ Tròn Cao Cấp ( nhiều màu) Tay Ngắn, chất cotton bề mặt vải mềm', 50, 100000, 'Đen', 'M', '0a59b3b00082cca50cbe1293cfc39fd9.jpg', 6, NULL, 'jjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj'),
(2, '[SALE 45%] - QUẦN SHORT KAKI NAM CO GIÃN CAO CẤP BẢNG MÀU CỰC SANG TRỌNG', 23423, 60000, 'Nâu', 'M', '5a82b1b2650c3ff78e9bd4b8fd17fecc.jpg', 5, NULL, 'quần addepj'),
(3, 'Áo Sơmi BASIC CỔ VEST FULLTAG | Sơ Mi Lụa Mềm CNK 100% | CHUẨN CAO CẤP', 19, 99999, 'Đen', 'M', '6b5d7dab6e8d64b6bf6d9210833d8119.jpg', 6, NULL, 'wwwwwwwwwww'),
(4, 'Áo thun tay lỡ nam nữ 3nana, áo phông Unisex in chữ monster', 12, 55555, 'Đen', 'M', '9af4232210c2c76053cdfd82ef9457a5.jpg', 6, NULL, '222222'),
(11, 'Áo phông nam nữ form rộng Gấu Predium Laylazm, áo thun unisex tay lỡ oversize Hàn Quốc', 232, 122222, 'Đen', '32', '17a4d7dd8ca6efffc3b0987b21013d9e.jpg', 5, NULL, '23123'),
(14, 'Quần short nữ dập nổi 3D xốp phồng cao cấp - Quần sooc xốp hoa phối nhiều kiểu áo năng động Q508 SUTANO', 123, 250000, 'Cam', 'M', 'd7cf7cd7d8b253288a9063ea2a235a2a.jpg', 29, NULL, '');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `fullname` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `gender` int(11) NOT NULL DEFAULT 1,
  `phone_number` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `address` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `image` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `role` int(1) NOT NULL DEFAULT 0,
  `status` varchar(5) COLLATE utf8_unicode_ci DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `fullname`, `gender`, `phone_number`, `address`, `email`, `password`, `image`, `role`, `status`) VALUES
(8, 'Nguyen van A', 1, '0399772605', 'Thụy Quỳnh,Thái Thụy,Thái Bình', 'hoan26052k2@gmail.com', '$2a$10$F5411x1DDmf/a5CTVn9HdeCp3fwtnL0Ss9TH/iiUB1jfQdkM4MfYa', NULL, 1, NULL),
(9, 'Hoan Đào Ngọc', 0, '0399772605', 'số nhà 6, ngõ 99/11 phường Phúc Diễn, đường Phúc Diễn, quận Bắc Từ Niêm, Hà Nội', 'hoanam2k2.tb@gmail.com', '$2a$10$sV1M6duIE5ysYWzAQRDof.xXoVqekKe4Ryo5qORzAWGVBM5IBjiqu', NULL, 1, NULL),
(16, 'le ban truong', 0, '0874196377', 'ladjfjskdjfl', 'levantruowng@gmail.com', '$2a$10$Vpc9BPKBc6r10S4ekmnK/.eHc.30U2DPdnvyJK7wVP6NGjFfo7pq.', NULL, 0, NULL),
(19, 'Lê Văn Minh', 1, '0965544225', 'Bắc Từ Niêm Hà Nội', 'anhminh@gmail.com', '$2a$10$j.A47XjthDYi07xADx4tMuyRh5aNGU1WBgj/HZrDfV9IwoiDMRZme', NULL, 0, NULL),
(20, 'Trần Văn Long', 0, '0985274196', 'Hải phòng', 'long@gmail.com', '$2a$10$E198aq.2oI8BUn9c016g2.HFpwAO.LRu25WoDntkV1AnFUBcoCuKy', NULL, 0, NULL),
(21, 'Lê Long An', 0, '0399852741', 'Đống Đa Hà Nội', 'anll@fpt.edu.vn', '$2a$10$L4Jt66w.agkZ6qqQ5Bvw6ulaz1jS4GlBlDOnUO/Wu8DC.nyPcKR22', NULL, 0, '1'),
(22, 'Dào Hoan', 0, '0936544555', 'Hà nội', 'hoan@gmail.com', '$2a$10$Y14XQab9/u8oSsBTV97WJuB.1k1QLHePCWPHPeao8PeBA2i9.ItiK', NULL, 0, NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `categories`
--
ALTER TABLE `categories`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `order_id` (`order_id`);

--
-- Indexes for table `order_detail`
--
ALTER TABLE `order_detail`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `categories`
--
ALTER TABLE `categories`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=41;

--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=50;

--
-- AUTO_INCREMENT for table `order_detail`
--
ALTER TABLE `order_detail`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;

--
-- AUTO_INCREMENT for table `products`
--
ALTER TABLE `products`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=61;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
