package controller;

import dao.UserDAO;
import model.Admin;
import model.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class UserManagementServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Admin admin = (Admin) session.getAttribute("admin");

        if (admin == null) {
            response.sendRedirect("login.jsp"); // fixed path
            return;
        }

        try {
            UserDAO userDAO = new UserDAO();
            List<User> users = userDAO.getUsersByAdmin(admin.getAdminId());
            request.setAttribute("users", users);
            request.getRequestDispatcher("manageUsers.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error retrieving users");
            request.getRequestDispatcher("adminDashboard.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Admin admin = (Admin) session.getAttribute("admin");

        if (admin == null) {
            response.sendRedirect("login.jsp"); // fixed path
            return;
        }

        String action = request.getParameter("action");

        try {
            UserDAO userDAO = new UserDAO();
            if ("create".equals(action)) {
                String userName = request.getParameter("userName");
                String role = request.getParameter("role");
                String password = request.getParameter("password");
                User newUser = new User(userName, role, password, admin.getAdminId());
                boolean created = userDAO.createUser(newUser);

                if (created) {
                    request.setAttribute("successMessage", "User created successfully");
                } else {
                    request.setAttribute("errorMessage", "Failed to create user");
                }
            } else if ("delete".equals(action)) {
                int userId = Integer.parseInt(request.getParameter("userId"));
                boolean deleted = userDAO.deleteUser(userId);

                if (deleted) {
                    request.setAttribute("successMessage", "User deleted successfully");
                } else {
                    request.setAttribute("errorMessage", "Failed to delete user");
                }
            }

            // Refresh user list
            List<User> users = userDAO.getUsersByAdmin(admin.getAdminId());
            request.setAttribute("users", users);
            request.getRequestDispatcher("manageUsers.jsp").forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Database error occurred");
            request.getRequestDispatcher("manageUsers.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Invalid user ID");
            request.getRequestDispatcher("manageUsers.jsp").forward(request, response);
        }
    }
}