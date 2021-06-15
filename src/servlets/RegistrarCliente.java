package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Cliente;
import exceptions.DomainException;
import exceptions.ServiceException;
import servicios.ServicioCliente;

/**
 * Servlet implementation class RegistrarCliente
 */
@WebServlet("/RegistrarCliente")
public class RegistrarCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegistrarCliente() {
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
		String nombre = "";
		String email = "";
		String clave = "";
		String telefono = "";
		String direccion = "";
		boolean coincide=false;

		try {
			sCliente = new ServicioCliente();
			nombre = request.getParameter("nombre");
			email = request.getParameter("email");
			clave = request.getParameter("clave");
			telefono = request.getParameter("telefono");
			direccion = request.getParameter("direccion");

			Cliente cliente = new Cliente(nombre, telefono, direccion, email, clave);
			
			coincide=sCliente.existeEmail(email);
			
			if (coincide) {
				getServletContext().getRequestDispatcher("/Mensaje.jsp?mensaje=2").forward(request, response);
			} else {
				sCliente.insertarCliente(cliente);
				getServletContext().getRequestDispatcher("/Mensaje.jsp?mensaje=1").forward(request, response);
			}	
		} catch (ServiceException | DomainException e) {
			if (e.getCause() == null) {
				getServletContext().getRequestDispatcher("/registro.jsp?error=" + e.getMessage() + " ").forward(request,
						response);
			} else {
				e.printStackTrace();
				getServletContext().getRequestDispatcher("/registro.jsp?error=Error Interno").forward(request,
						response);
			}

		}
	}
}
