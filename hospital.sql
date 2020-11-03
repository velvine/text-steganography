-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.4.14-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             9.3.0.4984
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping database structure for hospital
DROP DATABASE IF EXISTS `hospital`;
CREATE DATABASE IF NOT EXISTS `hospital` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `hospital`;


-- Dumping structure for table hospital.messages
DROP TABLE IF EXISTS `messages`;
CREATE TABLE IF NOT EXISTS `messages` (
  `Mid` int(11) NOT NULL AUTO_INCREMENT,
  `Sender` varchar(200) NOT NULL,
  `Receiver` varchar(200) NOT NULL,
  `Message` text NOT NULL,
  `DK` text NOT NULL,
  `Level` varchar(50) NOT NULL,
  `DateAdded` timestamp NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`Mid`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=latin1;

-- Dumping data for table hospital.messages: ~30 rows (approximately)
/*!40000 ALTER TABLE `messages` DISABLE KEYS */;
INSERT INTO `messages` (`Mid`, `Sender`, `Receiver`, `Message`, `DK`, `Level`, `DateAdded`) VALUES
	(1, 'User Four', 'User Four', 'UrrrrFU UrrrUFUF FrrrrrF FrrUrUrU UrrFrrUF FrrrFrrr UrrrUFUF FrrUrrFr UrrrUFUr FrrUFrrr UrrrUrrF FrrUFrFU UrrrrFrF FrrrrUF FrFrrrr FrrrrUFr UrrrrrU  ', 'SAGKTTKGXZXUPPHJST', 'User', '2020-04-09 17:44:03'),
	(2, 'User Four', 'User Three', 'UrrerFU UrrTrFrT FrerrrT FrerFrTU UerFUTUr TrrUTrrr UrrTrFrT FreUrUeU UerFrerr TrrrTrrU UrrTrFUT FrerrUeU UerrUTrr TrrreUF TrFrerr TrrrTrrr UrrerrU  ', 'LUXUIDEDVOJQITLHSP', 'User', '2020-04-09 17:44:36'),
	(3, 'User Four', 'Username Two', 'UreorFU UreTrrUT FeorreT FeorFeTU UorrUoUF TrreTrFU UreTrrUT FeoUrUTr UorrUTrF TrrUoUFU UreTUFeT FeorrUoU UorFeorr TrreoUF TrFeorr TrreTUFe UreorrU  ', 'ZWTUBQGCYADQEEYBGV', 'User', '2020-04-09 17:48:53'),
	(9, 'User Four', 'User Three', 'UrrerFU UrrTUFUe FrerrrT FreUrreU UerFreUr TrrUeUFr UrrTrrrT FrerFUeU UerrUeUr TrrrTrrU UrrTrrre FreUrrTr UerrUeUF TrrreUF TrFrerr TrrUeUFU UrrerrU  ', 'GLBOUFILBOQDJDMSWP', 'User', '2020-04-09 17:52:58'),
	(10, 'User Four', 'Username Two', 'UreorFU UreTrFUT FeorreT FeTrFeTU UorFUoUF  UrUoUFUT FeTUFeTU UorFeTrr TrrUTrre UreTrFUT FeorFeTU UorFUTrr  UrUTrFeT FeoUrUTU UorrUorr TrFeorF TrreoUF TrFeorr TrrUoUrU UreorrU   UorreTU UorrUoUF TrreorF TrFeTrFU UreTrrUT  UoUFUorF TrrUTUre UreorFeT FeoUrUTr  TrreTrFe UreoUFeT FeoUrUTr UorrUTUF  UreTrrUo FeoUreor UorrUTrr TrrUTUre UreTrFUo FeorFeTU UorrUoUF  UreoUFeo FeoUrUTr UorrUTrr TrreoUFe UreoUreo FeoUFeTU UorFeoUF TrrUoUFU UreTrreo FeorrUTU UorFUoUF  UreTrFUT FeoUreTr  TrreoUrU UreoUreo FeorFeTr UorFUTUr  UreTUFUo FeoUreTU UorFUorr TrreoUrU UreTrFUT FeorFUTU UorrUoUF TrreoUFU  FeorFeTU UorrUTUF TrrUorFe  FeoUFUoU UorFeTUF TrrUorrU UreTUrUo FeoUreor UorreTUr TrreoUrU  FeorFeor UorFeTUF TrreTUrU UreoUFUT FeoUreTr UorFeoUr TrreoUFe UreorFUo  UorFUorF TrrUTUre  FeoUFUoU UorrUoUr  UreoUreo FeoUFeor UorrUTrr TrreTrFe UreTUreo FeoUreTr UorFeTrr TrFeorF TrreoUF TrFeorr TrrUoUre UreorrU   UorreTU UorFeorr TrreorF TrFUorre UreoUFUT FeoUrUor UorFeorF TrreTrrU UreTUFeT  UorreTUr TrrUTrre UreTrrUo FeorFeor UorFeoUF  UreTUFeT FeoUreTr UorrUTUF TrrUTUFe UreTUrUo  UorreTUr TrreTUre  FeoUFUT FeorFUo FeTrreo FeoUFUT FeoUrUT FeTrreo FeoUFeT FeoUFUT FeoUFeT FeoUFUT  UorFUTUr TrrUorrU UreTrFUT  UorFUoUr TrreTrFe UreoUreo FeorFUTr UorrUorF TrrUTrFe UrUorrU UreorFU UrUorre UreTrrUT FeorreT   TrreoUF TrreTUre UreorrU UreoUrUo FeoUreTU UorFeoUr TrrUTUre UreoUrUT FeorFeor UorreTU UoUreor UorFeorF TrreorF  ', 'DSTMVAADRYDGJNVUSJXZUIIQDEIXHHYCEEIRDVLOTHBOONMJOLSMOQLOGJQHTHOBIAJJIWCXZDPKRTCJAWNWLHUIBUANNCSJGIXKATHAHPEOCUSDYVVMKKZZCZEGNDXQDYUOSXBGPSRCKFVUOSZWLTSPUVVZQFNQEWKLXJBWQAXUDNBEMPJEHTYJH', 'User', '2020-04-09 17:53:12'),
	(11, 'User Four', 'Username Two', 'UreorFU UreTrFUo FeorreT FeTUFUTr UorFUoUF  UrUoUrUT FeTrFUTU UorreTrF TrrUTUFe UreTrrUo FeoUFeTr UorrUTrF  UrUoUFeo FeorrUTr UorFeorr TrFeorF TrreoUF TrFeorr TrreoUFU UreorrU   UorreTU UorFeorF TrreorF TrFUTUFe UreTUFeT  UoUrUTUr TrrUTrFe UreTUrUT FeoUFeor  TrreoUFU UreoUFeo FeoUFeor UorrUoUr  UreTUFeo FeoUFUTr UorrUoUr TrrUorre UreoUreT FeoUrUTU UorFeorr  UreTrreT FeoUrUTr UorFeTUr TrrUTUrU UreorFUT FeorrUTr UorFeorr TrrUoUre UreTUreT FeorFeTU UorFUorF  UreoUreo FeorrUTr  TrreTrrU UreTUreo FeorFeoU UorFeTUF  UreTrrUo FeorFUTU UorrUTrr TrreTUre UreTUreo FeoUreTU UorFUTrF TrrUTUre  FeoUFUTr UorrUTUr TrrUorFe  FeoUFUor UorrUorr TrreoUrU UreTrreT FeorFUTU UorFeTrr TrreTrFe  FeorrUoU UorFUTrr TrrUorrU UreTUrUT FeoUreTr UorrUoUr TrreoUrU UreTrrUo  UorFeoUr TrrUTUrU  FeorFUTr UorrUoUF  UreTrFUT FeorFeor UorFeoUr TrreoUFU UreorFUT FeoUrUTU UorreTUF TrFeorF TrreoUF TrFeorr TrrUorre UreorrU   UorreTU UorFUoUr TrreorF TrFeTrre UreoUFUT FeorFeTr UorreTUF TrreTrrU UreTUFeo  UorFUTrF TrreoUFU UreorFUo FeoUrUTU UorrUTrF  UreorFeT FeorFeoU UorFUorF TrreTrFe UreTrrUT  UorFUorF TrreoUrU  FeoUFUT FeorFUo FeTrreo FeoUFUT FeoUrUT FeTrreo FeoUFeT FeoUFUT FeoUFeT FeoUFUT  UorFUTUr TrrUorFU UreTUrUo  UorFeorr TrrUorFe UreorFUo FeorFUTr UorreTUF TrreTUrU UrUorrU UreorFU UrUorre UreTUreT FeorreT   TrreoUF TrrUTrFU UreorrU UreoUFeT FeoUreTr UorFUoUr TrrUoUrU UreTUFeT FeorFUTr UorreTU UoUreor UorreTUr TrreorF  ', 'WTWTVXEPGSIRYHELKDVXIVDXHYXEXAJEICHUETQXJTDABBHLJLIPPLORXFSBITOXSBQQUAOQYZXPBADJXXCIUIGDMXVJBCAKUZEJEIGYSFYRTPFYCZZOOXJPPHEQXDYHGPGZYAZPFMYRRMEYIONKNXMLSFVXNLXIGWMYSKFQOLMAOBYFEWRYYJJYK', 'User', '2020-04-09 17:53:17'),
	(12, 'User Four', 'Username Two', 'UreorFU UreoUreo FeorreT FeTrFUTU UorFeorr  UrUTrreT FeTUrUoU UorrUTrr TrreTrFU UreTUreT FeorFUTU UorrUorr  UrUTrFUT FeorrUoU UorFUoUr TrFeorF TrreoUF TrFeorr TrrUTrFU UreorrU   UorreTU UorFeorF TrreorF TrFeTrrU UreTrFUo  UoUFeTrr TrreTUre UreoUFeo FeorFeor  TrreoUFe UreoUreT FeoUreTr UorFeTUF  UreTrrUo FeoUreor UorFeTUF TrrUTUre UreTUrUT FeoUreTr UorrUTUr  UreTUrUo FeoUrUTr UorFUorF TrrUTUFe UreTrFeo FeoUreTU UorFUTUr TrrUTrrU UreTrrUo FeoUreTr UorFUorF  UreTUreo FeoUreoU  TrreTrrU UreoUFeo FeoUFUTr UorFUTrF  UreTrFUo FeorFeTU UorFUTUr TrrUTrre UreTrFUo FeoUreTU UorFUTrF TrreTrrU  FeoUFeTU UorFeTUF TrreTUFU  FeorFUTU UorFeTrr TrreTrFe UreoUrUT FeorFeTr UorFeoUF TrrUTUrU  FeorFUoU UorrUoUF TrrUorre UreTUreT FeoUFUTr UorreTUF TrrUTrFU UreTUrUT  UorFUTrF TrrUTUFe  FeoUrUTr UorFUoUr  UreTrFUT FeorFeTU UorFeoUr TrrUorrU UreTrFUT FeoUreTU UorFeTrr TrFeorF TrreoUF TrFeorr TrreTUFe UreorrU   UorreTU UorreTrF TrreorF TrFeTUre UreTrreT FeoUreoU UorreTUr TrreTrFe UreoUrUo  UorFeTrF TrrUoUFU UreorFUT FeoUFeTU UorreTrF  UreTrrUo FeorFUor UorrUTUr TrrUTUre UreorFUo  UorFUTrr TrreTUre  FeoUFUT FeorFUo FeTrreo FeoUFUT FeoUrUT FeTrreo FeoUFeT FeoUFUT FeoUFeT FeoUFUT  UorrUTrr TrreoUFe UreTUFeo  UorreTUF TrreTrre UreTUrUo FeorrUoU UorFeTUr TrreoUFU UrUorrU UreorFU UrUorre UreoUrUo FeorreT   TrreoUF TrrUorFe UreorrU UreorFUT FeoUreor UorreTUF TrreTrrU UreTUreT FeorrUTr UorreTU UoUreor UorFeTUF TrreorF  ', 'SHMIGRYJZLBCDCTMAUVAOAFVQYIZEHDSXSZVHZDMTHQOJGJRALFOCYAMEYSWSILXEGKWQEWEAZXILDUMJKQDANHKNPMKDQDOLBTICARTSCYHDTSJPLBLQKAKXDCJYCQVOZFVGKMMQULVOFQJULLWAGKSNRNKLJGSMFXERYSFVCABXFEHXIVGMGSEA', 'User', '2020-04-09 17:53:19'),
	(13, 'User Four', 'Username Two', 'UreorFU UreTUrUT FeorreT FeorFUTU UorFUorr TrreTUFU UreTUreT FeorFeTr UorFUorF TrrUoUre UreTUreo FeoUFUoU UorreTU UoUreor UorrUTrr TrreorF   FeorrUT FeoUreor UorreoU UorFUTrF TrreTUFU UreorFUT FeorFUTU UorreTU UoUreor UorFeorF TrreorF   FeorrUT FeorFUoU UorreoU UoUFeoU UorFUoUr TrreTUFe UreoUFUo FeoUrUTr UorrUor UorreTU UoUreor UorrUoUF TrreorF  ', 'NOGMOMNRAHOZJUDFMLYZHZMSJMBYXIXLCLMRPYTRQIERA', 'User', '2020-04-09 18:05:49'),
	(14, 'User Four', 'Username Two', 'UreorFU UreTUreo FeorreT FeorFeTU UorrUTrF TrrUoUFe UreoUreT FeorFUTU UorrUorr TrreTrrU UreTUrUT FeoUreor UorrUoUF TrreoUF TrFeorr TrrUTrFU UreorrU   UorreTU UorrUorF TrreorF TrrUoUre UreTrreT FeoUreTU UorFeTrF TrreTUrU UreTUreT FeoUreoU UorreTU UoUreor UorrUTrr TrreorF   FeorrUT FeoUFeor UorreoU UorrUoUF TrreTUrU UreoUFUo FeoUFUoU UorFUTrF TrreTrFe UreTrFeT FeoUFUTr UorFeTrr TrreoUF TrFeorr TrreTUre UreorrU  ', 'MRRUZFQJDOFFJHJOLVCCGHEVLDLCVBBDJUJIRQQZJUVPCXSWVDZL', 'User', '2020-04-09 18:14:10'),
	(15, 'User Four', 'User Three', 'UrrerFU UrreUrUT FrerrrT FrerrUTr UerrrTrF TrrrTUFr UrreUrUe FreUrUeU UerrUeUF TrrUerFr UrrTUrUT FrerrUT FrTrrre FreUFreU UerrreU   UrrerFU UrrTrFre FrerrrT FrTUrrTU UerFUeUF TrrUTrFU UrrTrFrT FreUrUer UerrUTUr  UrrTUrUe FreUFUer UerrUTrF TrrreUF TrFrerr TrrreUFU UrrerrU  ', 'CEJCVZBLZEPPSQDBWZVYSWDWAYJLODWBIAD', 'User', '2020-04-09 18:20:09'),
	(16, 'User Four', 'sdfg', 'UrgrrsU srrgrFsr srrgrrs FgrrsUFg FgrrgUrs Fgrrsrrg FgrFgrrs FgrFsUFg FgrFgrrg FgrFsrFg FgrFsrFg FgrFsUFg FgrrsrFg FgrrgUF Ursrrgr srrgUFgU srrgrrs   UrgrrsU srrgrFsU srrgrrs FgrFsrrs FgrrsUFg FgrFgUFs FgrrsUrs FgrrgUFs FgrrgUF Ursrrgr srrsUrsU srrgrrs   UrgrrsU srrsUrsr srrgrrs FgrrsUFg FgrFgUrg FgrFgUFg FgrFgUrg FgrrgUF Ursrrgr srrgrFsr srrgrrs  ', 'PJMRHTIUVXGRKHOCBHUZIKNNCLWEIOVNGBPPLENJHAJCQ', 'UserRole', '2020-04-09 18:39:01'),
	(17, 'User Four', 'sszax', 'UrxrrsU srrsUrxr srrxrrs FxrFxUrx FxrrsUrs FxrFsUrx FxrFxrrx FxrFsrFs FxrrxUFx FxrFsrrs FxrFsUFx FxrrsrFs FxrFsUrx FxrFsUFx FxrFsUFx FxrFxUrs FxrrxUFx FxrFxrrs FxrFsrFs FxrFxrFx FxrFxUrx FxrrxUrs FxrrsUrs FxrFsrFs FxrrxUF Ursrrxr srrxUrxU srrxrrs   UrxrrsU srrsrFxU srrxrrs FxrrsUFs Fxrrsrrx FxrrsrFs FxrFxrrs FxrFxUFs FxrFxrrs FxrFsUrs FxrFsrFx FxrFxrrx FxrFsUrs FxrrsUFs FxrrxUF Ursrrxr srrsUFxr srrxrrs   UrxrrsU srrxUrsr srrxrrs FxrrsUFx FxrFsUrs FxrFxrrs FxrFxUFx FxrrxUF Ursrrxr srrsrFsU srrxrrs  ', 'KREHNLJKSYCKQQBMWKYREYEWGGGRGKHUTTEPKCEUBWWULXNSMSRFQLYSCMMSPV', 'UserRole', '2020-04-09 19:28:07'),
	(18, 'User Four', 'Medic', 'UrcrrMU MrrMrFcr MrrcrrM FcrrMUrM FcrrMrrc FcrrcUFM FcrFMUFc FcrrMUrc FcrrMrrM FcrFcrrM FcrrcUF UrMrrcr MrrcUrMr MrrcrrM   UrcrrMU MrrcUrcr MrrcrrM FcrrMrFc FcrrMrrM FcrrMUFc FcrrcUFc FcrrMrrc FcrFMUFc FcrrMUFc FcrFcrrM FcrrMUrM FcrFMrrc FcrrMUFc FcrrMUrc FcrFMUrM FcrrcUF UrMrrcr MrrcUFcU MrrcrrM   UrcrrMU MrrcrFMr MrrcrrM FcrFcUFc FcrFcUFM FcrrcUFc FcrFcrrc FcrFcUrM FcrFMUFc FcrFMrFc FcrrcUF UrMrrcr MrrMrFcU MrrcrrM   UrcrrMU MrrMUrcU MrrcrrM FcrFcrrc FcrFcrFc FcrrMUFM FcrrMrFc FcrrcUF UrMrrcr MrrMrrcr MrrcrrM  ', 'UVNORTJMCMWJFZOXOHURDHORIPEKUNWXRUCFOOIJEMDVJDJLVBUCJPIQEWJLPUZZGW', 'UserRole', '2020-04-09 19:46:05'),
	(19, 'User Four', 'Username Two', 'UreorFU UreoUFeT FeorreT FeTUreor UorFeoUF  UrUTUFeo FeTUFUoU UorFeTrr TrrUoUre UreorFUo FeorrUTU UorrUTrr  UrUTUFeT FeoUrUTU UorFeTrr TrFeorF TrreoUF TrFeorr TrrUorFe UreorrU   UorreTU UorFeTUr TrreorF TrFeoUFU UreTrrUo  UoUreTUr TrrUorre UreoUFUo FeorFUTU  TrreTrFe UreTUrUo FeorFeTr UorFeoUF  UreTrrUT FeorrUTU UorrUoUr TrreTrre UreoUFeo FeoUreoU UorFUTrr  UreTUreo FeorFUoU UorFUoUF TrrUoUrU UreoUFeo FeoUreoU UorFeoUF TrrUoUFU UreTUrUo FeorFeTU UorrUorF  UreoUrUo FeoUFeoU  TrrUTUrU UreTUrUo FeoUFUTr UorFUorr  UreoUFeT FeorFUTr UorFeorF TrreTrrU UreTUFUo FeorFUoU UorFUTUr TrrUTrrU  FeorFeoU UorFUorr TrrUTrre  FeorrUoU UorrUTUr TrrUorFU UreorFUo FeoUreTr UorFUorr TrreoUrU  FeoUreoU UorFUoUF TrrUorFU UreoUFeo FeorFeTU UorreTUr TrrUoUre UreorFeT  UorFUTrr TrreTUFU  FeoUreTr UorrUoUr  UreTrFeo FeoUFeor UorFeTUr TrrUTrFe UreoUreo FeoUFeor UorFeTrF TrFeorF TrreoUF TrFeorr TrrUorrU UreorrU   UorreTU UorreTUr TrreorF TrFUoUre UreTUreT FeoUreTU UorFeoUF TrreTrrU UreorFUo  UorFUTrr TrrUTrre UreTUFUo FeoUreTr UorrUoUr  UreorFUT FeoUreor UorFUoUF TrrUoUFU UreoUFUT  UorrUTUF TrreTUFe  FeoUFUT FeorFUo FeTrreo FeoUFUT FeoUrUT FeTrreo FeoUFeT FeoUFUT FeoUFeT FeoUFUT  UorrUTUr TrreTrre UreorFUT  UorFeorr TrrUoUFe UreTrreT FeorFUTr UorrUTrF TrreTUre UrUorrU UreorFU UrUorre UreoUFeT FeorreT   TrreoUF TrreoUFe UreorrU UreoUrUo FeorFeor UorFeTrF TrreoUrU UreTrrUo FeoUFeTU UorreTU UoUreor UorFeTUF TrreorF   FeorrUT FeorFUTU UorreoU UoUreTUr TrreTrFU  FeTrrUTU UorreTUF TrreoUFU UreoUrUo  UoUrUorr TrrUTUFe UreTUrUT FeorFeor UorrUorr TrreoUF TrFeorr TrrUoUre UreorrU  ', 'ACUHDMNBRCUKZANUGWGTXAMKKTJBICROVLSRQHHUSQDIYHVECUDXKALOWFIKGATDQGPOZBJTSFWSVVTDIUWUFFCIMLWHQJEVHGUXTEHZVPUYSORITUYLBVUJUVUHLDUZHYIEBQXIDZCJBDVARZJVMICAFDLIGLXEVWGZDESCGRQXJOBPJMCLOGSVCFSAWLFRLDVNWONYIFNZVFT', 'User', '2020-04-09 19:46:34'),
	(20, 'User Four', 'sszax', 'UrxrrsU srrsUFxr srrxrrs FxrFsUrx FxrrsrFs FxrrsUFx FxrrxUrs FxrFsrrx FxrFsrrx FxrrxUFx Fxrrsrrs FxrrxUF Ursrrxr srrsrFxr srrxrrs   UrxrrsU srrsrFsr srrxrrs FxrFxUrs FxrFxUFs FxrFxrFs FxrrxUFs FxrFsUFx FxrrsUFs FxrFsrFx FxrFxUFx FxrrsrFx FxrFsrrs FxrFxrrx FxrrxUF Ursrrxr srrsrFxr srrxrrs   UrxrrsU srrsUrxU srrxrrs FxrFxUrx FxrFxUFx FxrrsUFx FxrFsUrx FxrrxUF Ursrrxr srrxrFxU srrxrrs  ', 'PNPGPZGIMQHJJVBOLFTPDPFRXXYIPNOITVPEUJQKHQYXEQKOU', 'UserRole', '2020-04-09 19:48:04'),
	(21, 'User Four', 'Medic', 'UrcrrMU MrrMrFcr MrrcrrM FcrFMrrM FcrFMUrc FcUrcrFM  MrrcUFMU MrrMUFcU MrrMrrMr MrrMrrcU MrrMUrcU MrrMUrcU MrrcUFMr MrrcrFcU MrrMrFMU MrrcrFM FcUrcrr UrcUFcrF UrcrrcU   FcrrcUF UrcUrMrF UrcrrcU MrrcUrMU MrrMUrMr MrrcUFcr MrrcUrMr MrrMrrMU MrrcUrMU MrrMUrcU MrrcrFM FcUrcrr UrcUrMrF UrcrrcU  ', 'HVDCKGLMWJLDCVUGRJQUKRSURRZXOKMWLKUGW', 'UserRole', '2020-04-09 20:03:03'),
	(22, 'Username Two', 'Medic', 'UoceoMU MeoceTMU MeoceoM TceTceoc TceocUTM TceTMUoc TceTMeoM TceTcUTc TceocUT UoMeoce MeocUoMU MeoceoM   UoceoMU MeocUTcU MeoceoM TceocUTM TceoMUTc TceoMUoM TceoMeoM TceTceTc TceTMUoM TceoMeoc TceoMUTc TceocUT UoMeoce MeocUoMe MeoceoM   UoceoMU MeoMUTce MeoceoM TceoMUoc TceTceTM TceTMeTc TceTceoc TceTcUTM TceocUT UoMeoce MeoMeoMU MeoceoM   UoceoMU MeocUoMe MeoceoM TceTMeTc TceTceTM TceTcUTc TceTceTM TceocUT UoMeoce MeoMUocU MeoceoM   UoceoMU MeocUTce MeoceoM TceoMeTc TceTMUoc TceoMeTM TceTcUTc TceoMUoM TceocUT UoMeoce MeoMeoMe MeoceoM   UoceoMU MeoMeTcU MeoceoM TceTMeTM TceTMeTM TceoMeTM TceTcUoc TceoMeTc TceoMUTM TceTMeTc TceoMUTc TceTMUoM TceTMeTM TceoMeTM TceTcUoc TceTMUTc TceTMUoc TceTMUoc TceocUT UoMeoce MeoMUTce MeoceoM   UoceoMU MeoceTMe MeoceoM TcUTceT UocUoMeT UoceTMeT UoceoMUo UocUTMeT UoceTce MeoceTM TcUoceo UocUTMUo UoceocU   TceocUT UoceoMUT UoceocU MeTMeoM TceoMUoM TceTMeTM TceoMUoM TceTcUoc TceoMeo UoceoMU MeTceoc TceoMUoc TceoceT  ', 'SICLSWYZKQEQNRSCZFNMPFSMFHXFVLYNNWPGYHYWDWVCFFFYHJLITZQZKTVDTCZOCKCLXXZTVUPLAOENGUFJAOETWTXINDUASJQGWQGMUCWLJIPUIEJECZVSDQDRG', 'UserRole', '2020-04-09 23:18:07'),
	(23, 'Username Two', 'sdfg', 'UogeosU seosUoge seogeos TgeTgUTg TgeTgUos TgeTgeTs TgeTgeTg TgeTgeTs TgeTsUog TgeogUTg Tgeoseos TgeogUT Uoseoge seogUoge seogeos   UogeosU seosUogU seogeos TgUTgeT UogeTseo UogUoseT UogeTgeT UogUTgUT UogeTge seogeTs TgUogeo UogUTgeo UogeogU  ', 'NRWEGGVFJTPRIHFSJRQGWFIDOTDURCX', 'UserRole', '2020-04-09 23:18:15'),
	(24, 'User Three', 'sszax', 'UexresU sresrexr srexres TxresrTx TxrexUTx TxresUTs TxrTsUex TxrTxrex TxrTxrTs TxrTxrex TxresrTs TxrTsres TxresUTs TxrexUT Uesrexr sresrTxr srexres   UexresU sresresr srexres TxrexUes TxrTxUes TxresrTs TxrexUT Uesrexr srexUexU srexres   UexresU srexUTsU srexres TxrTsUes TxrTxrex TxrexUTx TxresUes TxrexUT Uesrexr sresUTxr srexres  ', 'AZUYVLAVUJNOKDLVEODZXWVNCDFGVBXHAGYKTADPNUN', 'UserRole', '2020-04-10 00:25:14'),
	(25, 'User Three', 'User Four', 'UerrrTU UerFUTrr TrrrerF TrrUerrU UrrTrrrT FreUFrTU UerFreUF TrrUTUFr UrreUrre FreUrreU UerFUeUr TrrreUF TrFrerr TrrUeUFU UrrerrU  ', 'FNPRJMSCCFQMVSST', 'User', '2020-04-10 10:41:13'),
	(26, 'User Three', 'User Four', 'UerrrTU UerrUTrr TrrrerF TrrUTrrr UrreUFUe FrerFUeU UerFUTUr TrrUeUFr UrrerFU UrUerrr UrrTrrrT FrerrrT   TrrreUF TrrUerrr UrrerrU UrrerFrT FreUFUTr UerrrTUF TrrUTUrU UrrTrrre FrerFUeU UerrrTUF TrrUTUrU UrrerFU UrUerrr UrrTrFUe FrerrrT   TrrreUF TrrrTUrr UrrerrU UrrTUrUe FreUFUTr UerrUeUr TrrrTUrr UrreUrrT FrerrUT FrTrrre FrerrUeU UerrreU   UrrerFU UrrTrFUe FrerrrT FreUFrTU UerFUTrr TrrUTUFr UrrerFrT FrerrUT FrTrrre FrerFUeU UerrreU   UrrerFU UrrTrrUT FrerrrT FreUFUTr UerFrTUr TrrUerrr UrrerFUe FreUrreU UerrrTU UeUrrer UerFrerr TrrrerF   FrerrUT FrerrUTr UerrreU UerFrTrF TrrUTUFr UrrTUrUe FreUFUer UerFrTUr TrrrTUrU UrreUrUe FrerrUTr UerFrerr TrrUTrrr UrrTUFre FreUFUTr UerrUTrr TrrrTrrU UrrTrrUe FrerrUT FrTrrre FreUFUeU UerrreU   UrrerFU UrrTUrrT FrerrrT FrTUrrT FreUFrTr UerrUTUF TrrrTrrU UrrTUFre FrerFre FrerrUT FrTrrre FrerrUTr UerrreU   UrrerFU UrrTrFrT FrerrrT FrTUrrT FreUrUTU UerFreUF TrrUTUrr UrreUFrT FrerFre FrerrUT FrTrrre FreUrUeU UerrreU  ', 'ODZDLLTZFSYPOPGZRHXSVHINQBBTITVVDWBVOLMLLKGMTKTNGADWZICQRPIWQIFJSGRFZSCGZJKRXZWBIKNWDXULPDIHMSJPHQUDRODNRSXJCSGQUYKUKKCDHAUWS', 'User', '2020-04-10 10:42:23'),
	(27, 'User Three', 'User Four', 'UerrrTU UerFrerr TrrrerF TrrUTrrU UrreUFUT FrerFrer UerrUTUr TrrrTUrr UrrTUFre FreUrUTr UerrrTrF TrrrTrrr UrreUrrT FreUFreU UerFrTUr TrrUeUrU UrrerFUT FreUrrTU UerFUTrF TrrrTUrr UrrTrrUT FrerrUT FrTrrre FrerrUTr UerrreU   UrrerFU UrrTrFrT FrerrrT FreUrrTr UerFUeUF TrrUeUFr UrreUrUT FreUrrTU UerrUTrF TrrreUF TrFrerr TrrUeUFU UrrerrU  ', 'KZMJLFXUIALFSZPEQGVJGXIJFZLWUHJYCMCHTKSBW', 'User', '2020-04-10 10:47:09'),
	(28, 'User Three', 'Username Two', 'UeeorTU UeeoUTUT TeoreeT TeorTUor UorTUorT TreUTUee UeeTreeo TeorTeTr UorTeTUe TreeTUTe UeeTUeeT TeoUeUTr UoreUore TreeTrTU UeeorTU UeUoree UeeTreUT TeoreeT   TreeoUT TreeoUTe UeeoreU UeeTreeo TeorTUor UoreeTrT TreUorTe UeeTreeT TeoreUT TeTreeo TeoUeUTr UoreeoU   UeeorTU UeeTrTUo TeoreeT TeoUeUTr UorTeorT TreeoUTU UeeTUeeo TeorTUoU UoUTeoU UoreUTre TreeTUee UeeorTUT TeoreUoU UoreUor UoUTeoU UoreeTrT TreeTreU UeeorTUT TeoUTeoU UoreUor UoUTeoU UoreUore TreUoUTU UeeoUeeT TeoUTeoU UoreUor UoUTeoU UorTeoUT TreUoreU UeeTreUo TeoreUTU UoreUor UoUTeoU UoreUTrT TreUTreU UeeoUeeo TeoreUTU UoreUor UoUTeoU UorTUoUT TreeTUeU UeeTrTUT TeoUeUor UoreUor UoUTeoU UorTUorT TreUTreU UeeoUeeT TeoreUTU UoreUor UoUTeoU UorTeore TreeTreU UeeTUeeo TeoUeeoU UoreUor  TreUTreU UeeoUeUo TeoreUTr UorTUoUe TreUoUee UeeorTU UeUoree UeeTrTeT TeoreeT  ', 'HASANZLCINNIETLLWJFMGJVWPUGKYJTBTCZTAFICAQZFRFKFKMUFQJPJGDQUTYMUIEJEEEIVAQQPVYLSEDIXDBUOYRSCPVYFFCUVT', 'User', '2020-04-10 10:57:08'),
	(29, 'User Fours', 'User Three', 'UsrerFU UsreUsUT FrersrT FreUFUTr UerFrTUF TrsrTrFU UsrTrsrT FreUFrTU UerFreUs TrsUTrFU UsrTrFUT FrersUT FrTrsre FreUsreU UersreU   UsrerFU UsrTUsUe FrersrT FreUFUer UersrTUs TrsrTUFU UsrTUFre FrerFUer UersrTrF TrsrTrsr UsrerFUe FreUsreU UerFUTUs TrsreUFr UsrerFU UsUersr UsrerFUT FrersrT   TrsreUF TrsUersU UsrersU UsrTrFUe FreUsUTr UerFreUs TrsrTUsU UsrTUFrT FreUsUer UerFrTUs TrsrTUsr UsrTrFUT FreUsreU UersUers TrsreUF TrFrers TrsUeUFr UsrersU  ', 'XEVEDCUFSVTPOYCVFUPGKROVRHPWGIVDTIPFDSYUQFGMYPGXGSUNFTPV', 'User', '2020-04-10 14:05:14'),
	(30, 'User Fours', 'User Three', 'UsrerFU UsrTrFUT FrersrT FrerFUer UerFrers TrsUTrFU UsrerFUT FreUFreU UersrTUF TrsUTrFU UsrTUsUe FrersUT FrTrsre FreUsUer UersreU   UsrerFU UsrTUFrT FrersrT FrerFrTU UerFUTrF TrsrTrFr UsrTrFUe FrerFrTU UerFreUF TrsrTrFr UsrTUFUe FrerFrer UerFrTUs TrsUeUsU UsrerFU UsUersr UsrerFUT FrersrT   TrsreUF TrsrTrsU UsrersU UsrTUsre FreUsUTU UerFUerF TrsUTUsU UsrTrsrT FrersUTU UersrTUF TrsUTrFr UsrTUFUe FrerFUeU UerFrTrF TrsreUF TrFrers TrsUeUsU UsrersU  ', 'OSYWKMEHDVQKNVIXPIMZBUTBSTNYPQGTUIBPWAGYOEZWKCVJZWHXCURU', 'User', '2020-04-10 14:05:26'),
	(31, 'User Fours', 'Medic', 'UscrsMU MrsMUFcU MrscrsM FcrFMUsc FcrFcUFc FcrFMrFM FcrsMUFc FcrFMUsM FcrFcrsc FcrFMrsc FcrsMUFM FcrFcUFc FcrFcrFc FcrscUF UsMrscr MrscUscU MrscrsM   UscrsMU MrsMUscr MrscrsM FcrFMrsM FcrFMUsM FcrscUsM FcrscUF UsMrscr MrscUFcr MrscrsM   UscrsMU MrsMrFMU MrscrsM FcrsMrFM FcrFMrsM FcrFcUsM FcrscUF UsMrscr MrsMUFMr MrscrsM   UscrsMU MrscrFMU MrscrsM FcrFcUsc FcrFMrFc FcrsMUFc FcrscUF UsMrscr MrsMrsMU MrscrsM  ', 'TMVDQALVVZRZCJLGYVFTRYNYUFSDNRDESIBCERLLMNTMIYSBLQDWJV', 'UserRole', '2020-04-10 14:09:25'),
	(32, 'Username Two', 'sszax', 'UoxeosU seosUosU seoxeos TxeTsUTx TxeTsUox TxeTxeTs TxeTsUos TxeTsUTx TxeosUox TxeTxeos TxeosUos TxeTsUTx TxeTsUos TxeoxUT Uoseoxe seoxUose seoxeos  ', 'XOEBKIWUZGTRRTMFYL', 'UserRole', '2020-04-11 11:57:25'),
	(33, 'User Three', 'User Three', 'UererTU UererTrT TrererT TreUTUeU UereUeUT TreUeUTU UereUeUT TreUTrTU UereUere TreUereU UereUTrT TrereUT TrTrere TreUerTr UerereU  ', 'QKRFPQAFCFDVLXER', 'User', '2020-04-13 13:11:44'),
	(34, 'User Three', 'Admin', 'UenreAU AreAUTAr ArenreA TnrTnrTn TnrTAren TnrTAren TnreAreA TnrTnrTA TnreAUTn TnrTArTA TnreAUeA TnrTnreA TnrTnreA TnreArTn TnrTArTA TnrTAUeA TnrTAren TnrTnUTn TnreAren TnrTAUTn TnrTArTn TnreArTn TnrenUTA TnrenUeA TnrTnUen TnreArTn TnrTAUen TnrTnUTA TnrTnUeA TnrenUT UeArenr AreAUTAr ArenreA   UenreAU AreAUTnU ArenreA TnrTnreA TnreArTn TnrTnrTn TnreAUTn TnrenUTA TnrTArTA TnrenUTA TnrTArTA TnrTnrTn TnreArTn TnrenUTn TnrenUT UeArenr ArenUenr ArenreA   UenreAU AreArTnr ArenreA TnrTAUTn TnrenUeA TnrTAreA TnreAUen TnrTnUen TnreAUTA TnrenUT UeArenr ArenUTnU ArenreA  ', 'CLTQODRULXYGPLJTWQTVNOESMLIXBNULHCZIMUQRKLCXWWZLLQEHVWDMVFIWAMDGVSCWI', 'UserRole', '2020-09-19 12:12:36'),
	(35, 'User Three', 'Medic', 'UecreMU MreMUTcr MrecreM TcreMrTM TcrTMrTM TcrTMrec TcreMUec TcreMUeM TcrTcUTc TcreMUec TcrTcUeM TcrTMrTc TcrTcrec TcrTMreM TcreMUec TcrecUTc TcrTcrTM TcrTcreM TcrTMrTM TcrTcUTM TcrecUeM TcrecUeM TcrTcrTM TcrTMrTM TcreMrec TcreMUTM TcrTMUeM TcrTcUeM TcreMUTc TcrecUT UeMrecr MrecUeMr MrecreM   UecreMU MreMUecU MrecreM TcrTcrec TcrTMrec TcrTcrTc TcreMrec TcrTcUTM TcrTMreM TcreMUTM TcrTMreM TcrTcUTM TcreMUTM TcreMrec TcrecUT UeMrecr MrecrTMU MrecreM   UecreMU MrecrTcU MrecreM TcreMrTc TcrecUeM TcrTcUTc TcreMUeM TcreMUTM TcrTMrec TcrecUT UeMrecr MreMrTMU MrecreM  ', 'XNMXLDOADMQXQWYQBVACITSWYGHZIOFFTWXZQURDKRMZOYUGJJXISCMBKECWDLIXKCSQP', 'UserRole', '2020-09-19 12:12:46'),
	(36, 'User Three', 'User Three', 'UererTU UerTUere TrererT TreUeUTr UerTrTrT TreUeUTr UerTrere TreUerTr UerTreUe TreUTUTr UerTUTrT TreUTrTr UereUTre TrereUeU UerTrTUe TrerTUTU UereUTUe TrerTUeU UerTUTUe TrerTUer UerTUTre TreUerTr UerTrere TreUerTr UereUerT TreUereU UerTUerT TrerTrTU UererTUe TrereUT TrTrere TrerTreU UerereU   UererTU UereUTre TrererT TrerTUer UerTreUT TrerTreU UerTreUT TreUeUeU UereUTre TreUerer UerTUTrT TreUerer UereUeUe TreUerTU UererTU UeUerer UerTrere TrererT   TrereUT TreUeUTU UerereU UereUeUe TrerTrTU UerTrTUT TreUerTU UerTrTUe TreUeUTr UererTUT TreUTrTr UerTUTUe TreUeUer UererTUT TrereUT TrTrere TreUTUeU UerereU  ', 'ERIMRFKVHUIXUQOHGZXNLGVFXELJQEGGXXNADNVITGOMNUBLYXIZYVJISWCQCFBZUIIOFUPMOE', 'User', '2020-09-19 12:12:55'),
	(37, 'User Three', 'User Three', 'UererTU UerTUerT TrererT TreUTUeU UereUeUe TreUeUT TreUTUeU UereUeUT TreUeUer UerTrTr UereUTUT TreUerTU UereUTUe TrereUT TrTrere TrereUeU UerereU  ', 'IQOFRNXOEGYGXTEKVO', 'User', '2020-09-20 22:52:11'),
	(38, 'Username Two', 'User Three', 'UoreeTU UoreUoUe TreeorT TreUoUTU UeeTreeo TeoUTeoU UorTeoUe TreUoUTe UeeoUeeT TeoUTeo TeoUeeTU UorTeTU UorTUor UorTeTUe TreUoree UeeTrTeo TeoreUT TeTreeo TeoUTeTr UoreeoU  ', 'UFDHLFULSETQLGJTVKPVJ', 'User', '2020-09-21 00:15:49');
/*!40000 ALTER TABLE `messages` ENABLE KEYS */;


-- Dumping structure for table hospital.patients
DROP TABLE IF EXISTS `patients`;
CREATE TABLE IF NOT EXISTS `patients` (
  `Pno` int(11) NOT NULL AUTO_INCREMENT,
  `Fullname` varchar(200) NOT NULL,
  `Age` int(11) NOT NULL,
  `Region` varchar(50) NOT NULL,
  `BirthPlace` varchar(100) NOT NULL,
  `Country` varchar(50) NOT NULL,
  `Contact` varchar(200) NOT NULL,
  `Creator` int(11) NOT NULL,
  `Groups` varchar(200) NOT NULL,
  `DateAdded` timestamp NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`Pno`),
  UNIQUE KEY `Fullname` (`Fullname`),
  KEY `Creator` (`Creator`),
  CONSTRAINT `patients_ibfk_1` FOREIGN KEY (`Creator`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=latin1 COMMENT='Used to Capture Patients Information';

-- Dumping data for table hospital.patients: ~10 rows (approximately)
/*!40000 ALTER TABLE `patients` DISABLE KEYS */;
INSERT INTO `patients` (`Pno`, `Fullname`, `Age`, `Region`, `BirthPlace`, `Country`, `Contact`, `Creator`, `Groups`, `DateAdded`) VALUES
	(22, 'Patient One', 24, 'Christian', 'Kiambu', 'Kenya', 'Kiambu East, +254772363212', 11, 'Medic', '2020-04-07 12:09:52'),
	(24, 'fsfgds', 23, 'sdfgbh', 'sdfcvgb', 'Kenya', 'xcdfvgbnhjmk,.\nftyjukil', 13, 'Medic,Admin', '2020-04-08 11:10:49'),
	(25, 'sdfghj', 34, 'dfghj', 'dfgh', 'Kenya', 'sdfghjk', 13, 'Medic', '2020-04-08 17:31:29'),
	(26, 'fghjk dfgh', 45, 'retgry', 'edrftghyj', 'Kenya', 'sdefrgthyju\nedrftgyhj', 13, 'Admin,Medic', '2020-04-08 17:31:48'),
	(27, 'wead', 21, '32fd', 'dsddzss', 'Kenya', 'srdffdfhf', 13, 'Medic', '2020-09-18 20:36:21'),
	(28, 'dsdads', 12, 'sdadftryt', 'dsfgr', 'Uganda', 'sdfssgt', 13, 'Medic', '2020-09-18 20:43:26'),
	(29, 'we3reras', 33, 'wewq', 'wqewq', 'Kenya', 'awertghr ewwq', 13, 'Medic', '2020-09-19 10:54:36'),
	(30, 'ewqdrw', 67, 'erwrew', 'ert5y6r45', 'Kenya', 'aewqe2 vrew', 13, 'Medic', '2020-09-19 10:55:03');
/*!40000 ALTER TABLE `patients` ENABLE KEYS */;


-- Dumping structure for table hospital.patientsinfo
DROP TABLE IF EXISTS `patientsinfo`;
CREATE TABLE IF NOT EXISTS `patientsinfo` (
  `PIno` int(11) NOT NULL AUTO_INCREMENT,
  `Pno` int(11) NOT NULL DEFAULT 0,
  `Cover` varchar(200) NOT NULL,
  `Record` text NOT NULL,
  `DK` text NOT NULL,
  `Medic` int(11) NOT NULL,
  `DateDone` date NOT NULL,
  `DateAdded` timestamp NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`PIno`),
  KEY `Pno` (`Pno`),
  KEY `Medic` (`Medic`),
  CONSTRAINT `patientsinfo_ibfk_1` FOREIGN KEY (`Pno`) REFERENCES `patients` (`Pno`),
  CONSTRAINT `patientsinfo_ibfk_2` FOREIGN KEY (`Medic`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=latin1 COMMENT='Stores patient diseases and treatments administred';

-- Dumping data for table hospital.patientsinfo: ~20 rows (approximately)
/*!40000 ALTER TABLE `patientsinfo` DISABLE KEYS */;
INSERT INTO `patientsinfo` (`PIno`, `Pno`, `Cover`, `Record`, `DK`, `Medic`, `DateDone`, `DateAdded`) VALUES
	(22, 24, 'fsfgds 2020-04-09', 'f9s9s2f f9s2f9s2 2s9s9s2 2s9f9s2s f9s2f9s2 2s9f2s2s f9s2f9f9 2s9s2f2s f9s2s2s2 2s9f9s2s f9s2s2s9 2s9f2s9s f9s2f2f9 2s9f2f2s f9s9s2f f9f9s9s f9s2s9s2 2s9s9s2   2s9s9f2 2s9s2s9s f9s9s9f f9s9s2f2 2s9f2s2f f9s2s2s9 2s9f9f2f f9s2s2s9 2s9s9f2 2s2s9s9 2s9f2f2s f9s9s9f   f9s9s2f f9s2s9f9 2s9s9s2 2s9f2s2s f9s2f9f9 2s9f9f2s f9s2f9f2 2s9s2s2s f9f2s9f f9s9f2f2 2s9f9s2s f9s9f9s9 2s9f2f9f f9s9f9s f9f2s9f f9s2f2s2 2s9f2f9s f9s2s2s9 2s9f9f9f f9s9f9s f9f2s9f f9s9f2s9 2s9s2s9s f9s2s9s9 2s9s9f9f f9s9f9s f9f2s9f f9s9f9f2 2s9f2s2s f9s9s2f2 2s9f9f9f f9s9f9s f9f2s9f f9s2s9f9 2s9f9s2s f9s9f9f2 2s9f9f9s f9s9f9s f9f2s9f f9s2s9f2 2s9s9f9f f9s2f2f9 2s9f2s2s f9s9f9s f9f2s9f f9s9f2f9 2s9f2f2s f9s2f2s2 2s9s9f2s f9s9f9s f9f2s9f f9s2f9f9 2s9f9f2f f9s2f2f9 2s9f9f9f f9s9f9s  2s9s2f9f f9s9s2f2 2s9s2f2f f9s2f2s9 2s9s2s2f f9s9s2f f9f9s9s f9s9f2s9 2s9s9s2  ', 'TQAUNBBYJJSGIADQYKDNBHUFAFBHZELNDUOXFBZNXTKCLEMSFOBSUFPFVWKDOGDFUGHZLBVLWYYIPUADZJJVBRGIULZOSMWOUSDBQ', 13, '2020-04-09', '2020-04-09 09:24:29'),
	(23, 26, 'fghjk dfgh 2020-04-09', 'fh9kh2f 2kh9fh2k 2kh9kh2 d9kd2kh9 d9kd9fd9 d9kd2fd9 d9kh2kd9 d9kd2fh2 d9kd9fh9 d9kd2fh9 d9kh9fh2 d9kh2fh2 d9kd2fd9 d9kh9fd fh2kh9k 2kh9fd2f 2kh9kh2  ', 'FFGKFWSITXTAVAMALO', 13, '2020-04-09', '2020-04-09 20:32:16'),
	(25, 26, 'fghjk dfgh 2020-04-03', 'fh3kh2f 2kh3fd2f 2kh3kh2 d3kd3fd2 d3kd3kh2 d3kh3fd3 d3kh2kh2 d3kd2fh3 d3kd3fh3 d3kd3kd3 d3kh2fd2 d3kd3fd3 d3kh3fd fh2kh3k 2kh2kh3f 2kh3kh2  ', 'GAHLJHCEQEBTFWYNE', 13, '2020-04-03', '2020-04-09 21:19:39'),
	(26, 24, 'fsfgds 2020-04-10', 'f0s0s2f f0s0s2s2 2s0s0s2 2s0f2s0s f0s2f0f2 2s0f0f0f f0s0f0f2 2s0s2f0s f0s2f0s2 2s0s0f2 2s2s0s0 2s0f2s0s f0s0s0f   f0s0s2f f0s2s0f2 2s0s0s2 2s0s2f2f f0s2f2s2 2s0s2f2s f0s0s2f0 2s0f2s2f f0s0s2f f0f0s0s f0s2s2s2 2s0s0s2   2s0s0f2 2s0f0f0s f0s0s0f f0s0f0f0 2s0s0f2f f0s2f0s2 2s0s0f2 2s2s0s0 2s0s2s2f f0s0s0f  ', 'YKJWUCNNCZGROTYTWJJYLGAQTUBMUFVAPUIEHEOS', 13, '2020-04-10', '2020-04-10 10:18:57'),
	(27, 24, 'fsfgds 2020-04-13', 'f3s3s2f f3s2s2f2 2s3s3s2 2s3f3f3s f3s2f2f3 2s3s2f3f f3s3f2s3 2s3f2f3f f3s3f2s2 2s3f3f2f f3s2f2s2 2s3f2s2f f3s3f3s2 2s3s2f2s f3s2s3s3 2s3s2s2s f3s3f3f2 2s3f3s2f f3s3f2s3 2s3f2s3f f3s3s2f f3f3s3s f3s2f2s3 2s3s3s2   2s3s3f2 2s3s2s3f f3s3s3f f3s2s2f2 2s3f3s2f f3s2f3f3 2s3f2s3f f3s3s2f f3f3s3s f3s2f2s3 2s3s3s2   2s3s3f2 2s3f2f3s f3s3s3f f3s2f3f3 2s3f2s2f f3s3f3f3 2s3s2s3s f3s2s2s3 2s3s3f2 2s2s3s3 2s3f3f3s f3s3s3f  ', 'QSNSDONVKUSQLFPXBIQAEHNZWMKGIHTBATONOBTLNZMAQRTVDVFM', 13, '2020-04-13', '2020-04-13 19:26:46'),
	(28, 24, 'fsfgds 2020-04-13', 'f3s3s2f f3s2f2s3 2s3s3s2 2s3s2s3f f3s2s3s2 2s3s2s3s f3s2f3f2 2s3s2s3f f3s3f3f2 2s3f2f3f f3s3s2f f3f3s3s f3s2f2f3 2s3s3s2   2s3s3f2 2s3s3f2s f3s3s3f f3s2s3f2 2s3s2s2f f3s3s2s2 2s3f3f3f f3s3f3s3 2s3f2f3s f3s2s3f2 2s3s2f2s f3s3s2s2 2s3f2s3s f3s2f2s2 2s3s2s2f f3s3s2f3 2s3f2s2f f3s2s2f3 2s3s3f2 2s2s3s3 2s3f3f2s f3s3s3f   f3s3s2f f3s2f3f2 2s3s3s2 2s3f3f3f f3s2s2s2 2s3f3s2f f3s3s2f3 2s3s2s3f f3s3s2f2 2s3s2f3f f3s2s3s3 2s3s2f3f f3s2f3s2 2s3f3f3f f3s2f3s2 2s3s2s2f f3s3f2s2 2s3f2s3s f3s3s2f f3f3s3s f3s2s3f2 2s3s3s2   2s3s3f2 2s3f3f3f f3s3s3f f3s3f2s2 2s3f3f3s f3s2s3s3 2s3s2s2f f3s2f2s3 2s3f2f2s f3s3f2f2 2s3f2f3s f3s2s2f2 2s3s3f2f f3s2s3s2 2s3s2s3 2s3s3f2 2s2s3s3 2s3f2s3s f3s3s3f  ', 'MNBZVSAEOVFRLGOUIJPTQUSQJESQMRLVYCYITGHGROUGEITPUMIKHPWLHSMWWLTRXUSOFLRXUITXMZNNLRGJ', 13, '2020-04-13', '2020-04-13 19:27:07'),
	(29, 24, 'fsfgds 2020-04-12', 'fssssff fssfffsf fsssssf fsssffss fssfssfs fssfsfff fsssfssf fssfssss fsssffff fsssffff fssfsfsf fssffsff fssfffsf fssffsss fssfsssf fsssfsff fssfssfs fsssffss fssssff fsfssss fssfssff fsssssf   fssssff fssssffs fsssssf fssfsfff fssfssff fsssfffs fssssffs fssssfsf fssffsff fssfsffs fssfsfsf fsssffsf fssssff fsfssss fssssffs fsssssf   fssssff fssssfsf fsssssf fsssfssf fssfsffs fssfffsf fsssfffs fssssfff fssssff fsfssss fssfsfss fsssssf   fssssff fsssfsss fsssssf fsffssf fssfffss fssfsffs fssssfsf fssffsss fsssfss fssssff fsfssss fsssfsfs fsssssf  ', 'FMFAMEQWKMEAWOIQJARZWPQKQJOFGNBABDGZHEJPHSXKHDFWKCLHVDZHUHEDPHHREWYFNM', 13, '2020-04-12', '2020-04-13 19:27:57'),
	(30, 24, 'fsfgds 2020-04-12', 'fssssff fssffssf fsssssf fssfssff fssfsfff fssssfff fsssffsf fssfsfsf fssfsffs fsssffsf fssffsff fssffsss fssfssff fssffsff fssfsssf fsssfssf fsssfffs fsssfssf fsssfssf fssssfff fssffsss fsssffss fssfssff fssfsffs fsssffsf fssfsfff fsssfffs fssfsfss fssfssss fssssff fsfssss fsssfsff fsssssf   fssssff fsssfssf fsssssf fssfsffs fssfsfsf fssfsfss fssffffs fssfffsf fssffsfs fssfffsf fssffsfs fsssffss fssfssfs fssfffsf fssssff fsfssss fssffsss fsssssf   fssssff fssfsfff fsssssf fssfsssf fssfffsf fsssfffs fssfsffs fsssffsf fssfffsf fssssff fsfssss fssssfff fsssssf  ', 'WQOPPUNSDLKZNUTNGDSSPMSBTYWAGLZEMJJFGVLGIVGYAXFDOPURTBKUSWVYLCKSQBIWP', 13, '2020-04-12', '2020-04-13 19:28:09'),
	(34, 30, 'ewqdrw 2020-09-01', 'e1w1w2e e1w1w2e2 2w1w1w2 2w2w1w2e e1w2w1w2 2w1w2w2e e1w2e2e1 2w2w2e2w e1e1e2e1 2w1e2e1e e1w1w2e e1e1w1w e1w2e1w1 2w1w1w2  ', 'DISCOTIQUFSNRIG', 13, '2020-09-01', '2020-09-19 12:14:24'),
	(35, 26, 'fghjk dfgh 2020-04-03', 'fh3kh2f 2kh3fh3f 2kh3kh2 d3kd3kh3 d3kh2fh3 d3kd2kh3 d3kd2fh2 d3kd3kh3 d3kh3fh2 d3kh2kh3 d3fd2kd3 d3fh3fh2 d3kh3fd fh2kh3k 2kh2kh3k 2kh3kh2   fh3kh2f 2kh3fh2f 2kh3kh2 d3fd2kh2 d3fd2fd3 d3fh2fd2 d3fd3fh2 d3fh2fd2 d3fd3fh2 d3fh2fh3 d3kd2fh3 d3kh2kd3 d3kh3fd3 d3kh3fd fh2kh3k 2kh2fh3k 2kh3kh2   fh3kh2f 2kh2kh2k 2kh3kh2 d3kd2fh2 d3kh3fd3 d3kd3kd3 d3kh2fd2 d3kd3kd3 d3kd2fd3 d3kh3fd3 d3kh3fd fh2kh3k 2kh3fh2f 2kh3kh2  ', 'VGXRAJBLHWMZXRZAONPEIOXKRPTSCEUPJRFWAFXFWVUNHWCRKEBT', 13, '2020-04-03', '2020-09-19 12:26:12'),
	(36, 24, 'fsfgds 2020-09-20', 'f0s0s2f f0s2f0f2 2s0s0s2 2s0f0f0f f0s2f0f0 2s0f0s2s f0s2s0f0 2s0f0s2s f0s2s0f0 2s2f2s0f f0f2f2s2 2s2s2f0s f0f2f2s0 2s2s0f2f f0f0f0f2  f0f2s0s0 2s2f0f2f f0f0f2s2 2s2s2f0s f0f2f2s2 2s2s2f2f f0f2f0f2 2s2f2f2s f0f2s0s0 2s2f2f2s f0f2f2f0 2s2f2f0f f0f2f2s2 2s2s2f2f f0f0s0f f0s0f0s0 2s0s2f2f f0s2s0s0 2s0s2f2f f0s2s2s2 2s0s2s0s f0s0f0s0 2s0s2s2f f0s2s0s0 2s0s0f2 2s2s0s0 2s0f2s0s f0s0s0f  ', 'VORLXMTOMIIUJZPOQNTWBLDTQWHWFZZQNVKDPFPKSIRSB', 13, '2020-09-20', '2020-09-20 21:28:13'),
	(37, 30, 'ewqdrw 2020-09-20', 'e0w0w2e e0w0e2w2 2w0w0w2 2w0e2e0w e0w2w2e2 2w0w2w0w e0w2e0w0 2w0e2e0e e0w0e0e2 2w0e2w0 2w0e2e2w e0w2w2e e0w2e0w e0w0e0w0 2w0w0e2w e0w0e0w2 2w0w0e2 2w2w0w0 2w0w2w0w e0w0w0e  ', 'PCZCEWOEQJIROUTEULHXP', 13, '2020-09-20', '2020-09-20 22:44:24'),
	(38, 29, 'we3reras 2020-09-20', 'w0s0s2w w0s0w0s0 2s0s0s2 2s0w2w0s w0s2w2s2 2s0w0w0w w0s2s2s2 2s0w2s0 2s0w0w2 2s0s0w2 2s2s0s0 2s0w0w0w w0s0s0w  ', 'HHSGLFGYPFXUDN', 13, '2020-09-20', '2020-09-20 22:46:23'),
	(39, 29, 'we3reras 2020-09-20', 'w0s0s2w w0s2s2w0 2s0s0s2 2s0w2s2s w0s2w2w0 2s0w2w2s w0s2w2w0 2s0w2s0 2s0w0w2 2s0s2w2w w0s0w0w2 2s0w0s0w w0s2s0w0 2s0w0s0w w0s2s2w2 2s0s2w2s w0s0s2w w0w0s0s w0s2s0w2 2s0s0s2  ', 'OTFIKWXPUMPLHWDNQXWWC', 13, '2020-09-20', '2020-09-20 22:46:33'),
	(40, 28, 'dsdads 2020-09-20', 'd0s0s2d d0s2d2s0 2s0s0s2 2s0d0d0d d0s2s0s2 2s0d0s2s d0s0d2s0 2s0d2s0d d0s2s0d0 2s0s2d0d d0s0d2d0 2s0s0d2 2s2s0s0 2s0d0d2d d0s0s0d  ', 'RNGGVMABHAKWJSYY', 13, '2020-09-20', '2020-09-20 22:47:45'),
	(41, 28, 'dsdads 2020-09-20', 'd0s0s2d d0s2d2s0 2s0s0s2 2s0d2d0d d0s2d2s2 2s0s2d0s d0s2d0s2 2s0d2d0d d0s2s2d2 2s0s2s2d d0s0s2s2 2s2s0s2  d0s2s2d0 2s0d2s0s d0s2s0s0 2s0s2d0s d0s2s2d0 2s0d0d0s d0s0s2d d0d0s0s d0s0d0s2 2s0s0s2  ', 'PNLYJSNXCCTYQQAHLISOIGEE', 13, '2020-09-20', '2020-09-20 22:48:02'),
	(42, 22, 'Patient One 2020-09-20', 'Pe0te2P 2te0Pe2t 2te0te2 O0tO2te Pe0tO0PO Pe0tO2PO Pe0Pe0Pe Pe0tO2PO Pe0Pe2P 2te2Pe0 O0tO0te2 O0te0PO Pe2te0t 2te2te2P 2te0te2  ', 'KFJCPTIYLLRYYWYE', 13, '2020-09-20', '2020-09-20 22:49:59'),
	(43, 27, 'wead 2020-09-20', 'w0d0d2w w0d0w0w2 2d0d0d2 2d0w2d0w w0d0d2d2 2d0w0w2 2d0d2d2w w0d0w2d0 2d0w0w0d w0d0d2w w0w0d0d w0d2d0w0 2d0d0d2  ', 'PEWJWCPNEXPXBB', 13, '2020-09-20', '2020-09-20 22:51:59'),
	(44, 26, 'fghjk dfgh 2020-09-20', 'fh0kh2f 2kh0fd2k 2kh0kh2 d0kd2fh2 d0kd2kh0 d0kd0kd2 d0kd0fd2 d0kh2kh2 d0kh0fh2 d0kh0fd2 d0kd2kh2 d0kd2fh2 d0kd0fh2 d0fh0kd fh0kd0fh fh0fh0kh fh0fh0fh fh0kd2kd  d0kh2kd2 d0kd0fd0 d0kd0kh2 d0kh2fh0 d0kd0fh fh0fd0fh fh2kh0f 2kh2kh0f 2kh2fd0k 2kh0kd2k 2kh0fd0k 2kd0kh2  2kd0fd2k 2kh2kd2f 2kh0fh2k 2kh2fh0k 2kh0fd0k 2kh2fd0k 2kh2kh0f 2kh0fh2f 2kh2kh0k 2kh2kh2f 2kh0fh0k 2kh0kd2 d0fh0kh fh0kd0kh fh0kh0f  ', 'BBXKKUEOXFHXSOMGRAOPESQTADKYHYCGNPNALKPOXHTIIHZG', 13, '2020-09-20', '2020-09-20 22:56:01'),
	(45, 26, 'fghjk dfgh 2020-09-20', 'fh0kh2f 2kh0fh0f 2kh0kh2 d0kh2kh2 d0kd2fd0 d0kd0fd0 d0kd0kd2 d0kh0fd0 d0kd2kh0 d0kd2fh0 d0kd0fd0 d0kh2kh2 d0kd0kh2 d0fh0kd fh0kd0fd fh0fd0kd fh0fh2fd fh0fh2kh  d0kh0fd0 d0kh0fd0 d0kd2fh0 d0kd2fd0 d0kd0fh fh0kh2kd fh2kh0f 2kh0fh2k 2kh0fh0k 2kh2fh0f 2kh0kd0f 2kd0kh2  2kd0fd0f 2kh0fh2k 2kh0fd2k 2kh2kh2k 2kh2fh2k 2kh2fd0k 2kh0fd2f 2kh2fh2k 2kh0fd2k 2kh2kd0f 2kh2fh2k 2kd0kh2 d0kd2kh2 d0kh2fh2 d0kh0fd2 d0kh2fd0 d0kd0fd0 d0kh0fh2 d0kd2kh0 d0kd0fh2 d0kd0fd0 d0kd2fh0 d0kh2kh0 d0kh2fd0 d0kh2kh0  2kh2fh0f 2kh2kd2f 2kh2fh2f 2kh2kd2f 2kh0fd0k 2kh0fh2k 2kh2fh0f 2kh2kd0k 2kh2fh0k 2kh2fd0f 2kh0fh2f 2kh2kd2k 2kh2fd0k 2kh0fh0k 2kd0kh2 d0kh0fd2 d0kd0fh0 d0kd2fh2 d0kh2kd0 d0kd0fd0 d0kh0fd2 d0kh2fh2  2kh0kd0f 2kh2kh0f 2kh2fh2k 2kh2fh0f 2kh0fd0k 2kh2fh2k 2kd0kh2 d0fh2kh2 d0fd2kd2 d0fd2kd2 d0fh2kh0 d0fd0fh2 d0kh0fd fh2kh0k 2kh0fd0k 2kh0kh2  ', 'LGFEERIREKKRWOLXMTDUUHYXVRRSOFIROCJGXKRZZFBSCNGZPQXNETPONNCFWAVRBEPYBBWFSUIVNZOWFFDVWRDYOWWTMOXDZQ', 13, '2020-09-20', '2020-09-20 22:56:19');
/*!40000 ALTER TABLE `patientsinfo` ENABLE KEYS */;


-- Dumping structure for table hospital.roles
DROP TABLE IF EXISTS `roles`;
CREATE TABLE IF NOT EXISTS `roles` (
  `Rid` int(11) NOT NULL AUTO_INCREMENT,
  `Role` varchar(200) NOT NULL,
  `Type` set('Views','Saves','Deletes','Updates') NOT NULL,
  `Description` text NOT NULL,
  `DateAdded` timestamp NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`Rid`),
  UNIQUE KEY `Role` (`Role`)
) ENGINE=InnoDB AUTO_INCREMENT=94 DEFAULT CHARSET=utf8mb4;

-- Dumping data for table hospital.roles: ~49 rows (approximately)
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` (`Rid`, `Role`, `Type`, `Description`, `DateAdded`) VALUES
	(36, 'LoadUserRoles', 'Views', 'Load UserRoles', '2020-04-13 22:37:11'),
	(37, 'PatientInfo', 'Views', 'Load Patient Information', '2020-04-13 22:39:02'),
	(38, 'NewPatientSearch', 'Views', 'Searching Patient Information', '2020-04-13 22:55:00'),
	(39, 'SendReceipient', 'Views', 'Viewing Receipient to Send', '2020-04-13 22:56:02'),
	(40, 'SharePatientSearch', 'Views', 'Viewing Patient to share', '2020-04-13 22:57:13'),
	(41, 'NewUser', 'Saves', 'Adds New User', '2020-04-13 22:59:09'),
	(42, 'NewPatient', 'Saves', 'Adds New Patient', '2020-04-13 22:59:36'),
	(44, 'SavePatientRecord', 'Saves', 'Adds New Patient Record', '2020-04-13 22:59:57'),
	(45, 'SearchPatient', 'Views', 'Finds Patient Information', '2020-04-13 23:00:30'),
	(47, 'NewPatientForm', 'Views', 'Views Form to Add New Patient', '2020-04-13 23:01:43'),
	(48, 'AddNewPatientInformation', 'Views', 'Viewing Form to Add new Patient Informatiom/Record', '2020-04-13 23:06:25'),
	(49, 'AddSharePatientInformation', 'Views', 'Viewing Form to Share new Patient Informatiom/Record', '2020-04-13 23:06:55'),
	(50, 'NewPatientInformation', 'Views', 'Viewing Form to Record New Record', '2020-04-13 23:07:45'),
	(51, 'NewSendPatientInformation', 'Views', 'Viewing Form to Send Patient Record', '2020-04-13 23:08:22'),
	(53, 'PatientInformation', 'Views', 'Viewing Form to View Patient Information', '2020-04-13 23:09:06'),
	(54, 'PatientInformationShared', 'Views', 'Viewing Form to View Patient Information to be Shared', '2020-04-13 23:09:23'),
	(55, 'ViewPatientRecords', 'Views', 'Viewing PatientRecords', '2020-04-13 23:09:50'),
	(56, 'ViewEditPatient', 'Views', 'Viewing Patient to edit Information', '2020-04-13 23:10:23'),
	(58, 'DeletePatient', 'Deletes', 'Deleting PAtient ', '2020-04-13 23:10:50'),
	(59, 'DeletePatientRecord', 'Deletes', 'Deleting PAtient  Record', '2020-04-13 23:11:04'),
	(61, 'UpdatePatient', 'Updates', 'Updates Patient', '2020-04-13 23:11:26'),
	(62, 'AddNewRoleForm', 'Views', 'Display Add new Role Form', '2020-04-14 08:50:33'),
	(63, 'ViewRoles', 'Views', 'Views Roles Information', '2020-04-14 08:50:59'),
	(64, 'AddNewRole', 'Saves', 'Adds New Role', '2020-04-14 08:51:19'),
	(65, 'AddNewUserRoleForm', 'Views', 'Displays New Userrole Form', '2020-04-14 08:52:05'),
	(66, 'ViewUserRoles', 'Views', 'Views All Userroles', '2020-04-14 08:52:41'),
	(68, 'AddNewUserRole', 'Saves', 'Adds New Userrole', '2020-04-14 08:53:39'),
	(70, 'ViewRoleForm', 'Views', 'Viewing Roles', '2020-04-14 08:54:22'),
	(71, 'DeleteUserRole', 'Deletes', 'Delete Userrole', '2020-04-14 08:54:58'),
	(73, 'RemoveRoleForm', 'Views', 'Display Remove Roles Form', '2020-04-14 08:55:22'),
	(74, 'RemoveRole', 'Deletes', 'Deletes and Removes Role from Userrole', '2020-04-14 08:55:56'),
	(75, 'ViewUsers', 'Views', 'View Users Header', '2020-04-14 08:56:29'),
	(76, 'ViewAllUsers', 'Views', 'View Users Information', '2020-04-14 08:56:48'),
	(77, 'ViewUserInfo', 'Views', 'View Users Info and Details', '2020-04-14 08:57:20'),
	(78, 'DeleteUser', 'Deletes', 'Deletes A User', '2020-04-14 08:57:42'),
	(79, 'MuteUser', 'Updates', 'Mutes, Deactivates and Disables a user', '2020-04-14 08:58:11'),
	(80, 'ActivateUser', 'Updates', 'Activates a user', '2020-04-14 08:58:30'),
	(81, 'AddSharedGroup', 'Updates', 'Updates patients by adding shared groups', '2020-04-14 08:59:06'),
	(82, 'UpdatePersonalInfo', 'Updates', 'Updates Your Personal Info', '2020-04-14 08:59:31'),
	(83, 'ChangePassword', 'Updates', 'Updates and Changes your Password', '2020-04-14 08:59:53'),
	(85, 'RemoveSharedGroup', 'Updates', 'Removes a shared userrole from a Patient', '2020-04-14 09:00:47'),
	(86, 'SendFormPatientRecord', 'Views', 'Displays a form to send patient information', '2020-04-14 09:01:22'),
	(87, 'LoadYourPersonalInformation', 'Views', 'Load Your Personal Information', '2020-04-14 09:01:58'),
	(88, 'LoadYourSentInformation', 'Views', 'Load Your Sent Information', '2020-04-14 09:02:37'),
	(89, 'LoadMessageSentInformations', 'Views', 'ViewYour Sent Information', '2020-04-14 09:03:14'),
	(90, 'LoadMessageRecievedInformation', 'Views', 'ViewYour Recieved Information', '2020-04-14 09:03:30'),
	(91, 'AddSendPatientInformation', 'Saves', 'Adds Send Message Information', '2020-04-14 09:03:59'),
	(92, 'ReportType', 'Views', 'Views Reports From Selected Type', '2020-04-14 09:04:33'),
	(93, 'PatientInfoTreatmentRecords', 'Views', 'Gets Patient treatment history', '2020-09-20 22:45:13');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;


-- Dumping structure for table hospital.userroles
DROP TABLE IF EXISTS `userroles`;
CREATE TABLE IF NOT EXISTS `userroles` (
  `URid` int(11) NOT NULL AUTO_INCREMENT,
  `UserRole` varchar(150) CHARACTER SET latin1 NOT NULL,
  `Role` int(11) NOT NULL,
  `Description` text NOT NULL,
  `Status` varchar(150) NOT NULL DEFAULT 'Active',
  `UniqueId` varchar(200) NOT NULL,
  `DateAdded` timestamp NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`URid`),
  UNIQUE KEY `UniqueId` (`UniqueId`),
  KEY `Role` (`Role`),
  KEY `UserRole` (`UserRole`) USING BTREE,
  CONSTRAINT `userroles_ibfk_1` FOREIGN KEY (`Role`) REFERENCES `roles` (`Rid`)
) ENGINE=InnoDB AUTO_INCREMENT=482 DEFAULT CHARSET=utf8mb4;

-- Dumping data for table hospital.userroles: ~94 rows (approximately)
/*!40000 ALTER TABLE `userroles` DISABLE KEYS */;
INSERT INTO `userroles` (`URid`, `UserRole`, `Role`, `Description`, `Status`, `UniqueId`, `DateAdded`) VALUES
	(307, 'Admin', 36, 'System Administrator Level One', 'Active', '36 Admin', '2020-04-13 22:38:15'),
	(308, 'Admin', 37, 'System Administrator Level One', 'Active', '37 Admin', '2020-04-13 22:39:12'),
	(311, 'Admin', 38, 'System Administrator Level One', 'Active', '38 Admin', '2020-04-13 22:57:39'),
	(312, 'Admin', 39, 'System Administrator Level One', 'Active', '39 Admin', '2020-04-13 22:57:39'),
	(313, 'Admin', 40, 'System Administrator Level One', 'Active', '40 Admin', '2020-04-13 22:57:39'),
	(314, 'Medic', 36, 'Updates Patient Records', 'Active', '36 Medic', '2020-04-13 22:58:22'),
	(315, 'Medic', 37, 'Updates Patient Records', 'Active', '37 Medic', '2020-04-13 22:58:22'),
	(316, 'Medic', 38, 'Updates Patient Records', 'Active', '38 Medic', '2020-04-13 22:58:22'),
	(317, 'Medic', 39, 'Updates Patient Records', 'Active', '39 Medic', '2020-04-13 22:58:22'),
	(318, 'Medic', 40, 'Updates Patient Records', 'Active', '40 Medic', '2020-04-13 22:58:22'),
	(319, 'Medic', 41, 'Updates Patient Records', 'Active', '41 Medic', '2020-04-13 23:12:47'),
	(320, 'Medic', 42, 'Updates Patient Records', 'Active', '42 Medic', '2020-04-13 23:12:47'),
	(321, 'Medic', 44, 'Updates Patient Records', 'Active', '44 Medic', '2020-04-13 23:12:47'),
	(327, 'Medic', 45, 'Updates Patient Records', 'Active', '45 Medic', '2020-04-13 23:12:48'),
	(328, 'Medic', 47, 'Updates Patient Records', 'Active', '47 Medic', '2020-04-13 23:12:48'),
	(329, 'Medic', 48, 'Updates Patient Records', 'Active', '48 Medic', '2020-04-13 23:12:48'),
	(330, 'Medic', 49, 'Updates Patient Records', 'Active', '49 Medic', '2020-04-13 23:12:48'),
	(331, 'Medic', 50, 'Updates Patient Records', 'Active', '50 Medic', '2020-04-13 23:12:48'),
	(332, 'Medic', 51, 'Updates Patient Records', 'Active', '51 Medic', '2020-04-13 23:12:48'),
	(333, 'Medic', 53, 'Updates Patient Records', 'Active', '53 Medic', '2020-04-13 23:12:48'),
	(334, 'Medic', 54, 'Updates Patient Records', 'Active', '54 Medic', '2020-04-13 23:12:48'),
	(335, 'Medic', 55, 'Updates Patient Records', 'Active', '55 Medic', '2020-04-13 23:12:48'),
	(336, 'Medic', 56, 'Updates Patient Records', 'Active', '56 Medic', '2020-04-13 23:12:48'),
	(337, 'Medic', 61, 'Updates Patient Records', 'Active', '61 Medic', '2020-04-13 23:12:48'),
	(338, 'Medic', 58, 'Updates Patient Records', 'Active', '58 Medic', '2020-04-13 23:12:48'),
	(339, 'Medic', 59, 'Updates Patient Records', 'Active', '59 Medic', '2020-04-13 23:12:48'),
	(343, 'Medic', 64, 'Updates Patient Records', 'Active', '64 Medic', '2020-04-14 09:13:44'),
	(344, 'Medic', 68, 'Updates Patient Records', 'Active', '68 Medic', '2020-04-14 09:13:44'),
	(345, 'Medic', 91, 'Updates Patient Records', 'Active', '91 Medic', '2020-04-14 09:13:44'),
	(361, 'Medic', 62, 'Updates Patient Records', 'Active', '62 Medic', '2020-04-14 09:13:45'),
	(362, 'Medic', 63, 'Updates Patient Records', 'Active', '63 Medic', '2020-04-14 09:13:45'),
	(363, 'Medic', 65, 'Updates Patient Records', 'Active', '65 Medic', '2020-04-14 09:13:45'),
	(364, 'Medic', 66, 'Updates Patient Records', 'Active', '66 Medic', '2020-04-14 09:13:45'),
	(365, 'Medic', 70, 'Updates Patient Records', 'Active', '70 Medic', '2020-04-14 09:13:45'),
	(366, 'Medic', 73, 'Updates Patient Records', 'Active', '73 Medic', '2020-04-14 09:13:45'),
	(367, 'Medic', 75, 'Updates Patient Records', 'Active', '75 Medic', '2020-04-14 09:13:46'),
	(368, 'Medic', 76, 'Updates Patient Records', 'Active', '76 Medic', '2020-04-14 09:13:46'),
	(369, 'Medic', 77, 'Updates Patient Records', 'Active', '77 Medic', '2020-04-14 09:13:46'),
	(370, 'Medic', 86, 'Updates Patient Records', 'Active', '86 Medic', '2020-04-14 09:13:46'),
	(371, 'Medic', 87, 'Updates Patient Records', 'Active', '87 Medic', '2020-04-14 09:13:46'),
	(372, 'Medic', 88, 'Updates Patient Records', 'Active', '88 Medic', '2020-04-14 09:13:46'),
	(373, 'Medic', 89, 'Updates Patient Records', 'Active', '89 Medic', '2020-04-14 09:13:46'),
	(374, 'Medic', 90, 'Updates Patient Records', 'Active', '90 Medic', '2020-04-14 09:13:46'),
	(375, 'Medic', 92, 'Updates Patient Records', 'Active', '92 Medic', '2020-04-14 09:13:46'),
	(377, 'Medic', 79, 'Updates Patient Records', 'Active', '79 Medic', '2020-04-14 09:13:46'),
	(378, 'Medic', 80, 'Updates Patient Records', 'Active', '80 Medic', '2020-04-14 09:13:46'),
	(379, 'Medic', 81, 'Updates Patient Records', 'Active', '81 Medic', '2020-04-14 09:13:46'),
	(380, 'Medic', 82, 'Updates Patient Records', 'Active', '82 Medic', '2020-04-14 09:13:46'),
	(381, 'Medic', 83, 'Updates Patient Records', 'Active', '83 Medic', '2020-04-14 09:13:46'),
	(382, 'Medic', 85, 'Updates Patient Records', 'Active', '85 Medic', '2020-04-14 09:13:46'),
	(385, 'Admin', 41, 'System Administrator Level One', 'Active', '41 Admin', '2020-04-14 09:14:11'),
	(386, 'Admin', 42, 'System Administrator Level One', 'Active', '42 Admin', '2020-04-14 09:14:12'),
	(387, 'Admin', 44, 'System Administrator Level One', 'Active', '44 Admin', '2020-04-14 09:14:12'),
	(388, 'Admin', 64, 'System Administrator Level One', 'Active', '64 Admin', '2020-04-14 09:14:12'),
	(389, 'Admin', 68, 'System Administrator Level One', 'Active', '68 Admin', '2020-04-14 09:14:12'),
	(390, 'Admin', 91, 'System Administrator Level One', 'Active', '91 Admin', '2020-04-14 09:14:12'),
	(396, 'Admin', 45, 'System Administrator Level One', 'Active', '45 Admin', '2020-04-14 09:14:13'),
	(397, 'Admin', 47, 'System Administrator Level One', 'Active', '47 Admin', '2020-04-14 09:14:13'),
	(398, 'Admin', 48, 'System Administrator Level One', 'Active', '48 Admin', '2020-04-14 09:14:13'),
	(399, 'Admin', 49, 'System Administrator Level One', 'Active', '49 Admin', '2020-04-14 09:14:13'),
	(400, 'Admin', 50, 'System Administrator Level One', 'Active', '50 Admin', '2020-04-14 09:14:13'),
	(401, 'Admin', 51, 'System Administrator Level One', 'Active', '51 Admin', '2020-04-14 09:14:13'),
	(402, 'Admin', 53, 'System Administrator Level One', 'Active', '53 Admin', '2020-04-14 09:14:13'),
	(403, 'Admin', 54, 'System Administrator Level One', 'Active', '54 Admin', '2020-04-14 09:14:13'),
	(404, 'Admin', 55, 'System Administrator Level One', 'Active', '55 Admin', '2020-04-14 09:14:13'),
	(405, 'Admin', 56, 'System Administrator Level One', 'Active', '56 Admin', '2020-04-14 09:14:13'),
	(406, 'Admin', 62, 'System Administrator Level One', 'Active', '62 Admin', '2020-04-14 09:14:13'),
	(407, 'Admin', 63, 'System Administrator Level One', 'Active', '63 Admin', '2020-04-14 09:14:13'),
	(408, 'Admin', 65, 'System Administrator Level One', 'Active', '65 Admin', '2020-04-14 09:14:13'),
	(409, 'Admin', 66, 'System Administrator Level One', 'Active', '66 Admin', '2020-04-14 09:14:13'),
	(410, 'Admin', 70, 'System Administrator Level One', 'Active', '70 Admin', '2020-04-14 09:14:13'),
	(411, 'Admin', 73, 'System Administrator Level One', 'Active', '73 Admin', '2020-04-14 09:14:13'),
	(412, 'Admin', 75, 'System Administrator Level One', 'Active', '75 Admin', '2020-04-14 09:14:13'),
	(413, 'Admin', 76, 'System Administrator Level One', 'Active', '76 Admin', '2020-04-14 09:14:13'),
	(414, 'Admin', 77, 'System Administrator Level One', 'Active', '77 Admin', '2020-04-14 09:14:13'),
	(415, 'Admin', 86, 'System Administrator Level One', 'Active', '86 Admin', '2020-04-14 09:14:13'),
	(416, 'Admin', 87, 'System Administrator Level One', 'Active', '87 Admin', '2020-04-14 09:14:13'),
	(417, 'Admin', 88, 'System Administrator Level One', 'Active', '88 Admin', '2020-04-14 09:14:13'),
	(418, 'Admin', 89, 'System Administrator Level One', 'Active', '89 Admin', '2020-04-14 09:14:13'),
	(419, 'Admin', 90, 'System Administrator Level One', 'Active', '90 Admin', '2020-04-14 09:14:13'),
	(420, 'Admin', 92, 'System Administrator Level One', 'Active', '92 Admin', '2020-04-14 09:14:13'),
	(421, 'Admin', 61, 'System Administrator Level One', 'Active', '61 Admin', '2020-04-14 09:14:13'),
	(422, 'Admin', 79, 'System Administrator Level One', 'Active', '79 Admin', '2020-04-14 09:14:13'),
	(423, 'Admin', 80, 'System Administrator Level One', 'Active', '80 Admin', '2020-04-14 09:14:13'),
	(424, 'Admin', 81, 'System Administrator Level One', 'Active', '81 Admin', '2020-04-14 09:14:14'),
	(425, 'Admin', 82, 'System Administrator Level One', 'Active', '82 Admin', '2020-04-14 09:14:14'),
	(426, 'Admin', 83, 'System Administrator Level One', 'Active', '83 Admin', '2020-04-14 09:14:14'),
	(427, 'Admin', 85, 'System Administrator Level One', 'Active', '85 Admin', '2020-04-14 09:14:14'),
	(428, 'Admin', 58, 'System Administrator Level One', 'Active', '58 Admin', '2020-04-14 09:14:14'),
	(429, 'Admin', 59, 'System Administrator Level One', 'Active', '59 Admin', '2020-04-14 09:14:14'),
	(430, 'Admin', 71, 'System Administrator Level One', 'Active', '71 Admin', '2020-04-14 09:14:14'),
	(431, 'Admin', 74, 'System Administrator Level One', 'Active', '74 Admin', '2020-04-14 09:14:14'),
	(432, 'Admin', 78, 'System Administrator Level One', 'Active', '78 Admin', '2020-04-14 09:14:14'),
	(481, 'Medic', 93, 'Updates Patient Records', 'Active', '93 Medic', '2020-09-20 22:45:50');
/*!40000 ALTER TABLE `userroles` ENABLE KEYS */;


-- Dumping structure for table hospital.users
DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Fullname` varchar(200) NOT NULL,
  `Username` varchar(100) CHARACTER SET latin1 COLLATE latin1_general_cs NOT NULL,
  `Phone` varchar(13) NOT NULL,
  `IDno` bigint(8) NOT NULL,
  `Email` varchar(50) NOT NULL,
  `Userrole` varchar(50) NOT NULL,
  `Password` varchar(200) CHARACTER SET latin1 COLLATE latin1_general_cs NOT NULL,
  `Status` set('Active','Disabled','Reset','Generated','Changed') NOT NULL DEFAULT 'Generated',
  `StartTime` varchar(100) NOT NULL DEFAULT '0',
  `Online` set('1','0') NOT NULL DEFAULT '0',
  `dateadded` timestamp NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id`),
  UNIQUE KEY `Username` (`Username`),
  KEY `Userrole` (`Userrole`,`Password`),
  CONSTRAINT `users_ibfk_1` FOREIGN KEY (`Userrole`) REFERENCES `userroles` (`UserRole`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;

-- Dumping data for table hospital.users: ~3 rows (approximately)
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`id`, `Fullname`, `Username`, `Phone`, `IDno`, `Email`, `Userrole`, `Password`, `Status`, `StartTime`, `Online`, `dateadded`) VALUES
	(8, 'Username One', 'User1', '+254725025536', 12121212, 'user1@gmail.com', 'Admin', 'J5U5K4N3Q3', 'Active', '0', '0', '2020-02-19 02:46:38'),
	(11, 'Username Two', 'User2', '+254725025536', 12121212, 'user2@gmail.com', 'Medic', 'R4F0W1L8D3', 'Active', '0', '0', '2020-02-19 02:51:06'),
	(13, 'User Three', 'User3', '+234534534523', 1243434, 'user@gmail.com', 'Medic', 'sam3', 'Active', '0', '0', '2020-03-29 15:18:48');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
