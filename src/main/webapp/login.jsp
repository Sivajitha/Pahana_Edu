<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Pahana Edu Bookshop Management System</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <div class="login-container">
        <div class="login-box">
            <div class="logo">
                <h1>📚 Pahana Edu</h1>
                <p>Bookshop Management System</p>
            </div>

            <form action="LoginServlet" method="post" class="login-form">
                <div class="form-group">
                    <label for="username">Username:</label>
                    <input type="text" id="username" name="username" required>
                </div>

                <div class="form-group">
                    <label for="password">Password:</label>
                    <input type="password" id="password" name="password" required>
                </div>

                <button type="submit" class="login-btn">Login</button>

                <div class="demo-accounts">
                    <h4>Demo Accounts:</h4>
                    <p><strong>Admin:</strong> admin / admin123</p>
                    <p><strong>Cashier:</strong> cashier / cashier123</p>
                </div>
            </form>

            <div id="error-message" class="error-message" style="display: none;"></div>
        </div>
    </div>

    <script src="js/login.js"></script>
</body>
</html>