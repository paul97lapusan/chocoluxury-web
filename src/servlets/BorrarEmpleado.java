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
import servicios.ServicioEmpleado;

/**
 * Servlet implementation class BorrarEmpleado
 */
@WebServlet("/BorrarEmpleado")
public class BorrarEmpleado extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BorrarEmpleado() {
    	super();
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
		String codEmp;
		Empleado empleado;
		ServicioEmpleado sEmpleado;
		
		try {
			HttpSession ses = request.getSession();
			
			if (ses.getAttribute("empleado") != null) {
				// si es empleado
				empleado = (Empleado) ses.getAttribute("empleado");
				//si no es admin
				if (empleado.getPerfil().getIdPerfil() != 1) {
					response.sendRedirect("login.jsp");
				}
			} else {
				response.sendRedirect("login.jsp");
			}
			
			sEmpleado = new ServicioEmpleado();
			
			codEmp = request.getParameter("codEmp");
			int codigo = Integer.parseInt(codEmp);
			
			sEmpleado.borrarEmpleado(codigo);
									
			response.sendRedirect("verempleados.jsp");
									
		} catch (ServiceException | DomainException e){
			if(e.getCause()==null){
				getServletContext().getRequestDispatcher("/verempleados.jsp?error="+e.getMessage()+" ").forward(request, response);
			}else{
				e.printStackTrace();
				getServletContext().getRequestDispatcher("/verempleados.jsp?error=Error Interno").forward(request, response);
			}
		}
	}

}
