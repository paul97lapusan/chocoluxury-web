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

<title>Rese�as | ChocoLuxury</title>


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
								<li class="nav-item"><a class="nav-link" href="resenas.jsp">Rese�as</a>
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


		<!-- client section -->

		<section class="client_section layout_padding">
			<div class="container-fluid">
				<div class="row">
					<div class="col-lg-4 ml-auto">
						<div class="img-box sub_img-box">
							<img src="images/client-chocolate.png" alt="">
						</div>
					</div>
					<div class="col-lg-6 px-0">
						<div class="client_container">
							<div class="heading_container">
								<h2>Rese�as</h2>
							</div>
							<div id="customCarousel2" class="carousel slide"
								data-ride="carousel">
								<div class="carousel-inner">
									<div class="carousel-item active">
										<div class="box">
											<div class="img-box">
												<img src="images/client-img.jpg" alt="">
											</div>
											<div class="detail-box">
												<h4>Scarlett Johansson</h4>
												<p>Los productos de ChocoLuxury est�n excelentemente ricos, 
												y en este punto de la vida el precio es un valor que para m� es algo subjetivo y siempre est� supeditado al 
												sabor o al servicio; creo que yo y muchos amantes de la gastronom�a o la buena vida hemos comprendido que 
												el precio es relativo cuando la satisfacci�n es mucha y podemos pagar lo que sea, claro, dentro de nuestro 
												presupuesto para disfrutar de algo que nos guste.</p>
												<i class="fa fa-quote-left" aria-hidden="true"></i>
											</div>
										</div>
									</div>
									<div class="carousel-item">
										<div class="box">
											<div class="img-box">
												<img src="images/client-img.jpg" alt="">
											</div>
											<div class="detail-box">
												<h4>Scarlett Johansson</h4>
												<p>Los productos de ChocoLuxury est�n excelentemente ricos, 
												y en este punto de la vida el precio es un valor que para m� es algo subjetivo y siempre est� supeditado al 
												sabor o al servicio; creo que yo y muchos amantes de la gastronom�a o la buena vida hemos comprendido que 
												el precio es relativo cuando la satisfacci�n es mucha y podemos pagar lo que sea, claro, dentro de nuestro 
												presupuesto para disfrutar de algo que nos guste.</p>
												<i class="fa fa-quote-left" aria-hidden="true"></i>
											</div>
										</div>
									</div>
									<div class="carousel-item">
										<div class="box">
											<div class="img-box">
												<img src="images/client-img.jpg" alt="">
											</div>
											<div class="detail-box">
												<h4>Scarlett Johansson</h4>
												<p>Los productos de ChocoLuxury est�n excelentemente ricos, 
												y en este punto de la vida el precio es un valor que para m� es algo subjetivo y siempre est� supeditado al 
												sabor o al servicio; creo que yo y muchos amantes de la gastronom�a o la buena vida hemos comprendido que 
												el precio es relativo cuando la satisfacci�n es mucha y podemos pagar lo que sea, claro, dentro de nuestro 
												presupuesto para disfrutar de algo que nos guste.</p>
												<i class="fa fa-quote-left" aria-hidden="true"></i>
											</div>
										</div>
									</div>
								</div>
								<div class="carousel_btn-box">
									<a class="carousel-control-prev" href="#customCarousel2"
										role="button" data-slide="prev"> <i
										class="fa fa-arrow-left" aria-hidden="true"></i> <span
										class="sr-only">Anterior</span>
									</a> <a class="carousel-control-next" href="#customCarousel2"
										role="button" data-slide="next"> <i
										class="fa fa-arrow-right" aria-hidden="true"></i> <span
										class="sr-only">Siguiente</span>
									</a>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>

		<!-- end client section -->

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
									href="resenas.jsp"> Rese�as </a> <a href="contacto.jsp">
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
								de atenderles en nuestra tienda f�sica de la Avenida Fuenlabrada
								15, Legan�s.
							</p>
						</div>
					</div>
					<div class="col-md-6 col-lg-3">
						<h4>Contactanos</h4>
						<div class="info_contact">
							<a href=""> <i class="fa fa-map-marker" aria-hidden="true"></i>
								<span> Ubicaci�n </span>
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
	<!-- Google Map -->

</body>

</html>