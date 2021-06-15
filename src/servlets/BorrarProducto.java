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
import servicios.ServicioArticulo;
import servicios.ServicioCliente;

/**
 * Servlet implementation class BorrarProducto
 */
@WebServlet("/BorrarProducto")
public class BorrarProducto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BorrarProducto() {
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
		String codArt;
		Empleado empleado;
		ServicioArticulo sArticulo;

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

			sArticulo = new ServicioArticulo();

			codArt = request.getParameter("codArt");
			int codigo = Integer.parseInt(codArt);

			sArticulo.borrarArticulo(codigo);

			response.sendRedirect("verproductos.jsp");

		} catch (ServiceException | DomainException e) {
			if (e.getCause() == null) {
				getServletContext().getRequestDispatcher("/verproductos.jsp?error=" + e.getMessage() + " ")
						.forward(request, response);
			} else {
				e.printStackTrace();
				getServletContext().getRequestDispatcher("/verproductos.jsp?error=Error Interno").forward(request,
						response);
			}
		}
	}

}
