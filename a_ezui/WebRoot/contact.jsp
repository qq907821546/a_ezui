<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String ezuiPath = path + "/ezui";
%>
<!DOCTYPE html>
<!-- templatemo 413 flip turn -->
<!-- 
Flip Turn Template 
http://www.templatemo.com/tm-413-flip-turn
-->
<head>
	<title>Flip Turn - Contact</title>
	<meta name="keywords" content="" />
	<meta name="description" content="" />
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,700' rel='stylesheet' type='text/css'>
	<link href="css/font-awesome.min.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" type="text/css" href="css/templatemo_style.css">
</head>
<body>
	<div class="main-container">
		<nav class="main-nav">
			<div id="logo" class="left"><a href="#">GK WHITE</a></div>
			<ul class="nav right center-text">
				<li class="btn"><a href="index.jsp">Home</a></li>
				<li class="btn"><a href="about.jsp">About</a></li>
				<li class="btn"><a href="awards.jsp">Awards</a></li>
				<li class="btn active">Contact</li>
			</ul>
		</nav>
		<div class="content-container">
			<header>
				<h1 class="center-text">Contact</h1>
				<h2 class="center-text">Vivamus sollicitudin est id purus feugiat laoreet. Quisque sagittis viverra fringilla. Nullam ut ligula porttitor, rutrum ipsum quis, pretium massa. Aenean vulputate ultrices nisl a vulputate. Proin pellentesque, quam et facilisis lobortis, nibh elit consectetur turpis, nec lobortis metus erat sit amet nisi.</h2>
			</header>

			<div id="contact-content" class="content">
				<div class="templatemo_contactmap">
					<div id="templatemo_map"></div>
				</div>
				<form role="form" method="post" action="#">
					<div class="templatemo_form">
						<input name="fullname" type="text" class="form-control" id="fullname" placeholder="Your Name" maxlength="40">
					</div>
					<div class="templatemo_form">
						<input name="email" type="text" class="form-control" id="email" placeholder="Your Email" maxlength="40">
					</div>
					<div class="templatemo_form">
						<textarea name="message" rows="10" class="form-control" id="message" placeholder="Message"></textarea>
					</div>
					<div class="templatemo_form"><button type="submit" class="btn btn-primary">Send Message</button></div>
					<address class="content-description">
						<i class="fa fa-phone"></i> 010-010-0110<br>
						<i class="fa fa-map-marker"></i> 110 Proin eu erat vitae, mauris ullamcorper luctus 10110<br>
						<i class="fa fa-envelope"></i> <a href="#">info@company.com</a><br>
					</address>
				</form>
			</div>
		</div> <!-- /.content-container -->
		<footer>
			<p>Copyright &copy; 2084 Your Company Name <!-- Credit: www.templatemo.com --></p>
			<div class="social right">
				<a href="#"><i class="fa fa-facebook"></i></a>
				<a href="#"><i class="fa fa-twitter"></i></a>
				<a href="#"><i class="fa fa-google-plus"></i></a>
				<a href="#"><i class="fa fa-dribbble"></i></a>
				<a href="#"><i class="fa fa-instagram"></i></a>
				<a href="#"><i class="fa fa-linkedin"></i></a>
			</div>
		</footer>
	</div>
	<script type="text/javascript" src="js/templatemo_script.js"></script>
	<script type="text/javascript">
	loadScript();
	</script>
</body>
</html>