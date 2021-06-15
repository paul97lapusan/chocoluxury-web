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

<title>Historial Pedidos | ChocoLuxury</title>


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

	<%@ page import="servicios.ServicioPedido"%>
	<%@ page import="java.util.List"%>
	<%@ page import="java.util.ArrayList"%>
	<%@ page import="domain.Pedido"%>
	<%@ page import="domain.Empleado"%>
	<%@ page import="domain.Cliente"%>
	<%@ page import="servicios.ServicioCliente"%>
	<%@ page import="servicios.ServicioEstadoPedido"%>
	<%@ page import="domain.EstadoPedido"%>

	<%
	HttpSession ses = request.getSession();
	ServicioPedido sPedido;
	ArrayList<Pedido> listaPedidos = new ArrayList<Pedido>();
	Pedido pedido = null;
	Empleado empleado = null;
	sPedido = new ServicioPedido();
	listaPedidos = sPedido.recuperarTodosPedido();
	Cliente cliente = null;
	ServicioCliente sCliente = new ServicioCliente();
	EstadoPedido estado = null;
	ServicioEstadoPedido sEstado = new ServicioEstadoPedido();
	%>

	<%
	if (ses.getAttribute("empleado") != null) {
		// si es empleado
		empleado = (Empleado) ses.getAttribute("empleado");
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
			<a href="verpedidos.jsp"><i class="fa fa-arrow-left"
				aria-hidden="true"></i> Pedidos pendientes</a>
		</div>

		<table class='table borderless' id='tabla' name='tabla'>
			<thead>
				<tr>
					<th>ID</th>
					<th>Fecha</th>
					<th>Cliente</th>
					<th>Estado</th>
					<th>A domicilio</th>
					<th>Importe</th>
				</tr>
			</thead>
			<tbody>
				<%
				for (int i = 0; i < listaPedidos.size(); i++) {
					if (listaPedidos.get(i).getEstado().getIdEstado() == 4) {
						out.println("<tr>");
						out.println("<td>" + listaPedidos.get(i).getCodped() + "</td>");
						out.println("<td>" + listaPedidos.get(i).getFechaped() + "</td>");
						
						int cod_cli = listaPedidos.get(i).getCliente().getCodCli();
						int cod_est = listaPedidos.get(i).getEstado().getIdEstado();
						
						cliente = sCliente.recuperarClienteCompletoById(cod_cli);
						estado = sEstado.recuperarEstadoPedidoCompletoById(cod_est);
						
						out.println("<td>" + cliente.getNombre() + "</td>");
						out.println("<td>" + estado.getEstado() + "</td>");
						if (listaPedidos.get(i).getDomicilio() == 1) {
							out.println("<td> <i class='fa fa-check' aria-hidden='true'></i> </td>");
						} else {
							out.println("<td> <i class='fa fa-times' aria-hidden='true'></i> </td>");
						}
						out.println("<td>" + listaPedidos.get(i).getImporte() + " &euro; </td>");
						out.println("</tr>");
					}
				}
				%>
			</tbody>
		</table>

	</div>
</body>
</html>