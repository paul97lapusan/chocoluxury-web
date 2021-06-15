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

<title>Editar producto | ChocoLuxury</title>


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
.error {
	color: red;
}

.centrado {
	text-align: center;
}

.incorrecto {
	text-align: center;
	color: red;
	font-family: "Monaco";
}

.nombre {
	padding-bottom: 2em;
}

.acciones {
	padding-top: 0.5em;
}

i {
	margin-right: 0.5em;
}

.volver {
	padding-left: 0.5em;
}
</style>
</head>
<body>

	<%@ page import="servicios.ServicioArticulo"%>
	<%@ page import="java.util.List"%>
	<%@ page import="java.util.ArrayList"%>
	<%@ page import="domain.Empleado"%>
	<%@ page import="domain.Articulo"%>

	<%
	HttpSession ses = request.getSession();
	ServicioArticulo sArticulo;
	Empleado empleado = null;
	Articulo articulo = null;
	%>

	<%
	if (ses.getAttribute("empleado") != null) {
		// si es empleado
		empleado = (Empleado) ses.getAttribute("empleado");
		//si no es admin
		if (empleado.getPerfil().getIdPerfil() != 1) {
			response.sendRedirect("login.jsp");
		}
	} else {
		response.sendRedirect("login.jsp");
	}
	%>

	<div class="main_body_content">
		<div class="centrado">
			<a class="navbar-brand" href="index.jsp"> Pasteleria ChocoLuxury
			</a>
		</div>

		<div class="volver">
			<a href="panelcontrol.jsp"><i class="fa fa-arrow-left"
				aria-hidden="true"></i> Panel de control</a>
		</div>

		<section class="contact_section layout_padding">
			<div class="container-fluid">
				<div class="row">
					<div class="col-md-5 col-lg-4 offset-md-1 offset-lg-2">
						<div class="form_container">

							<div class="heading_container">
								<h2>Editar producto</h2>
							</div>

							<div class="error">
								<%
								String error = "";
								error = request.getParameter("error");
								if (error != null) {
									out.println("<div class='centrado'>");
									out.println("<h2>" + error + "</h2>");
									out.println("</div>");
								}
								%>
							</div>

							<div class="acciones">
								<form action='EditarProducto' method='post'>
									<%
									String parametro = request.getParameter("codArt");
									int codArt = Integer.parseInt(parametro);
							
									sArticulo = new ServicioArticulo();

									articulo = sArticulo.recuperarArticuloById(codArt);

									out.println("<input type='hidden' name='codArt' value='" + articulo.getCodArt() + "'>");

									out.println(
											"<i class='fa fa-id-card-o' aria-hidden='true'></i><label><b>Nombre</b></label> <input type='text' name='nombre' value='"
											+ articulo.getNombreArt() + "'>");
									
									String descripcion = articulo.getDescripcion();
									if (descripcion == null) {
										out.println("<i class='fa fa-sticky-note' aria-hidden='true'></i><label><b>Descripcion</b></label> <input type='text' name='descripcion'>");
									} else {
										out.println(
												"<i class='fa fa-sticky-note' aria-hidden='true'></i><label><b>Descripcion</b></label> <input type='text' name='descripcion' value='"
												+ articulo.getDescripcion() + "'>");
									}
									
									out.println(
											"<i class='fa fa-eur' aria-hidden='true'></i><label><b>Precio venta</b></label> <input type='text' name='precio' value='" 
												+ articulo.getPrecioVenta() + "'>");
									
									out.println(
											"<i class='fa fa-cubes' aria-hidden='true'></i><label><b>Stock</b></label> <input type='text' name='stock' value='" 
												+ articulo.getStock() + "'>");
									
									out.println(
											"<i class='fa fa-user' aria-hidden='true'></i><label><b>Imagen</b></label> <input type='text' name='imagen' value='" 
												+ articulo.getImagen() + "'>");
									%>

									<div class="d-flex ">
										<button type="submit" name="editarproducto">Editar
											producto</button>
									</div>


								</form>
							</div>


						</div>
					</div>
				</div>
			</div>
		</section>
	</div>

</body>
</html>