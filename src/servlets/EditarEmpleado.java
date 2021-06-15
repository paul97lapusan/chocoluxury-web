package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.Cliente;
import domain.Empleado;
import domain.PerfilEmpleado;
import exceptions.DomainException;
import exceptions.ServiceException;
import servicios.ServicioCliente;
import servicios.ServicioEmpleado;

/**
 * Servlet implementation class EditarEmpleado
 */
@WebServlet("/EditarEmpleado")
public class EditarEmpleado extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditarEmpleado() {
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
		ServicioEmpleado sEmpleado;
		ServicioCliente sCliente; 
		
		Empleado empleado;
		Empleado empleadoNuevo;
		PerfilEmpleado perfilEmp;
		
		String dni = "";
		String nombre = "";
		String email = "";
		String telefono = "";
		String perfil;

		boolean coincide = false;

		try {
			sEmpleado = new ServicioEmpleado();
			
			dni = request.getParameter("dni");
			nombre = request.getParameter("nombre");
			email = request.getParameter("email");
			telefono = request.getParameter("telefono");
			perfil = request.getParameter("perfil");
			
			int perfilId = Integer.parseInt(perfil);
			perfilEmp = new PerfilEmpleado(perfilId);
			
			String param = request.getParameter("codEmp");
			int codEmp = Integer.parseInt(param);
			
			empleado = sEmpleado.recuperarEmpleadoCompletoById(codEmp);
			
			empleadoNuevo = new Empleado(codEmp, dni, email, empleado.getClave(), nombre, telefono, perfilEmp);
			
			String emailPrevio = empleado.getEmail();
			
			// si se ha modificado el email en el formulario, se comprueba que el nuevo email no este repe
			if (!emailPrevio.equals(email)) {
				sCliente = new ServicioCliente();
				coincide=sCliente.existeEmail(email);	
				if (coincide) {
					getServletContext().getRequestDispatcher("/Mensaje.jsp?mensaje=4").forward(request, response);
				} else {
					sEmpleado.modificarEmpleado(empleadoNuevo);
					getServletContext().getRequestDispatcher("/Mensaje.jsp?mensaje=3").forward(request, response);
				}
			} else {
				sEmpleado.modificarEmpleado(empleadoNuevo);
				getServletContext().getRequestDispatcher("/Mensaje.jsp?mensaje=3").forward(request, response);
			}

		} catch (ServiceException | DomainException e) {
			if (e.getCause() == null) {
				getServletContext().getRequestDispatcher("/editarempleado.jsp?error=" + e.getMessage() + " ")
						.forward(request, response);
			} else {
				e.printStackTrace();
				getServletContext().getRequestDispatcher("/editarempleado.jsp?error=Error Interno").forward(request,
						response);
			}

		}
	}

}
