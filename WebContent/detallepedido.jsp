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

<title>Pedidos | ChocoLuxury</title>


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
.volver {
	padding-left: 0.5em;
	padding-bottom: 3em;
}

.agregar {
	text-align: center;
	padding-bottom: 2em;
}
</style>

</head>

<body class="sub_page">

	<%@ page import="servicios.ServicioLinPed"%>
	<%@ page import="java.util.List"%>
	<%@ page import="java.util.ArrayList"%>
	<%@ page import="domain.LinPed"%>
	<%@ page import="domain.Empleado"%>
	<%@ page import="domain.Articulo"%>
	<%@ page import="servicios.ServicioArticulo"%>
	<%@ page import="domain.EstadoPedido"%>

	<%
	HttpSession ses = request.getSession();
	ServicioLinPed sLinPed = new ServicioLinPed();
	ArrayList<LinPed> lineas = new ArrayList<LinPed>();
	Empleado empleado = null;
	Articulo articulo = null;
	ServicioArticulo sArticulo = new ServicioArticulo();
	%>

	<%
	if (ses.getAttribute("empleado") != null) {
		// si es empleado
		empleado = (Empleado) ses.getAttribute("empleado");
	} else {
		response.sendRedirect("login.jsp");
	}
	
	String param = request.getParameter("codPed");
	int codPed = Integer.parseInt(param);
	
	lineas = sLinPed.recuperarTodosLinPedById(codPed);
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

		<table class='table borderless' id='tabla' name='tabla'>
			<thead>
				<tr>
					<th>ID</th>
					<th>Articulo</th>
					<th>Cantidad</th>
				</tr>
			</thead>
			<tbody>
				<%
				for (int i = 0; i < lineas.size(); i++) {
					out.println("<tr>");
					out.println("<td>" + lineas.get(i).getPedido().getCodped() + "</td>");
					
					int codArt = lineas.get(i).getArticulo().getCodArt();
					articulo = sArticulo.recuperarArticuloById(codArt);
					
					out.println("<td>" + articulo.getNombreArt() + "</td>");
					out.println("<td>" + lineas.get(i).getCantidad() + "</td>");
					out.println("</tr>");
				}
				%>
			</tbody>
		</table>

	</div>
</body>
</html>