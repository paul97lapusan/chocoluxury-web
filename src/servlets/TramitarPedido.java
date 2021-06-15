package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.Articulo;
import domain.Cliente;
import domain.LinPed;
import domain.Pedido;
import exceptions.DomainException;
import exceptions.ServiceException;
import servicios.ServicioArticulo;

/**
 * Servlet implementation class TramitarPedido
 */
@WebServlet("/TramitarPedido")
public class TramitarPedido extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TramitarPedido() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String[] cantidad;
		Cliente cliente;
		Pedido pedido;

		try {
			HttpSession ses = request.getSession();
			// si eres empleado no puedes tramitar pedidos
			if (ses.getAttribute("empleado") != null) {
				getServletContext().getRequestDispatcher(
						"/productos.jsp?error=No puedes comprar desde tu cuenta de empleado. Accede como cliente si deseas comprar.")
						.forward(request, response);
				// si no esta registrado, que se registre
			} else if (ses.getAttribute("cliente") == null) {
				getServletContext().getRequestDispatcher(
						"/registro.jsp?error=Para tramitar pedidos en la web, necesita estar registrado, cree una cuenta.")
						.forward(request, response);
			} else {
				ArrayList<LinPed> carrito = (ArrayList<LinPed>) ses.getAttribute("carrito");

				if (carrito == null) {
					response.sendRedirect("carrito.jsp");
				}

				cliente = (Cliente) ses.getAttribute("cliente");
				pedido = new Pedido(cliente);

				for (int i = 0; i < carrito.size(); i++) {
					cantidad = request.getParameterValues("cantidad");
					int cant = Integer.parseInt(cantidad[i]);
					carrito.get(i).setCantidad(cant);
					carrito.get(i).setPedido(pedido);
				}
				
				String importestring = request.getParameter("total");
				Double importe = Double.parseDouble(importestring);
				ses.setAttribute("importe", importe);
				

				response.sendRedirect("confirmarpedido.jsp");
			}
		} catch (DomainException e) {
			if (e.getCause() == null) {
				getServletContext().getRequestDispatcher("/productos.jsp?error=" + e.getMessage() + " ")
						.forward(request, response);
			} else {
				e.printStackTrace();
				getServletContext().getRequestDispatcher("/productos.jsp?error=Error Interno").forward(request,
						response);
			}
		}

	}
}
