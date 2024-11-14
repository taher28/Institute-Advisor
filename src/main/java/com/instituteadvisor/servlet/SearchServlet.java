package com.instituteadvisor.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Step 1: Retrieve form data from the request
        String collegeName = request.getParameter("collegeName");
        String district = request.getParameter("district");
        String course = request.getParameter("course");
        String cutoffStr = request.getParameter("cutoff");
        String ratingsStr = request.getParameter("ratings");
        String feesRangeStr = request.getParameter("feesRange");

        // Step 2: Validate inputs
        String errorMessage = validateInput(cutoffStr, ratingsStr, feesRangeStr);
        if (errorMessage != null) {
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("search.jsp").forward(request, response);
            return;
        }

        // Step 3: Parse the validated inputs
        float cutoffPercentage = parseCutoff(cutoffStr);
        int ratings = Integer.parseInt(ratingsStr);
        int[] feesRange = parseFeesRange(feesRangeStr);
        int minFees = feesRange[0];
        int maxFees = feesRange[1];

        // Step 4: Interact with the database to fetch search results
        List<College> resultsList = new ArrayList<>();
        try {
            Connection conn = DatabaseConnection.getConnection(); // Ensure `DatabaseConnection` is a utility class that manages your DB connections

            String sql = "SELECT * FROM colleges WHERE (College_name LIKE ? OR ? IS NULL) AND (district = ? OR ? IS NULL) AND (course = ? OR ? IS NULL) AND (ratings >= ? OR ? IS NULL) AND (fees BETWEEN ? AND ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, collegeName != null && !collegeName.isEmpty() ? "%" + collegeName + "%" : null);
            ps.setString(2, collegeName);
            ps.setString(3, district);
            ps.setString(4, district);
            ps.setString(5, course);
            ps.setString(6, course);
            ps.setInt(7, ratings);
            ps.setInt(8, ratings);
            ps.setInt(9, minFees);
            ps.setInt(10, maxFees);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                College college = new College();
                college.setName(rs.getString("name"));
                college.setDistrict(rs.getString("district"));
                college.setCourse(rs.getString("course"));
                college.setRating(rs.getInt("ratings"));
                college.setFees(rs.getInt("fees"));
                resultsList.add(college);
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace(); // For debugging; use logging for production
        }

        // Step 5: Set attributes for the request and forward to the results page
        request.setAttribute("resultsList", resultsList);
        request.setAttribute("collegeName", collegeName);
        request.setAttribute("district", district);
        request.setAttribute("course", course);
        request.setAttribute("cutoff", cutoffStr);
        request.setAttribute("ratings", ratingsStr);
        request.setAttribute("feesRange", feesRangeStr);
        request.getRequestDispatcher("search.jsp").forward(request, response);
    }

    private String validateInput(String cutoffStr, String ratingsStr, String feesRangeStr) {
        if (cutoffStr != null && !cutoffStr.isEmpty()) {
            if (!cutoffStr.matches("below-45|45-65|65-85|85-95|above-95")) {
                return "Invalid cutoff range.";
            }
        }

        if (ratingsStr != null && !ratingsStr.isEmpty()) {
            try {
                Integer.parseInt(ratingsStr);
            } catch (NumberFormatException e) {
                return "Invalid ratings. Please enter a valid number.";
            }
        }

        if (feesRangeStr != null && !feesRangeStr.isEmpty()) {
            if (!feesRangeStr.matches("below-75000|75000-100000|100000-180000|above-200000")) {
                return "Invalid fees range.";
            }
        }
        return null;
    }

    private float parseCutoff(String cutoffStr) {
        switch (cutoffStr) {
            case "below-45":
                return 45;
            case "45-65":
                return 65;
            case "65-85":
                return 85;
            case "85-95":
                return 95;
            case "above-95":
                return 100;
            default:
                return 0; // Shouldn't happen due to previous validation
        }
    }

    private int[] parseFeesRange(String feesRangeStr) {
        int[] range = new int[2];
        switch (feesRangeStr) {
            case "below-75000":
                range[0] = 0;
                range[1] = 75000;
                break;
            case "75000-100000":
                range[0] = 75000;
                range[1] = 100000;
                break;
            case "100000-180000":
                range[0] = 100000;
                range[1] = 180000;
                break;
            case "above-200000":
                range[0] = 200000;
                range[1] = Integer.MAX_VALUE;
                break;
            default:
                range[0] = 0;
                range[1] = Integer.MAX_VALUE;
                break;
        }
        return range;
    }
}
