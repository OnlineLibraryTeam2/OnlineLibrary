<!doctype html>

<html>
<head>
    <title>Online Library</title>

    <link rel="stylesheet" type="text/css" href="css/style.css" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
  	<script>
		(function($) {
    $(function() {
        $('input[name="radioName"]').on('change',function() {
            if(this.value == 'yes') {
                $('#hiddenFiels').css('display','block')
            } else {
                $('#hiddenFiels').css('display','none')
            }
        });
    });
})(jQuery)
	</script>
</head>

<body>
<header>

    <nav>
        <ul>
            <li>"Admin"</li>
        </ul>
    </nav>

</header>






<div id="content" align=center>
    <h1>Search for...</h1>
		<form method="get" action="#">
		<input
 			onblur="this.value=(this.value=='')?this.title:this.value;"
 			onfocus="this.value=(this.value==this.title)?'':this.value;"
 			value="Search"
			title="Search"
 			type="text"
		></br>Search book by: </br>
    		<input name="radioName" id="radio1" value="title" type="radio">
		Title</br>
		<input name="radioName" id="radio2" value="genre" type="radio">
		Genre</br>
		<input name="radioName" id="radio3" value="author" type="radio" >
		Author</br>
		<input name="radioName" id="radio4" value="count" type="radio" >
		Count
		</br>
		</br>Search Client by: </br>
		<input name="radioName" id="radio5" value="name" type="radio">
		Name</br>
		<input name="radioName" id="radio6" value="surname" type="radio">
		Surname</br>
		<input name="radioName" id="radio7" value="mail" type="radio" >
		Mail</br>
		<input name="radioName" id="radio8" value="pnum" type="radio" >
		Phone Num#
		</br>
		</br>
		<input type=submit value="Search.">
		</form>

</div>
<div id="searchBooks" >
	<div class="showBooks">
		
	</div>
</div>

<div id="footer">This project "Online Library" has been created by Team2 @ Riga JAVA Bootcamp 2016 "Aleksandr Larev, Deniss Prohorenko, Eduard Morozov, Oleksandr Pustovyi.</div>
</body>
</html>