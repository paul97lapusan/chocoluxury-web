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

<title>Editar perfil | ChocoLuxury</title>


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

<body class="sub_page">

	<%@ page import="servicios.ServicioCliente"%>
	<%@ page import="servicios.ServicioEmpleado"%>
	<%@ page import="domain.Cliente"%>
	<%@ page import="domain.Empleado"%>

	<%
	HttpSession ses = request.getSession();
	ServicioCliente sCliente;
	ServicioEmpleado sEmpleado;
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

							<%
							if (ses.getAttribute("cliente") == null && ses.getAttribute("empleado") == null) {
								response.sendRedirect("login.jsp");
							}
							%>


							<div class="heading_container">
								<h2>Editar perfil</h2>
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
								<form action='EditarPerfil' method='post'>
									<%
									if (ses.getAttribute("cliente") != null) {
										sCliente = new ServicioCliente();
										Cliente cliente;
										cliente = (Cliente) ses.getAttribute("cliente");

										out.println(
										"<i class='fa fa-id-card-o' aria-hidden='true'></i><label><b>Nombre</b></label> <input type='text' name='nombre' value='"
												+ cliente.getNombre() + "'>");

										out.println(
										"<i class='fa fa-envelope-o' aria-hidden='true'></i><label><b>Email</b></label> <input type='text' name='email' value='"
												+ cliente.getEmail() + "'>");

										out.println(
										"<i class='fa fa-key' aria-hidden='true'></i><label><b>Clave</b></label> <input type='text' name='clave' value='"
												+ cliente.getClave() + "'>");

										String telefono = cliente.getTelefono();
										if (telefono == null) {
											out.println(
											"<i class='fa fa-phone' aria-hidden='true'></i><label><b>Telefono</b></label> <input type='text' name='telefono'>");
										} else {
											out.println(
											"<i class='fa fa-phone' aria-hidden='true'></i><label><b>Telefono</b></label> <input type='text' name='telefono' value='"
													+ cliente.getTelefono() + "'>");
										}

										String direccion = cliente.getDireccion();
										if (direccion == null) {
											out.println(
											"<i class='fa fa-location-arrow' aria-hidden='true'></i><label><b>Direccion</b></label> <input type='text' name='direccion'>");
										} else {
											out.println(
											"<i class='fa fa-location-arrow' aria-hidden='true'></i><label><b>Direccion</b></label> <input type='text' name='direccion' value='"
													+ cliente.getDireccion() + "'>");
										}
									} else if (ses.getAttribute("empleado") != null) {
										sEmpleado = new ServicioEmpleado();
										Empleado empleado;
										empleado = (Empleado) ses.getAttribute("empleado");

										// si es admin puede modificar su nombre y ver/modificar su dni
										if (empleado.getPerfil().getIdPerfil() == 1) {
											out.println(
											"<i class='fa fa-id-badge' aria-hidden='true'></i><label><b>DNI</b></label> <input type='text' name='dni' value='"
													+ empleado.getDni() + "'>");
											out.println(
													"<i class='fa fa-id-card-o' aria-hidden='true'></i><label><b>Nombre</b></label> <input type='text' name='nombre' value='"
															+ empleado.getNombre() + "'>");
										} else {
											out.println(
											"<i class='fa fa-id-card-o' aria-hidden='true'></i><label><b>Nombre</b></label> <input type='text' name='nombre' value='"
													+ empleado.getNombre() + "' readonly>");
										}

										out.println(
										"<i class='fa fa-envelope-o' aria-hidden='true'></i><label><b>Email</b></label> <input type='text' name='email' value='"
												+ empleado.getEmail() + "'>");

										out.println(
										"<i class='fa fa-key' aria-hidden='true'></i><label><b>Clave</b></label> <input type='text' name='clave' value='"
												+ empleado.getClave() + "'>");

										String telefono = empleado.getTelefono();
										if (telefono == null) {
											out.println(
											"<i class='fa fa-phone' aria-hidden='true'></i><label><b>Telefono</b></label> <input type='text' name='telefono'>");
										} else {
											out.println(
											"<i class='fa fa-phone' aria-hidden='true'></i><label><b>Telefono</b></label> <input type='text' name='telefono' value='"
													+ empleado.getTelefono() + "'>");
										}
									}
									%>

									<div class="d-flex ">
										<button type="submit" name="editarperfil">Editar
											perfil</button>
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