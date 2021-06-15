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
import domain.Cliente;
import domain.Empleado;
import domain.LinPed;
import domain.Pedido;
import exceptions.DomainException;
import exceptions.ServiceException;
import servicios.ServicioArticulo;
import servicios.ServicioPedido;

/**
 * Servlet implementation class PedidoPreparado
 */
@WebServlet("/PedidoPreparado")
public class PedidoPreparado extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PedidoPreparado() {
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
		Empleado empleado;
		ServicioPedido sPedido;
		Pedido pedido = null;
		
		try {
			HttpSession ses = request.getSession();
			if (ses.getAttribute("empleado") != null) {
				// si es empleado
				empleado = (Empleado) ses.getAttribute("empleado");
				//si no es repostero
				if (empleado.getPerfil().getIdPerfil() != 2) {
					response.sendRedirect("login.jsp");
				}
			} else {
				response.sendRedirect("login.jsp");
			}
			
			sPedido = new ServicioPedido();
			
			String cod = request.getParameter("codPed");
			int codPed = Integer.parseInt(cod);
			pedido = sPedido.recuperarPedidoCompletoById(codPed);
			
			int nuevoPedido = 1;
			
			if (pedido.getDomicilio() == 0) {
				nuevoPedido = 3;
			} else {
				nuevoPedido = 2;
			}
			
			pedido.getEstado().setIdEstado(nuevoPedido);
			sPedido.modificarPedido(pedido);
			
			response.sendRedirect("verpedidosrepostero.jsp");
			
									
		} catch (ServiceException | DomainException e){
			if(e.getCause()==null){
				getServletContext().getRequestDispatcher("/verpedidosrepostero.jsp?error="+e.getMessage()+" ").forward(request, response);
			}else{
				e.printStackTrace();
				getServletContext().getRequestDispatcher("/verpedidosrepostero.jsp?error=Error Interno").forward(request, response);
			}
		}
	}

}
