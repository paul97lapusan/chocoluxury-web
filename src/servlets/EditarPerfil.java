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
 * Servlet implementation class EditarPerfil
 */
@WebServlet("/EditarPerfil")
public class EditarPerfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditarPerfil() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServicioCliente sCliente;
		ServicioEmpleado sEmpleado;
		
		String dni = "";
		String email = "";
		String clave = "";
		String telefono = "";
		String nombre = "";
		String direccion = "";
		String emailPrevio = "";
		boolean coincide=false;

		try {
			HttpSession ses = request.getSession();
			
				// cliente
			if (ses.getAttribute("cliente") != null) {
				
				sCliente = new ServicioCliente();
				nombre = request.getParameter("nombre");
				email = request.getParameter("email");
				clave = request.getParameter("clave");
				telefono = request.getParameter("telefono");
				direccion = request.getParameter("direccion"); 
			
				
				Cliente cliente2 = (Cliente) ses.getAttribute("cliente");
				
				Cliente cliente = new Cliente(cliente2.getCodCli(), nombre, telefono, direccion, email, clave);
				
				emailPrevio = cliente2.getEmail();
				
				// si se ha modificado el email en el formulario, se comprueba que el nuevo email no este repe
				if (!emailPrevio.equals(email)) {
					coincide=sCliente.existeEmail(email);	
					if (coincide) {
						getServletContext().getRequestDispatcher("/Mensaje.jsp?mensaje=4").forward(request, response);
					} else {
						sCliente.modificarCliente(cliente);
						ses.setAttribute("cliente", cliente);
						getServletContext().getRequestDispatcher("/Mensaje.jsp?mensaje=3").forward(request, response);
					}
				} else {
					sCliente.modificarCliente(cliente);
					ses.setAttribute("cliente", cliente);
					getServletContext().getRequestDispatcher("/Mensaje.jsp?mensaje=3").forward(request, response);
				}	
				//empleado
			} else if (ses.getAttribute("empleado") != null) {
				sCliente = new ServicioCliente();
				sEmpleado = new ServicioEmpleado();
				
				email = request.getParameter("email");
				clave = request.getParameter("clave");
				telefono = request.getParameter("telefono");
				
				Empleado empleado2 = (Empleado) ses.getAttribute("empleado");	
				Empleado empleado;
				
				//si es admin
				if (empleado2.getPerfil().getIdPerfil()==1) {
					dni = request.getParameter("dni");
					nombre = request.getParameter("nombre");
					empleado = new Empleado (empleado2.getCodemp(), dni, email, clave, nombre, telefono, empleado2.getPerfil());
				} else {
					empleado = new Empleado (empleado2.getCodemp(), empleado2.getDni(), email, clave, empleado2.getNombre(), telefono, empleado2.getPerfil());
				}
									
				emailPrevio = empleado2.getEmail();
				
				// si se ha modificado el email en el formulario, se comprueba que el nuevo email no este repe
				if (!emailPrevio.equals(email)) {
					coincide=sCliente.existeEmail(email);	
					if (coincide) {
						getServletContext().getRequestDispatcher("/Mensaje.jsp?mensaje=4").forward(request, response);
					} else {
						sEmpleado.modificarEmpleado(empleado);
						ses.setAttribute("empleado", empleado);
						getServletContext().getRequestDispatcher("/Mensaje.jsp?mensaje=3").forward(request, response);
					}
				} else {
					sEmpleado.modificarEmpleado(empleado);
					ses.setAttribute("empleado", empleado);
					getServletContext().getRequestDispatcher("/Mensaje.jsp?mensaje=3").forward(request, response);
				}
			
			}
				
		} catch (ServiceException | DomainException e) {
			if (e.getCause() == null) {
				getServletContext().getRequestDispatcher("/editarperfil.jsp?error=" + e.getMessage() + " ").forward(request,
						response);
			} else {
				e.printStackTrace();
				getServletContext().getRequestDispatcher("/editarperfil.jsp?error=Error Interno").forward(request,
						response);
			}

		}
	}

}
