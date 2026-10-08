# Institute Advisor

Institute Advisor is a web-based college search application that helps students find colleges based on different criteria. Users can create an account, log in, and search for colleges using filters such as college name, district, course, cutoff percentage, rating, and fees.

The application uses Java Servlets and JSP for the backend and frontend, with MySQL used to store user and college information.

## Tech stack

- **Language:** Java
- **Web technology:** JSP, HTML, CSS, JavaScript
- **Backend:** Java Servlets
- **Database:** MySQL
- **Server:** Apache Tomcat 10
- **Database connectivity:** JDBC
- **IDE:** Eclipse

## Architecture

The project follows a Java web application structure:

- `src/main/java/com/instituteadvisor/servlet/` contains the Java classes and servlets used for authentication, college search, and database operations.
- `src/main/webapp/` contains the JSP pages, HTML, CSS, and other web resources.
- `WEB-INF/` contains the web application configuration and required libraries.
- `DatabaseConnection.java` handles the connection between the application and MySQL.
- `CollegeDAO.java` handles college-related database operations.
- The servlet classes process user requests and communicate with the database.

Requests from the frontend are handled by Java Servlets. The servlets communicate with the MySQL database using JDBC and display the required information through JSP pages.

### User authentication

Users can create an account and log in using their credentials.

The application provides:

- User registration
- User login
- Email validation
- Phone number validation
- Logout functionality

The authentication functionality is handled using Java Servlets and MySQL.

### College search

The main feature of Institute Advisor is the college search system.

Users can search and filter colleges using:

- College name
- District
- Course
- Cutoff percentage
- Rating
- Fees

The application retrieves matching college records from the MySQL database and displays the results to the user.

### College information

The application stores college information such as:

- College name
- Location
- District
- Course
- Cutoff percentage
- Rating
- Fees

The information is retrieved from the database and displayed through the search and results pages.

## Prerequisites

- Java JDK
- Apache Tomcat 10
- MySQL Server
- Eclipse IDE
- MySQL Connector/J
- Git, if cloning the repository

## Setup

1. Install Java JDK, MySQL Server, and Apache Tomcat.
2. Create the required MySQL database for the project.
3. Configure the MySQL connection details in `DatabaseConnection.java`.
4. Open the project in Eclipse.
5. Configure Apache Tomcat 10 as the server.
6. Add the project to the Tomcat server.
7. Start MySQL and Apache Tomcat.

## Run the project

After starting the Tomcat server, open the application in a web browser.

The application starts with the login/signup page, where users can create an account or log in.

After logging in, users can search for colleges using the available filters and view the matching results.

## Main features

### User Management

- User registration
- User login
- Email and phone validation
- Logout functionality

### College Search

- Search by college name
- Filter by district
- Filter by course
- Filter by cutoff percentage
- Filter by rating
- Filter by fees

### Database

- MySQL database
- JDBC connection
- User data storage
- College data storage
- Dynamic college search

### User Interface

- Login and signup pages
- College search page
- Search results page
- College information display
- Simple and responsive interface

## Contributors

- Krish Sarvaiya
- Taher Saterdawala
- Hamza Selanawala
- Gautham Seshapalli

## License

This project is licensed under the MIT License.
