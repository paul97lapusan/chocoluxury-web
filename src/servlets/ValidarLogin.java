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
import servicios.ServicioCliente;
import servicios.ServicioEmpleado;

/**
 * Servlet implementation class ValidarLogin
 */
@WebServlet("/ValidarLogin")
public class ValidarLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ValidarLogin() {
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
		ServicioCliente sCliente;
		String email = "";
		String clave = "";
		boolean coincide = false;
		boolean esCliente = false;

		try {
			sCliente = new ServicioCliente();
			email = request.getParameter("email");
			clave = request.getParameter("clave");
			coincide = sCliente.verificarLogin(email, clave);

			if (coincide) {
				// AQUI CREO LA SESION
				HttpSession ses = request.getSession();

				esCliente = sCliente.esCliente(email);
				if (esCliente) {
					// le pongo el num 2 al atributo de la sesion porque 2 es el perfil del Cliente
					// en la BD
					Cliente cliente;
					cliente = sCliente.recuperarClienteCompletoByEmail(email);
					ses.setAttribute("cliente", cliente);

					// si no existe, le creamos el carrito del cliente
					ArrayList<LinPed> carrito = (ArrayList<LinPed>) ses.getAttribute("carrito");

					if (carrito == null) {
						carrito = new ArrayList<LinPed>();
						ses.setAttribute("carrito", carrito);
					}

					getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
				} else {
					// Es empleado, sacamos que tipo de empleado es para asignarle un perfil de
					// sesion
					ServicioEmpleado sEmpleado = new ServicioEmpleado();
					Empleado empleado;
					empleado = sEmpleado.recuperarEmpleadoCompletoByEmail(email);
					ses.setAttribute("empleado", empleado);

					getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
				}

			} else {
				getServletContext().getRequestDispatcher("/login.jsp?error=Tus credenciales no son correctas.")
						.forward(request, response);
				// getServletContext().getRequestDispatcher("/login.jsp").forward(request,
				// response);
			}
		} catch (ServiceException | DomainException e) {
			if (e.getCause() == null) {
				getServletContext().getRequestDispatcher("/login.jsp?error=" + e.getMessage() + " ").forward(request,
						response);
			} else {
				e.printStackTrace();
				getServletContext().getRequestDispatcher("/login.jsp?error=Error Interno").forward(request, response);
			}
		}

	}

}
