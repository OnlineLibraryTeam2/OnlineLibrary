<!doctype html>

<html>
	<head>
		<title>Online Library</title>

		<link rel="stylesheet" type="text/css" href="css/style.css" />
	<script>
	function validateLogin(){
			var v=document.forms["loginform"]["mail"].value;
			var z=document.forms["loginform"]["password"].value;

		var emailID = document.loginform.mail.value;
		atpos = emailID.indexOf("@");
		dotpos = emailID.lastIndexOf(".");
			if (atpos < 1 || ( dotpos - atpos < 2 )){
				alert("Please enter correct Login.")
				document.loginform.mail.focus() ;
				return false;
			}
			var y=document.forms["loginform"]["password"].value;
				if (y==null || y=="") {
					alert("Enter password.");
					return false;
			}
		return( true );
	}
	</script>
	</head>

	<body>
		<header>
		
			<nav>
				<ul>
					<li><a href="#" id="home" class="active">Home</a></li>
				</ul>
			</nav>
			
		</header>

		<div id="content" align=center>
			<h1>To view books that are available for you, please sign in or register.</h1>
		
			
				<form id="loginForm" action="login" method="post">
                        <input type="text" name = "login" placeholder="Login"><br>
                        <input id="password" name="password" type="password" placeholder="Password"><br>
                        <button id="loginButton">login</button>
                </form>
			
			<ul class="filter">
				<li><a href="WEB-INF/pages/register.jsp" >Register!</a></li>
			</ul>

		
		</div>
		<div id="footer">This project "Online Library" has been created by Team2 @ Riga JAVA Bootcamp 2016 "Aleksandr Larev, Deniss Prohorenko, Eduard Morozov, Oleksandr Pustovyi.</div>
	</body>
</html>