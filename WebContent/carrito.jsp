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

<title>Carrito | ChocoLuxury</title>


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

<style>
.exito {
	text-align: center;
	color: green;
	font-family: "Monaco";
}

.sinborde {
	border: 0;
	padding-bottom: 0.5em;
}

.importe {
	text-align: right;
	float: right;
	padding-bottom: 2em;
}

table {
	padding-top: 2em;
}

.vacio {
	padding-bottom: 7em;
}

.volver {
	padding-left: 0.5em;
}
</style>

<script>
	function calcularTotal() {
		var table = document.getElementById("tabla");
		var cajatotal = document.getElementById("total");
		var contador = 0;

		for (i = 1; i < table.rows.length; i++) {
			var valortexto = table.rows[i].cells[3].innerText;
			var valor = parseFloat(valortexto);
			var cantidadtexto = table.rows[i].cells[2].firstChild.value;
			var cantidad = parseInt(cantidadtexto);
			contador = contador + valor * cantidad;
		}
		contador = Math.round(contador * 100) / 100;
		cajatotal.value = contador;

	}
</script>


</head>

<body class="sub_page" onload="calcularTotal()">

	<%@ page import="servicios.ServicioArticulo"%>
	<%@ page import="java.util.List"%>
	<%@ page import="java.util.ArrayList"%>
	<%@ page import="domain.Articulo"%>
	<%@ page import="domain.LinPed"%>

	<%
	HttpSession ses = request.getSession();
	ServicioArticulo sArticulo;
	ArrayList<LinPed> carrito;

	carrito = (ArrayList<LinPed>) ses.getAttribute("carrito");
	
	if (carrito == null) {
		carrito = new ArrayList<LinPed>();
		ses.setAttribute("carrito", carrito);
	}
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


		<div class="col-md-5 col-lg-4 offset-md-1 offset-lg-2">
			<div class="heading_container">
				<h2>Tu carrito</h2>
			</div>

			<%
			if (carrito.size()<1) {
				out.println("<div class='vacio'>Tu carrito de Compras vive para servir. Dale propósito, llénalo comprando en <a href='productos.jsp'>ChocoLuxury</a></div>");
			} else {
				out.println("<form action='TramitarPedido' method='post'>");
				out.println("<div class='importe'");
				out.println("<label class='linea'>TOTAL: </label>");
				out.println("<input class='sinborde' type='number' id='total' name='total' readonly> &euro;");
				out.println("<button type='submit' name='tramitarpedido' class='btn btn-success'>TRAMITAR PEDIDO</button>");
				out.println("</div>");
				out.println("<table class='table borderless' id='tabla' name='tabla'>");
				out.println("<thead>");
				out.println("<tr>");
				out.println("<th>Articulo</th>");
				out.println("<th></th>");
				out.println("<th>Cantidad</th>");
				out.println("<th>Precio/u</th>");
				out.println("<th></th>");
				out.println("</tr>");
				out.println("</thead>");
				out.println("<tbody>");
				for (int i = 0; i < carrito.size(); i++) {
					out.println("<tr>");
					out.println("<td><img src='" + carrito.get(i).getArticulo().getImagen() + "'></td>");
					out.println("<td>" + carrito.get(i).getArticulo().getNombreArt() + "</td>");
					out.println("<td><input type='number' name='cantidad' min='1' max='" + carrito.get(i).getArticulo().getStock()
					+ "' value='" +  carrito.get(i).getCantidad()  + "' onchange='calcularTotal();'</td>");
					out.println("<td>" + carrito.get(i).getArticulo().getPrecioVenta() + "&euro;</td>");
					out.println("<td><a href='BorrarCesta?codArt=" + carrito.get(i).getArticulo().getCodArt() + "' class='btn btn-danger'><i class='fa fa-trash-o' aria-hidden='true'></i></a></td>");
					out.println("</tr>");
				}
				out.println("</tbody>");
				out.println("</table>");
				out.println("</form>");
			}
			
			%>

		</div>

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