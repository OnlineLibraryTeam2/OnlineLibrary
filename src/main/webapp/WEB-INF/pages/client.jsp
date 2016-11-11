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

    <nav class="UserLog">
        <ul>
        <li class="userLogged">Logged in as:</li> ${clientName} </br> ${clientSurname}
        </ul>
    </nav>

</header>


<div id="content" align=center>
    <h1>Commence search.</h1>
		<form method="get" action="#">
		<input
 			onblur="this.value=(this.value=='')?this.title:this.value;"
 			onfocus="this.value=(this.value==this.title)?'':this.value;"
 			value="Search"
			title="Search"
 			type="text"
		></br>Search book by: 
    		<input name="radioName" id="radio1" value="title" type="radio">
		Title
		<input name="radioName" id="radio2" value="genre" type="radio">
		Genre
		<input name="radioName" id="radio3" value="author" type="radio" >
		Author
		</br></br>
		<input type=submit value="Search." onclick="findFunction()" >
		</form>

</div>
<div >
	<p id="searchBooks"></p>
</div>
<div id="Books" >
        
	<div class="takenBooks"><h1>Currently taken books.</h1>
		<p id="takenBooks"></p>
	</div>
	
	<div class="recommendedBooks"><h1>Recommended for you.</h1>
		<p id="recommendedBooks"></p>
	</div>

	<div class="historyBooks"><h1>Books you have read.</h1>
	<p id="bookHistory"></p>
	</div>
</div>

<script>
var taken_books, text, bLen, i;

taken_books = [];
bLen = taken_books.length;
text = "<ul>";
if(taken_books.length == 0){
		text +="You have NOT taken any books.";
	} else if (taken_books.length !=0){
for (i = 0; i < bLen; i++) {
    text +="<li>"  + "<button type='button' onclick='myFunction()'>Return</button>" + " 	&nbsp; 	&nbsp; 	&nbsp;" + taken_books[i] + "</li>";
}
}
text += "</ul>";
document.getElementById("takenBooks").innerHTML = text;
</script>
<script>
var books, text, bLen, i;

recommended_book = ["The Witcher"];
books = recommended_book;
bLen = books.length;
text = "<ul>";
if(books.length == 0){
		text +="Nothing to display at the moment.";
	} else if (books.length !=0){
for (i = 0; i < bLen; i++) {
    text +="<li>"  + "<button type='button' onclick='myFunction()'>Take</button>" + " 	&nbsp; 	&nbsp; 	&nbsp;" + books[i] + "</li>";
}
}
text += "</ul>";
document.getElementById("recommendedBooks").innerHTML = text;
</script>
<script>
var books, text, bLen, i;

books = ["The Witcher", "Metro 2033", "War and Piece", "Java8", "Java9"];
bLen = books.length;
text = "<ul>";
if(books.length == 0){
		text +="You didn't read any books yet.";
	} else if (books.length !=0){
for (i = 0; i < bLen; i++) {
    text +="<li>" + books[i] + "</li>";
}
}
text += "</ul>";
document.getElementById("bookHistory").innerHTML = text;
</script>
<script>
var found_books, text, bLen, i;

found_books = [];
bLen = found_books.length;
text = "<ul>";
if(found_books.length == 0){
		text +="You have NOT taken any books.";
	} else if (found_books.length !=0){
for (i = 0; i < bLen; i++) {
    text +="<li>"  + "<button type='button' onclick='findFunction()'>Take</button>" + " 	&nbsp; 	&nbsp; 	&nbsp;" + found_books[i] + "</li>";
}
}
text += "</ul>";
document.getElementById("searchBooks").innerHTML = text;
</script>
<script>
	function myFunction(){
		taken_books.push(recommended_book);
		document.getElementById("takenBooks").innerHTML = taken_books;
		recommended_book.splice(0);
		document.getElementById("recommendedBooks").innerHTML = recommended_book;
	}
</script>
<script>
	function findFunction(){
		found_books.push("The Witcher", "Metro 2033", "War and Piece", "Java8", "Java9","The Witcher", "Metro 2033", "War and Piece", "Java8", "Java9");
		document.getElementById("searchBooks").innerHTML = found_books;
	}
</script>

<div id="footer">This project "Online Library" has been created by Team2 @ Riga JAVA Bootcamp 2016 "Aleksandr Larev, Deniss Prohorenko, Eduard Morozov, Oleksandr Pustovyi.</div>
</body>
</html>