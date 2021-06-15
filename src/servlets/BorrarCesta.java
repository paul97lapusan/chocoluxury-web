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
import domain.LinPed;
import exceptions.DomainException;
import exceptions.ServiceException;
import servicios.ServicioArticulo;

/**
 * Servlet implementation class BorrarCesta
 */
@WebServlet("/BorrarCesta")
public class BorrarCesta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BorrarCesta() {
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
		String codArt;
		Articulo articulo;
		ServicioArticulo sArticulo;
		LinPed linped;
		boolean existe=false;
		
		try {
			HttpSession ses = request.getSession();
			//solo se borra del carrito si no estas log o eres un cliente, si eres empleado no.
			if (ses.getAttribute("empleado") != null) {
				getServletContext().getRequestDispatcher("/productos.jsp?error=No puedes comprar desde tu cuenta de empleado. Accede como cliente si deseas comprar.").forward(request, response);
			} else {
				codArt = request.getParameter("codArt");
				int cod = Integer.parseInt(codArt);
				ArrayList<LinPed> carrito = (ArrayList<LinPed>) ses.getAttribute("carrito");
						
				sArticulo = new ServicioArticulo();
				articulo = sArticulo.recuperarArticuloById(cod);
				
				for (int i=0; i<carrito.size(); i++) {
					if (carrito.get(i).getArticulo().getCodArt() == articulo.getCodArt()) {
						existe=true;
						carrito.remove(i);
					}
				}
									
				response.sendRedirect("carrito.jsp");
			}
									
		} catch (ServiceException | DomainException e){
			if(e.getCause()==null){
				getServletContext().getRequestDispatcher("/productos.jsp?error="+e.getMessage()+" ").forward(request, response);
			}else{
				e.printStackTrace();
				getServletContext().getRequestDispatcher("/productos.jsp?error=Error Interno").forward(request, response);
			}
		}
	}

}
