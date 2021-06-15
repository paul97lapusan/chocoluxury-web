package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Articulo;
import domain.Empleado;
import domain.PerfilEmpleado;
import exceptions.DomainException;
import exceptions.ServiceException;
import servicios.ServicioArticulo;
import servicios.ServicioCliente;
import servicios.ServicioEmpleado;

/**
 * Servlet implementation class AgregarProducto
 */
@WebServlet("/AgregarProducto")
public class AgregarProducto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AgregarProducto() {
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
		ServicioArticulo sArticulo;

		Articulo articulo;

		String nombre = "";
		String descripcion = "";
		String precio = "";
		String stock = "";
		String imagen = "";

		try {
			sArticulo = new ServicioArticulo();

			nombre = request.getParameter("nombre");
			descripcion = request.getParameter("descripcion");
			precio = request.getParameter("precio");
			stock = request.getParameter("stock");
			imagen = request.getParameter("imagen");
			
			try {
				Double precioVenta = Double.parseDouble(precio);
				int stockInt = Integer.parseInt(stock);
				articulo = new Articulo(0, nombre, descripcion, precioVenta, stockInt, imagen);

				sArticulo.insertarArticulo(articulo);
				
				getServletContext().getRequestDispatcher("/verproductos.jsp").forward(request, response);
			} catch (NumberFormatException ex) {
				getServletContext().getRequestDispatcher("/agregarproducto.jsp?error=Error en campo numerico").forward(request,
						response);
			}
			
		} catch (ServiceException | DomainException e) {
			if (e.getCause() == null) {
				getServletContext().getRequestDispatcher("/agregarproducto.jsp?error=" + e.getMessage() + " ")
						.forward(request, response);
			} else {
				e.printStackTrace();
				getServletContext().getRequestDispatcher("/agregarproducto.jsp?error=Error Interno").forward(request,
						response);
			}
		}
	}
}
