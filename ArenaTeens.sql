-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: 07-Dez-2018 às 15:03
-- Versão do servidor: 10.1.36-MariaDB
-- versão do PHP: 5.6.38

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `arenateens`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `codigo`
--

CREATE TABLE `codigo` (
  `cod_id` int(11) NOT NULL,
  `cod_type` int(11) NOT NULL,
  `cod_extid` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `fechamento`
--

CREATE TABLE `fechamento` (
  `fech_id` int(11) NOT NULL,
  `fech_inic` date NOT NULL,
  `fech_fim` date NOT NULL,
  `fech_type` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `ga`
--

CREATE TABLE `ga` (
  `ga_id` int(11) NOT NULL,
  `ga_nome` varchar(400) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `relatorios`
--

CREATE TABLE `relatorios` (
  `rel_id` int(11) NOT NULL,
  `rel_type` int(11) NOT NULL,
  `rel_isclosed` tinyint(1) DEFAULT NULL,
  `rel_idfech` int(11) DEFAULT NULL,
  `rel_idteen` int(11) NOT NULL,
  `rel_extid` int(11) NOT NULL,
  `rel_pont` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `rel_arena`
--

CREATE TABLE `rel_arena` (
  `arena_rel_id` int(11) NOT NULL,
  `arena_rel_presenca` tinyint(1) DEFAULT NULL,
  `arena_rel_meditacao` tinyint(1) DEFAULT NULL,
  `arena_rel_pontualidade` tinyint(1) DEFAULT NULL,
  `arena_rel_anotDom` tinyint(1) DEFAULT NULL,
  `arena_rel_anotArena` tinyint(1) DEFAULT NULL,
  `arena_rel_versiculos` int(11) DEFAULT NULL,
  `arena_rel_extra` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `rel_imersao`
--

CREATE TABLE `rel_imersao` (
  `imer_rel_id` int(11) NOT NULL,
  `imer_rel_presenca` tinyint(1) DEFAULT NULL,
  `imer_rel_extra` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `usuario`
--

CREATE TABLE `usuario` (
  `usu_id` int(11) NOT NULL,
  `usu_name` varchar(400) NOT NULL,
  `usu_login` varchar(400) NOT NULL,
  `usu_password` varchar(400) NOT NULL,
  `usu_type` int(11) NOT NULL,
  `usu_ref` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `usu_sublider`
--

CREATE TABLE `usu_sublider` (
  `subl_id` int(11) NOT NULL,
  `subl_codga` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `usu_teen`
--

CREATE TABLE `usu_teen` (
  `teen_id` int(11) NOT NULL,
  `teen_codga` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `codigo`
--
ALTER TABLE `codigo`
  ADD PRIMARY KEY (`cod_id`);

--
-- Indexes for table `fechamento`
--
ALTER TABLE `fechamento`
  ADD PRIMARY KEY (`fech_id`);

--
-- Indexes for table `ga`
--
ALTER TABLE `ga`
  ADD PRIMARY KEY (`ga_id`);

--
-- Indexes for table `relatorios`
--
ALTER TABLE `relatorios`
  ADD PRIMARY KEY (`rel_id`);

--
-- Indexes for table `rel_arena`
--
ALTER TABLE `rel_arena`
  ADD PRIMARY KEY (`arena_rel_id`);

--
-- Indexes for table `rel_imersao`
--
ALTER TABLE `rel_imersao`
  ADD PRIMARY KEY (`imer_rel_id`);

--
-- Indexes for table `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`usu_id`);

--
-- Indexes for table `usu_sublider`
--
ALTER TABLE `usu_sublider`
  ADD PRIMARY KEY (`subl_id`);

--
-- Indexes for table `usu_teen`
--
ALTER TABLE `usu_teen`
  ADD PRIMARY KEY (`teen_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `codigo`
--
ALTER TABLE `codigo`
  MODIFY `cod_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `fechamento`
--
ALTER TABLE `fechamento`
  MODIFY `fech_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `ga`
--
ALTER TABLE `ga`
  MODIFY `ga_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `relatorios`
--
ALTER TABLE `relatorios`
  MODIFY `rel_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `rel_arena`
--
ALTER TABLE `rel_arena`
  MODIFY `arena_rel_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `rel_imersao`
--
ALTER TABLE `rel_imersao`
  MODIFY `imer_rel_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `usuario`
--
ALTER TABLE `usuario`
  MODIFY `usu_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `usu_sublider`
--
ALTER TABLE `usu_sublider`
  MODIFY `subl_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `usu_teen`
--
ALTER TABLE `usu_teen`
  MODIFY `teen_id` int(11) NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
