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

<title>Panel de control | ChocoLuxury</title>


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
	padding-top: 1em;
	padding-bottom: 2em;
}
</style>

</head>

<body class="sub_page">
	
	<%@ page import="servicios.ServicioCliente"%>
	<%@ page import="servicios.ServicioEmpleado"%>
	<%@ page import="domain.Cliente"%>
	<%@ page import="domain.Empleado"%>
	<%
	HttpSession ses = request.getSession();
	%>

	<div class="main_body_content">
		<div class="centrado">
			<a class="navbar-brand" href="index.jsp"> Pasteleria ChocoLuxury </a>
		</div>

		<section class="contact_section layout_padding">
			<div class="container-fluid">
				<div class="row">
					<div class="col-md-5 col-lg-4 offset-md-1 offset-lg-2">
						<div class="form_container">

							<%
							if (ses.getAttribute("cliente") == null && ses.getAttribute("empleado")== null) {
								response.sendRedirect("login.jsp");
							}
							%>


							<div class="heading_container">
								<h2>Panel de control</h2>
							</div>

							<div class="nombre">
								<%
								if (ses.getAttribute("cliente") != null) {
									Cliente cliente;
									cliente = (Cliente) ses.getAttribute("cliente");
									String[] nombre = cliente.getNombre().split(" ");
									out.println("Hola, " + nombre[0]);
								} else if (ses.getAttribute("empleado") != null){
									Empleado empleado;
									empleado = (Empleado) ses.getAttribute ("empleado");
									String[] nombre = empleado.getNombre().split(" ");
									out.println("Hola, " + nombre[0]);
								}
								%>
								
								
							</div>

							<h4>Acciones</h4>
							
							<div class="acciones">
							<a href="editarperfil.jsp">
								<i class="fa fa-pencil-square-o " aria-hidden="true"></i>
								<span>Editar perfil</span>
							</a><br>
							
							
							<% if (ses.getAttribute("empleado") != null) {
								// si es empleado
								Empleado empleado;
								empleado = (Empleado) ses.getAttribute("empleado");
								out.println("<br>");
								
								//si es admin
								if (empleado.getPerfil().getIdPerfil() == 1) {
									out.println("<a href='verpedidos.jsp'>");
									out.println("<i class='fa fa-credit-card' aria-hidden='true'></i>");
									out.println("<span>Ver pedidos</span>");
									out.println("</a><br>");
									
									out.println("<a href='verempleados.jsp'>");
									out.println("<i class='fa fa-briefcase' aria-hidden='true'></i>");
									out.println("<span>Ver empleados</span>");
									out.println("</a><br>");
									
									out.println("<a href='verclientes.jsp'>");
									out.println("<i class='fa fa-user-o' aria-hidden='true'></i>");
									out.println("<span>Ver clientes</span>");
									out.println("</a><br>");
									
									out.println("<a href='verproductos.jsp'>");
									out.println("<i class='fa fa-birthday-cake' aria-hidden='true'></i>");
									out.println("<span>Ver productos</span>");
									out.println("</a><br>");
									// si es repostero
								} else if (empleado.getPerfil().getIdPerfil() == 2) {
									out.println("<a href='verpedidosrepostero.jsp'>");
									out.println("<i class='fa fa-credit-card' aria-hidden='true'></i>");
									out.println("<span>Ver pedidos</span>");
									out.println("</a><br>");
									// si es repartidor
								} else if (empleado.getPerfil().getIdPerfil() == 3) {
									out.println("<a href='verpedidosrepartidor.jsp'>");
									out.println("<i class='fa fa-credit-card' aria-hidden='true'></i>");
									out.println("<span>Ver pedidos</span>");
									out.println("</a><br>");
								}
							} else if (ses.getAttribute("cliente") != null) {
								// si es cliente
								Cliente cliente;
								cliente = (Cliente) ses.getAttribute("cliente");
								out.println("<br>");
								
								out.println("<a href='verpedidoscliente.jsp'>");
								out.println("<i class='fa fa-credit-card' aria-hidden='true'></i>");
								out.println("<span>Mis pedidos</span>");
								out.println("</a><br>");
								
							}
							%>
							
							
							
							</div>

							<form action="CerrarSesion">
								<div class="d-flex ">
									<button type="submit" name="cerrarsesion">Cerrar
										sesión</button>
								</div>
							</form>

						</div>
					</div>
				</div>
			</div>
		</section>
	</div>

</body>
</html>