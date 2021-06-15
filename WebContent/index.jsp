<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" session="true"%>
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

<title>ChocoLuxury</title>


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

<body>

	<%@ page import="servicios.ServicioArticulo"%>
	<%@ page import="java.util.List"%>
	<%@ page import="java.util.ArrayList"%>
	<%@ page import="domain.Articulo"%>
	
	<%
	HttpSession ses=request.getSession();
	ServicioArticulo sArticulo;
	ArrayList<Articulo> listaArticulos = new ArrayList<Articulo>();
	Articulo articulo = null;

	sArticulo = new ServicioArticulo();
	listaArticulos = sArticulo.recuperarTodosArticulo();
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
			<!-- slider section -->
			<section class="slider_section ">
				<div id="customCarousel1" class="carousel slide"
					data-ride="carousel">
					<div class="carousel-inner">
						<div class="carousel-item active">
							<div class="container">
								<div class="row">
									<div class="col-md-6">
										<div class="detail_box">
											<h1>
												Pastelería <br> <span> ChocoLuxury </span>
											</h1>
											<a href="sobrenosotros.jsp"> <span> Sobre nosotros
											</span> <img src="images/white-arrow.png" alt="">
											</a>
										</div>
									</div>
									<div class="col-md-4 ml-auto">
										<div class="img-box">
											<img src="images/slider-img.png" alt="">
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="carousel-item ">
							<div class="container">
								<div class="row">
									<div class="col-md-6">
										<div class="detail_box">
											<h2>

												<span> Ya no hay excusa para no disfrutar de ChocoLuxury
													en cualquier lugar y a cualquier hora. Entra en nuestra
													tienda online y descubre un sinfín de tentaciones con las
													que darte un capricho. </span>
											</h2>
											<a href="productos.jsp"> <span> Productos </span> <img
												src="images/white-arrow.png" alt="">
											</a>
										</div>
									</div>
									<div class="col-md-4 ml-auto">
										<div class="img-box">
											<img src="images/slider-img.png" alt="">
										</div>
									</div>
								</div>
							</div>
						</div>

					</div>
				</div>
				<div class="carousel_btn-box">
					<a class="carousel-control-prev" href="#customCarousel1"
						role="button" data-slide="prev"> <i class="fa fa-arrow-left"
						aria-hidden="true"></i> <span class="sr-only">Anterior</span>
					</a> <a class="carousel-control-next" href="#customCarousel1"
						role="button" data-slide="next"> <i class="fa fa-arrow-right"
						aria-hidden="true"></i> <span class="sr-only">Siguiente</span>
					</a>
				</div>
			</section>
			<!-- end slider section -->
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
							<a href="sobrenosotros.jsp"> <span> Leer más </span> <img
								src="images/color-arrow.png" alt="">
							</a>
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

		<!-- productos section -->

		<section class="chocolate_section ">
			<div class="container">
				<div class="heading_container">
					<h2>Nuestros productos</h2>
					<p>Ya no hay excusa para no disfrutar de ChocoLuxury en cualquier
						lugar y a cualquier hora. Entra en nuestra tienda online y
						descubre un sinfín de tentaciones con las que darte un capricho.</p>
				</div>
			</div>
			<div class="container">
				<div class="chocolate_container">
					<%
					for (int i = 0; i < listaArticulos.size(); i++) {
						out.println("<div class='box'>");
						out.println("<div class='img-box'>");
						out.println("<img src='" + listaArticulos.get(i).getImagen() + "'>");
						out.println("</div>");
						out.println("<div class='detail-box'>");
						out.println("<h6>");
						out.println(listaArticulos.get(i).getNombreArt() + " <span>ChocoLuxury</span>");
						out.println("</h6>");
						out.println("<h5>" + listaArticulos.get(i).getPrecioVenta() + "&euro;</h5>");
						out.println("<a href=''> AGREGAR A LA CESTA</a>");
						out.println("</div>");
						out.println("</div>");
					}
					%>

				</div>
			</div>
		</section>

		<!-- end productos section -->

		<!-- offer section -->

		<section class="offer_section layout_padding">
			<div class="container">
				<div class="box">
					<div class="detail-box">
						<h2>Nuestros productos</h2>
						<h3>
							Ya no hay excusa para no disfrutar de ChocoLuxury <br> en cualquier lugar. Pide a domicilio!
						</h3>
						<a href="productos.jsp"> Ver productos </a>
					</div>
					<div class="img-box">
						<img src="images/offer-img.png" alt="">
					</div>
				</div>
				<div class="btn-box">
					<a href=""> <span> Ver más productos </span> <img
						src="images/color-arrow.png" alt="">
					</a>
				</div>
			</div>
		</section>

		<!-- end offer section -->

		<!-- client section -->

		<section class="client_section">
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
								<h2>Reseñas</h2>
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
												<p>Los productos de ChocoLuxury están excelentemente ricos, 
												y en este punto de la vida el precio es un valor que para mí es algo subjetivo y siempre está supeditado al 
												sabor o al servicio; creo que yo y muchos amantes de la gastronomía o la buena vida hemos comprendido que 
												el precio es relativo cuando la satisfacción es mucha y podemos pagar lo que sea, claro, dentro de nuestro 
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
												<p>Los productos de ChocoLuxury están excelentemente ricos, 
												y en este punto de la vida el precio es un valor que para mí es algo subjetivo y siempre está supeditado al 
												sabor o al servicio; creo que yo y muchos amantes de la gastronomía o la buena vida hemos comprendido que 
												el precio es relativo cuando la satisfacción es mucha y podemos pagar lo que sea, claro, dentro de nuestro 
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
												<p>Los productos de ChocoLuxury están excelentemente ricos, 
												y en este punto de la vida el precio es un valor que para mí es algo subjetivo y siempre está supeditado al 
												sabor o al servicio; creo que yo y muchos amantes de la gastronomía o la buena vida hemos comprendido que 
												el precio es relativo cuando la satisfacción es mucha y podemos pagar lo que sea, claro, dentro de nuestro 
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


		<!-- contact section -->

		<section class="contact_section layout_padding">
			<div class="container-fluid">
				<div class="row">
					<div class="col-md-5 col-lg-4 offset-md-1 offset-lg-2">
						<div class="form_container">
							<div class="heading_container">
								<h2>Contactanos</h2>
							</div>
							<form action="">
								<div>
									<input type="text" placeholder="Nombre y apellidos" />
								</div>
								<div>
									<input type="text" placeholder="Teléfono" />
								</div>
								<div>
									<input type="email" placeholder="Email" />
								</div>
								<div>
									<input type="text" class="message-box" placeholder="Mensaje" />
								</div>
								<div class="d-flex ">
									<button>ENVIAR</button>
								</div>
							</form>
						</div>
					</div>
					<div class="col-md-6  px-0">
						<div class="map_container">
							<div class="map">
								<div id="googleMap">
									<iframe
										src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3041.440034295081!2d-3.763526585108191!3d40.33258356867768!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0xd4189f4f87291e5%3A0xc7feba3d637b83ef!2sAv.%20de%20Fuenlabrada%2C%2015%2C%2028912%20Legan%C3%A9s%2C%20Madrid!5e0!3m2!1ses!2ses!4v1614182615893!5m2!1ses!2ses"
										width="600" height="450" style="border: 0;" allowfullscreen=""
										loading="lazy"></iframe>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>

		<!-- end contact section -->


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