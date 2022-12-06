-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Хост: 127.0.0.1:3306
-- Время создания: Дек 06 2022 г., 14:32
-- Версия сервера: 5.6.51
-- Версия PHP: 7.2.34

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `personalProject`
--

-- --------------------------------------------------------

--
-- Структура таблицы `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(12);

-- --------------------------------------------------------

--
-- Структура таблицы `model_certificate`
--

CREATE TABLE `model_certificate` (
  `id_certificate` bigint(20) NOT NULL,
  `certificate_name` varchar(50) DEFAULT NULL,
  `expiration_date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `model_certificate`
--

INSERT INTO `model_certificate` (`id_certificate`, `certificate_name`, `expiration_date`) VALUES
(9, 'абжобжа', '2022-12-20');

-- --------------------------------------------------------

--
-- Структура таблицы `model_good`
--

CREATE TABLE `model_good` (
  `id_good` bigint(20) NOT NULL,
  `good_name` varchar(40) NOT NULL,
  `good_weight` double NOT NULL,
  `certificate_id_certificate` bigint(20) DEFAULT NULL,
  `good_type_id_good_type` bigint(20) DEFAULT NULL,
  `material_id_material` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `model_good`
--

INSERT INTO `model_good` (`id_good`, `good_name`, `good_weight`, `certificate_id_certificate`, `good_type_id_good_type`, `material_id_material`) VALUES
(11, 'абоба', 33, 9, 10, 8);

-- --------------------------------------------------------

--
-- Структура таблицы `model_good_type`
--

CREATE TABLE `model_good_type` (
  `id_good_type` bigint(20) NOT NULL,
  `good_type_name` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `model_good_type`
--

INSERT INTO `model_good_type` (`id_good_type`, `good_type_name`) VALUES
(10, 'еееее');

-- --------------------------------------------------------

--
-- Структура таблицы `model_maintenance`
--

CREATE TABLE `model_maintenance` (
  `id_maintenance` bigint(20) NOT NULL,
  `maintenance_date` date NOT NULL,
  `maintenance_status` bit(1) NOT NULL,
  `maintenance_text` varchar(200) DEFAULT NULL,
  `user_id_user` bigint(20) DEFAULT NULL,
  `warehouse_id_warehouse` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Структура таблицы `model_material`
--

CREATE TABLE `model_material` (
  `id_material` bigint(20) NOT NULL,
  `material_name` varchar(40) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `model_material`
--

INSERT INTO `model_material` (`id_material`, `material_name`) VALUES
(8, 'попа');

-- --------------------------------------------------------

--
-- Структура таблицы `model_pasport_data`
--

CREATE TABLE `model_pasport_data` (
  `idpasport` bigint(20) NOT NULL,
  `pasport_number` int(11) NOT NULL,
  `pasport_series` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `model_pasport_data`
--

INSERT INTO `model_pasport_data` (`idpasport`, `pasport_number`, `pasport_series`) VALUES
(1, 123456, 1234);

-- --------------------------------------------------------

--
-- Структура таблицы `model_private_data`
--

CREATE TABLE `model_private_data` (
  `idprivate_data` bigint(20) NOT NULL,
  `name` varchar(35) DEFAULT NULL,
  `oms_number` varchar(16) DEFAULT NULL,
  `patronymic` varchar(35) DEFAULT NULL,
  `surname` varchar(35) DEFAULT NULL,
  `pasportdata_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `model_private_data`
--

INSERT INTO `model_private_data` (`idprivate_data`, `name`, `oms_number`, `patronymic`, `surname`, `pasportdata_id`) VALUES
(2, 'абоба', '1234567890123456', 'абоба', 'абоба', 1);

-- --------------------------------------------------------

--
-- Структура таблицы `model_quality`
--

CREATE TABLE `model_quality` (
  `id_quality` bigint(20) NOT NULL,
  `comments` varchar(100) DEFAULT NULL,
  `date_of_completion` date NOT NULL,
  `good_id_good` bigint(20) DEFAULT NULL,
  `user_id_user` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Структура таблицы `model_shipment`
--

CREATE TABLE `model_shipment` (
  `id_shipment` bigint(20) NOT NULL,
  `shipment_date` date NOT NULL,
  `good_id_good` bigint(20) DEFAULT NULL,
  `user_id_user` bigint(20) DEFAULT NULL,
  `warehouse_id_warehouse` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Структура таблицы `model_user`
--

CREATE TABLE `model_user` (
  `id_user` bigint(20) NOT NULL,
  `password` varchar(100) DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL,
  `privatedata_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `model_user`
--

INSERT INTO `model_user` (`id_user`, `password`, `username`, `privatedata_id`) VALUES
(3, '$2a$08$t2CsZaroXCZYkgMP9GqDjeZq7YLnkwDNX3OLASGsbk/b6sNUFMX0e', 'aboba', 2);

-- --------------------------------------------------------

--
-- Структура таблицы `model_warehouse`
--

CREATE TABLE `model_warehouse` (
  `id_warehouse` bigint(20) NOT NULL,
  `warehouse_address` varchar(80) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Структура таблицы `user_role`
--

CREATE TABLE `user_role` (
  `user_id` bigint(20) NOT NULL,
  `roles` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `user_role`
--

INSERT INTO `user_role` (`user_id`, `roles`) VALUES
(3, 'ADMIN'),
(3, 'MAINTENANCEWORKER'),
(3, 'QUALITYWORKER'),
(3, 'WAREHOUSEWORKER'),
(3, 'GOODSWORKER');

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `model_certificate`
--
ALTER TABLE `model_certificate`
  ADD PRIMARY KEY (`id_certificate`);

--
-- Индексы таблицы `model_good`
--
ALTER TABLE `model_good`
  ADD PRIMARY KEY (`id_good`),
  ADD KEY `FK4a86bdeoagkndm8wppd3uwdx8` (`certificate_id_certificate`),
  ADD KEY `FKr1in2jwnwy5t3v98yvy68svco` (`good_type_id_good_type`),
  ADD KEY `FK7k8bbl5vwuaigjfacr2lsdg76` (`material_id_material`);

--
-- Индексы таблицы `model_good_type`
--
ALTER TABLE `model_good_type`
  ADD PRIMARY KEY (`id_good_type`);

--
-- Индексы таблицы `model_maintenance`
--
ALTER TABLE `model_maintenance`
  ADD PRIMARY KEY (`id_maintenance`),
  ADD KEY `FK65debbmv76dt38oe8rf4jnff1` (`user_id_user`),
  ADD KEY `FK541e5v0sl1uwb9c95oo10qavf` (`warehouse_id_warehouse`);

--
-- Индексы таблицы `model_material`
--
ALTER TABLE `model_material`
  ADD PRIMARY KEY (`id_material`);

--
-- Индексы таблицы `model_pasport_data`
--
ALTER TABLE `model_pasport_data`
  ADD PRIMARY KEY (`idpasport`);

--
-- Индексы таблицы `model_private_data`
--
ALTER TABLE `model_private_data`
  ADD PRIMARY KEY (`idprivate_data`),
  ADD KEY `FKmc53g01kxt3e7c2arsnjs0wwt` (`pasportdata_id`);

--
-- Индексы таблицы `model_quality`
--
ALTER TABLE `model_quality`
  ADD PRIMARY KEY (`id_quality`),
  ADD KEY `FKl7g3ebvemycosffl5tjpxma9e` (`good_id_good`),
  ADD KEY `FK4e79dwwitxank3pr2xjv2cw7d` (`user_id_user`);

--
-- Индексы таблицы `model_shipment`
--
ALTER TABLE `model_shipment`
  ADD PRIMARY KEY (`id_shipment`),
  ADD KEY `FKgxemh666hcyj4jqupfepeifx2` (`good_id_good`),
  ADD KEY `FKgml6d351l3wtrg9ep931xhdh7` (`user_id_user`),
  ADD KEY `FKdjunyx6nw299rxumlprt34u25` (`warehouse_id_warehouse`);

--
-- Индексы таблицы `model_user`
--
ALTER TABLE `model_user`
  ADD PRIMARY KEY (`id_user`),
  ADD KEY `FK43noqqopaykyii4bmk2bwaf4l` (`privatedata_id`);

--
-- Индексы таблицы `model_warehouse`
--
ALTER TABLE `model_warehouse`
  ADD PRIMARY KEY (`id_warehouse`);

--
-- Индексы таблицы `user_role`
--
ALTER TABLE `user_role`
  ADD KEY `FKhnk3nw6rsvkly3ww7umdq7ys1` (`user_id`);

--
-- Ограничения внешнего ключа сохраненных таблиц
--

--
-- Ограничения внешнего ключа таблицы `model_good`
--
ALTER TABLE `model_good`
  ADD CONSTRAINT `FK4a86bdeoagkndm8wppd3uwdx8` FOREIGN KEY (`certificate_id_certificate`) REFERENCES `model_certificate` (`id_certificate`),
  ADD CONSTRAINT `FK7k8bbl5vwuaigjfacr2lsdg76` FOREIGN KEY (`material_id_material`) REFERENCES `model_material` (`id_material`),
  ADD CONSTRAINT `FKr1in2jwnwy5t3v98yvy68svco` FOREIGN KEY (`good_type_id_good_type`) REFERENCES `model_good_type` (`id_good_type`);

--
-- Ограничения внешнего ключа таблицы `model_maintenance`
--
ALTER TABLE `model_maintenance`
  ADD CONSTRAINT `FK541e5v0sl1uwb9c95oo10qavf` FOREIGN KEY (`warehouse_id_warehouse`) REFERENCES `model_warehouse` (`id_warehouse`),
  ADD CONSTRAINT `FK65debbmv76dt38oe8rf4jnff1` FOREIGN KEY (`user_id_user`) REFERENCES `model_user` (`id_user`);

--
-- Ограничения внешнего ключа таблицы `model_private_data`
--
ALTER TABLE `model_private_data`
  ADD CONSTRAINT `FKmc53g01kxt3e7c2arsnjs0wwt` FOREIGN KEY (`pasportdata_id`) REFERENCES `model_pasport_data` (`idpasport`);

--
-- Ограничения внешнего ключа таблицы `model_quality`
--
ALTER TABLE `model_quality`
  ADD CONSTRAINT `FK4e79dwwitxank3pr2xjv2cw7d` FOREIGN KEY (`user_id_user`) REFERENCES `model_user` (`id_user`),
  ADD CONSTRAINT `FKl7g3ebvemycosffl5tjpxma9e` FOREIGN KEY (`good_id_good`) REFERENCES `model_good` (`id_good`);

--
-- Ограничения внешнего ключа таблицы `model_shipment`
--
ALTER TABLE `model_shipment`
  ADD CONSTRAINT `FKdjunyx6nw299rxumlprt34u25` FOREIGN KEY (`warehouse_id_warehouse`) REFERENCES `model_warehouse` (`id_warehouse`),
  ADD CONSTRAINT `FKgml6d351l3wtrg9ep931xhdh7` FOREIGN KEY (`user_id_user`) REFERENCES `model_user` (`id_user`),
  ADD CONSTRAINT `FKgxemh666hcyj4jqupfepeifx2` FOREIGN KEY (`good_id_good`) REFERENCES `model_good` (`id_good`);

--
-- Ограничения внешнего ключа таблицы `model_user`
--
ALTER TABLE `model_user`
  ADD CONSTRAINT `FK43noqqopaykyii4bmk2bwaf4l` FOREIGN KEY (`privatedata_id`) REFERENCES `model_private_data` (`idprivate_data`);

--
-- Ограничения внешнего ключа таблицы `user_role`
--
ALTER TABLE `user_role`
  ADD CONSTRAINT `FKhnk3nw6rsvkly3ww7umdq7ys1` FOREIGN KEY (`user_id`) REFERENCES `model_user` (`id_user`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
