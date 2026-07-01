/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author FRIZKI
 */
import config.Koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Pengembalian;
import model.Penyewa;
import model.Penyewaan;
import model.Kendaraan;
import model.MobilSUV;
import model.MobilMPV;
import model.MobilSedan;
public class PengembalianDAO {

    private final Connection conn;

    public PengembalianDAO() {
        conn = Koneksi.getConnection();
    }

    public boolean simpan(
        Pengembalian pengembalian) {

    String sql =
            "INSERT INTO pengembalian ("
            + "kode_pengembalian, "
            + "id_sewa, "
            + "tanggal_kembali, "
            + "hari_terlambat, "
            + "total_denda, "
            + "is_deleted"
            + ") VALUES "
            + "(?, ?, ?, ?, ?, ?)";

    try {

        PreparedStatement pst =
                conn.prepareStatement(sql);

        pst.setString(
                1,
                pengembalian
                        .getKodePengembalian());

        pst.setInt(
                2,
                pengembalian
                        .getPenyewaan()
                        .getIdSewa());

        pst.setDate(
                3,
                java.sql.Date.valueOf(
                        pengembalian
                                .getTanggalKembali()));

        pst.setInt(
                4,
                pengembalian
                        .getHariTerlambat());

        pst.setDouble(
                5,
                pengembalian
                        .getTotalDenda());

        pst.setBoolean(
                6,
                pengembalian
                        .isDeleted());

        int hasil =
                pst.executeUpdate();

        pst.close();

        // Ubah status kendaraan
        KendaraanDAO kendaraanDAO =
                new KendaraanDAO();

kendaraanDAO.ubahStatus(
        pengembalian
                .getPenyewaan()
                .getKendaraan()
                .getIdKendaraan(),

        "Tersedia");

        return hasil > 0;

    } catch (SQLException e) {

        System.out.println(
                "Gagal simpan pengembalian: "
                + e.getMessage());

        return false;
    }
}
    
    public List<Pengembalian> getAll() {

    List<Pengembalian> list =
            new ArrayList<>();

    String sql =
            "SELECT * "
            + "FROM pengembalian pg "
            + "JOIN penyewaan s "
            + "ON pg.id_sewa = s.id_sewa "
            + "JOIN penyewa p "
            + "ON s.id_penyewa = p.id_penyewa "
            + "JOIN kendaraan k "
            + "ON s.id_kendaraan = k.id_kendaraan "
            + "WHERE pg.is_deleted = 0 "
            + "ORDER BY pg.id_pengembalian";

    try {

        PreparedStatement pst =
                conn.prepareStatement(sql);

        ResultSet rs =
                pst.executeQuery();

        while (rs.next()) {

            // ===================
            // Penyewa
            // ===================

            Penyewa penyewa =
                    new Penyewa();

            penyewa.setIdPenyewa(
                    rs.getInt(
                            "id_penyewa"));

            penyewa.setKodePenyewa(
                    rs.getString(
                            "kode_penyewa"));

            penyewa.setNama(
                    rs.getString(
                            "nama"));

            penyewa.setAlamat(
                    rs.getString(
                            "alamat"));

            penyewa.setNoHp(
                    rs.getString(
                            "no_hp"));

            // ===================
            // Kendaraan
            // ===================

            String jenis =
                    rs.getString(
                            "jenis_kendaraan");

            Kendaraan kendaraan;

            switch (jenis) {

                case "SUV":
                    kendaraan =
                            new MobilSUV();
                    break;

                case "MPV":
                    kendaraan =
                            new MobilMPV();
                    break;

                case "SEDAN":
                    kendaraan =
                            new MobilSedan();
                    break;

                default:
                    continue;
            }

            kendaraan.setIdKendaraan(
                    rs.getInt(
                            "id_kendaraan"));

            kendaraan.setKodeKendaraan(
                    rs.getString(
                            "kode_kendaraan"));

            kendaraan.setMerk(
                    rs.getString(
                            "merk"));

            kendaraan.setModel(
                    rs.getString(
                            "model"));

            kendaraan.setJenisKendaraan(
                    jenis);

            kendaraan.setPlatNomor(
                    rs.getString(
                            "plat_nomor"));

            kendaraan.setTahun(
                    rs.getInt(
                            "tahun"));

            kendaraan.setWarna(
                    rs.getString(
                            "warna"));

            kendaraan.setHargaSewa(
                    rs.getDouble(
                            "harga_sewa"));

            kendaraan.setStatus(
                    rs.getString(
                            "status"));

            // ===================
            // Penyewaan
            // ===================

            Penyewaan sewa =
                    new Penyewaan();

            sewa.setIdSewa(
                    rs.getInt(
                            "id_sewa"));

            sewa.setKodeSewa(
                    rs.getString(
                            "kode_sewa"));

            sewa.setPenyewa(
                    penyewa);

            sewa.setKendaraan(
                    kendaraan);

            sewa.setTanggalSewa(
                    rs.getDate(
                            "tanggal_sewa")
                            .toLocalDate());

            sewa.setLamaSewa(
                    rs.getInt(
                            "lama_sewa"));

            sewa.setTanggalJatuhTempo(
                    rs.getDate(
                            "tanggal_jatuh_tempo")
                            .toLocalDate());

            sewa.setHargaSewaHarian(
                    rs.getDouble(
                            "harga_sewa_harian"));

            sewa.setTotalBayar(
                    rs.getDouble(
                            "total_bayar"));

            // ===================
            // Pengembalian
            // ===================

            Pengembalian pengembalian =
                    new Pengembalian();

            pengembalian.setIdPengembalian(
                    rs.getInt(
                            "id_pengembalian"));

            pengembalian.setKodePengembalian(
                    rs.getString(
                            "kode_pengembalian"));

            pengembalian.setPenyewaan(
                    sewa);

            pengembalian.setTanggalKembali(
                    rs.getDate(
                            "tanggal_kembali")
                            .toLocalDate());

            pengembalian.setHariTerlambat(
                    rs.getInt(
                            "hari_terlambat"));

            pengembalian.setTotalDenda(
                    rs.getDouble(
                            "total_denda"));

            pengembalian.setDeleted(
                    rs.getBoolean(
                            "is_deleted"));

            list.add(pengembalian);
        }

        rs.close();
        pst.close();

    } catch (SQLException e) {

        System.out.println(
                "Gagal ambil pengembalian: "
                + e.getMessage());
    }

    return list;
}
    
