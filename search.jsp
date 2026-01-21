<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Search Institutes</title>
<style>
@charset "UTF-8";

body {
	font-family: Arial, sans-serif;
	margin: 0;
	padding: 0;
	background-color: #34495E;
	overflow-y: auto; /* Enable vertical scrolling */
}

header {
	background-color: #F44336;
	color: #fff;
	padding: 20px;
	text-align: center;
}

header h1 {
	font-family: 'Roboto', sans-serif;
	font-size: 36px;
	margin: 0;
}

header p {
	font-size: 18px;
	margin: 10px 0;
}

header hr {
	width: 50%;
	border: 1px solid #fff;
	margin: 10px auto;
}

.container {
	max-width: 1200px;
	margin: 20px auto;
	padding: 0 15px;
}

.search-bar {
	background: #FFFFFF;
	border-radius: 5px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	padding: 20px;
	margin-bottom: 20px;
}

.search-bar h2 {
	margin: 0 0 20px;
	font-size: 24px;
}

.search-bar input[type="text"], .search-bar select {
	width: calc(100% - 22px);
	padding: 10px;
	margin-bottom: 10px;
	border: 1px solid #ddd;
	border-radius: 5px;
}

.search-bar button {
	background-color: #F44336;
	color: #fff;
	border: none;
	padding: 10px 20px;
	border-radius: 5px;
	cursor: pointer;
	font-size: 16px;
}

.search-bar button:hover {
	background-color: #FF6F61;
}

.results {
	background: #fff;
	border-radius: 5px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	padding: 20px;
}

.results h2 {
	margin: 0 0 20px;
	font-size: 24px;
}

.college-card {
	background: #f9f9f9;
	border: 1px solid #ddd;
	border-radius: 5px;
	padding: 15px;
	margin-bottom: 10px;
}

.college-card h3 {
	margin: 0 0 10px;
}

.college-card p {
	margin: 0;
}

.college-card .ratings {
	color: #ffcc00;
}

.college-card .ratings::before {
	content: "★★★★★";
	letter-spacing: 1px;
	display: inline-block;
}

.college-card .ratings[data-rating="1"]::before {
	content: "★☆☆☆☆";
}

.college-card .ratings[data-rating="2"]::before {
	content: "★★☆☆☆";
}

.college-card .ratings[data-rating="3"]::before {
	content: "★★★☆☆";
}

.college-card .ratings[data-rating="4"]::before {
	content: "★★★★☆";
}

.college-card .ratings[data-rating="5"]::before {
	content: "★★★★★";
}

.support-help {
	background: #fff;
	border-top: 1px solid #ddd;
	padding: 20px;
	text-align: center;
	margin-top: 20px;
}

.support-help h3 {
	margin: 0 0 10px;
}

