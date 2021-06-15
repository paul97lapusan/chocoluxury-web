package servicios;

import java.util.ArrayList;
import java.util.List;

import daos.LinPedDAO;
import daos.PedidoDAO;
import daos.TransaccionesManager;
import domain.LinPed;
import domain.Pedido;
import exceptions.DAOException;
import exceptions.ServiceException;

public class ServicioLinPed {
	
	public ServicioLinPed() {

	}
	
	public void insertarLinPed(LinPed linped) throws ServiceException{
		TransaccionesManager trans =  null;
		try {

			trans =  new TransaccionesManager();
			LinPedDAO linpedDAO = trans.getLinPedDAO();
			linpedDAO.insertarLinPed(linped);


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
	
	public ArrayList<LinPed> recuperarTodosLinPedById(int codPed) throws ServiceException {
		TransaccionesManager trans = null;
		ArrayList<LinPed> list = new ArrayList<LinPed>();
		try {

			trans = new TransaccionesManager();
			LinPedDAO linpedDAO = trans.getLinPedDAO();
			list = (ArrayList<LinPed>) linpedDAO.recuperarTodosLinPedById(codPed);

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
