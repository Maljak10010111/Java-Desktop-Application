/*
SQLyog Community v13.1.6 (64 bit)
MySQL - 10.4.18-MariaDB : Database - salon1
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`ordinacija` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;

USE `ordinacija`;

DROP TABLE IF EXISTS `Ustanova`;

CREATE TABLE `Ustanova` (
  `UstanovaID` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `Naziv` VARCHAR(30) NOT NULL,
  `Grad` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`UstanovaID`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

INSERT  INTO `Ustanova`(`UstanovaID`,`Naziv`,`Grad`) VALUES 
(1,'KC Srbije', 'Beograd'),
(2,'Ortopedska bolnica Banjica', 'Beograd'),
(3,'Opsta bolnica Sremska Mitrovica', 'Sremska Mitrovica');

/*Table structure for table `Administrator` */

DROP TABLE IF EXISTS `Doktor`;

CREATE TABLE `Doktor` (
  `DoktorID` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `Ime` VARCHAR(20) NOT NULL,
  `Prezime` VARCHAR(30) NOT NULL,
  `Username` VARCHAR(20) NOT NULL,
  `Password` VARCHAR(20) NOT NULL,
  `UstanovaID` BIGINT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`DoktorID`),
  CONSTRAINT `fk_ustanova_id` FOREIGN KEY (`UstanovaID`) REFERENCES `Ustanova` (`UstanovaID`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

/*Data for the table `Upravnik` */

INSERT  INTO `Doktor`(`DoktorID`,`Ime`,`Prezime`,`Username`,`Password`,`UstanovaID`) VALUES 
(1,'Igor','Maljkovic','igor','igor123',2),
(2,'Milos','Milakovic','milos','milos123',1),
(3,'Petar','Jovanovic','petar','pera123',3);

/*Table structure for table `kategorija` */

DROP TABLE IF EXISTS `Pacijent`;

CREATE TABLE `Pacijent` (
  `PacijentID` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `Ime` VARCHAR(20) NOT NULL,
  `Prezime` VARCHAR(30) NOT NULL,
  `Email` VARCHAR(20) NOT NULL,
  `Telefon` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`PacijentID`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

/*Data for the table `Scena` */

INSERT  INTO `Pacijent`(`PacijentID`,`Ime`,`Prezime`,`Email`,`Telefon`) VALUES 
(1,'Milena', 'Perisic', 'milena@gmail.com', '0631231234'),
(2,'Vasilije', 'Srpnjakovic', 'sprnja@gmail.com', '0654645434'),
(3,'Kristijan', 'Petrov', 'vaske@gmail.com', '0641235153');

DROP TABLE IF EXISTS `VrstaUsluge`;

CREATE TABLE `VrstaUsluge` (
  `VrstaUslugeID` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `Naziv` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`VrstaUslugeID`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

/*Data for the table `Scena` */

INSERT  INTO `VrstaUsluge`(`VrstaUslugeID`,`Naziv`) VALUES 
(1,'Pregled'),
(2,'Operacija'),
(3,'Namestanje'),
(4,'PRP tretman');


/*Table structure for table `Predstava` */

DROP TABLE IF EXISTS `Termin`;

CREATE TABLE `Termin` (
  `TerminID` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `DatumVreme` DATETIME NOT NULL,
  `CenaTermina` DECIMAL(10,2) NOT NULL,
  `IzvestajID` BIGINT(10) UNSIGNED,
  `PacijentID` BIGINT(10) UNSIGNED NOT NULL,
  `DoktorID` BIGINT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`TerminID`),
  CONSTRAINT `fk_pacijent_id` FOREIGN KEY (`PacijentID`) REFERENCES `Pacijent` (`PacijentID`) ON DELETE CASCADE,
  CONSTRAINT `fk_doktor_id` FOREIGN KEY (`DoktorID`) REFERENCES `Doktor` (`DoktorID`),
  CONSTRAINT `fk_izvestaj_id` FOREIGN KEY (`IzvestajID`) REFERENCES `Izvestaj` (`IzvestajID`) ON DELETE CASCADE
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `Predstava` */

INSERT  INTO `Termin`(`TerminID`,`DatumVreme`,`CenaTermina`,`IzvestajID`,`PacijentID`,`DoktorID`) VALUES 
(1,'2021-09-01 08:00:00',10000,1,1,1);


DROP TABLE IF EXISTS `Usluga`;

CREATE TABLE `Usluga` (
  `TerminID` BIGINT(10) UNSIGNED NOT NULL,
  `RbUsluge` INT(7) NOT NULL,
  `CenaUsluge` DECIMAL(10,2) NOT NULL,
  `VrstaUslugeID` BIGINT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`TerminID`,`RbUsluge`),
  CONSTRAINT `fk_termin_id` FOREIGN KEY (`TerminID`) REFERENCES `Termin` (`TerminID`) ON DELETE CASCADE,
  CONSTRAINT `fk_vrstaUsluge_id` FOREIGN KEY (`VrstaUslugeID`) REFERENCES `VrstaUsluge` (`VrstaUslugeID`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

/*Data for the table `racun` */

INSERT  INTO `Usluga`(`TerminID`,`RbUsluge`,`CenaUsluge`,`VrstaUslugeID`) VALUES 
(1,1,2000,1),
(1,2,8000,4);

DROP TABLE IF EXISTS `Izvestaj`;

CREATE TABLE `Izvestaj` (
  `IzvestajID` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `Opis` VARCHAR(200) NOT NULL,
  `TerminID` BIGINT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`IzvestajID`),
  CONSTRAINT `fk2_termin_id` FOREIGN KEY (`TerminID`) REFERENCES `Termin` (`TerminID`) ON DELETE CASCADE
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

/*Data for the table `Scena` */

INSERT  INTO `Izvestaj`(`IzvestajID`,`Opis`,`TerminID`) VALUES 
(1,'Ruptura ahilove tetive. Izvrsen pregled i PRP tretman. Mirovanje, bandaziranje i fastum gel 7 dana.',1);


/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
