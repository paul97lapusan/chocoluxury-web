package servicios;

import java.util.ArrayList;

import daos.ArticuloDAO;
import daos.ClienteDAO;
import daos.EmpleadoDAO;
import daos.TransaccionesManager;
import domain.Articulo;
import domain.Cliente;
import domain.Empleado;
import exceptions.DAOException;
import exceptions.ServiceException;

public class ServicioEmpleado {

	public ServicioEmpleado() {
	}
	
	public void insertarEmpleado(Empleado empleado) throws ServiceException{
		TransaccionesManager trans =  null;
		try {

			trans =  new TransaccionesManager();
			EmpleadoDAO empleadoDAO = trans.getEmpleadoDAO();
			empleadoDAO.insertarEmpleado(empleado);


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
	
	public int borrarEmpleado(int empleado) throws ServiceException{
		TransaccionesManager trans = null;
		int borrado=0;
		try {
			trans = new TransaccionesManager();
			EmpleadoDAO empleadoDAO = trans.getEmpleadoDAO();
			borrado = empleadoDAO.borrarEmpleadoById(empleado);


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
		return borrado;
	}

	public int modificarEmpleado(Empleado empleado) throws ServiceException {
		TransaccionesManager trans = null;
		int modificar = 0;
		try {
			trans = new TransaccionesManager();
			EmpleadoDAO empleadoDAO = trans.getEmpleadoDAO();
			modificar = empleadoDAO.modificarEmpleado(empleado);

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
		return modificar;
	}

	public Empleado recuperarEmpleadoCompletoByEmail(String email) throws ServiceException {
		TransaccionesManager trans = null;
		Empleado empleado = null;
		try {
			trans = new TransaccionesManager();
			EmpleadoDAO empleadodao = trans.getEmpleadoDAO();
			empleado = empleadodao.recuperarEmpleadoByEmail(email);

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
		return empleado;
	}

	public Empleado recuperarEmpleadoCompletoById(int id) throws ServiceException {
		TransaccionesManager trans = null;
		Empleado empleado = null;
		try {
			trans = new TransaccionesManager();
			EmpleadoDAO empleadodao = trans.getEmpleadoDAO();
			empleado = empleadodao.recuperarEmpleadoById(id);

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
		return empleado;
	}

	public ArrayList<Empleado> recuperarTodosEmpleado() throws ServiceException {
		TransaccionesManager trans = null;
		ArrayList<Empleado> list = new ArrayList<Empleado>();
		try {

			trans = new TransaccionesManager();
			EmpleadoDAO empleadoDAO = trans.getEmpleadoDAO();
			list = (ArrayList<Empleado>) empleadoDAO.recuperarTodosEmpleado();

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
