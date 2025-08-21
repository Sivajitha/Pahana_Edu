package controller;

import dao.AdminDAO;
import dao.UserDAO;
import model.Admin;
import model.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String userType = request.getParameter("userType");

        System.out.println("👉 Login attempt: userType=" + userType + ", username=" + username);

        try {
            if ("admin".equals(userType)) {
                AdminDAO adminDAO = new AdminDAO();
                Admin admin = adminDAO.authenticate(username, password);

                if (admin != null) {
                    HttpSession session = request.getSession();
                    session.setAttribute("admin", admin);
                    System.out.println("✅ Session set for admin: " + admin.getAdminName());
                    response.sendRedirect(request.getContextPath() + "/adminDashboard.jsp");
                } else {
                    request.setAttribute("errorMessage", "Invalid admin credentials");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }
            } else {
                UserDAO userDAO = new UserDAO();
                User user = userDAO.authenticate(username, password);

                if (user != null) {
                    userDAO.updateLoginTime(user.getUserId());
                    HttpSession session = request.getSession();
                    session.setAttribute("user", user);
                    System.out.println("✅ Session set for user: " + user.getUserName());
                    response.sendRedirect(request.getContextPath() + "/userDashboard.jsp");
                } else {
                    request.setAttribute("errorMessage", "Invalid user credentials");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Database error occurred");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}