    public List<Pengembalian> cari(
        String keyword) {

    List<Pengembalian> list =
            new ArrayList<>();

    String sql =
            "SELECT * "
            + "FROM pengembalian pg "
            + "JOIN penyewaan s "
            + "ON pg.id_sewa = s.id_sewa "
            + "JOIN penyewa p "
            + "ON s.id_penyewa = p.id_penyewa "
            + "JOIN kendaraan k "
            + "ON s.id_kendaraan = k.id_kendaraan "
            + "WHERE pg.is_deleted = 0 "
            + "AND ("
            + "pg.kode_pengembalian LIKE ? "
            + "OR s.kode_sewa LIKE ? "
            + "OR p.nama LIKE ? "
            + "OR k.merk LIKE ? "
            + "OR k.model LIKE ?"
            + ") "
            + "ORDER BY pg.id_pengembalian";

    try {

        PreparedStatement pst =
                conn.prepareStatement(sql);

        String cari =
                "%" + keyword + "%";

        pst.setString(1, cari);
        pst.setString(2, cari);
        pst.setString(3, cari);
        pst.setString(4, cari);
        pst.setString(5, cari);

        ResultSet rs =
                pst.executeQuery();

        while (rs.next()) {

            // ===================
            // Penyewa
            // ===================

            Penyewa penyewa =
                    new Penyewa();

            penyewa.setIdPenyewa(
                    rs.getInt("id_penyewa"));

            penyewa.setKodePenyewa(
                    rs.getString(
                            "kode_penyewa"));

            penyewa.setNama(
                    rs.getString("nama"));

            penyewa.setAlamat(
                    rs.getString("alamat"));

            penyewa.setNoHp(
                    rs.getString("no_hp"));

            // ===================
            // Kendaraan
            // ===================

            String jenis =
                    rs.getString(
                            "jenis_kendaraan");

            Kendaraan kendaraan;

            switch (jenis) {

                case "SUV":
                    kendaraan =
                            new MobilSUV();
                    break;

                case "MPV":
                    kendaraan =
                            new MobilMPV();
                    break;

                case "SEDAN":
                    kendaraan =
                            new MobilSedan();
                    break;

                default:
                    continue;
            }

            kendaraan.setIdKendaraan(
                    rs.getInt(
                            "id_kendaraan"));

            kendaraan.setKodeKendaraan(
                    rs.getString(
                            "kode_kendaraan"));

            kendaraan.setMerk(
                    rs.getString("merk"));

            kendaraan.setModel(
                    rs.getString("model"));

            kendaraan.setJenisKendaraan(
                    jenis);

            kendaraan.setPlatNomor(
                    rs.getString(
                            "plat_nomor"));

            kendaraan.setTahun(
                    rs.getInt("tahun"));

            kendaraan.setWarna(
                    rs.getString("warna"));

            kendaraan.setHargaSewa(
                    rs.getDouble(
                            "harga_sewa"));

            kendaraan.setStatus(
                    rs.getString("status"));

            // ===================
            // Penyewaan
            // ===================

            Penyewaan sewa =
                    new Penyewaan();

            sewa.setIdSewa(
                    rs.getInt("id_sewa"));

            sewa.setKodeSewa(
                    rs.getString(
                            "kode_sewa"));

            sewa.setPenyewa(
                    penyewa);

            sewa.setKendaraan(
                    kendaraan);

            sewa.setTanggalSewa(
                    rs.getDate(
                            "tanggal_sewa")
                            .toLocalDate());

            sewa.setLamaSewa(
                    rs.getInt(
                            "lama_sewa"));

            sewa.setTanggalJatuhTempo(
                    rs.getDate(
                            "tanggal_jatuh_tempo")
                            .toLocalDate());

            sewa.setHargaSewaHarian(
                    rs.getDouble(
                            "harga_sewa_harian"));

            sewa.setTotalBayar(
                    rs.getDouble(
                            "total_bayar"));

            // ===================
            // Pengembalian
            // ===================

            Pengembalian pengembalian =
                    new Pengembalian();

            pengembalian.setIdPengembalian(
                    rs.getInt(
                            "id_pengembalian"));

            pengembalian.setKodePengembalian(
                    rs.getString(
                            "kode_pengembalian"));

            pengembalian.setPenyewaan(
                    sewa);

            pengembalian.setTanggalKembali(
                    rs.getDate(
                            "tanggal_kembali")
                            .toLocalDate());

            pengembalian.setHariTerlambat(
                    rs.getInt(
                            "hari_terlambat"));

            pengembalian.setTotalDenda(
                    rs.getDouble(
                            "total_denda"));

            pengembalian.setDeleted(
                    rs.getBoolean(
                            "is_deleted"));

            list.add(
                    pengembalian);
        }

        rs.close();
        pst.close();

    } catch (SQLException e) {

        System.out.println(
                "Gagal cari pengembalian: "
                + e.getMessage());
    }

    return list;
}
}
