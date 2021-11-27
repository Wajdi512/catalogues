-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : sam. 27 nov. 2021 à 14:54
-- Version du serveur :  5.7.31
-- Version de PHP : 7.3.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `catalogues`
--

-- --------------------------------------------------------

--
-- Structure de la table `catalogues`
--

DROP TABLE IF EXISTS `catalogues`;
CREATE TABLE IF NOT EXISTS `catalogues` (
  `code` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) NOT NULL,
  PRIMARY KEY (`code`)
) ENGINE=MyISAM AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `catalogues`
--

INSERT INTO `catalogues` (`code`, `nom`) VALUES
(2, 'Electromenager'),
(3, 'Telephonie'),
(1, 'Informatique');

-- --------------------------------------------------------

--
-- Structure de la table `produits`
--

DROP TABLE IF EXISTS `produits`;
CREATE TABLE IF NOT EXISTS `produits` (
  `reference` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) NOT NULL,
  `prix` double NOT NULL,
  `quantite` int(11) DEFAULT '0',
  `catalogue_id` int(11) NOT NULL,
  PRIMARY KEY (`reference`),
  KEY `fk_foreign_key_categorie_id` (`catalogue_id`)
) ENGINE=MyISAM AUTO_INCREMENT=31 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `produits`
--

INSERT INTO `produits` (`reference`, `nom`, `prix`, `quantite`, `catalogue_id`) VALUES
(2, 'HP pc GAMEer', 5000, 2, 1),
(1, 'Dell laptop', 2000, 10, 1);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
