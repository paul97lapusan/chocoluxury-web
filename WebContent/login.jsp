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

<title>Accede a tu cuenta | ChocoLuxury</title>


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
</style>

</head>

<body class="sub_page">

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
							if (ses.getAttribute("perfil") != null) {
								response.sendRedirect("index.jsp");
							} 
							%>

							<%
							String error = "";
							error = request.getParameter("error");
							if (error != null) {
								out.println("<div class='incorrecto'>");
								out.println("<h2>" + error + "</h2>");
								out.println("</div>");
							}
							%>

							<div class="heading_container">
								<h2>Iniciar sesión</h2>
							</div>
							<form action="ValidarLogin" method="post">
								<div>
									<input type="text" name="email" placeholder="Email" />
								</div>
								<div>
									<input type="password" name="clave" placeholder="Contraseña" />
								</div>
								<div class="d-flex ">
									<button type="submit" name="login">Entrar</button>
								</div>
							</form>
							<br>
							<p>¿Eres un nuevo cliente?</p>
							<button type="button">
								<a href="registro.jsp">Registrate</a>
							</button>
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
	</div>

</body>
</html>