.support-help p {
	margin: 0;
}
</style>
</head>
<body>
	<header>
		<h1>INSTITUTE ADVISOR</h1>
		<hr>
		<p>Find the best colleges across the state with ease</p>
		<a href="logout">
			<button
				style="position: absolute; top: 10px; right: 20px; background-color: #34495E; color: white; font-weight: bold; border: none; padding: 10px 20px; border-radius: 5px; cursor: pointer;">
				Logout</button>
		</a>
	</header>
	<div class="container">
		<form action="search.jsp" method="post" class="search-bar">
			<h2>Find Your Perfect College</h2>
			<label for="college_name">College Name:</label> <input type="text"
				name="college_name" id="college_name"><br> <label
				for="district">District:</label> <select name="district"
				id="district">
				<option value="">Select District</option>
				<option value="Mumbai_City">Mumbai</option>
				<option value="Mumbai_Suburban">Mumbai Suburban</option>
				<option value="Nashik">Nashik</option>
				<option value="Pune">Pune</option>
				<option value="Thane">Thane</option>
			</select><br> <label for="course">Course:</label> <select name="course"
				id="course">
				<option value="">Select Course</option>
				<option value="ba">Bachelor of Arts (BA)</option>
				<option value="bmm">Bachelor of Mass Media (BMM)</option>
				<option value="bcom">Bachelor of Commerce (BCom)</option>
				<option value="baf">Bachelor of Accounting and Finance
					(BAF)</option>
				<option value="bsc">Bachelor of Science (BSc)</option>
				<option value="btech_cs">Bachelor of Technology in Computer
					Science (BTech CS)</option>
				<option value="btech_it">Bachelor of Technology in
					Information Technology (BTech IT)</option>
				<option value="mbbs">Bachelor of Medicine, Bachelor of
					Surgery (MBBS)</option>
				<option value="llb">Bachelor of Laws (LLB)</option>
				<option value="mba">Master of Business Administration (MBA)</option>
			</select><br> <label for="cutoff">Cutoff Percentage:</label> <select
				name="cutoff" id="cutoff">
				<option value="">Select Cutoff</option>
				<option value="below-45">Below 45%</option>
				<option value="45-65">45% - 65%</option>
				<option value="65-85">65% - 85%</option>
				<option value="85-95">85% - 95%</option>
				<option value="above-95">Above 95%</option>
			</select><br> <label for="ratings">Ratings:</label> <select
				name="ratings" id="ratings">
				<option value="">Select Ratings</option>
				<option value="1">1 Star</option>
				<option value="2">2 Stars</option>
				<option value="3">3 Stars</option>
				<option value="4">4 Stars</option>
				<option value="5">5 Stars</option>
			</select><br> <label for="feesRange">Fees:</label> <select
				name="feesRange" id="feesRange">
				<option value="">Select Fees Range</option>
				<option value="below-75000">Below ₹75000</option>
				<option value="75000-100000">₹75000 - ₹100000</option>
				<option value="100000-180000">₹100000 - ₹180000</option>
				<option value="above-200000">Above ₹200000</option>
			</select><br>

			<button type="submit">Search</button>
		</form>
		<%
            String jdbcUrl = "jdbc:mysql://localhost:3306/instituteadvisor"; // Replace with your DB URL
            String username = "root"; // Replace with your DB username
            String password = "root"; // Replace with your DB password

            Connection conn = null;
            PreparedStatement pstmt = null;
            ResultSet rs = null;

            try {
                conn = DriverManager.getConnection(jdbcUrl, username, password);

                StringBuilder query = new StringBuilder("SELECT * FROM colleges WHERE 1=1");
                if (request.getParameter("college_name") != null && !request.getParameter("college_name").isEmpty()) {
                    query.append(" AND College_name LIKE ?");
                }
                if (request.getParameter("district") != null && !request.getParameter("district").isEmpty()) {
                    query.append(" AND District = ?");
                }
                if (request.getParameter("course") != null && !request.getParameter("course").isEmpty()) {
                    query.append(" AND Course = ?");
                }

                String cutoffParam = request.getParameter("cutoff");
                if (cutoffParam != null && !cutoffParam.isEmpty()) {
                    if (cutoffParam.equals("below-45")) {
                        query.append(" AND Cutoff_percentage < 45");
                    } else if (cutoffParam.equals("45-65")) {
                        query.append(" AND Cutoff_percentage BETWEEN 45 AND 65");
                    } else if (cutoffParam.equals("65-85")) {
                        query.append(" AND Cutoff_percentage BETWEEN 65 AND 85");
                    } else if (cutoffParam.equals("85-95")) {
                        query.append(" AND Cutoff_percentage BETWEEN 85 AND 95");
                    } else if (cutoffParam.equals("above-95")) {
                        query.append(" AND Cutoff_percentage > 95");
                    }
                }

                if (request.getParameter("ratings") != null && !request.getParameter("ratings").isEmpty()) {
                    query.append(" AND Ratings = ?");
                }

                String feesRangeParam = request.getParameter("feesRange");
                if (feesRangeParam != null && !feesRangeParam.isEmpty()) {
                    if (feesRangeParam.equals("below-75000")) {
                        query.append(" AND Fees < 75000");
                    } else if (feesRangeParam.equals("75000-100000")) {
                        query.append(" AND Fees BETWEEN 75000 AND 100000");
                    } else if (feesRangeParam.equals("100000-180000")) {
                        query.append(" AND Fees BETWEEN 100000 AND 180000");
                    } else if (feesRangeParam.equals("above-200000")) {
                        query.append(" AND Fees > 200000");
                    }
                }

                pstmt = conn.prepareStatement(query.toString());

                int index = 1;
                if (request.getParameter("college_name") != null && !request.getParameter("college_name").isEmpty()) {
                    pstmt.setString(index++, "%" + request.getParameter("college_name") + "%");
                }
                if (request.getParameter("district") != null && !request.getParameter("district").isEmpty()) {
                    pstmt.setString(index++, request.getParameter("district"));
                }
                if (request.getParameter("course") != null && !request.getParameter("course").isEmpty()) {
                    pstmt.setString(index++, request.getParameter("course"));
                }
                if (request.getParameter("ratings") != null && !request.getParameter("ratings").isEmpty()) {
                    pstmt.setInt(index++, Integer.parseInt(request.getParameter("ratings")));
                }

                rs = pstmt.executeQuery();
        %>
		<div class="results">
			<h2>Search Results</h2>
			<%
                boolean hasResults = false;
                while (rs.next()) {
                    hasResults = true;
            %>
			<div class="college-card">
				<h3><%= rs.getString("College_name") %></h3>
				<p>
					District:
					<%= rs.getString("District") %></p>
				<p>
					Course:
					<%= rs.getString("Course") %></p>
				<p>
					Cutoff:
					<%= rs.getInt("Cutoff_percentage") %>
					%
				</p>
				<p>
					Fees: ₹<%= rs.getInt("Fees") %></p>
				<p class="ratings" data-rating="<%= rs.getInt("Ratings") %>"></p>
			</div>
			<%
                }
                if (!hasResults) {
            %>
			<p>No results found for your criteria.</p>
			<%
                }
            %>
		</div>
		<%
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (rs != null) rs.close();
                    if (pstmt != null) pstmt.close();
                    if (conn != null) conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        %>
	</div>
	<div class="support-help">
		<h3>Need Help?</h3>
		<p>
			Contact our support team at: <a
				href="mailto:support@instituteadvisor.com">support@instituteadvisor.com</a>
		</p>
		<p>Call us: +91-9152583872</p>
	</div>
</body>
</html>
