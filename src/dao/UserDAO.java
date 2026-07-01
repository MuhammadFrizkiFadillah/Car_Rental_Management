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
import model.User;

public class UserDAO {

    private final Connection conn;

    public UserDAO() {
        conn = Koneksi.getConnection();
    }

    public boolean simpan(User user) {

        String sql = "INSERT INTO users "
                + "(kode_user, nama_lengkap, "
                + "username, password, is_deleted) "
                + "VALUES (?, ?, ?, ?, ?)";

        try {

            PreparedStatement pst =
                    conn.prepareStatement(sql);

            pst.setString(1,
                    user.getKodeUser());

            pst.setString(2,
                    user.getNamaLengkap());

            pst.setString(3,
                    user.getUsername());

            pst.setString(4,
                    user.getPassword());

            pst.setBoolean(5,
                    user.isDeleted());

            int hasil = pst.executeUpdate();

            pst.close();

            return hasil > 0;

        } catch (SQLException e) {

            System.out.println(
                    "Gagal simpan user: "
                    + e.getMessage());

            return false;
        }
    }

    public User login(
            String username,
            String password) {

        String sql =
                "SELECT * FROM users "
                + "WHERE username = ? "
                + "AND password = ? "
                + "AND is_deleted = 0";

        try {

            PreparedStatement pst =
                    conn.prepareStatement(sql);

            pst.setString(1, username);
            pst.setString(2, password);

            ResultSet rs =
                    pst.executeQuery();

            if (rs.next()) {

                User user = new User();

                user.setIdUser(
                        rs.getInt("id_user"));

                user.setKodeUser(
                        rs.getString(
                                "kode_user"));

                user.setNamaLengkap(
                        rs.getString(
                                "nama_lengkap"));

                user.setUsername(
                        rs.getString(
                                "username"));

                user.setPassword(
                        rs.getString(
                                "password"));

                user.setDeleted(
                        rs.getBoolean(
                                "is_deleted"));

                rs.close();
                pst.close();

                return user;
            }

            rs.close();
            pst.close();

        } catch (SQLException e) {

            System.out.println(
                    "Login gagal: "
                    + e.getMessage());
        }

        return null;
    }
}