<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.instituteadvisor.servlet.College"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Search Results</title>
<style>
.college-card {
	border: 1px solid #ccc;
	padding: 15px;
	margin-bottom: 10px;
	border-radius: 5px;
	box-shadow: 2px 2px 5px rgba(0, 0, 0, 0.1);
}
</style>
</head>
<body>
	<h2>Search Results</h2>

	<%
    List<College> resultsList = (List<College>) request.getAttribute("resultsList");
    if (resultsList != null && !resultsList.isEmpty()) {
        for (College college : resultsList) {
%>
	<div class="college-card">
		<h3><%= college.getName() %></h3>
		<p>
			District:
			<%= college.getDistrict() %></p>
		<p>
			Course:
			<%= college.getCourse() %></p>
		<p>
			Ratings:
			<%= college.getRating() %>
			Stars
		</p>
		<p>
			Fees Range: ₹<%= college.getFees() %></p>
	</div>
	<%
        }
    } else {
%>
	<p>No results found for your search criteria.</p>
	<%
    }
%>

</body>
</html>

