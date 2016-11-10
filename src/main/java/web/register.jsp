<!doctype html>

<html>
	<head>
		<title>Online Library</title>

		<link rel="stylesheet" type="text/css" href="css/style.css" />
<script type="text/javascript">
function validate()
{
var emailID = document.registerform.mail.value;
		atpos = emailID.indexOf("@");
		dotpos = emailID.lastIndexOf(".");

   if( document.registerform.firstname.value == "" )
   {
     alert( "Please provide your name!" );
     document.registerform.firstname.focus() ;
     return false;
   }
   if( document.registerform.lastname.value == "" )
   {
     alert( "Please provide your surname!" );
     document.registerform.lastname.focus() ;
     return false;
   }
   if( document.registerform.age.value == "" )
   {
     alert( "Please provide your age!" );
     document.registerform.age.focus() ;
     return false;
   }
   if( document.registerform.pnum.value == "" ||
           isNaN( document.registerform.pnum.value ) ||
           document.registerform.pnum.value.length != 12 )
   {
     alert( "Please provide phone number in the format +371########." );
     document.registerform.pnum.focus() ;
     return false;
   }
 if( document.registerform.mail.value == "" )
   {
				alert("Please enter valid email.")
				return false;
   }
	if (atpos < 1 || ( dotpos - atpos < 2 )){
				alert("Please enter correct Login.")
				document.registerform.mail.focus() ;
				return false;
			}
   if( document.registerform.password.value == "" )
   {
     alert( "Please select your password!" );
     document.registerform.password.focus() ;
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
					<li><a href="index.html" id="home">Home</a></li>
				</ul>
			</nav>
			
		</header>

		<div id="content" align=center>
			<h1>Fill out the registration form.</h1>
		
			
				<form name="registerform" action="#" onsubmit="return validate()" method="post">
				  First name:<span style="color: red">*</span><br>
				  <input type="text" pattern="[A-Za-z]{3,36}" name="firstname" >
				  <br>
				  Last name:<span style="color: red">*</span><br>
				  <input type="text" name="lastname" >
				  <br>
				  Age:<span style="color: red">*</span><br>
				  <input id="age" type="number" name="age" min="16" max="99">
				  <br>
				  Phone Number#:<span style="color: red">*</span><br>
				  <input type="text" name="pnum" >
				  <br>
				E-mail (this will be used as a login): <span style="color: red">*</span><br>
				  <input type="text" name="mail" >
				  <br>
				  Password:<span style="color: red">*</span><br>
				  <input type="text" name="password" >
				  <br>
				  <input type="submit" value="Submit">
				</form>
				
							<h1 id="needed"><span style="font-size: 24px">*</span> - Fields that are required to be filled</br>with relatively correct information.</h1>
		
		</div>
		<div id="footer">This project "Online Library" has been created by Team2 @ Riga JAVA Bootcamp 2016 "Aleksandr Larev, Deniss Prohorenko, Eduard Morozov, Oleksandr Pustovyi.</div>
	</body>
</html>