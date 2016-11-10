<!doctype html>

<html>
	<head>
		<title>Online Library</title>

		<link rel="stylesheet" type="text/css" href="css/style.css" />
	<script>
	function validateLogin(){
			var v=document.forms["loginform"]["mail"].value;
			var z=document.forms["loginform"]["password"].value;
			if (v=="admin" && z=="admin"){
				var newWin = window.open("admin.html",
	  	 			"_self",
	   				"width=100%,height=100%,resizable=yes,scrollbars=yes,status=yes"
					)
				alert("welcome admin");
			}
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
		
			
				<form name="loginform" onsubmit="return validateLogin();" method="post" action="client.html">
				  Login:<br>
				  <input type="text" name="mail" >
				  <br>
				  Password:<br>
				  <input id="password" maxlength="12" type="password" name="password" >
				  <br>
				  <input type="submit" value="Sign in">
				  
				</form>
			
			<ul class="filter">
				<li><a href="register.html" >Register!</a></li>
			</ul>

		
		</div>
		<div id="footer">This project "Online Library" has been created by Team2 @ Riga JAVA Bootcamp 2016 "Aleksandr Larev, Deniss Prohorenko, Eduard Morozov, Oleksandr Pustovyi.</div>
	</body>
</html>