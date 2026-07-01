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
import model.Penyewa;
public class PenyewaDAO {

    private final Connection conn;

    public PenyewaDAO() {
        conn = Koneksi.getConnection();
    }
public boolean simpan(Penyewa penyewa) {

    String sql = "INSERT INTO penyewa "
            + "(kode_penyewa, nama, alamat, "
            + "no_hp, is_deleted) "
            + "VALUES (?, ?, ?, ?, ?)";

    try {

        PreparedStatement pst =
                conn.prepareStatement(sql);

        pst.setString(1,
                penyewa.getKodePenyewa());

        pst.setString(2,
                penyewa.getNama());

        pst.setString(3,
                penyewa.getAlamat());

        pst.setString(4,
                penyewa.getNoHp());

        pst.setBoolean(5,
                penyewa.isDeleted());

        int hasil = pst.executeUpdate();

        pst.close();

        return hasil > 0;

    } catch (SQLException e) {

        System.out.println(
                "Gagal simpan penyewa: "
                + e.getMessage());

        return false;
    }
}
public boolean ubah(Penyewa penyewa) {

    String sql = "UPDATE penyewa "
            + "SET nama = ?, "
            + "alamat = ?, "
            + "no_hp = ? "
            + "WHERE id_penyewa = ?";

    try {

        PreparedStatement pst =
                conn.prepareStatement(sql);

        pst.setString(1,
                penyewa.getNama());

        pst.setString(2,
                penyewa.getAlamat());

        pst.setString(3,
                penyewa.getNoHp());

        pst.setInt(4,
                penyewa.getIdPenyewa());

        int hasil =
                pst.executeUpdate();

        pst.close();

        return hasil > 0;

    } catch (SQLException e) {

        System.out.println(
                "Gagal ubah penyewa: "
                + e.getMessage());

        return false;
    }
}
public boolean hapus(int idPenyewa) {

    String sql =
            "UPDATE penyewa "
            + "SET is_deleted = 1 "
            + "WHERE id_penyewa = ?";

    try {

        PreparedStatement pst =
                conn.prepareStatement(sql);

        pst.setInt(1, idPenyewa);

        int hasil =
                pst.executeUpdate();

        pst.close();

        return hasil > 0;

    } catch (SQLException e) {

        System.out.println(
                "Gagal hapus penyewa: "
                + e.getMessage());

        return false;
    }
}
public List<Penyewa> getAll() {

    List<Penyewa> list =
            new ArrayList<>();

    String sql =
            "SELECT * "
            + "FROM penyewa "
            + "WHERE is_deleted = 0 "
            + "ORDER BY id_penyewa";

    try {

        PreparedStatement pst =
                conn.prepareStatement(sql);

        ResultSet rs =
                pst.executeQuery();

        while (rs.next()) {

            Penyewa p =
                    new Penyewa();

            p.setIdPenyewa(
                    rs.getInt(
                            "id_penyewa"));

            p.setKodePenyewa(
                    rs.getString(
                            "kode_penyewa"));

            p.setNama(
                    rs.getString(
                            "nama"));

            p.setAlamat(
                    rs.getString(
                            "alamat"));

            p.setNoHp(
                    rs.getString(
                            "no_hp"));

            p.setDeleted(
                    rs.getBoolean(
                            "is_deleted"));

            list.add(p);
        }

        rs.close();
        pst.close();

    } catch (SQLException e) {

        System.out.println(
                "Gagal ambil data: "
                + e.getMessage());
    }

    return list;
}
public List<Penyewa> cari(
        String keyword) {

    List<Penyewa> list =
            new ArrayList<>();

    String sql =
            "SELECT * FROM penyewa "
            + "WHERE is_deleted = 0 "
            + "AND (kode_penyewa LIKE ? "
            + "OR nama LIKE ?)";

    try {

        PreparedStatement pst =
                conn.prepareStatement(sql);

        pst.setString(1,
                "%" + keyword + "%");

        pst.setString(2,
                "%" + keyword + "%");

        ResultSet rs =
                pst.executeQuery();

        while (rs.next()) {

            Penyewa p =
                    new Penyewa();

            p.setIdPenyewa(
                    rs.getInt(
                            "id_penyewa"));

            p.setKodePenyewa(
                    rs.getString(
                            "kode_penyewa"));

            p.setNama(
                    rs.getString(
                            "nama"));

            p.setAlamat(
                    rs.getString(
                            "alamat"));

            p.setNoHp(
                    rs.getString(
                            "no_hp"));

            p.setDeleted(
                    rs.getBoolean(
                            "is_deleted"));

            list.add(p);
        }

        rs.close();
        pst.close();

    } catch (SQLException e) {

        System.out.println(
                "Gagal cari data: "
                + e.getMessage());
    }

    return list;
}
}
