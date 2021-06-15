package servlets;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.Articulo;
import domain.Cliente;
import domain.EstadoPedido;
import domain.LinPed;
import domain.Pedido;
import exceptions.DomainException;
import exceptions.ServiceException;
import servicios.ServicioArticulo;
import servicios.ServicioLinPed;
import servicios.ServicioPedido;

/**
 * Servlet implementation class AgregarPedido
 */
@WebServlet("/AgregarPedido")
public class AgregarPedido extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AgregarPedido() {
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
		String parImporte;
		Double importe;
		Pedido pedido;
		java.sql.Date date;
		Cliente cliente;
		EstadoPedido estado;
		ServicioPedido sPedido;
		
		try {
			HttpSession ses = request.getSession();
			
			if (ses.getAttribute("cliente") == null) {
				getServletContext().getRequestDispatcher(
						"/productos.jsp?error=Accede como cliente si deseas comprar.")
						.forward(request, response);
			}
			
			parImporte = request.getParameter("total");
			importe = Double.parseDouble(parImporte);
			
			date = new java.sql.Date(System.currentTimeMillis());
			cliente = (Cliente) ses.getAttribute("cliente");
			estado = new EstadoPedido(1, "Preparando");
			
			
			if (request.getParameter("domicilio") == null) {
				pedido = new Pedido(date, cliente, estado, 0, importe);
			} else {
				pedido = new Pedido(date, cliente, estado, 1, importe);
			}
			
			ArrayList<LinPed> carrito = (ArrayList<LinPed>) ses.getAttribute("carrito");
			
			for (int i=0; i<carrito.size(); i++) {
				carrito.get(i).setPedido(pedido);	
				System.out.println(carrito.get(i).getPedido().getCodped());
			}
			
			
			
			sPedido = new ServicioPedido();
			sPedido.insertarPedido(pedido);
			
			request.getRequestDispatcher("AgregarLinPed").forward(request,response);
			
			//getServletContext().getRequestDispatcher("/Mensaje.jsp?mensaje=5").forward(request, response);

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
