package service;

import dao.CustomerDAO;
import model.Customer;
import java.sql.SQLException;
import java.util.List;

public class CustomerService {
    private final CustomerDAO dao = new CustomerDAO();
    public void create(Customer c) throws SQLException { dao.create(c); }
    public void update(Customer c) throws SQLException { dao.update(c); }
    public void delete(String account) throws SQLException { dao.delete(account); }
    public Customer get(String account) throws SQLException { return dao.findByAccount(account); }
    public List<Customer> list() throws SQLException { return dao.findAll(); }
}
