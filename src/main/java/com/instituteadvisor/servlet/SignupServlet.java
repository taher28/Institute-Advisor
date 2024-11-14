package com.instituteadvisor.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/signup")
public class SignupServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve form parameters
        String username = request.getParameter("txt");
        String email = request.getParameter("email");
        String phone = request.getParameter("broj");
        String password = request.getParameter("pswd");

        // Check if email is from the allowed domain
        String allowedDomain = "@gmail.com";
        if (!email.endsWith(allowedDomain)) {
            displayErrorMessage(response, "Email must be from the domain " + allowedDomain + ".");
            return;
        }

        // Check if phone number is exactly 10 digits
        if (phone == null || !phone.matches("\\d{10}")) {
            displayErrorMessage(response, "Phone number must be exactly 10 digits.");
            return;
        }

        // Establish connection to the database
        Connection connection = DatabaseConnection.getConnection();

        if (connection != null) {
            try {
                // SQL query to insert user details into the database
                String query = "INSERT INTO users (Username, Email, Phone, Password) VALUES (?, ?, ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(query);

                // Set parameters for the query
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, email);
                preparedStatement.setString(3, phone);
                preparedStatement.setString(4, password); // Ideally, use hashed password here

                // Execute the update to insert data into the database
                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    // Show a styled success message for a few seconds and then redirect to login.jsp
                    response.setContentType("text/html");
                    response.getWriter().write("<html><head>");
                    response.getWriter().write("<style>");
                    response.getWriter().write("body { font-family: Arial, sans-serif; background: linear-gradient(to bottom, #FF5733, #FFFFFF); display: flex; align-items: center; justify-content: center; height: 100vh; margin: 0; }");
                    response.getWriter().write(".container { text-align: center; background: #ffffff; padding: 30px; border-radius: 8px; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2); max-width: 400px; }");
                    response.getWriter().write("h2 { color: #34495E; }");
                    response.getWriter().write("h3 { color: #34495E; }");
                    response.getWriter().write("</style>");
                    response.getWriter().write("</head><body>");
                    response.getWriter().write("<div class='container'>");
                    response.getWriter().write("<h2>Signup successful!</h2>");
                    response.getWriter().write("<h3>Welcome, " + username + "!</h3>");
                    response.getWriter().write("<p>Redirecting to login page...</p>");
                    response.getWriter().write("</div>");
                    response.getWriter().write("<meta http-equiv='refresh' content='3;URL=login.jsp'>");
                    response.getWriter().write("<script>setTimeout(function() { window.location.href = 'login.jsp'; }, 3000);</script>");
                    response.getWriter().write("</body></html>");
                } else {
                    displayErrorMessage(response, "Signup failed, please try again.");
                }

                // Close the resources
                preparedStatement.close();
                connection.close();

            } catch (SQLException e) {
                e.printStackTrace();
                displayErrorMessage(response, "An error occurred while processing your signup.");
            }
        } else {
            displayErrorMessage(response, "Failed to connect to the database.");
        }
    }

    // Helper method to display error messages in a styled format
    private void displayErrorMessage(HttpServletResponse response, String errorMessage) throws IOException {
        response.setContentType("text/html");
        response.getWriter().write("<html><head>");
        response.getWriter().write("<style>");
        response.getWriter().write("body { font-family: Arial, sans-serif; background: linear-gradient(to bottom, #FF5733, #FFFFFF); display: flex; align-items: center; justify-content: center; height: 100vh; margin: 0; }");
        response.getWriter().write(".container { text-align: center; background: #ffffff; padding: 30px; border-radius: 8px; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2); max-width: 400px; }");
        response.getWriter().write("h2 { color: #34495E; }");
        response.getWriter().write("p { color: #34495E; font-size: 16px; }");
        response.getWriter().write("a { color: #34495E; font-weight: bold; text-decoration: none; }");
        response.getWriter().write("a:hover { text-decoration: underline; }");
        response.getWriter().write("</style>");
        response.getWriter().write("</head><body>");
        response.getWriter().write("<div class='container'>");
        response.getWriter().write("<h2>Signup Failed</h2>");
        response.getWriter().write("<p>" + errorMessage + "</p>");
        response.getWriter().write("<p><a href='login.jsp'>Try again</a></p>");
        response.getWriter().write("</div>");
        response.getWriter().write("</body></html>");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }
}
