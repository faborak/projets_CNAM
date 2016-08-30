-- phpMyAdmin SQL Dump
-- version 4.3.11
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- G�n�r� le :  Mar 05 Juillet 2016 � 15:38
-- Version du serveur :  5.6.24
-- Version de PHP :  5.6.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de donn�es :  `my_swap`
--

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE IF NOT EXISTS `User` (
  `id_user` int(11) NOT NULL,
  `name` varchar(30) COLLATE utf8_bin NOT NULL,
  `lastname` varchar(30) COLLATE utf8_bin NOT NULL,
  `password` varchar(30) COLLATE utf8_bin NOT NULL,
  `birth_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Contenu de la table `User`
--

INSERT INTO `User` (`id_user`, `name`, `lastname`, `password`, `birth_date`) VALUES
(1, 'nom1','prenom1', 'password01', '1999-05-25'),
(2, 'nom2','prenom2', 'password02','1999-05-25'),
(3, 'nom3','prenom3', 'password03','1999-05-25'),
(4, 'nom4','prenom4', 'password04','1999-05-25'),
(5, 'nom5','prenom5', 'password05','1999-05-25');

-- --------------------------------------------------------

--
-- Structure de la table `Account`
--

CREATE TABLE IF NOT EXISTS `Account` (
  `id_user` int(11) NOT NULL,
  `phone_number` varchar(15) NOT NULL,
  `email` varchar(50) COLLATE utf8_bin NOT NULL,
  `emailchecked` tinyint(1) NOT NULL,
  `phonechecked` tinyint(1) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Contenu de la table `Account`
--

INSERT INTO `Account` (`id_user`, `phone_number`, `email`, `emailchecked`, `phonechecked`) VALUES
(1, '0164800585', 'usrint0001@gmail.com', 0, 0),
(2, '0164800585', 'usrint0002@gmail.com', 0, 0),
(3, '0164800585', 'usrint0003@gmail.com', 0, 0),
(4, '0164800585', 'usrint0004@gmail.com', 0, 0),
(5, '0164800585', 'usrint0005@gmail.com', 0, 0);

-- --------------------------------------------------------

--
-- Structure de la table `Adress`
--

CREATE TABLE IF NOT EXISTS `Adress` (
  `id_user` int(11) NOT NULL,
  `street` varchar(30) COLLATE utf8_bin NOT NULL,
  `state` varchar(30) COLLATE utf8_bin NOT NULL,
  `zipcode` int(6) NOT NULL,
  `city` varchar(30) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Adress from the users of the MySwap WebSite';

--
-- Contenu de la table `adress`
--

INSERT INTO `Adress` (`id_user`, `street`, `state`, `zipcode`) VALUES
(1, '1 rue test1', 'Ile-de-France', 77184),
(2, '1 rue test2', 'Ile-de-France', 77184),
(3, '1 rue test3', 'Ile-de-France', 77184),
(4, '1 rue test4', 'Ile-de-France', 77184),
(5, '1 rue test5', 'Ile-de-France', 77184);

-- --------------------------------------------------------

--
-- Structure de la table `Comment`
--

CREATE TABLE IF NOT EXISTS `Comment` (
  `id_comment` int(11) NOT NULL,
  `id_noting_user` int(10) NOT NULL,
  `id_noted_user` int(10) NOT NULL,
  `label` varchar(1800) NOT NULL,
  `mark` int(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Contenu de la table `Comment`
--

INSERT INTO `Comment` (`id_comment`, `id_noting_user`, `id_noted_user`, `label`, `mark`) VALUES
(1, 1, 2, 'Moi, 1, pense �a de 2.', 1),
(2, 2, 3, 'Moi, 2, pense �a de 3.', 2),
(3, 3, 4, 'Moi, 3, pense �a de 4.', 3),
(4, 4, 5, 'Moi, 4, pense �a de 5.', 4),
(5, 5, 1, 'Moi, 5, pense �a de 1.', 5);

-- --------------------------------------------------------

--
-- Structure de la table `Deal`
--

CREATE TABLE IF NOT EXISTS `Deal` (
  `id_deal` int(11) NOT NULL,
  `id_first_user` int(11) NOT NULL,
  `id_second_user` int(11) NOT NULL,
  `date_creation` date DEFAULT NULL,
  `date_modification` date DEFAULT NULL,
  `status` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Table principale des deals, i.e échanges non validés';

--
-- Contenu de la table `Deal`
--

INSERT INTO `Deal` (`id_deal`, `id_first_user`, `id_second_user`,`date_creation`, `date_modification`, `status`) VALUES
(1, 1, 2, "2016-08-17","2016-08-17", 'En cours de création'),
(2, 2, 3, "2016-08-17","2016-08-17", 'En cours de création'),
(3, 3, 4, "2016-08-17","2016-08-17", 'Transaction refusée'),
(4, 4, 5, "2016-08-17","2016-08-17", 'En cours de création'),
(5, 5, 1, "2016-08-17","2016-08-17", 'En cours de création');

-- --------------------------------------------------------

--
-- Structure de la table `Swap`
--

CREATE TABLE IF NOT EXISTS `Swap` (
  `id_deal` int(11) ,
  `date_reception_initiator` date DEFAULT NULL,
  `date_reception_proposed` date DEFAULT NULL,
  `date_envoi_initiator` date DEFAULT NULL,
  `date_envoi_proposed` date DEFAULT NULL,
  `date_final_initiator` date DEFAULT NULL,
  `date_final_proposed` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Contenu de la table `Swap`
--

INSERT INTO `Swap` (`id_deal`, `date_reception_initiator`, `date_reception_proposed`, `date_envoi_initiator`,`date_envoi_proposed`) VALUES
(1, "2016-08-17","2016-08-17","2016-08-17","2016-08-17");

-- --------------------------------------------------------

--
-- Structure de la table `Status`
--
CREATE TABLE `Status` (
  `code` varchar(40) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Contenu de la table `Status`
--

INSERT INTO `Status` (`code`) VALUES
('En cours de création'),
('En attente d''acceptation'),
('Transaction refusée'),
('Transaction acceptée'),
('Transaction mise à jour par l''initiateur'),
('Transaction mise à jour par le proposed'),
('Transaction validée');

-- --------------------------------------------------------

--
-- Structure de la table `DealComment`
--

CREATE TABLE IF NOT EXISTS `DealComment` (
  `id_deal_comment` int(11) NOT NULL,
  `id_deal` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `lib_comment` varchar(400) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Contenu de la table `DealComment`
--

INSERT INTO `DealComment` (`id_deal_comment`, `id_deal`, `id_user`, `lib_comment`) VALUES
(1, 1, 1,'commentaire 1 du user 1 sur deal 1'),
(2, 1, 2,'commentaire 2 du user 2 sur deal 1'),
(3, 1, 1,'commentaire 3 du user 1 sur deal 1'),
(4, 1, 1,'commentaire 4 du user 1 sur deal 1'),
(5, 1, 2,'commentaire 5 du user 2 sur deal 1');

-- --------------------------------------------------------

--
-- Structure de la table `SwapObject`
--

CREATE TABLE IF NOT EXISTS `SwapObject` (
  `id_swap_object` int(11) NOT NULL,
  `name` varchar(140) COLLATE utf8_bin NOT NULL,
  `date_creation` date DEFAULT NULL,
  `date_modification` date DEFAULT NULL,
  `description` varchar(140) COLLATE utf8_bin,
  `id_user` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='SwapObject, object parent, Item et sur uen evolution, services';

--
-- Contenu de la table `SwapObject`
--

INSERT INTO `SwapObject` (`id_swap_object`, `name`, `date_creation`, `date_modification`, `description`, `id_user`) VALUES
(1, 'objet 1', '2015-01-25', '2015-05-25', 'description de l objet 1', 1),
(2, 'objet 2', '2015-01-25', '2015-05-25', 'description de l objet 2', 1),
(3, 'objet 3', '2015-01-25', '2015-05-25', 'description de l objet 3', 1),
(4, 'objet 4', '2015-01-25', '2015-05-25', 'description de l objet 4', 2),
(5, 'objet 5', '2015-01-25', '2015-05-25', 'description de l objet 5', 3);

-- --------------------------------------------------------

--
-- Structure de la table `Item`
--

CREATE TABLE IF NOT EXISTS `Item` (
  `id_swap_object` int(11) NOT NULL,
  `category` varchar(30) COLLATE utf8_bin NOT NULL,
  `cost` float(10) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Contenu de la table `Item`
--

INSERT INTO `Item` (`id_swap_object`, `category`, `cost`) VALUES
(1, 'Informatique', 1.50),
(2, 'Ameublement', 2.50),
(3, 'Décoration', 3.50),
(4, 'Montres / Bijous', 4.50),
(5, 'Livres', 5.50);

-- --------------------------------------------------------
--
-- Structure de la table `Category`
--
CREATE TABLE `Category` (
  `code` varchar(30) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Contenu de la table `Category`
--

INSERT INTO `Category` (`code`) VALUES
('Véhicule'),
('Informatique'),
('Consoles'),
('Image et son'),
('Téléphonie'),
('Ameublement'),
('Electroménager'),
('Décoration'),
('Linge de maison'),
('Bricolage / Jardinage'),
('Vétements / chaussures'),
('Montres / Bijous'),
('Equipement bébé'),
('CD / DVD'),
('Livres'),
('Instrument de musique'),
('Fourniture de bureau'),
('Matéreil professionnel');

-- --------------------------------------------------------

--
-- Structure de la table `Dealswap`
--

CREATE TABLE IF NOT EXISTS `Dealswap` (
`id_deal` int(11) NOT NULL,
 `id_swap_object` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Contenu de la table `Dealswap`
--

INSERT INTO `Dealswap` (`id_deal`, `id_swap_object`) VALUES
(1, 1),
(1, 2),
(2, 3),
(2, 4),
(3, 5),
(3, 1),
(4, 2),
(4, 3),
(5, 4),
(5, 5);

-- --------------------------------------------------------

--
-- Structure de la table `Activity`
--

CREATE TABLE IF NOT EXISTS `Activity` (
  `id_user` int(11) ,
  `date` datetime(6) ,
  `token` varchar(30) COLLATE utf8_bin
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Contenu de la table `Activity`
--

INSERT INTO `Activity` (`id_user`, `date`, `token`) VALUES
(1, '2016-07-31 10:04:37', 'aaaaaabaaa'),
(5, '2016-07-31 10:04:37', 'aaaaaabaab');

--
-- Structure de la table `Info`
--

CREATE TABLE IF NOT EXISTS `Info` (
  `id_user` int(11) NOT NULL,
  `school` varchar(30) NOT NULL,
  `job` varchar(30) NOT NULL,
  `about` varchar(140) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Contenu de la table `Info`
--

INSERT INTO `Info` (`id_user`, `school`, `job`,`about`) VALUES
(1, 'ecole de la vie', 'job de mes r�ves', 'Salut ! Je pourrais vous dire que moi c''est Freddy, mais non.'),
(2, 'CNAM',  'informaticien', 'about.'),
(3, 'CNAM Paris',  'vendeur grande surface', 'about.'),
(4, 'UPsud XI',  'marketing op�rationnel', 'about.'),
(5, 'Lycee Jean Moulin', 'Un boulot.', 'Mon long about que je vais r�sumer rapidement.');

-- --------------------------------------------------------

--
-- Structure de la table `Picture`
--

CREATE TABLE IF NOT EXISTS `Picture` (
  `id_picture` int(11) NOT NULL,
  `name` varchar(30) COLLATE utf8_bin NOT NULL,
  `path` varchar(60) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='les pictures correspondent � une classe m�re Java.';

--
-- Contenu de la table `Picture`
--

INSERT INTO `Picture` (`id_picture`, `name`, `path`) VALUES
(1, 'premier gisement de Bouzon', '1999-05-25'),
(2, 'premier gisement de Hzk2', '2015-04-08'),
(3, 'Bouzon 3', '2015-08-30'),
(4, 'Bouzon 3', '2015-08-30'),
(5, 'Bouzon 3', '2015-08-30'),
(6, 'Bouzon 9', '2009-03-04');

-- --------------------------------------------------------

--
-- Structure de la table `UserPicture`
--

CREATE TABLE IF NOT EXISTS `UserPicture` (
  `id_picture` int(11) NOT NULL,
  `id_user` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Contenu de la table `UserPicture`
--

INSERT INTO `UserPicture` (`id_picture`, `id_user`) VALUES
(1, 1),
(2, 2),
(3, 3);

--
-- Structure de la table `ItemPicture`
--

CREATE TABLE IF NOT EXISTS `ItemPicture` (
  `id_picture` int(11) NOT NULL,
  `id_swap_object` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Contenu de la table `ItemPicture`
--

INSERT INTO `ItemPicture` (`id_picture`, `id_swap_object`) VALUES
(4, 1),
(5, 2),
(6, 3);

--
-- Index pour les tables export�es
--

--
-- Index pour la table `User`
--
ALTER TABLE `User`
  ADD PRIMARY KEY (`id_user`);

--
-- Index pour la table `Account`
--
ALTER TABLE `Account`
  ADD PRIMARY KEY (`id_user`);

--
-- Index pour la table `Adress`
--
ALTER TABLE `Adress`
  ADD PRIMARY KEY (`id_user`);

--
-- Index pour la table `Activity`
--
ALTER TABLE `Activity`
  ADD PRIMARY KEY (`token`);

--
-- Index pour la table `Comment`
--
ALTER TABLE `Comment`
  ADD PRIMARY KEY (`id_comment`);

--
-- Index pour la table `Deal`
--
ALTER TABLE `Deal`
  ADD PRIMARY KEY (`id_deal`);
  
--
-- Index pour la table `Swap`
--
ALTER TABLE `Swap`
  ADD PRIMARY KEY (`id_deal`);  

--
-- Index pour la table `Swapobject`
--
ALTER TABLE `SwapObject`
  ADD PRIMARY KEY (`id_swap_object`);

--
-- Index pour la table `Item`
--
ALTER TABLE `Item`
  ADD PRIMARY KEY (`id_swap_object`);


--
-- Index pour la table `DealComment`
--
ALTER TABLE `DealComment`
  ADD PRIMARY KEY (`id_deal_comment`);	  

--
-- Index pour la table `Info`
--
ALTER TABLE `Info`
  ADD PRIMARY KEY (`id_user`);
 
--
-- Index pour la table `Picture`
--
ALTER TABLE `Picture`
  ADD PRIMARY KEY (`id_picture`);	  
--
-- Index pour la table `UserPicture`
--
ALTER TABLE `UserPicture`
  ADD PRIMARY KEY (`id_picture`);
--
-- Index pour la table `ItemPicture`
--
ALTER TABLE `ItemPicture`
  ADD PRIMARY KEY (`id_picture`);	 
	 

--
-- AUTO_INCREMENT pour les tables export�es
--

--
-- AUTO_INCREMENT pour la table `User`
--
ALTER TABLE `User`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT pour la table `Comment`
--
ALTER TABLE `Comment`
  MODIFY `id_comment` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=46;  
--
-- AUTO_INCREMENT pour la table `Deal`
--
ALTER TABLE `Deal`
  MODIFY `id_deal` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=50;    
  
--
-- Contraintes pour la table `Swap`
--
ALTER TABLE `Swap`
ADD CONSTRAINT `swap_idfk_1` FOREIGN KEY (`id_deal`) REFERENCES `Deal` (`id_deal`) ON DELETE CASCADE ON UPDATE CASCADE;

  
--
-- AUTO_INCREMENT pour la table `DealComment`
--
ALTER TABLE `DealComment`
  MODIFY `id_deal_comment` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=50;    
--
-- AUTO_INCREMENT pour la table `SwapObject`
--
ALTER TABLE `SwapObject`
  MODIFY `id_swap_object` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=50; 
--
-- AUTO_INCREMENT pour la table `Picture`
--
ALTER TABLE `Picture`
  MODIFY `id_picture` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=50; 

--
-- Contraintes pour la table `Account`
--
ALTER TABLE `Account`
ADD CONSTRAINT `account_idfk_1` FOREIGN KEY (`id_user`) REFERENCES `User` (`id_user`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `Adress`
--
ALTER TABLE `Adress`
ADD CONSTRAINT `adress_idfk_1` FOREIGN KEY (`id_user`) REFERENCES `User` (`id_user`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `Activity`
--
ALTER TABLE `Activity`
ADD CONSTRAINT `activity_idfk_1` FOREIGN KEY (`id_user`) REFERENCES `User` (`id_user`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `Info`
--
ALTER TABLE `Info`
ADD CONSTRAINT `info_idfk_1` FOREIGN KEY (`id_user`) REFERENCES `User` (`id_user`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `Comment`
--
ALTER TABLE `Comment`
ADD CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`id_noting_user`) REFERENCES `User` (`id_user`) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE `Comment`
ADD CONSTRAINT `comment_ibfk_2` FOREIGN KEY (`id_noted_user`) REFERENCES `User` (`id_user`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `Deal`
--
ALTER TABLE `Deal`
ADD CONSTRAINT `deal_ibfk_1` FOREIGN KEY (`id_first_user`) REFERENCES `User` (`id_user`) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE `Deal`
ADD CONSTRAINT `deal_ibfk_2` FOREIGN KEY (`id_second_user`) REFERENCES `User` (`id_user`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `Swapobject`
--
ALTER TABLE `SwapObject`
ADD CONSTRAINT `swapobject_idfk_1` FOREIGN KEY (`id_user`) REFERENCES `User` (`id_user`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `DealComment`
--
ALTER TABLE `DealComment`
ADD CONSTRAINT `deal_comment_ibfk_1` FOREIGN KEY (`id_deal`) REFERENCES `Deal` (`id_deal`) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE `DealComment`
ADD CONSTRAINT `deal_comment_ibfk_2` FOREIGN KEY (`id_user`) REFERENCES `User` (`id_user`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `userPicture`
--
ALTER TABLE `UserPicture`
ADD CONSTRAINT `user_picture_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `User` (`id_user`) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE `ItemPicture`
ADD CONSTRAINT `item_picture_ibfk_1` FOREIGN KEY (`id_swap_object`) REFERENCES `SwapObject` (`id_swap_object`) ON DELETE CASCADE ON UPDATE CASCADE;


/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
