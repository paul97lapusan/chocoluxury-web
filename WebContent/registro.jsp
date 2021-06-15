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

<title>Registro | ChocoLuxury</title>


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

<!-- JavaScript funciones y validaciones -->
<script src='js/validar.js'></script>
<script src='js/funciones.js'></script>


<style>
.centrado {
	text-align: center;
	color: red;
	font-family: "Monaco";
}

.obligatorio {
	display: none;
	color: red;
	font-family: "Monaco";
	font-size: 90%;
	padding-bottom: 3%;
}
</style>


</head>

<body class="sub_page">

	<%
	HttpSession ses = request.getSession();
	if (ses.getAttribute("cliente") != null || ses.getAttribute("empleado") != null) {
		response.sendRedirect("panelcontrol.jsp");
	}
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
							<div class="heading_container">
								<h2>Registrate</h2>
							</div>

							<%
							String error = "";
							error = request.getParameter("error");
							if (error != null) {
								out.println("<div class='centrado'>");
								out.println("<h2>" + error + "</h2>");
								out.println("</div>");
							}
							%>
							<form action="RegistrarCliente" method="post">
								<div>
									<input type="text" name="nombre"
										onblur="validarStringInside(this,1,30,errorNombre);"
										placeholder="* Nombre y apellidos" />
									<p class="obligatorio" id="errorNombre">Introduzca un
										nombre correcto.</p>
								</div>
								<div>
									<input type="text" name="email"
										onblur="revisarEmail(this,1,50,errorEmail);"
										placeholder="* Email" />
									<p class="obligatorio" id="errorEmail">Introduzca un email
										correcto.</p>
								</div>
								<div>
									<input type="password" name="clave"
										onblur="validarStringInside(this,1,30,errorClave);"
										placeholder="* Contraseña" />
								</div>
								<div>
									<input type="text" name="telefono" placeholder="Telefono" />
								</div>
								<div>
									<input type="text" name="direccion" placeholder="Direccion" />
								</div>
								<div class="d-flex ">
									<button type="submit" name="registro">Crear cuenta</button>
								</div>
							</form>
							<br>
							<p>Ya tengo una cuenta</p>
							<button type="button">
								<a href="login.jsp">Iniciar sesión</a>
							</button>
						</div>
					</div>
				</div>
			</div>
		</section>


	</div>


</body>
</html>