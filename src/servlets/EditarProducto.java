package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Articulo;
import exceptions.DomainException;
import exceptions.ServiceException;
import servicios.ServicioArticulo;

/**
 * Servlet implementation class EditarProducto
 */
@WebServlet("/EditarProducto")
public class EditarProducto extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditarProducto() {
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
		ServicioArticulo sArticulo;

		Articulo articulo;
		Articulo articuloNuevo;

		String codArt = "";
		String nombre = "";
		String descripcion = "";
		String precio = "";
		String stock = "";
		String imagen = "";

		boolean coincide = false;

		try {
			sArticulo = new ServicioArticulo();

			codArt = request.getParameter("codArt");
			nombre = request.getParameter("nombre");
			descripcion = request.getParameter("descripcion");
			precio = request.getParameter("precio");
			stock = request.getParameter("stock");
			imagen = request.getParameter("imagen");

			try {
				Double precioArt = Double.parseDouble(precio);
				int codArtInt = Integer.parseInt(codArt);
				int stockArt = Integer.parseInt(stock);

				articulo = sArticulo.recuperarArticuloById(codArtInt);
				articuloNuevo = new Articulo(articulo.getCodArt(), nombre, descripcion, precioArt, stockArt, imagen);

				sArticulo.modificarArticulo(articuloNuevo);
				getServletContext().getRequestDispatcher("/Mensaje.jsp?mensaje=3").forward(request, response);
			} catch (NumberFormatException ex) {
				getServletContext().getRequestDispatcher("/editarproducto.jsp?error=Error en campos numericos")
						.forward(request, response);
			}

		} catch (ServiceException | DomainException e) {
			if (e.getCause() == null) {
				getServletContext().getRequestDispatcher("/editarproducto.jsp?error=" + e.getMessage() + " ")
						.forward(request, response);
			} else {
				e.printStackTrace();
				getServletContext().getRequestDispatcher("/editarproducto.jsp?error=Error Interno").forward(request,
						response);
			}

		}
	}

}
