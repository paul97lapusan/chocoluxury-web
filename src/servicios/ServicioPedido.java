package servicios;

import java.util.ArrayList;
import java.util.List;

import daos.ClienteDAO;
import daos.PedidoDAO;
import daos.TransaccionesManager;
import domain.Cliente;
import domain.Pedido;
import exceptions.DAOException;
import exceptions.ServiceException;

public class ServicioPedido {
	
	public ServicioPedido() {

	}
	
	public void insertarPedido(Pedido pedido) throws ServiceException{
		TransaccionesManager trans =  null;
		try {

			trans =  new TransaccionesManager();
			PedidoDAO pedidoDAO = trans.getPedidoDAO();
			pedidoDAO.insertarPedido(pedido);


			trans.closeCommit();
		} catch (DAOException e) {

			try{
				if(trans!=null)
				trans.closeRollback();
			}catch (DAOException e1){
				throw new ServiceException(e.getMessage(),e1);//Error interno
			}

			if(e.getCause()==null){
				throw new ServiceException(e.getMessage());//Error Lógico
			}else{

				throw new ServiceException(e.getMessage(),e);//Error interno
			}

		}
	}
	
	public Pedido recuperarUltimoPedido() throws ServiceException{
		TransaccionesManager trans = null;
		Pedido pedido=null;
		try {
			trans = new TransaccionesManager();
			PedidoDAO pedidodao = trans.getPedidoDAO();
			pedido = pedidodao.recuperarUltimoPedido();

			trans.closeCommit();
		} catch (DAOException e) {
			try{
				if(trans!=null)
				trans.closeRollback();
			}catch (DAOException e1){
				throw new ServiceException(e.getMessage(),e1);//Error interno
			}

			if(e.getCause()==null){
				throw new ServiceException(e.getMessage());//Error Lógico
			}else{

				throw new ServiceException(e.getMessage(),e);//Error interno
			}

		}
		return pedido;
	}
	
	public Pedido recuperarPedidoCompletoById(int cod_ped) throws ServiceException{
		TransaccionesManager trans = null;
		Pedido pedido=null;
		try {
			trans = new TransaccionesManager();
			PedidoDAO pedidodao = trans.getPedidoDAO();
			pedido = pedidodao.recuperarPedidoById(cod_ped);

			trans.closeCommit();
		} catch (DAOException e) {
			try{
				if(trans!=null)
				trans.closeRollback();
			}catch (DAOException e1){
				throw new ServiceException(e.getMessage(),e1);//Error interno
			}

			if(e.getCause()==null){
				throw new ServiceException(e.getMessage());//Error Lógico
			}else{

				throw new ServiceException(e.getMessage(),e);//Error interno
			}

		}
		return pedido;
	}
	
	public int modificarPedido(Pedido pedido) throws ServiceException{
		TransaccionesManager trans = null;
		int modificar=0;
		try {
			 trans = new TransaccionesManager();
			 PedidoDAO pedidoDAO = trans.getPedidoDAO();
			modificar = pedidoDAO.modificarPedido(pedido);

			trans.closeCommit();
		} catch (DAOException e) {
			try{
				if(trans!=null)
				trans.closeRollback();
			}catch (DAOException e1){
				throw new ServiceException(e.getMessage(),e1);//Error interno
			}
		
			if(e.getCause()==null){
				throw new ServiceException(e.getMessage());//Error Lógico
			}else{
				throw new ServiceException(e.getMessage(),e);//Error interno
			}

		}
		return modificar;
	}
	
	public ArrayList<Pedido> recuperarTodosPedido() throws ServiceException {
		TransaccionesManager trans = null;
		ArrayList<Pedido> list = new ArrayList<Pedido>();
		try {

			trans = new TransaccionesManager();
			PedidoDAO pedidoDAO = trans.getPedidoDAO();
			list = (ArrayList<Pedido>) pedidoDAO.recuperarTodosPedido();

			trans.closeCommit();
		} catch (DAOException e) {
			try {
				if (trans != null)
					trans.closeRollback();
			} catch (DAOException e1) {
				throw new ServiceException(e.getMessage(), e1);// Error interno
			}

			if (e.getCause() == null) {
				throw new ServiceException(e.getMessage());// Error Lógico
			} else {

				throw new ServiceException(e.getMessage(), e);// Error interno
			}

		}
		return list;
	}
	
	
}
