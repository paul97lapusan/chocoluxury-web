package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.Articulo;
import domain.Cliente;
import domain.Empleado;
import domain.LinPed;
import exceptions.DomainException;
import exceptions.ServiceException;
import servicios.ServicioArticulo;
import servicios.ServicioCliente;
import servicios.ServicioEmpleado;

/**
 * Servlet implementation class AgregarCesta
 */
@WebServlet("/AgregarCesta")
public class AgregarCesta extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AgregarCesta() {
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
		String codArt;
		Articulo articulo;
		LinPed linped;
		ServicioArticulo sArticulo;
		boolean existe = false;

		try {
			HttpSession ses = request.getSession();
			// solo se usa el carrito si no estas log o eres un cliente, si eres empleado
			// no.
			if (ses.getAttribute("empleado") != null) {
				getServletContext().getRequestDispatcher(
						"/productos.jsp?error=No puedes comprar desde tu cuenta de empleado. Accede como cliente si deseas comprar.")
						.forward(request, response);
			} else {
				codArt = request.getParameter("codArt");
				int cod = Integer.parseInt(codArt);
				ArrayList<LinPed> carrito = (ArrayList<LinPed>) ses.getAttribute("carrito");

				if (carrito == null) {
					carrito = new ArrayList<LinPed>();
					ses.setAttribute("carrito", carrito);
				}

				sArticulo = new ServicioArticulo();
				articulo = sArticulo.recuperarArticuloById(cod);

				for (int i = 0; i < carrito.size(); i++) {
					if (carrito.get(i).getArticulo().getCodArt() == articulo.getCodArt()) {
						existe = true;
					}
				}

				if (!existe) {
					linped = new LinPed(articulo, 1);
					carrito.add(linped);
				}

				response.sendRedirect("carrito.jsp");
			}

		} catch (ServiceException | DomainException e) {
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
