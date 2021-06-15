package servicios;

import java.util.ArrayList;
import java.util.List;

import daos.EstadoPedidoDAO;
import daos.TransaccionesManager;
import domain.EstadoPedido;
import exceptions.DAOException;
import exceptions.ServiceException;

public class ServicioEstadoPedido {
	
	public EstadoPedido recuperarEstadoPedidoCompletoById(int cod_est) throws ServiceException{
		TransaccionesManager trans = null;
		EstadoPedido estado=null;
		try {
			trans = new TransaccionesManager();
			EstadoPedidoDAO estadodao = trans.getEstadoPedidoDAO();
			estado = estadodao.recuperarEstadoPedidoById(cod_est);

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
		return estado;
	}

}
