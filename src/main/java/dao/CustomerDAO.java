package dao;

import model.Customer;
import util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {

    public void create(Customer c) throws SQLException {
        String sql = "INSERT INTO customers(account_number,name,address,telephone,units_consumed) VALUES (?,?,?,?,?)";
        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, c.getAccountNumber());
            ps.setString(2, c.getName());
            ps.setString(3, c.getAddress());
            ps.setString(4, c.getTelephone());
            ps.setInt(5, c.getUnitsConsumed());
            ps.executeUpdate();
        }
    }

    public void update(Customer c) throws SQLException {
        String sql = "UPDATE customers SET name=?, address=?, telephone=?, units_consumed=? WHERE account_number=?";
        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, c.getName());
            ps.setString(2, c.getAddress());
            ps.setString(3, c.getTelephone());
            ps.setInt(4, c.getUnitsConsumed());
            ps.setString(5, c.getAccountNumber());
            ps.executeUpdate();
        }
    }

    public void delete(String accountNumber) throws SQLException {
        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("DELETE FROM customers WHERE account_number=?")) {
            ps.setString(1, accountNumber);
            ps.executeUpdate();
        }
    }

    public Customer findByAccount(String accountNumber) throws SQLException {
        String sql = "SELECT * FROM customers WHERE account_number=?";
        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, accountNumber);
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    Customer c = new Customer();
                    c.setId(rs.getInt("id"));
                    c.setAccountNumber(rs.getString("account_number"));
                    c.setName(rs.getString("name"));
                    c.setAddress(rs.getString("address"));
                    c.setTelephone(rs.getString("telephone"));
                    c.setUnitsConsumed(rs.getInt("units_consumed"));
                    return c;
                }
            }
        }
        return null;
    }

    public List<Customer> findAll() throws SQLException {
        List<Customer> list = new ArrayList<>();
        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM customers ORDER BY id DESC");
            ResultSet rs = ps.executeQuery()){
            while(rs.next()){
                Customer c = new Customer();
                c.setId(rs.getInt("id"));
                c.setAccountNumber(rs.getString("account_number"));
                c.setName(rs.getString("name"));
                c.setAddress(rs.getString("address"));
                c.setTelephone(rs.getString("telephone"));
                c.setUnitsConsumed(rs.getInt("units_consumed"));
                list.add(c);
            }
        } catch (SQLException e){
            throw e;
        }
        return list;
    }
}
