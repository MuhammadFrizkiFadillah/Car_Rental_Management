/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author FRIZKI
 */
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Penyewa;
import model.Kendaraan;
import model.MobilSUV;
import model.MobilMPV;
import model.MobilSedan;
import config.Koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.Penyewaan;
import dao.KendaraanDAO;
import java.time.LocalDate;
public class PenyewaanDAO {

    private final Connection conn;

    public PenyewaanDAO() {
        conn = Koneksi.getConnection();
    }
public boolean simpan(
        Penyewaan penyewaan) {

    String sql =
            "INSERT INTO penyewaan ("
            + "kode_sewa, "
            + "id_penyewa, "
            + "id_kendaraan, "
            + "tanggal_sewa, "
            + "lama_sewa, "
            + "tanggal_jatuh_tempo, "
            + "harga_sewa_harian, "
            + "total_bayar, "
            + "is_deleted"
            + ") VALUES "
            + "(?, ?, ?, ?, ?, ?, ?, ?, ?)";

    try {

        PreparedStatement pst =
                conn.prepareStatement(sql);

        pst.setString(
                1,
                penyewaan.getKodeSewa());

        pst.setInt(
                2,
                penyewaan.getPenyewa()
                        .getIdPenyewa());

        pst.setInt(
                3,
                penyewaan.getKendaraan()
                        .getIdKendaraan());

        pst.setDate(
                4,
                java.sql.Date.valueOf(
                        penyewaan.getTanggalSewa()));

        pst.setInt(
                5,
                penyewaan.getLamaSewa());

        pst.setDate(
                6,
                java.sql.Date.valueOf(
                        penyewaan
                                .getTanggalJatuhTempo()));

        pst.setDouble(
                7,
                penyewaan
                        .getHargaSewaHarian());

        pst.setDouble(
                8,
                penyewaan
                        .getTotalBayar());

        pst.setBoolean(
                9,
                penyewaan.isDeleted());

        int hasil =
                pst.executeUpdate();

        pst.close();

        // Update status kendaraan
        KendaraanDAO kendaraanDAO =
                new KendaraanDAO();

        penyewaan.getKendaraan()
                .setStatus("Disewa");

        kendaraanDAO.ubah(
                penyewaan.getKendaraan());

        return hasil > 0;

    } catch (SQLException e) {

        System.out.println(
                "Gagal simpan penyewaan: "
                + e.getMessage());

        return false;
    }
}

public List<Penyewaan> getAll() {

    List<Penyewaan> list =
            new ArrayList<>();

    String sql =
            "SELECT * "
            + "FROM penyewaan s "
            + "JOIN penyewa p "
            + "ON s.id_penyewa = p.id_penyewa "
            + "JOIN kendaraan k "
            + "ON s.id_kendaraan = k.id_kendaraan "
            + "WHERE s.is_deleted = 0 "
            + "ORDER BY s.id_sewa";

    try {

        PreparedStatement pst =
                conn.prepareStatement(sql);

        ResultSet rs =
                pst.executeQuery();

        while (rs.next()) {

            // ===================
            // Buat Objek Penyewa
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
            // Buat Objek Kendaraan
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
            // Buat Objek Penyewaan
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

            sewa.setDeleted(
                    rs.getBoolean(
                            "is_deleted"));

            list.add(sewa);
        }

        rs.close();
        pst.close();

    } catch (SQLException e) {

        System.out.println(
                "Gagal ambil penyewaan: "
                + e.getMessage());
    }

    return list;
}

public List<Penyewaan> cari(
        String keyword) {

    List<Penyewaan> list =
            new ArrayList<>();

    String sql =
            "SELECT * "
            + "FROM penyewaan s "
            + "JOIN penyewa p "
            + "ON s.id_penyewa = p.id_penyewa "
            + "JOIN kendaraan k "
            + "ON s.id_kendaraan = k.id_kendaraan "
            + "WHERE s.is_deleted = 0 "
            + "AND ("
            + "s.kode_sewa LIKE ? "
            + "OR p.nama LIKE ? "
            + "OR k.merk LIKE ? "
            + "OR k.model LIKE ?"
            + ") "
            + "ORDER BY s.id_sewa";

    try {

        PreparedStatement pst =
                conn.prepareStatement(sql);

        String cari =
                "%" + keyword + "%";

        pst.setString(1, cari);
        pst.setString(2, cari);
        pst.setString(3, cari);
        pst.setString(4, cari);

        ResultSet rs =
                pst.executeQuery();

        while (rs.next()) {

            // ===================
            // Objek Penyewa
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
            // Objek Kendaraan
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
            // Objek Penyewaan
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

            sewa.setDeleted(
                    rs.getBoolean(
                            "is_deleted"));

            list.add(sewa);
        }

        rs.close();
        pst.close();

    } catch (SQLException e) {

        System.out.println(
                "Gagal cari penyewaan: "
                + e.getMessage());
    }

    return list;
}
public List<Penyewaan> getBelumKembali() {

    List<Penyewaan> list =
            new ArrayList<>();

String sql =
        "SELECT p.*, py.nama, "
        + "k.id_kendaraan, "
        + "k.kode_kendaraan, "
        + "k.merk, "
        + "k.model, "
        + "k.status, "
        + "k.jenis_kendaraan "
        + "FROM penyewaan p "
        + "JOIN penyewa py "
        + "ON p.id_penyewa = py.id_penyewa "
        + "JOIN kendaraan k "
        + "ON p.id_kendaraan = k.id_kendaraan "
        + "WHERE p.id_sewa NOT IN "
        + "(SELECT id_sewa FROM pengembalian)";

    try {

        PreparedStatement pst =
                conn.prepareStatement(sql);

        ResultSet rs =
                pst.executeQuery();

        while (rs.next()) {

            Penyewaan sewa =
                    new Penyewaan();

            sewa.setIdSewa(
                    rs.getInt(
                            "id_sewa"));

            sewa.setKodeSewa(
                    rs.getString(
                            "kode_sewa"));

            Penyewa penyewa =
                    new Penyewa();

            penyewa.setIdPenyewa(
                    rs.getInt(
                            "id_penyewa"));
            penyewa.setNama(
                    rs.getString(
                            "nama"));
            sewa.setPenyewa(
                    penyewa);
            String jenis =
        rs.getString(
                "jenis_kendaraan");

            Kendaraan kendaraan;

            switch (jenis) {

                case "MobilSUV":

                    kendaraan =
                            new MobilSUV();
                    break;

                case "MobilSedan":

                    kendaraan =
                            new MobilSedan();
                    break;

                default:

                    kendaraan =
                            new MobilMPV();
                    break;
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

            kendaraan.setStatus(
                    rs.getString(
                            "status"));

            sewa.setKendaraan(
                    kendaraan);
            sewa.setTanggalJatuhTempo(
                    rs.getObject(
                            "tanggal_jatuh_tempo",
                            LocalDate.class));

            list.add(sewa);
        }

        rs.close();
        pst.close();

    } catch (SQLException e) {

        System.out.println(
                "Error getBelumKembali : "
                + e.getMessage());
    }

    return list;
}
}
