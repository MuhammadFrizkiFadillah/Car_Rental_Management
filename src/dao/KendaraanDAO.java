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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Kendaraan;
import model.MobilMPV;
import model.MobilSUV;
import model.MobilSedan;
public class KendaraanDAO {

    private final Connection conn;

    public KendaraanDAO() {
        conn = Koneksi.getConnection();
    }
public boolean simpan(
        Kendaraan kendaraan) {

    String sql =
            "INSERT INTO kendaraan "
            + "(kode_kendaraan, "
            + "merk, model, "
            + "jenis_kendaraan, "
            + "plat_nomor, "
            + "tahun, warna, "
            + "harga_sewa, "
            + "status, "
            + "is_deleted) "
            + "VALUES "
            + "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    try {

        PreparedStatement pst =
                conn.prepareStatement(sql);

        pst.setString(
                1,
                kendaraan.getKodeKendaraan());

        pst.setString(
                2,
                kendaraan.getMerk());

        pst.setString(
                3,
                kendaraan.getModel());

        pst.setString(
                4,
                kendaraan.getJenisKendaraan());

        pst.setString(
                5,
                kendaraan.getPlatNomor());

        pst.setInt(
                6,
                kendaraan.getTahun());

        pst.setString(
                7,
                kendaraan.getWarna());

        pst.setDouble(
                8,
                kendaraan.getHargaSewa());

        pst.setString(
                9,
                kendaraan.getStatus());

        pst.setBoolean(
                10,
                kendaraan.isDeleted());

        int hasil =
                pst.executeUpdate();

        pst.close();

        return hasil > 0;

    } catch (SQLException e) {

        System.out.println(
                "Gagal simpan kendaraan: "
                + e.getMessage());

        return false;
    }
}

public boolean ubah(
        Kendaraan kendaraan) {

    String sql =
            "UPDATE kendaraan SET "
            + "merk = ?, "
            + "model = ?, "
            + "jenis_kendaraan = ?, "
            + "plat_nomor = ?, "
            + "tahun = ?, "
            + "warna = ?, "
            + "harga_sewa = ?, "
            + "status = ? "
            + "WHERE id_kendaraan = ?";

    try {

        PreparedStatement pst =
                conn.prepareStatement(sql);

        pst.setString(
                1,
                kendaraan.getMerk());

        pst.setString(
                2,
                kendaraan.getModel());

        pst.setString(
                3,
                kendaraan.getJenisKendaraan());

        pst.setString(
                4,
                kendaraan.getPlatNomor());

        pst.setInt(
                5,
                kendaraan.getTahun());

        pst.setString(
                6,
                kendaraan.getWarna());

        pst.setDouble(
                7,
                kendaraan.getHargaSewa());

        pst.setString(
                8,
                kendaraan.getStatus());

        pst.setInt(
                9,
                kendaraan.getIdKendaraan());

        int hasil =
                pst.executeUpdate();

        pst.close();

        return hasil > 0;

    } catch (SQLException e) {

        System.out.println(
                "Gagal ubah kendaraan: "
                + e.getMessage());

        return false;
    }
}

public boolean hapus(
        int idKendaraan) {

    String sql =
            "UPDATE kendaraan "
            + "SET is_deleted = 1 "
            + "WHERE id_kendaraan = ?";

    try {

        PreparedStatement pst =
                conn.prepareStatement(sql);

        pst.setInt(
                1,
                idKendaraan);

        int hasil =
                pst.executeUpdate();

        pst.close();

        return hasil > 0;

    } catch (SQLException e) {

        System.out.println(
                "Gagal hapus kendaraan: "
                + e.getMessage());

        return false;
    }
}

public List<Kendaraan> getAll() {

    List<Kendaraan> list =
            new ArrayList<>();

    String sql =
            "SELECT * FROM kendaraan "
            + "WHERE is_deleted = 0 "
            + "ORDER BY id_kendaraan";

    try {

        PreparedStatement pst =
                conn.prepareStatement(sql);

        ResultSet rs =
                pst.executeQuery();

        while (rs.next()) {

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
                    rs.getString(
                            "jenis_kendaraan"));

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

            kendaraan.setDeleted(
                    rs.getBoolean(
                            "is_deleted"));

            list.add(kendaraan);
        }

        rs.close();
        pst.close();

    } catch (SQLException e) {

        System.out.println(
                "Gagal ambil data kendaraan: "
                + e.getMessage());
    }

    return list;
}
public List<Kendaraan> cari(String keyword) {

    List<Kendaraan> list =
            new ArrayList<>();

    String sql =
            "SELECT * FROM kendaraan "
            + "WHERE is_deleted = 0 "
            + "AND ("
            + "kode_kendaraan LIKE ? "
            + "OR merk LIKE ? "
            + "OR model LIKE ? "
            + "OR plat_nomor LIKE ?"
            + ") "
            + "ORDER BY id_kendaraan";

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

            kendaraan.setDeleted(
                    rs.getBoolean(
                            "is_deleted"));

            list.add(kendaraan);
        }

        rs.close();
        pst.close();

    } catch (SQLException e) {

        System.out.println(
                "Gagal cari kendaraan: "
                + e.getMessage());
    }

    return list;
}

public List<Kendaraan> getTersedia() {

    List<Kendaraan> list =
            new ArrayList<>();

    String sql =
            "SELECT * FROM kendaraan "
            + "WHERE status = 'Tersedia' "
            + "AND is_deleted = 0 "
            + "ORDER BY id_kendaraan";

    try {

        PreparedStatement pst =
                conn.prepareStatement(sql);

        ResultSet rs =
                pst.executeQuery();

        while (rs.next()) {

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

            kendaraan.setDeleted(
                    rs.getBoolean(
                            "is_deleted"));

            list.add(kendaraan);
        }

        rs.close();
        pst.close();

    } catch (SQLException e) {

        System.out.println(
                "Gagal ambil kendaraan tersedia: "
                + e.getMessage());
    }

    return list;
}

public boolean ubahStatus(
        int idKendaraan,
        String status) {

    String sql =
            "UPDATE kendaraan "
            + "SET status = ? "
            + "WHERE id_kendaraan = ?";

    try {

        PreparedStatement pst =
                conn.prepareStatement(sql);

        pst.setString(
                1,
                status);

        pst.setInt(
                2,
                idKendaraan);

        int hasil =
                pst.executeUpdate();

        pst.close();

        return hasil > 0;

    } catch (SQLException e) {

        System.out.println(
                "Gagal ubah status: "
                + e.getMessage());

        return false;
    }
}
}
