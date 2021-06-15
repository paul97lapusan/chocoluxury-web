package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.Cliente;
import domain.EstadoPedido;
import domain.LinPed;
import domain.Pedido;
import exceptions.DomainException;
import exceptions.ServiceException;
import servicios.ServicioLinPed;
import servicios.ServicioPedido;

/**
 * Servlet implementation class AgregarLinPed
 */
@WebServlet("/AgregarLinPed")
public class AgregarLinPed extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AgregarLinPed() {
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
		Pedido pedido;
		ServicioPedido sPedido;
		ServicioLinPed sLinPed;
		LinPed linped;
		
		try {
			HttpSession ses = request.getSession();
			
			if (ses.getAttribute("cliente") == null) {
				getServletContext().getRequestDispatcher(
						"/productos.jsp?error=Accede como cliente si deseas comprar.")
						.forward(request, response);
			}
			
			ArrayList<LinPed> carrito = (ArrayList<LinPed>) ses.getAttribute("carrito");
			
			sPedido = new ServicioPedido();
			pedido = sPedido.recuperarUltimoPedido();
			
			for (int i=0; i<carrito.size(); i++) {
				carrito.get(i).getPedido().setCodped(pedido.getCodped());
			}
			
			sLinPed = new ServicioLinPed();
			
			for (int i=0; i<carrito.size(); i++) {
				linped = carrito.get(i);
				sLinPed.insertarLinPed(linped);
			}
			
			getServletContext().getRequestDispatcher("/Mensaje.jsp?mensaje=5").forward(request, response);

		} catch (ServiceException | DomainException e) {
			if (e.getCause() == null) {
				getServletContext().getRequestDispatcher("/carrito.jsp?error=" + e.getMessage() + " ")
						.forward(request, response);
			} else {
				e.printStackTrace();
				getServletContext().getRequestDispatcher("/carrito.jsp?error=Error Interno").forward(request,
						response);
			}
		}
	}

}
