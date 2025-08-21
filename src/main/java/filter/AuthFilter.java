package filter;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization code (optional)
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String path = req.getRequestURI();
        HttpSession session = req.getSession(false);

        boolean loggedIn = session != null &&
                (session.getAttribute("user") != null || session.getAttribute("admin") != null);

        // ✅ login.jsp, login servlet, static assets allow
        boolean loginRequest = path.endsWith("login.jsp")
                || path.endsWith("login")
                || path.contains("/assets/")
                || path.endsWith("index.jsp");

        if (loggedIn || loginRequest) {
            chain.doFilter(request, response); // ✅ Continue
        } else {
            resp.sendRedirect(req.getContextPath() + "/login.jsp"); // ❌ Not logged in → redirect
        }
    }

    @Override
    public void destroy() {
        // Cleanup code (optional)
    }
}
