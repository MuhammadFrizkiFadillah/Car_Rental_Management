/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

/**
 *
 * @author FRIZKI
 */
import config.Koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class KodeGenerator {

    public static String generateKode(
            String tableName,
            String columnName,
            String prefix) {

        Connection conn = Koneksi.getConnection();

        String kodeBaru = prefix + "0001";

        try {

            String sql =
                    "SELECT " + columnName +
                    " FROM " + tableName +
                    " ORDER BY " + columnName +
                    " DESC LIMIT 1";

            PreparedStatement pst =
                    conn.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {

                String kodeLama =
                        rs.getString(columnName);

                String angka =
                        kodeLama.substring(
                                prefix.length());

                int nomor =
                        Integer.parseInt(angka);

                nomor++;

                kodeBaru =
                        String.format(
                                prefix + "%04d",
                                nomor);

            }

            rs.close();
            pst.close();

        } catch (SQLException e) {

            System.out.println(
                    "Gagal generate kode: "
                    + e.getMessage());

        }

        return kodeBaru;
    }
}
