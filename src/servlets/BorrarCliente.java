package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.Empleado;
import exceptions.DomainException;
import exceptions.ServiceException;
import servicios.ServicioCliente;
import servicios.ServicioEmpleado;

/**
 * Servlet implementation class BorrarCliente
 */
@WebServlet("/BorrarCliente")
public class BorrarCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BorrarCliente() {
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
		String codCliente;
		Empleado empleado;
		ServicioCliente sCliente;

		try {
			HttpSession ses = request.getSession();

			if (ses.getAttribute("empleado") != null) {
				// si es empleado
				empleado = (Empleado) ses.getAttribute("empleado");
				// si no es admin
				if (empleado.getPerfil().getIdPerfil() != 1) {
					response.sendRedirect("login.jsp");
				}
			} else {
				response.sendRedirect("login.jsp");
			}

			sCliente = new ServicioCliente();

			codCliente = request.getParameter("codCli");
			int codigo = Integer.parseInt(codCliente);

			sCliente.borrarCliente(codigo);

			response.sendRedirect("verclientes.jsp");

		} catch (ServiceException | DomainException e) {
			if (e.getCause() == null) {
				getServletContext().getRequestDispatcher("/verclientes.jsp?error=" + e.getMessage() + " ")
						.forward(request, response);
			} else {
				e.printStackTrace();
				getServletContext().getRequestDispatcher("/verclientes.jsp?error=Error Interno").forward(request,
						response);
			}
		}
	}

}
