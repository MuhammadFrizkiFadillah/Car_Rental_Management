/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package config;

/**
 *
 * @author FRIZKI
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Koneksi {

    private static Connection connection;

    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {

                String url =
                        "jdbc:mysql://localhost:3306/rental_mobil";

                String user = "root";
                String password = "";

                connection =
                        DriverManager.getConnection(
                                url,
                                user,
                                password
                        );

                System.out.println(
                        "Koneksi database berhasil!"
                );
            }

        } catch (SQLException e) {

            System.out.println(
                    "Koneksi database gagal: "
                    + e.getMessage()
            );
        }

        return connection;
    }
}
