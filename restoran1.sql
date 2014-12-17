-- MySQL dump 10.13  Distrib 5.5.40, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: restoran1
-- ------------------------------------------------------
-- Server version	5.5.40-0+wheezy1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `HakAkses`
--

DROP TABLE IF EXISTS `HakAkses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `HakAkses` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `Nama_Akses` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `HakAkses`
--

LOCK TABLES `HakAkses` WRITE;
/*!40000 ALTER TABLE `HakAkses` DISABLE KEYS */;
INSERT INTO `HakAkses` VALUES (1,'admin'),(2,'pelanggan'),(3,'kasir'),(4,'pelayan');
/*!40000 ALTER TABLE `HakAkses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Orang`
--

DROP TABLE IF EXISTS `Orang`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Orang` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `Alamat` varchar(255) DEFAULT NULL,
  `Kontak` varchar(255) DEFAULT NULL,
  `Nama` varchar(255) DEFAULT NULL,
  `Jenis_Kelamin_id` bigint(20) DEFAULT NULL,
  `Nama_Akses_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK48E8CF7385344C4` (`Nama_Akses_id`),
  KEY `FK48E8CF7C283C0A3` (`Jenis_Kelamin_id`),
  CONSTRAINT `FK48E8CF7385344C4` FOREIGN KEY (`Nama_Akses_id`) REFERENCES `HakAkses` (`id`),
  CONSTRAINT `FK48E8CF7C283C0A3` FOREIGN KEY (`Jenis_Kelamin_id`) REFERENCES `jenisKelamin` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Orang`
--

LOCK TABLES `Orang` WRITE;
/*!40000 ALTER TABLE `Orang` DISABLE KEYS */;
INSERT INTO `Orang` VALUES (2,'alfa','alfa','alfa',1,3),(3,'beta','beta','beta',1,4),(4,'None','None','makan ditempat',1,2),(6,'delta','delta','delta',1,2),(7,'ceta','ceta','ceta',1,2),(8,'omega','omega','omega',1,2),(9,'Ap pendopo','Ap pendopo','Ap pendopo',1,2),(19,'brian','brian','brian',1,1),(20,'test','test','test',2,4);
/*!40000 ALTER TABLE `Orang` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bahan`
--

DROP TABLE IF EXISTS `bahan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bahan` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `Harga_Persatuan` bigint(20) NOT NULL,
  `Nama_Bahan` varchar(255) DEFAULT NULL,
  `Stock` bigint(20) NOT NULL,
  `Tanggal_Beli` datetime DEFAULT NULL,
  `Satuan_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK592A9F6ACE3583E` (`Satuan_id`),
  CONSTRAINT `FK592A9F6ACE3583E` FOREIGN KEY (`Satuan_id`) REFERENCES `satuan` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bahan`
--

LOCK TABLES `bahan` WRITE;
/*!40000 ALTER TABLE `bahan` DISABLE KEYS */;
INSERT INTO `bahan` VALUES (1,1000,'beras',598,'2014-12-16 18:11:23',1),(2,500,'gula',10,'2014-12-12 07:33:53',1),(3,700,'garam',969,'2014-12-16 18:12:42',1),(4,1500,'minyak',967,'2014-12-16 18:13:14',1),(6,500,'kecap',1000,'2014-12-17 10:28:55',1);
/*!40000 ALTER TABLE `bahan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bahanbeli`
--

DROP TABLE IF EXISTS `bahanbeli`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bahanbeli` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `Harga_Persatuan` bigint(20) NOT NULL,
  `Nama_bahan` varchar(255) DEFAULT NULL,
  `Stock` bigint(20) NOT NULL,
  `Tanggal_Beli` datetime DEFAULT NULL,
  `satuan_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK47CED3F6ACE3583E` (`satuan_id`),
  CONSTRAINT `FK47CED3F6ACE3583E` FOREIGN KEY (`satuan_id`) REFERENCES `satuan` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bahanbeli`
--

LOCK TABLES `bahanbeli` WRITE;
/*!40000 ALTER TABLE `bahanbeli` DISABLE KEYS */;
INSERT INTO `bahanbeli` VALUES (1,1000,'beras',10,'2014-12-12 07:33:16',1),(2,500,'gula',10,'2014-12-12 07:33:53',1),(3,700,'garam',10,'2014-12-12 07:34:06',1),(4,1500,'minyak',10,'2014-12-12 07:34:19',2),(5,1000,'beras',1000,'2014-12-16 18:11:23',1),(6,700,'garam',1000,'2014-12-16 18:12:42',1),(7,1500,'minyak',1000,'2014-12-16 18:13:14',1),(8,1000,'kecap',1000,'2014-12-17 10:27:15',1),(9,1000,'kecap',1000,'2014-12-17 10:27:52',1),(10,500,'kecap',1000,'2014-12-17 10:28:55',1);
/*!40000 ALTER TABLE `bahanbeli` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bahanpakai`
--

