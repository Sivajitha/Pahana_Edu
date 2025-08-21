package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Bill;
import model.Customer;
import service.BillingService;
import service.CustomerService;
import dao.BillDAO;

import java.io.IOException;
import java.sql.SQLException;

public class BillController extends HttpServlet {

    private final CustomerService customerService = new CustomerService();
    private final BillingService billingService = new BillingService();
    private final BillDAO billDAO = new BillDAO();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String account = req.getParameter("account");
        if(account != null) {
            try {
                Customer customer = customerService.get(account);
                req.setAttribute("customer", customer);
            } catch (SQLException e) {
                throw new ServletException("Error fetching customer data", e);
            }
        }
        req.getRequestDispatcher("/billForm.jsp").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String account = req.getParameter("accountNumber");
        int units = Integer.parseInt(req.getParameter("units"));
        double amount = billingService.calculateAmount(units);
        Bill bill = new Bill();
        bill.setAccountNumber(account);
        bill.setUnits(units);
        bill.setAmount(amount);
        try {
            billDAO.save(bill);
            req.setAttribute("bill", bill);
            req.getRequestDispatcher("/billView.jsp").forward(req, resp);
        } catch (SQLException e) {
            throw new ServletException("Error saving bill", e);
        }
    }
}
