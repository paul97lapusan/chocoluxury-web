package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Empleado;
import domain.PerfilEmpleado;
import exceptions.DomainException;
import exceptions.ServiceException;
import servicios.ServicioCliente;
import servicios.ServicioEmpleado;

/**
 * Servlet implementation class AgregarEmpleado
 */
@WebServlet("/AgregarEmpleado")
public class AgregarEmpleado extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AgregarEmpleado() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ServicioEmpleado sEmpleado;
		ServicioCliente sCliente;

		Empleado empleado;
		PerfilEmpleado perfilEmp;

		String dni = "";
		String email = "";
		String clave = "";
		String nombre = "";
		String telefono = "";
		String perfil = "";

		boolean coincide = false;

		try {
			sEmpleado = new ServicioEmpleado();

			dni = request.getParameter("dni");
			email = request.getParameter("email");
			clave = request.getParameter("clave");
			nombre = request.getParameter("nombre");
			telefono = request.getParameter("telefono");
			perfil = request.getParameter("perfil");
			
			boolean esNumerico =  perfil.matches("[+-]?\\d*(\\.\\d+)?");
			
			if (!esNumerico) {
				getServletContext().getRequestDispatcher("/agregarempleado.jsp?error='El campo perfil tiene que ser numerico'")
				.forward(request, response);
			}
			
			int perfilId = Integer.parseInt(perfil);
			
			if (perfilId < 1 || perfilId >3) {
				getServletContext().getRequestDispatcher("/agregarempleado.jsp?error='El campo perfil solo admite 1,2 y 3'")
				.forward(request, response);
			}
			perfilEmp = new PerfilEmpleado(perfilId);

			empleado = new Empleado(0, dni, email, clave, nombre, telefono, perfilEmp);

			sCliente = new ServicioCliente();
			coincide = sCliente.existeEmail(email);
			
			if (coincide) {
				getServletContext().getRequestDispatcher("/Mensaje.jsp?mensaje=4").forward(request, response);
			} else {
				sEmpleado.insertarEmpleado(empleado);
				getServletContext().getRequestDispatcher("/verempleados.jsp").forward(request, response);
			}

		} catch (ServiceException | DomainException e) {
			if (e.getCause() == null) {
				getServletContext().getRequestDispatcher("/agregarempleado.jsp?error=" + e.getMessage() + " ")
						.forward(request, response);
			} else {
				e.printStackTrace();
				getServletContext().getRequestDispatcher("/agregarempleado.jsp?error=Error Interno").forward(request,
						response);
			}

		}

	}

}