DROP TABLE IF EXISTS `bahanpakai`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bahanpakai` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `Stock` bigint(20) NOT NULL,
  `Tanggal_Pakai` datetime DEFAULT NULL,
  `oleh` varchar(255) DEFAULT NULL,
  `Nama_Bahan_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKB2CF1E0C93A1A42E` (`Nama_Bahan_id`),
  CONSTRAINT `FKB2CF1E0C93A1A42E` FOREIGN KEY (`Nama_Bahan_id`) REFERENCES `bahan` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=138 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bahanpakai`
--

LOCK TABLES `bahanpakai` WRITE;
/*!40000 ALTER TABLE `bahanpakai` DISABLE KEYS */;
INSERT INTO `bahanpakai` VALUES (2,4,'2014-12-12 12:29:36','test1',1),(3,4,'2014-12-12 12:32:14','test1',1),(4,4,'2014-12-12 12:38:01','test1',1),(7,2,'2014-12-13 09:11:56','test2',1),(8,2,'2014-12-13 09:11:56','test2',3),(9,8,'2014-12-13 09:12:28','test3',1),(10,2,'2014-12-13 09:12:41','test3',1),(11,2,'2014-12-13 09:12:41','test3',3),(12,2,'2014-12-13 09:36:05','alfa',1),(13,2,'2014-12-13 09:36:05','alfa',3),(14,2,'2014-12-13 09:36:05','alfa',4),(15,4,'2014-12-13 09:59:46','alfa',1),(16,8,'2014-12-14 06:55:56','alfa',1),(17,8,'2014-12-14 06:56:40','alfa',1),(18,2,'2014-12-14 06:59:02','alfa1',1),(19,2,'2014-12-14 06:59:02','alfa1',3),(20,2,'2014-12-14 06:59:02','alfa1',4),(21,8,'2014-12-14 08:24:24','alfa3',1),(22,2,'2014-12-14 08:24:29','alfa3',1),(23,2,'2014-12-14 08:24:29','alfa3',3),(24,2,'2014-12-14 08:24:29','alfa3',4),(33,4,'2014-12-14 14:47:23','alfa1',1),(34,4,'2014-12-14 14:52:14','alfa1',1),(41,8,'2014-12-15 19:29:29','brian',1),(42,8,'2014-12-15 19:29:29','brian',1),(43,8,'2014-12-15 19:38:03','brian',1),(44,8,'2014-12-15 19:50:12','brian',1),(45,2,'2014-12-15 19:50:53','brian',1),(46,2,'2014-12-15 19:50:53','brian',3),(47,2,'2014-12-15 19:50:53','brian',4),(48,8,'2014-12-15 19:51:12','brian',1),(49,8,'2014-12-15 19:58:25','brian',1),(50,4,'2014-12-15 20:00:06','sesuatu',1),(51,4,'2014-12-15 20:00:11','sesuatu',1),(52,4,'2014-12-15 20:00:16','sesuatu',1),(53,4,'2014-12-15 20:00:20','sesuatu',1),(54,4,'2014-12-15 20:00:23','sesuatu',1),(55,1,'2014-12-15 20:01:35','alfa1',1),(56,1,'2014-12-15 20:01:35','alfa1',3),(57,1,'2014-12-15 20:01:35','alfa1',4),(58,40,'2014-12-15 20:01:42','alfa1',1),(59,4,'2014-12-15 20:03:44','',1),(62,10,'2014-12-16 20:17:23','brian',1),(63,10,'2014-12-16 20:17:28','brian',1),(64,2,'2014-12-16 20:17:35','brian',1),(65,2,'2014-12-16 20:17:35','brian',4),(66,1,'2014-12-16 20:17:45','sesuatu',1),(67,1,'2014-12-16 20:17:45','sesuatu',3),(68,1,'2014-12-16 20:17:45','sesuatu',4),(69,10,'2014-12-16 20:17:52','sesuatu',1),(70,2,'2014-12-16 20:17:59','sesuatu',1),(71,2,'2014-12-16 20:17:59','sesuatu',4),(72,10,'2014-12-16 20:18:05','sesuatu',1),(73,2,'2014-12-16 20:21:00',NULL,1),(74,2,'2014-12-16 20:21:00',NULL,3),(75,2,'2014-12-16 20:21:00',NULL,4),(76,10,'2014-12-16 20:21:27',NULL,1),(77,2,'2014-12-16 20:37:09','brian',1),(78,2,'2014-12-16 20:37:09','brian',3),(79,2,'2014-12-16 20:37:09','brian',4),(80,1,'2014-12-16 20:38:45',NULL,1),(81,1,'2014-12-16 20:38:45',NULL,3),(82,1,'2014-12-16 20:38:45',NULL,4),(86,2,'2014-12-16 20:40:37','brian',1),(87,2,'2014-12-16 20:40:37','brian',3),(88,2,'2014-12-16 20:40:37','brian',4),(89,2,'2014-12-16 20:40:37','brian',1),(90,2,'2014-12-16 20:40:37','brian',3),(91,2,'2014-12-16 20:40:37','brian',4),(92,2,'2014-12-16 21:07:00','brian',1),(93,2,'2014-12-16 21:07:00','brian',3),(94,2,'2014-12-16 21:07:00','brian',4),(95,1,'2014-12-16 21:07:07','sesuatu',1),(96,1,'2014-12-16 21:07:07','sesuatu',3),(97,1,'2014-12-16 21:07:07','sesuatu',4),(98,2,'2014-12-16 21:18:25',NULL,1),(99,2,'2014-12-16 21:18:25',NULL,3),(100,2,'2014-12-16 21:18:25',NULL,4),(101,2,'2014-12-16 21:19:27','brian',1),(102,2,'2014-12-16 21:19:27','brian',3),(103,2,'2014-12-16 21:19:27','brian',4),(104,10,'2014-12-16 21:21:45','brian',1),(105,10,'2014-12-16 21:21:57','brian',1),(106,10,'2014-12-16 21:23:18','brian',1),(107,10,'2014-12-16 21:24:59','brian',1),(108,10,'2014-12-16 21:28:01','brian',1),(109,10,'2014-12-16 21:33:06','brian',1),(110,2,'2014-12-16 21:33:13','brian',1),(111,2,'2014-12-16 21:33:13','brian',3),(112,2,'2014-12-16 21:33:13','brian',4),(113,10,'2014-12-16 21:33:20','brian',1),(114,5,'2014-12-16 21:47:19','ap pendopo pesan',1),(115,6,'2014-12-16 21:47:31','ap pendopo pesan',1),(116,6,'2014-12-16 21:47:31','ap pendopo pesan',3),(117,6,'2014-12-16 21:47:31','ap pendopo pesan',4),(118,5,'2014-12-16 21:48:28','alfatest',1),(119,5,'2014-12-16 21:49:36','sesuatu',1),(120,5,'2014-12-16 21:49:42','sesuatu',1),(121,1,'2014-12-16 21:49:48','sesuatu',1),(122,1,'2014-12-16 21:49:48','sesuatu',4),(125,5,'2014-12-17 17:14:16','test',1),(129,10,'2014-12-17 18:34:23','test',1);
/*!40000 ALTER TABLE `bahanpakai` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jenisKelamin`
--

DROP TABLE IF EXISTS `jenisKelamin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jenisKelamin` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `Nama_Jeniskelamin` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jenisKelamin`
--

LOCK TABLES `jenisKelamin` WRITE;
/*!40000 ALTER TABLE `jenisKelamin` DISABLE KEYS */;
INSERT INTO `jenisKelamin` VALUES (1,'laki-laki'),(2,'perempuan');
/*!40000 ALTER TABLE `jenisKelamin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `meja`
--

DROP TABLE IF EXISTS `meja`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `meja` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `No_Meja` bigint(20) NOT NULL,
  `state` bit(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `meja`
--

LOCK TABLES `meja` WRITE;
/*!40000 ALTER TABLE `meja` DISABLE KEYS */;
INSERT INTO `meja` VALUES (1,0,'\0'),(2,1,'\0'),(3,2,'\0'),(4,3,'\0'),(5,4,'\0'),(6,5,'\0');
/*!40000 ALTER TABLE `meja` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menu`
--

DROP TABLE IF EXISTS `menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `Harga` bigint(20) NOT NULL,
  `HargaUntung` bigint(20) NOT NULL,
  `Keterangan` varchar(255) DEFAULT NULL,
  `Nama_Menu` varchar(255) DEFAULT NULL,
  `gambar` varchar(255) DEFAULT NULL,
  `Nama_Resep_id` bigint(20) DEFAULT NULL,
  `Untung` double NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK33155F75134330` (`Nama_Resep_id`),
  CONSTRAINT `FK33155F75134330` FOREIGN KEY (`Nama_Resep_id`) REFERENCES `realresep` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu`
--

LOCK TABLES `menu` WRITE;
/*!40000 ALTER TABLE `menu` DISABLE KEYS */;
INSERT INTO `menu` VALUES (5,5000,5000,'Sesuatu yang bisa dimakan dan kebanyakan di isi oleh nasi yang digoreng','Nasi Goreng ','d4789087-78be-4bd9-94aa-6a0ff5ee6eb3|image/jpeg',4,0),(6,3200,5000,'soto yang dijual dikantin kampus ','Soto Sokaraja','fc22ae6b-b9c8-4e05-ae50-1e39e79fdf29|image/jpeg',5,3300),(7,5000,5000,'test','test','8400ea60-fa46-43a4-aebd-ec2b944b03f0|image/jpeg',4,1000),(9,4000,3000,'di sensor - - - - - - -','XxX','640c697c-226a-476b-9715-ed19384152b3|image/jpeg',7,500);
/*!40000 ALTER TABLE `menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pembelian`
--

DROP TABLE IF EXISTS `pembelian`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pembelian` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `Harga` bigint(20) NOT NULL,
  `Pemesan` varchar(255) DEFAULT NULL,
  `kode_pembelian` varchar(255) DEFAULT NULL,
  `tgl_bayar` datetime DEFAULT NULL,
  `Untung` double NOT NULL,
  `pesanannya` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pembelian`
--

LOCK TABLES `pembelian` WRITE;
/*!40000 ALTER TABLE `pembelian` DISABLE KEYS */;
INSERT INTO `pembelian` VALUES (1,5000,'test','KodePem1_test','2014-12-12 12:08:57',0,'test ,'),(2,18000,'test1','KodePem2_test1','2014-12-12 12:19:04',0,'test ,'),(3,0,'test4','KodePem5_test4','2014-12-13 09:15:46',0,'test ,'),(4,10000,'test2','KodePem3_test2','2014-12-13 09:02:49',0,'test ,'),(5,22000,'test3','KodePem4_test3','2014-12-13 09:12:16',0,'test ,'),(6,15000,'alfa','KodePem6_alfa','2014-12-13 00:00:00',7600,'test ,'),(7,10000,'alfa','KodePem1_alfa','2014-12-14 06:55:49',2000,'test ,'),(8,10000,'alfa','KodePem2_alfa','2014-12-14 06:56:32',2000,'test ,'),(9,10000,'alfa1','KodePem3_alfa1','2014-12-14 06:58:55',6600,'test ,'),(10,20000,'alfa3','KodePem4_alfa3','2014-12-14 08:24:16',8600,'test ,'),(11,10000,'test','KodePem1_test','2014-12-14 14:36:34',4300,'test ,'),(12,10000,'test','KodePem2_test','2014-12-14 14:45:22',4300,'test ,'),(13,5000,'alfa1','KodePem3_alfa1','2014-12-14 14:47:19',1000,'test ,'),(14,5000,'alfa1','KodePem4_alfa1','2014-12-14 14:52:10',1000,'Nasi Goreng ,'),(15,20000,'test','KodePem1_test','2014-12-15 19:21:56',6300,'Nasi Goreng ,test,Soto Sokaraja,Nasi Goreng ,'),(16,20000,'brian','KodePem2_brian','2014-12-15 19:27:33',0,'Nasi Goreng ,Nasi Goreng ,'),(17,10000,'brian','KodePem3_brian','2014-12-15 19:37:07',2000,'Nasi Goreng ,'),(18,40000,'brian','KodePem4_brian','2014-12-15 19:49:59',12600,'Nasi Goreng ,Soto Sokaraja,test,test,'),(19,25000,'sesuatu','KodePem5_sesuatu','2014-12-15 20:00:00',5000,'Nasi Goreng ,Nasi Goreng ,Nasi Goreng ,Nasi Goreng ,Nasi Goreng ,'),(20,55000,'alfa1','KodePem6_alfa1','2014-12-15 20:01:30',13300,'Soto Sokaraja,Nasi Goreng ,'),(21,5000,'','KodePem7_','2014-12-15 20:03:35',1000,'Nasi Goreng ,'),(22,10000,'test','KodePem8_test','2014-12-15 20:24:44',2000,'Nasi Goreng ,Nasi Goreng ,'),(23,31000,'sesuatu','KodePem10_sesuatu','2014-12-15 20:31:26',8300,'Soto Sokaraja,test,XxX,Nasi Goreng ,'),(24,26000,'brian','KodePem9_brian','2014-12-15 20:30:16',5000,'Nasi Goreng ,test,XxX,'),(25,40000,'brian','KodePem11_brian','2014-12-16 20:20:45',21800,'Soto Sokaraja,Soto Sokaraja,test,Soto Sokaraja,'),(26,10000,'brian','KodePem15_brian','2014-12-16 20:37:03',6600,'Soto Sokaraja,'),(27,20000,'brian','KodePem12_brian','2014-12-16 00:00:00',8600,''),(28,10000,'brian','KodePem18_brian','2014-12-16 00:00:00',6600,'Soto Sokaraja,'),(29,5000,'sesuatu','KodePem19_sesuatu','2014-12-16 20:47:48',3300,'Soto Sokaraja,'),(30,5000,'test','KodePem13_test','2014-12-16 20:28:31',1000,'test,'),(31,3000,'test','KodePem14_test','2014-12-16 00:00:00',500,'XxX,'),(32,10000,'brian','KodePem20_brian','2014-12-16 21:17:04',2000,'Nasi Goreng ,'),(33,20000,'brian','KodePem22_brian','2014-12-16 21:19:21',8600,'Soto Sokaraja,Nasi Goreng ,'),(34,10000,'brian','KodePem23_brian','2014-12-16 21:23:11',2000,'Nasi Goreng ,'),(35,10000,'brian','KodePem24_brian','2014-12-16 21:24:53',2000,'Nasi Goreng ,'),(36,10000,'brian','KodePem25_brian','2014-12-16 21:27:56',2000,'Nasi Goreng ,'),(37,30000,'brian','KodePem26_brian','2014-12-16 21:33:01',10600,'Nasi Goreng ,Soto Sokaraja,test,'),(38,35000,'ap pendopo pesan','KodePem27_ap pendopo pesan','2014-12-16 21:47:14',20800,'Nasi Goreng ,Soto Sokaraja,'),(39,10000,'test','KodePem34_test','2014-12-17 18:33:12',2000,'Nasi Goreng ,'),(40,5000,'alfatest','KodePem28_alfatest','2014-12-16 21:48:14',1000,'Nasi Goreng ,');
/*!40000 ALTER TABLE `pembelian` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pesanan`
--

DROP TABLE IF EXISTS `pesanan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pesanan` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `Harga` bigint(20) NOT NULL,
  `Jumlah_Pesan` bigint(20) NOT NULL,
  `Tanggal_Pesan` datetime DEFAULT NULL,
  `cek` int(11) NOT NULL,
  `Nama_pesanannya_id` bigint(20) DEFAULT NULL,
  `menu_pesan_id` bigint(20) DEFAULT NULL,
  `Untung` double NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKD795B338E7C1B5DA` (`Nama_pesanannya_id`),
  KEY `FKD795B338A71F3952` (`menu_pesan_id`),
  CONSTRAINT `FKD795B338A71F3952` FOREIGN KEY (`menu_pesan_id`) REFERENCES `menu` (`id`),
  CONSTRAINT `FKD795B338E7C1B5DA` FOREIGN KEY (`Nama_pesanannya_id`) REFERENCES `realpesanan` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pesanan`
--

LOCK TABLES `pesanan` WRITE;
/*!40000 ALTER TABLE `pesanan` DISABLE KEYS */;
INSERT INTO `pesanan` VALUES (1,0,0,'2014-12-15 19:19:09',0,NULL,7,0),(2,0,0,'2014-12-15 19:19:30',0,NULL,7,0),(3,0,2,'2014-12-15 19:19:44',0,NULL,5,0),(4,0,2,'2014-12-15 19:20:14',0,NULL,5,0),(9,0,0,'2014-12-15 19:23:43',0,NULL,6,0),(10,0,2,'2014-12-15 19:24:20',0,NULL,5,0),(43,5000,1,'2014-12-16 21:49:35',1,29,5,0),(44,5000,1,'2014-12-16 21:49:42',1,29,7,1000),(45,3000,1,'2014-12-16 21:49:48',1,29,9,500),(46,5000,1,'2014-12-17 10:32:08',1,30,5,1000),(47,10000,2,'2014-12-17 11:01:50',1,31,5,2000),(49,5000,1,'2014-12-17 17:14:16',1,33,5,1000),(50,10000,2,'2014-12-17 17:14:42',1,31,6,6600);
/*!40000 ALTER TABLE `pesanan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `realpesanan`
--

DROP TABLE IF EXISTS `realpesanan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `realpesanan` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `Nama_Pesanan` varchar(255) DEFAULT NULL,
  `tagihan` bigint(20) NOT NULL,
  `tanggal` datetime DEFAULT NULL,
  `Nama_Orang_id` bigint(20) DEFAULT NULL,
  `No_Meja_id` bigint(20) DEFAULT NULL,
  `Status_Pesan_id` bigint(20) DEFAULT NULL,
  `Untung` double NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8753EBDA56122FE0` (`No_Meja_id`),
  KEY `FK8753EBDA5118D46E` (`Nama_Orang_id`),
  KEY `FK8753EBDA6D903DB2` (`Status_Pesan_id`),
  CONSTRAINT `FK8753EBDA5118D46E` FOREIGN KEY (`Nama_Orang_id`) REFERENCES `Orang` (`id`),
  CONSTRAINT `FK8753EBDA56122FE0` FOREIGN KEY (`No_Meja_id`) REFERENCES `meja` (`id`),
  CONSTRAINT `FK8753EBDA6D903DB2` FOREIGN KEY (`Status_Pesan_id`) REFERENCES `status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `realpesanan`
--

LOCK TABLES `realpesanan` WRITE;
/*!40000 ALTER TABLE `realpesanan` DISABLE KEYS */;
INSERT INTO `realpesanan` VALUES (29,'sesuatu',13000,'2014-12-16 21:49:31',4,4,2,1500),(30,'test',5000,'2014-12-17 10:32:02',6,1,1,1000),(31,'testting',20000,'2014-12-17 11:01:45',4,3,2,8600),(33,'test',5000,'2014-12-17 17:14:11',4,2,2,1000),(35,'testting',0,'2014-12-17 18:42:39',4,2,2,0);
/*!40000 ALTER TABLE `realpesanan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `realresep`
--

DROP TABLE IF EXISTS `realresep`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `realresep` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `Harga_menu` bigint(20) NOT NULL,
  `Nama_Resep` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `realresep`
--

LOCK TABLES `realresep` WRITE;
/*!40000 ALTER TABLE `realresep` DISABLE KEYS */;
INSERT INTO `realresep` VALUES (4,5000,'Nasi Goreng'),(5,3200,'Soto'),(6,1000,'test'),(7,4000,'XxX');
/*!40000 ALTER TABLE `realresep` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `resep`
--

DROP TABLE IF EXISTS `resep`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `resep` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `Harga` bigint(20) NOT NULL,
  `Jumlah` bigint(20) NOT NULL,
  `Bahan_id` bigint(20) DEFAULT NULL,
  `Nama_RealResep_id` bigint(20) DEFAULT NULL,
  `Satuan_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6761D4BD73E02CE` (`Nama_RealResep_id`),
  KEY `FK6761D4BACE3583E` (`Satuan_id`),
  KEY `FK6761D4B326C0576` (`Bahan_id`),
  CONSTRAINT `FK6761D4B326C0576` FOREIGN KEY (`Bahan_id`) REFERENCES `bahan` (`id`),
  CONSTRAINT `FK6761D4BACE3583E` FOREIGN KEY (`Satuan_id`) REFERENCES `satuan` (`id`),
  CONSTRAINT `FK6761D4BD73E02CE` FOREIGN KEY (`Nama_RealResep_id`) REFERENCES `realresep` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resep`
--

LOCK TABLES `resep` WRITE;
/*!40000 ALTER TABLE `resep` DISABLE KEYS */;
INSERT INTO `resep` VALUES (10,5000,5,1,4,1),(11,3000,3,1,NULL,1),(12,3000,3,1,NULL,1),(13,3000,3,1,NULL,1),(14,3000,3,1,NULL,1),(15,1000,1,1,5,1),(16,700,1,3,5,1),(17,1500,1,4,5,1),(18,1000,1,1,6,1),(19,1000,1,1,7,1),(20,3000,2,4,7,2);
/*!40000 ALTER TABLE `resep` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `satuan`
--

DROP TABLE IF EXISTS `satuan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `satuan` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `Satuan` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `satuan`
--

LOCK TABLES `satuan` WRITE;
/*!40000 ALTER TABLE `satuan` DISABLE KEYS */;
INSERT INTO `satuan` VALUES (1,'ons'),(2,'liter');
/*!40000 ALTER TABLE `satuan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `setting`
--

DROP TABLE IF EXISTS `setting`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `setting` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `Nama_setting` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `printKasir` varchar(255) DEFAULT NULL,
  `printadmin` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `setting`
--

LOCK TABLES `setting` WRITE;
/*!40000 ALTER TABLE `setting` DISABLE KEYS */;
INSERT INTO `setting` VALUES (1,'Sesuatu','brianraharjo80@gmail.com','a','b');
/*!40000 ALTER TABLE `setting` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `staff`
--

DROP TABLE IF EXISTS `staff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `staff` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `nama` varchar(255) DEFAULT NULL,
  `sebagai_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK68AC2E055470D85` (`sebagai_id`),
  CONSTRAINT `FK68AC2E055470D85` FOREIGN KEY (`sebagai_id`) REFERENCES `HakAkses` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `staff`
--

LOCK TABLES `staff` WRITE;
/*!40000 ALTER TABLE `staff` DISABLE KEYS */;
INSERT INTO `staff` VALUES (1,'brian@localhost1','brian1',1);
/*!40000 ALTER TABLE `staff` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `status`
--

DROP TABLE IF EXISTS `status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `status` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `Nama_Status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `status`
--

LOCK TABLES `status` WRITE;
/*!40000 ALTER TABLE `status` DISABLE KEYS */;
INSERT INTO `status` VALUES (1,'diantar'),(2,'dimakan'),(3,'dibawa');
/*!40000 ALTER TABLE `status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `pass` varchar(255) DEFAULT NULL,
  `user` varchar(255) DEFAULT NULL,
  `level_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK36EBCB15808B5F` (`level_id`),
  CONSTRAINT `FK36EBCB15808B5F` FOREIGN KEY (`level_id`) REFERENCES `HakAkses` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'brian','brian',1),(2,'alfa','alfa',3),(3,'beta','beta',4);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-12-17 20:17:39
