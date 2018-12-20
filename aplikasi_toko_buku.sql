-- phpMyAdmin SQL Dump
-- version 4.1.12
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 01 Mar 2017 pada 08.23
-- Versi Server: 5.6.16
-- PHP Version: 5.5.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `aplikasi_toko_buku`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `buku`
--

CREATE TABLE IF NOT EXISTS `buku` (
  `id_buku` varchar(100) NOT NULL,
  `judul_buku` varchar(100) NOT NULL,
  `noisbn` varchar(15) NOT NULL,
  `penulis` varchar(100) NOT NULL,
  `penerbit` varchar(100) NOT NULL,
  `tahun_terbit` varchar(5) NOT NULL,
  `stok` int(100) NOT NULL,
  `harga_pokok` int(100) NOT NULL,
  `harga_jual` int(100) NOT NULL,
  `ppn` float NOT NULL,
  `diskon` float NOT NULL,
  PRIMARY KEY (`id_buku`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `buku`
--

INSERT INTO `buku` (`id_buku`, `judul_buku`, `noisbn`, `penulis`, `penerbit`, `tahun_terbit`, `stok`, `harga_pokok`, `harga_jual`, `ppn`, `diskon`) VALUES
('BK1', 'Tuntunan Sholat Lengkap', '2022020202202', 'Nurul', 'Jadid', '2009', 989, 5000, 5025, 1, 0.5),
('BK2', 'Asmaul Husna', '2022020202202', 'Nurul Qodim', 'Nurul Jadid', '2011', 991, 30000, 30000, 0, 0),
('BK3', 'Laskar Al-waly', '12122212121221', 'Abdullah', 'Arifin', '2016', 195, 45000, 48510, 10, 2);

-- --------------------------------------------------------

--
-- Struktur dari tabel `distributor`
--

CREATE TABLE IF NOT EXISTS `distributor` (
  `id_distributor` varchar(100) NOT NULL,
  `nama_distributor` varchar(100) NOT NULL,
  `alamat` varchar(100) NOT NULL,
  `telepon` varchar(15) NOT NULL,
  PRIMARY KEY (`id_distributor`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `distributor`
--

INSERT INTO `distributor` (`id_distributor`, `nama_distributor`, `alamat`, `telepon`) VALUES
('DST1', 'Roby Sugara Tri Handoko', 'Maron Wetan, Probolinggo', '089123123123'),
('DST2', 'Zainul Hasan', 'Genggong, Probolinggo', '082222022022'),
('DST3', 'Bayu Rizkiansyah', 'Pajarakan, Probolinggo', '0831022102112'),
('DST4', 'Royful Ananda', 'Sentul, Gading, Probolinggo', '0853130324345');

-- --------------------------------------------------------

--
-- Struktur dari tabel `karyawan`
--

CREATE TABLE IF NOT EXISTS `karyawan` (
  `id_karyawan` varchar(100) NOT NULL,
  `nama_karyawan` varchar(100) NOT NULL,
  `alamat` varchar(100) NOT NULL,
  `telepon` varchar(15) NOT NULL,
  `status` varchar(15) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `akses` varchar(15) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `karyawan`
--

INSERT INTO `karyawan` (`id_karyawan`, `nama_karyawan`, `alamat`, `telepon`, `status`, `username`, `password`, `akses`) VALUES
('KRY1', 'Achmad Khoiri', 'Kandangjati Wetan, Kraksaan, Probolinggo', '085234567891', '-', 'admin', 'admin', 'Admin'),
('KRY3', 'Siti Masruroh', 'Paiton, Probolinggo', '085203042033', '-', 'ruroh', '123', 'Karyawan'),
('KRY2', 'Wahyudi', 'Sumberan, Besuk, Probolinggo', '089687358039', '-', 'wahyudi', '12345', 'Karyawan');

-- --------------------------------------------------------

--
-- Struktur dari tabel `kodeoto`
--

CREATE TABLE IF NOT EXISTS `kodeoto` (
  `kode` int(11) NOT NULL AUTO_INCREMENT,
  `id` varchar(100) NOT NULL,
  PRIMARY KEY (`kode`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=61 ;

--
-- Dumping data untuk tabel `kodeoto`
--

INSERT INTO `kodeoto` (`kode`, `id`) VALUES
(1, 'KRY13'),
(2, 'KRY2'),
(3, 'KRY4'),
(4, 'KRY2'),
(5, 'KRY11'),
(6, 'KRY16'),
(7, 'KRY22'),
(8, 'KRY11'),
(9, 'PJDJTTMR037'),
(10, 'PJDJTTMR046'),
(11, 'PJDJTTMR056'),
(12, 'PJDJTTMR067'),
(13, 'PJDJTTMR079'),
(14, 'KRY92'),
(15, 'PJDJTTMR0106'),
(16, 'PJDJTTMR0121'),
(17, 'PJDJTTMR0137'),
(18, 'PJDJTTMR0154'),
(19, 'PJDJTTMR0172'),
(20, 'PJDJTTMR0191'),
(21, 'PJDJTTMR0211'),
(22, 'PJDJTTMR0232'),
(23, 'PJDJTTMR0254'),
(24, 'PJDJTTMR0277'),
(25, 'PJDJTTMR0301'),
(26, 'PJDJTTMR0326'),
(27, 'PJDJTTMR0352'),
(28, 'PJDJTTMR0379'),
(29, 'PJDJTTMR0407'),
(30, 'PJDJTTMR0436'),
(31, 'PJDJTTMR0466'),
(32, 'PJDJTTMR0497'),
(33, 'PJDJTTMR0529'),
(34, 'PJDJTTMR0562'),
(35, 'PJDJTTMR0596'),
(36, 'PJDJTTMR0631'),
(37, 'PJDJTTMR0667'),
(38, 'PJDJTTMR0704'),
(39, 'PJDJTTMR0742'),
(40, 'PJDJTTMR0781'),
(41, 'PJDJTTMR0821'),
(42, 'PJDJTTMR0862'),
(43, 'PJDJTTMR0904'),
(44, 'PJDJTTMR0947'),
(45, 'PJDJTTMR0991'),
(46, 'PJDJTTMR01036'),
(47, 'PJDJTTMR01082'),
(48, 'PJDJTTMR01129'),
(49, 'PJDJTTMR01177'),
(50, 'PJDJTTMR01226'),
(51, 'PJDJTTMR01276'),
(52, 'PJDJTTMR01327'),
(53, 'PJDJTTMR01379'),
(54, 'PJDJTTMR01432'),
(55, 'PJDJTTMR01486'),
(56, 'PJDJTTMR01541'),
(57, 'KRY1597'),
(58, 'KRY159'),
(59, 'KRY1595'),
(60, 'KRY1771');

-- --------------------------------------------------------

--
-- Struktur dari tabel `pasok`
--

CREATE TABLE IF NOT EXISTS `pasok` (
  `id_pasok` varchar(100) NOT NULL,
  `nama_distributor` varchar(100) NOT NULL,
  `judul_buku` varchar(100) NOT NULL,
  `jumlah` varchar(10) NOT NULL,
  `tanggal_beli` varchar(15) NOT NULL,
  PRIMARY KEY (`id_pasok`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `pasok`
--

INSERT INTO `pasok` (`id_pasok`, `nama_distributor`, `judul_buku`, `jumlah`, `tanggal_beli`) VALUES
('PS1', 'Roby Sugara Tri Handoko', 'Asmaul Husna', '100', '21-01-2017'),
('PS2', 'Zainul Hasan', 'Laskar Al-Waly', '200', '12-02-2017');

-- --------------------------------------------------------

--
-- Struktur dari tabel `penjualan`
--

CREATE TABLE IF NOT EXISTS `penjualan` (
  `id_penjualan` varchar(100) NOT NULL,
  `judul_buku` varchar(100) NOT NULL,
  `nama_karyawan` char(100) NOT NULL,
  `jumlah` varchar(10) NOT NULL,
  `total` varchar(15) NOT NULL,
  `tanggal` varchar(15) NOT NULL,
  PRIMARY KEY (`id_penjualan`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `penjualan`
--

INSERT INTO `penjualan` (`id_penjualan`, `judul_buku`, `nama_karyawan`, `jumlah`, `total`, `tanggal`) VALUES
('PJDJTTMR01177', 'Laskar Al-waly', 'Achmad Khoiri', '5', '250000', '01-03-2017'),
('PJDJTTMR01226', 'Asmaul Husna', 'Achmad Khoiri', '5', '175000', '01-03-2017'),
('PJDJTTMR01276', 'Tuntunan Sholat Lengkap', 'Achmad Khoiri', '1', '10000', '01-03-2017'),
('PJDJTTMR013', 'Laskar Al-waly', 'Achmad Khoiri', '4', '200000', '01-03-2017'),
('PJDJTTMR01327', 'Laskar Al-waly', 'Achmad Khoiri', '4', '200000', '01-03-2017'),
('PJDJTTMR01486', 'Tuntunan Sholat Lengkap', 'Achmad Khoiri', '4', '40000', '01-03-2017'),
('PJDJTTMR01541', 'Laskar Al-waly', 'Achmad Khoiri', '5', '250000', '01-03-2017');

-- --------------------------------------------------------

--
-- Struktur dari tabel `rincian`
--

CREATE TABLE IF NOT EXISTS `rincian` (
  `id_penjualan` varchar(100) NOT NULL,
  `nama_karyawan` varchar(100) NOT NULL,
  `id_buku` varchar(50) NOT NULL,
  `judul_buku` varchar(100) NOT NULL,
  `noisbn` varchar(15) NOT NULL,
  `penerbit` varchar(100) NOT NULL,
  `tahun_terbit` int(5) NOT NULL,
  `harga` float NOT NULL,
  `jumlah` int(11) NOT NULL,
  `stok` int(11) NOT NULL,
  `tanggal` varchar(15) NOT NULL,
  PRIMARY KEY (`id_buku`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
