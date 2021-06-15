package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.Empleado;
import domain.Pedido;
import exceptions.DomainException;
import exceptions.ServiceException;
import servicios.ServicioPedido;

/**
 * Servlet implementation class PedidoEntregado
 */
@WebServlet("/PedidoEntregado")
public class PedidoEntregado extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PedidoEntregado() {
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
		Empleado empleado;
		ServicioPedido sPedido;
		Pedido pedido = null;
		
		try {
			HttpSession ses = request.getSession();
			if (ses.getAttribute("empleado") != null) {
				// si es empleado
				empleado = (Empleado) ses.getAttribute("empleado");
				//si no es repostero o repartidor
				if (empleado.getPerfil().getIdPerfil() != 2 && empleado.getPerfil().getIdPerfil() != 3) {
					response.sendRedirect("login.jsp");
				}
			} else {
				response.sendRedirect("login.jsp");
			}
			
			sPedido = new ServicioPedido();
			
			String cod = request.getParameter("codPed");
			int codPed = Integer.parseInt(cod);
			pedido = sPedido.recuperarPedidoCompletoById(codPed);
			
			int nuevoPedido = 4;
			
			pedido.getEstado().setIdEstado(nuevoPedido);
			sPedido.modificarPedido(pedido);
			
			response.sendRedirect("panelcontrol.jsp");
			
									
		} catch (ServiceException | DomainException e){
			if(e.getCause()==null){
				getServletContext().getRequestDispatcher("/panelcontrol.jsp?error="+e.getMessage()+" ").forward(request, response);
			}else{
				e.printStackTrace();
				getServletContext().getRequestDispatcher("/panelcontrol.jsp?error=Error Interno").forward(request, response);
			}
		}
	}

}
