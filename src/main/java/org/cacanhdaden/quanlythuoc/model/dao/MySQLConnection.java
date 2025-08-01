package org.cacanhdaden.quanlythuoc.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/QUANLYDONTHUOC";
    private static final String USER = "root";
    private static final String PASSWORD = "012345678";

    // Trả về Connection mỗi khi gọi
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Đóng kết nối (option)
    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

