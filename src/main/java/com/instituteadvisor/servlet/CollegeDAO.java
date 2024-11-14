package com.instituteadvisor.servlet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CollegeDAO {
    
    public List<College> searchColleges(String district, String course, 
                                         float minCutoffPercentage, float maxCutoffPercentage, 
                                         int minRating, int minFees, int maxFees) {
        List<College> colleges = new ArrayList<>();
        String sql = "SELECT * FROM colleges WHERE district = ? AND course = ? " +
                     "AND cutoff_percentage >= ? AND cutoff_percentage <= ? " +
                     "AND rating >= ? AND fees BETWEEN ? AND ?"; // Adjusted query

        try (Connection connection = DatabaseConnection.getConnection()) {
            if (connection == null) {
                System.err.println("Database connection is null. Unable to perform search.");
                return colleges; // Return an empty list or handle the error as needed
            }

            // Prepare the SQL statement
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, district);
            stmt.setString(2, course);
            stmt.setFloat(3, minCutoffPercentage);
            stmt.setFloat(4, maxCutoffPercentage);
            stmt.setInt(5, minRating);
            stmt.setInt(6, minFees);
            stmt.setInt(7, maxFees); // Using BETWEEN for fees range

            // Execute the query and process the results
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                College college = new College();
                college.setId(rs.getInt("id"));
                college.setName(rs.getString("name"));
                college.setLocation(rs.getString("location"));
                college.setDistrict(rs.getString("district"));
                college.setCourse(rs.getString("course"));
                college.setCutoffPercentage(rs.getFloat("cutoff_percentage"));
                college.setRating(rs.getInt("rating")); // Ensure 'rating' column exists
                college.setFees(rs.getInt("fees"));

                colleges.add(college);
            }
        } catch (Exception e) {
            System.err.println("Error occurred while searching for colleges: " + e.getMessage());
            e.printStackTrace(); // Consider logging this error to a file or monitoring system
        }
        return colleges;
    }
    
    // Optional: If you still need the previous version of searchColleges, consider renaming it
    public List<College> searchcolleges(String district, String course, float minCutoffPercentage, float maxCutoffPercentage, 
            int minRating, int minFees, int maxFees) {
List<College> colleges = new ArrayList<>();
StringBuilder sql = new StringBuilder("SELECT * FROM colleges WHERE 1=1"); // Start with a base query

// Initialize a counter for parameters
int parameterIndex = 1;

// Conditional checks to append to the SQL query
if (district != null && !district.isEmpty()) {
sql.append(" AND district = ?");
}
if (course != null && !course.isEmpty()) {
sql.append(" AND course = ?");
}
if (minCutoffPercentage > 0) {
sql.append(" AND cutoff_percentage >= ?");
}
if (maxCutoffPercentage > 0) {
sql.append(" AND cutoff_percentage <= ?");
}
if (minRating > 0) {
sql.append(" AND ratings >= ?");
}
if (minFees > 0) {
sql.append(" AND fees >= ?");
}
if (maxFees < Integer.MAX_VALUE) {
sql.append(" AND fees <= ?");
}

try (Connection connection = DatabaseConnection.getConnection()) {
if (connection == null) {
System.err.println("Database connection is null. Unable to perform search.");
return colleges; // Return an empty list or handle the error as needed
}

// Prepare the SQL statement
PreparedStatement stmt = connection.prepareStatement(sql.toString());

// Set parameters based on conditions
if (district != null && !district.isEmpty()) {
stmt.setString(parameterIndex++, district);
}
if (course != null && !course.isEmpty()) {
stmt.setString(parameterIndex++, course);
}
if (minCutoffPercentage > 0) {
stmt.setFloat(parameterIndex++, minCutoffPercentage);
}
if (maxCutoffPercentage > 0) {
stmt.setFloat(parameterIndex++, maxCutoffPercentage);
}
if (minRating > 0) {
stmt.setInt(parameterIndex++, minRating);
}
if (minFees > 0) {
stmt.setInt(parameterIndex++, minFees);
}
if (maxFees < Integer.MAX_VALUE) {
stmt.setInt(parameterIndex++, maxFees);
}

// Execute the query and process the results
ResultSet rs = stmt.executeQuery();
while (rs.next()) {
College college = new College();
college.setId(rs.getInt("id"));
college.setName(rs.getString("name"));
college.setLocation(rs.getString("location"));
college.setDistrict(rs.getString("district"));
college.setCourse(rs.getString("course"));
college.setCutoffPercentage(rs.getFloat("cutoff_percentage"));
college.setRating(rs.getInt("rating"));
college.setFees(rs.getInt("fees"));

colleges.add(college);
}
} catch (SQLException e) {
System.err.println("Error occurred while searching for colleges: " + e.getMessage());
e.printStackTrace(); // Consider logging this error to a file or monitoring system
}
return colleges;
}

}
