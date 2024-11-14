package com.instituteadvisor.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("pswd");

        // Check if email is from the allowed domain
        String allowedDomain = "@gmail.com";
        if (!email.endsWith(allowedDomain)) {
            // Redirect back to login page with domain error
            response.sendRedirect("login.jsp?error=domain");
            return;
        }

        Connection connection = DatabaseConnection.getConnection();

        if (connection != null) {
            try {
                String query = "SELECT * FROM users WHERE Email = ? AND Password = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, email);
                preparedStatement.setString(2, password);
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    // Successful login: Redirect to the main page
                    response.sendRedirect("index.html");
                } else {
                    // Redirect back to login page with invalid credentials error
                    response.sendRedirect("login.jsp?error=invalid");
                }

                resultSet.close();
                preparedStatement.close();
                connection.close();

            } catch (Exception e) {
                e.printStackTrace();
                // Redirect to login page with database error
                response.sendRedirect("login.jsp?error=error");
            }
        } else {
            // Redirect to login page with database connection error
            response.sendRedirect("login.jsp?error=db");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }
}
