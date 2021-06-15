<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
<!-- Basic -->
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<!-- Mobile Metas -->
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<!-- Site Metas -->
<meta name="keywords" content="" />
<meta name="description" content="" />
<meta name="author" content="" />

<title>Sobre nosotros | ChocoLuxury</title>


<!-- bootstrap core css -->
<link rel="stylesheet" type="text/css" href="css/bootstrap.css" />
<!--slick slider stylesheet -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.5.9/slick.min.css" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.5.9/slick-theme.min.css" />

<!-- fonts style -->
<link
	href="https://fonts.googleapis.com/css?family=Poppins:400,600,700&display=swap"
	rel="stylesheet" />
<!-- slick slider -->

<link rel="stylesheet" href="css/slick-theme.css" />
<!-- font awesome style -->
<link href="css/font-awesome.min.css" rel="stylesheet" />
<!-- Custom styles for this template -->
<link href="css/style.css" rel="stylesheet" />
<!-- responsive style -->
<link href="css/responsive.css" rel="stylesheet" />

</head>

<body class="sub_page">

	<%
	HttpSession ses = request.getSession();
	%>

	<div class="main_body_content">

		<div class="hero_area">
			<!-- header section strats -->
			<header class="header_section">
				<div class="container-fluid">
					<nav class="navbar navbar-expand-lg custom_nav-container ">
						<a class="navbar-brand" href="index.jsp"> Pasteleria ChocoLuxury
						</a> </a>
						<button class="navbar-toggler" type="button"
							data-toggle="collapse" data-target="#navbarSupportedContent"
							aria-controls="navbarSupportedContent" aria-expanded="false"
							aria-label="Toggle navigation">
							<span class=""> </span>
						</button>

						<div class="collapse navbar-collapse " id="navbarSupportedContent">
							<ul class="navbar-nav ml-auto">
								<li class="nav-item active"><a class="nav-link"
									href="index.jsp">Home <span class="sr-only">(current)</span></a>
								</li>
								<li class="nav-item"><a class="nav-link"
									href="sobrenosotros.jsp">Sobre nosotros</a></li>
								<li class="nav-item"><a class="nav-link"
									href="productos.jsp">Productos</a></li>
								<li class="nav-item"><a class="nav-link" href="resenas.jsp">Reseñas</a>
								</li>
								<li class="nav-item"><a class="nav-link"
									href="contacto.jsp">Contacto</a></li>
							</ul>
							<div class="quote_btn-container">
								<a href="carrito.jsp"> <i class="fa fa-shopping-cart"
									aria-hidden="true"></i>
								</a>
								<%
								if (ses.getAttribute("cliente") != null || ses.getAttribute("empleado") != null) {
									out.println("<a href='panelcontrol.jsp'><i class='fa fa-user' aria-hidden='true'></i></a>");
								} else {
									out.println("<a href='login.jsp'><i class='fa fa-user' aria-hidden='true'></i></a>");
								}
								%>
							</div>
						</div>
					</nav>
				</div>
			</header>
			<!-- end header section -->
		</div>

		<!-- about section -->

		<section class="about_section layout_padding ">
			<div class="container  ">
				<div class="row">
					<div class="col-md-6">
						<div class="detail-box">
							<div class="heading_container">
								<h2>Sobre nuestra empresa</h2>
							</div>
							<p>Pastelería artesanal formada por personas apasionadas de
								la buena repostería. Nuestro objetivo es ofrecer un producto de
								calidad y personalidad propia, rico en matices de sabores y
								estética.</p>
							<p>Los pilares de nuestro saber hacer son tres: la máxima
								calidad de la materia prima, la elaboración artesanal del
								producto y el cuidado de los detalles que hacen cada bocado muy
								grande.</p>
							<p>Para nosotros cuidar al máximo los detalles es importante.
								Cada pequeña acción en nuestra pastelería hace que al final
								nuestro producto sea excelente e irremplazable.</p>
							<div class="iconitos">
								<a href=""> <i class="fa fa-map-marker" aria-hidden="true"></i>
									<span> Avenida de Fuenlabrada 25 (Leganés) </span>
								</a> <i class="fa fa-clock-o" aria-hidden="true"></i> <span>
									Lunes a viernes, de 9.00 a 20.30 horas </span> <br> <i
									class="fa fa-phone" aria-hidden="true"></i> <span> 623
									456 789 </span>

							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="img-box">
							<img src="images/about-img.png" alt="">
						</div>
					</div>
				</div>
			</div>
		</section>

		<!-- end about section -->


		<!-- info section -->
		<section class="info_section layout_padding2">
			<div class="container">
				<div class="row info_form_social_row">
					<div class="col-md-8 col-lg-9">
						<div class="info_form">
							<form action="contacto.jsp">
								<input type="email"
									placeholder="Pinche en la flecha para ponerse en contacto con ChocoLuxury" readonly>
								<button>
									<i class="fa fa-arrow-right" aria-hidden="true"></i>
								</button>
							</form>
						</div>
					</div>
					<div class="col-md-4 col-lg-3">

						<div class="social_box">
							<a href=""> <i class="fa fa-facebook" aria-hidden="true"></i>
							</a> <a href=""> <i class="fa fa-twitter" aria-hidden="true"></i>
							</a> <a href=""> <i class="fa fa-linkedin" aria-hidden="true"></i>
							</a>
						</div>
					</div>
				</div>
				<div class="row info_main_row">
					<div class="col-md-6 col-lg-3">
						<div class="info_links">
							<h4>Menu</h4>
							<div class="info_links_menu">
								<a href="index.jsp"> Home </a> <a href="sobrenosotros.jsp">
									Sobre nosotros </a> <a href="productos.jsp"> Productos </a> <a
									href="resenas.jsp"> Reseñas </a> <a href="contacto.jsp">
									Contacto </a>
							</div>
						</div>
					</div>
					<div class="col-md-6 col-lg-3">
						<div class="info_insta">
							<h4>Instagram</h4>
							<div class="insta_box">
								<div class="img-box">
									<img src="images/insta-img.png" alt="">
								</div>
								<div class="img-box">
									<img src="images/insta-img.png" alt="">
								</div>
							</div>
							<div class="insta_box">
								<div class="img-box">
									<img src="images/insta-img.png" alt="">
								</div>
								<div class="img-box">
									<img src="images/insta-img.png" alt="">
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-6 col-lg-3">
						<div class="info_detail">
							<h4>Horario</h4>
							<p class="mb-0">
								Lunes a Viernes de 9.00 a 20.30h <br> Estaremos encantados
								de atenderles en nuestra tienda física de la Avenida Fuenlabrada
								15, Leganés.
							</p>
						</div>
					</div>
					<div class="col-md-6 col-lg-3">
						<h4>Contactanos</h4>
						<div class="info_contact">
							<a href=""> <i class="fa fa-map-marker" aria-hidden="true"></i>
								<span> Ubicación </span>
							</a> <a href=""> <i class="fa fa-phone" aria-hidden="true"></i> <span>
									Llamanos +34 623456789 </span>
							</a> <a href=""> <i class="fa fa-envelope"></i> <span>
									ChocoLuxury@gmail.com </span>
							</a>
						</div>
					</div>
				</div>
			</div>
		</section>

		<!-- end info_section -->

	</div>

	<!-- footer section -->
	<footer class="container-fluid footer_section">
		<div class="container">
			<div class="col-md-11 col-lg-8 mx-auto">
				<p>
					&copy; <span id="displayYear"></span> Paul Lapusan

				</p>
			</div>
		</div>
	</footer>
	<!-- footer section -->

	<!-- jQery -->
	<script src="js/jquery-3.4.1.min.js"></script>
	<!-- bootstrap js -->
	<script src="js/bootstrap.js"></script>
	<!-- slick slider -->
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.5.9/slick.min.js"></script>
	<!-- custom js -->
	<script src="js/custom.js"></script>

</body>

</html>