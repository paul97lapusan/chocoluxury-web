package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Cliente;
import domain.Empleado;
import domain.PerfilEmpleado;
import exceptions.DomainException;
import exceptions.ServiceException;
import servicios.ServicioCliente;
import servicios.ServicioEmpleado;

/**
 * Servlet implementation class EditarCliente
 */
@WebServlet("/EditarCliente")
public class EditarCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditarCliente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServicioCliente sCliente; 
		
		Cliente cliente;
		Cliente clienteNuevo;
		
		String nombre = "";
		String email = "";
		String telefono = "";
		String direccion = "";
		

		boolean coincide = false;

		try {
			sCliente = new ServicioCliente();
			
			nombre = request.getParameter("nombre");
			email = request.getParameter("email");
			telefono = request.getParameter("telefono");
			direccion = request.getParameter("direccion");
			
			cliente = sCliente.recuperarClienteCompletoByEmail(email);
			
			clienteNuevo = new Cliente(cliente.getCodCli(), nombre, telefono, direccion, email, cliente.getClave());
			
			String emailPrevio = cliente.getEmail();
			
			// si se ha modificado el email en el formulario, se comprueba que el nuevo email no este repe
			if (!emailPrevio.equals(email)) {
				sCliente = new ServicioCliente();
				coincide=sCliente.existeEmail(email);	
				if (coincide) {
					getServletContext().getRequestDispatcher("/Mensaje.jsp?mensaje=4").forward(request, response);
				} else {
					sCliente.modificarCliente(clienteNuevo);
					getServletContext().getRequestDispatcher("/Mensaje.jsp?mensaje=3").forward(request, response);
				}
			} else {
				sCliente.modificarCliente(clienteNuevo);
				getServletContext().getRequestDispatcher("/Mensaje.jsp?mensaje=3").forward(request, response);
			}

		} catch (ServiceException | DomainException e) {
			if (e.getCause() == null) {
				getServletContext().getRequestDispatcher("/editarcliente.jsp?error=" + e.getMessage() + " ")
						.forward(request, response);
			} else {
				e.printStackTrace();
				getServletContext().getRequestDispatcher("/editarcliente.jsp?error=Error Interno").forward(request,
						response);
			}

		}
	}

}
