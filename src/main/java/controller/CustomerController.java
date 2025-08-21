package controller;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Customer;
import service.CustomerService;

import java.io.IOException;
import java.sql.SQLException;

public class CustomerController extends HttpServlet {
    private final CustomerService service = new CustomerService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        try {
            if ("new".equals(action)) {
                req.getRequestDispatcher("/customerForm.jsp").forward(req, resp);
            } else if ("edit".equals(action)) {
                String account = req.getParameter("account");
                Customer c = service.get(account);
                req.setAttribute("customer", c);
                req.getRequestDispatcher("/customerForm.jsp").forward(req, resp);
            } else if ("delete".equals(action)) {
                service.delete(req.getParameter("account"));
                resp.sendRedirect(req.getContextPath()+"/customers");
            } else {
                req.setAttribute("customers", service.list());
                req.getRequestDispatcher("/customers.jsp").forward(req, resp);
            }
        } catch (SQLException e){
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Customer c = new Customer();
        c.setAccountNumber(req.getParameter("accountNumber"));
        c.setName(req.getParameter("name"));
        c.setAddress(req.getParameter("address"));
        c.setTelephone(req.getParameter("telephone"));
        c.setUnitsConsumed(Integer.parseInt(req.getParameter("unitsConsumed")));
        try {
            String mode = req.getParameter("mode");
            if ("update".equals(mode)) {
                service.update(c);
            } else {
                service.create(c);
            }
            resp.sendRedirect(req.getContextPath()+"/customers");
        } catch (SQLException e){
            throw new ServletException(e);
        }
    }
}
