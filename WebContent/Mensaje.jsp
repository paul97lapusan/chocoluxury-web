<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>
<head>
<!-- Basic -->
  <meta charset="utf-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge" />
  <!-- Mobile Metas -->
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
  <!-- Site Metas -->
  <meta name="keywords" content="" />
  <meta name="description" content="" />
  <meta name="author" content="" />

  <title>ChocoLuxury</title>


  <!-- bootstrap core css -->
  <link rel="stylesheet" type="text/css" href="css/bootstrap.css" />
  <!--slick slider stylesheet -->
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.5.9/slick.min.css" />
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.5.9/slick-theme.min.css" />

  <!-- fonts style -->
  <link href="https://fonts.googleapis.com/css?family=Poppins:400,600,700&display=swap" rel="stylesheet" />
  <!-- slick slider -->

  <link rel="stylesheet" href="css/slick-theme.css" />
  <!-- font awesome style -->
  <link href="css/font-awesome.min.css" rel="stylesheet" />
  <!-- Custom styles for this template -->
  <link href="css/style.css" rel="stylesheet" />
  <!-- responsive style -->
  <link href="css/responsive.css" rel="stylesheet" />
</head>

<style>
  	.centrado {
  		text-align:center;
  	}
  </style>
<body>

<%
String mensaje = "";
mensaje = request.getParameter("mensaje");
		
//registrar
if(mensaje.equals("1")){
	out.println("<div class='centrado'>");
	out.println("<section class='contact_section layout_padding'>");
	out.println("<h1>Cliente registrado correctamente</h1>");
	out.println("<button type='button'><a href='login.jsp'>Iniciar sesión</a></button>");
	out.println("</section>");
	out.println("</div>");
}else if(mensaje.equals("2")){
	out.println("<div class='centrado'>");
	out.println("<section class='contact_section layout_padding'>");
	out.println("<h1>Este email ya está en uso</h1>");
	out.println("<button type='button'><a href='login.jsp'>Iniciar sesión</a></button>");
	out.println("<button type='button'><a href='registro.jsp'>Registrarse</a></button>");
	out.println("</section>");
	out.println("</div>");
}
// editar perfil
else if (mensaje.equals("3")){
	out.println("<div class='centrado'>");
	out.println("<section class='contact_section layout_padding'>");
	out.println("<h1>Modificado correctamente</h1>");
	out.println("<button type='button'><a href='panelcontrol.jsp'>Panel de control</a></button>");
	out.println("</section>");
	out.println("</div>");
}
else if (mensaje.equals("4")) {
	out.println("<div class='centrado'>");
	out.println("<section class='contact_section layout_padding'>");
	out.println("<h1>Este email ya está en uso</h1>");
	out.println("<button type='button'><a href='editarperfil.jsp'>Editar perfil</a></button>");
	out.println("<button type='button'><a href='paneldecontrol.jsp'>Panel de control</a></button>");
	out.println("</section>");
	out.println("</div>");
}

// pedido exito
else if (mensaje.equals("5")) {
	out.println("<div class='centrado'>");
	out.println("<section class='contact_section layout_padding'>");
	out.println("<h1>Pedido realizado con éxito</h1>");
	out.println("<button type='button'><a href='panelcontrol.jsp'>Ver mis pedidos</a></button>");
	out.println("</section>");
	out.println("</div>");	
}

%>

</body>
</html>