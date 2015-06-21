<%@tag description="Template page d'accueil" pageEncoding="UTF-8"%>
<%@attribute name="texte_principal" fragment="true"%>
<%@attribute name="colonne_1" fragment="true"%>
<%@attribute name="colonne_2" fragment="true"%>
<%@attribute name="colonne_3" fragment="true"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta name="keywords" content="" />
<meta name="description" content="" />
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>NSY135 : Exploitation Minière</title>
<link
	href="http://fonts.googleapis.com/css?family=Source+Sans+Pro:200,300,400,600,700"
	rel="stylesheet" type="text/css" />
<link href="default.css" rel="stylesheet" type="text/css" media="all" />

</head>
<body>
	<div id="header-wrapper">
		<div id="header">
			<div id="logo">
				<h1>
					<a href="#">Projet : mines</a>
				</h1>
			</div>
			<div id="menu">
				<ul>
					<li><a href="#" accesskey="1" title="">Homepage</a></li>
					<li><a href="http://orm.bdpedia.fr/intro.html" accesskey="2"
						title="">Cours NSY135</a></li>
					<li><a href="http://localhost/phpmyadmin/" accesskey="3"
						title="">PhpMyAdmin</a></li>
					<li><a href="http://orm.bdpedia.fr/projets.html" accesskey="4"
						title="">Sujet</a></li>
					<li><a href="http://www.outlook.fr/" accesskey="5" title="">Hotmail</a></li>
				</ul>
			</div>
		</div>
	</div>
	<div id="banner">
		<div class="img-border">
			<a href="#"><img
				src="images/Image accueil exploitation minière.jpg" width="1120"
				height="300" alt="" /></a>
		</div>
	</div>
	<div id="wrapper">
		<div id="wide-content">
			<div>
				<jsp:invoke fragment="texte_principal" />
				<p class="button-style">
					<a href="#">Haut de page</a>
				</p>
			</div>
		</div>
	</div>
	<div id="page-wrapper">
		<div id="page" class="container">
			<div id="content">
				<div>
					<!--<ul class="style1">  -->
				 		<!-- <li class="first"> 
							 <h3>Insertions :</h3>-->
					<!-- <p>Ut vel nisl tristique justo ornare iaculis. Suspendisse suscipit, orci ac interdum viverra, nulla orci facilisis mi, a ultrices mi lectus vitae felis. Morbi suscipit adipiscing orci et blandit. Morbi a nulla ut tellus blandit placerat a quis eros.</p>
					 
							<p>
								<a href="#" class="button-style">Read More</a>
							</p>--> 
					<!-- 	</li>  -->
						<jsp:invoke fragment="colonne_2" />
					<!-- 	<li>
							 <h3>Magna phasellus etiam ultrices</h3>
					<p>Ut vel nisl tristique justo ornare iaculis. Suspendisse suscipit, orci ac interdum viverra, nulla orci facilisis mi, a ultrices mi lectus vitae felis. Morbi suscipit adipiscing orci et blandit. Morbi a nulla ut tellus blandit placerat a quis eros.</p>
					 
							<p>
								<a href="#" class="button-style">Read More</a>
							</p>
						</li>-->
					<!-- </ul>  -->
					<%-- <jsp:invoke fragment="colonne_3" /> --%>
				</div>
			</div>
			<div id="sidebar">
				 <!-- <h2>Fusce ultrices</h2> -->
				  <ul class="style3">
					<!-- <li class="first">
						 <p class="date">05-04-2013</p>
						<p>
							<a href="#">Vestibulum laoreet lorem sed amet condimentum
								eget ultrices et mago porttitor nequese blandit.</a>
						</p>
					</li>
					<li>
						<p class="date">04-23-2013</p>
						<p>
							<a href="#">Vestibulum laoreet lorem sed amet condimentum
								eget ultrices et mago porttitor nequese blandit.</a>
						</p>
					</li>
					<li>
						<p class="date">04-17-2013</p>
						<p>
							<a href="#">Vestibulum laoreet lorem sed amet condimentum
								eget ultrices et mago porttitor nequese blandit.</a>
						</p>
					</li>  -->
				</ul>
				<jsp:invoke fragment="colonne_1" />
			</div>
		</div>
	</div>
	
	<div id="footer">
		<p>Projet Exploitation Minières pour NSY135.</p>
	</div>
</body>
</html>
