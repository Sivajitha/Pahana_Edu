package dao;

import model.Admin;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDAO {

    public Admin authenticate(String adminName, String password) throws SQLException {
        String sql = "SELECT * FROM admin WHERE admin_name = ? AND password = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            System.out.println("DAO.authenticate → " + adminName + " / " + password);
            stmt.setString(1, adminName);
            stmt.setString(2, password);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Admin admin = new Admin();
                    admin.setAdminId(rs.getInt("adminid"));
                    admin.setAdminName(rs.getString("admin_name"));
                    admin.setPassword(rs.getString("password"));
                    System.out.println("✅ DAO: admin found → " + admin.getAdminName());
                    return admin;
                } else {
                    System.out.println("❌ DAO: no admin match");
                }
            }
        }
        return null;
    }
}
