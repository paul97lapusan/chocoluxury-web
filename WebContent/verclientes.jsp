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

<title>Clientes | ChocoLuxury</title>


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

	<%@ page import="servicios.ServicioCliente"%>
	<%@ page import="java.util.List"%>
	<%@ page import="java.util.ArrayList"%>
	<%@ page import="domain.Cliente"%>
	<%@ page import="domain.Empleado"%>

	<%
	HttpSession ses = request.getSession();
	ServicioCliente sCliente;
	ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();
	Cliente cliente = null;
	Empleado empleado = null;
	sCliente = new ServicioCliente();
	listaClientes = sCliente.recuperarTodosCliente();
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

		<table class='table borderless' id='tabla' name='tabla'>
			<thead>
				<tr>
					<th>Nombre</th>
					<th>Email</th>
					<th>Clave</th>
					<th>Telefono</th>
					<th>Direccion</th>
					<th></th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<%
				for (int i = 0; i < listaClientes.size(); i++) {
					out.println("<tr>");
					out.println("<td>" + listaClientes.get(i).getNombre() + "</td>");
					out.println("<td>" + listaClientes.get(i).getEmail() + "</td>");
					out.println("<td>" + listaClientes.get(i).getClave() + "</td>");
					if (listaClientes.get(i).getTelefono() == null) {
						out.println("<td></td>");
					} else {
						out.println("<td>" + listaClientes.get(i).getTelefono() + "</td>");
					}
					if (listaClientes.get(i).getDireccion() == null) {
						out.println("<td></td>");
					} else {
						out.println("<td>" + listaClientes.get(i).getDireccion() + "</td>");
					}
					out.println("<td><a href='editarcliente.jsp?email=" + listaClientes.get(i).getEmail()
					+ "'> <i class='fa fa-pencil' aria-hidden='true'></i></a></td>");
					
					out.println("<td><a href='BorrarCliente?codCli=" + listaClientes.get(i).getCodCli()
					+ "'> <i class='fa fa-trash-o' aria-hidden='true'></i></a></td>");
					}
					out.println("</tr>");
					
				%>
			</tbody>
		</table>

	</div>
</body>
</html>