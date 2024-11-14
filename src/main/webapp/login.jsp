<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Institute Advisor</title>
<link
	href="https://fonts.googleapis.com/css2?family=Jost:wght@500&display=swap"
	rel="stylesheet">
<style>
body {
	margin: 0;
	padding: 0;
	display: flex;
	flex-direction: column; /* Allow text and main to stack */
	justify-content: center;
	align-items: center;
	min-height: 100vh;
	font-family: 'Jost', sans-serif;
	background: linear-gradient(to bottom, #FF5733, #FFFFFF);
	/* Red to White gradient */
}

.institute-advisor {
	color: #FFFFFF; /* White color */
	font-size: 2em;
	font-weight: bold;
	margin: 20px 0; /* Add some margin for spacing */
	text-align: center; /* Center the text */
}

.main {
	width: 350px;
	height: 500px;
	background:
		url("https://doc-08-2c-docs.googleusercontent.com/docs/securesc/68c90smiglihng9534mvqmq1946dmis5/fo0picsp1nhiucmc0l25s29respgpr4j/1631524275000/03522360960922298374/03522360960922298374/1Sx0jhdpEpnNIydS4rnN4kHSJtU1EyWka?e=view&authuser=0&nonce=gcrocepgbb17m&user=03522360960922298374&hash=tfhgbs86ka6divo3llbvp93mg4csvb38")
		no-repeat center/cover;
	border-radius: 10px;
	box-shadow: 5px 20px 50px #000;
}

#chk {
	display: none;
}

.signup {
	position: relative;
	width: 100%;
	height: 100%;
}

label {
	color: #fff;
	font-size: 2.3em;
	justify-content: center;
	display: flex;
	margin: 50px;
	font-weight: bold;
	cursor: pointer;
	transition: .5s ease-in-out;
}

input {
	width: 60%;
	height: 10px;
	background: #e0dede;
	justify-content: center;
	display: flex;
	margin: 20px auto;
	padding: 12px;
	border: none;
	outline: none;
	border-radius: 5px;
}

button {
	width: 60%;
	height: 40px;
	margin: 10px auto;
	justify-content: center;
	display: block;
	color: #fff;
	background: #34495E; /* Match login button color */
	font-size: 1em;
	font-weight: bold;
	margin-top: 30px;
	outline: none;
	border: none;
	border-radius: 5px;
	transition: .2s ease-in;
	cursor: pointer;
}

button:hover {
	background: #5A6E8A;
}

.login {
	height: 460px;
	background: #eee;
	border-radius: 60%/10%;
	transform: translateY(-180px);
	transition: .8s ease-in-out;
}

.login label {
	color: #34495E;
	transform: scale(.6);
}

#chk:checked ~ .login {
	transform: translateY(-500px);
}

#chk:checked ~ .login label {
	transform: scale(1);
}

#chk:checked ~ .signup label {
	transform: scale(.6);
}

.signup label {
	color: #34495E; /* Purple color for sign up */
}
</style>
</head>
<body>
	<div class="institute-advisor">INSTITUTE ADVISOR</div>
	<div class="main">
		<input type="checkbox" id="chk" aria-hidden="true">
		<div class="signup">
			<form action="signup" method="POST">
				<!-- Connects to SignupServlet -->
				<label for="chk" aria-hidden="true">Sign up</label> <input
					type="text" name="txt" placeholder="User name" required="">
				<input type="email" name="email" placeholder="Email" required="">
				<input type="number" name="broj" placeholder="Phone Number"
					required=""> <input type="password" name="pswd"
					placeholder="Password" required="">
				<button type="submit">Sign up</button>
			</form>
		</div>
		<div class="login">
			<form action="login" method="POST">
				<!-- Assume there's a LoginServlet at /login -->
				<label for="chk" aria-hidden="true">Login</label> <input
					type="email" name="email" placeholder="Email" required="">
				<input type="password" name="pswd" placeholder="Password"
					required="">
				<button type="submit">Login</button>
			</form>
		</div>
	</div>
</body>
</html>
