package dao;

import model.Bill;
import util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BillDAO {
    public void save(Bill b) throws SQLException {
        String sql = "INSERT INTO bills(account_number, units, amount, created_at) VALUES (?,?,?,NOW())";
        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, b.getAccountNumber());
            ps.setInt(2, b.getUnits());
            ps.setDouble(3, b.getAmount());
            ps.executeUpdate();
        }
    }

    public List<Bill> findByAccount(String account) throws SQLException {
        String sql = "SELECT * FROM bills WHERE account_number=? ORDER BY id DESC";
        List<Bill> list = new ArrayList<>();
        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, account);
            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    Bill b = new Bill();
                    b.setId(rs.getInt("id"));
                    b.setAccountNumber(rs.getString("account_number"));
                    b.setUnits(rs.getInt("units"));
                    b.setAmount(rs.getDouble("amount"));
                    list.add(b);
                }
            }
        }
        return list;
    }
}
