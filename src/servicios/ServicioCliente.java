package servicios;

import java.util.ArrayList;

import daos.ClienteDAO;
import daos.EmpleadoDAO;
import daos.TransaccionesManager;
import domain.Cliente;
import domain.Empleado;
import exceptions.DAOException;
import exceptions.ServiceException;

public class ServicioCliente {
	
	public ServicioCliente() {

	}
	
	public void insertarCliente(Cliente cliente) throws ServiceException{
		TransaccionesManager trans =  null;
		try {

			trans =  new TransaccionesManager();
			ClienteDAO clienteDAO = trans.getClienteDAO();
			clienteDAO.insertarCliente(cliente);


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
	
	public int borrarCliente(int cliente) throws ServiceException{
		TransaccionesManager trans = null;
		int borrado=0;
		try {
			trans = new TransaccionesManager();
			ClienteDAO clienteDAO = trans.getClienteDAO();
			borrado = clienteDAO.borrarClienteById(cliente);


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
	
	public int modificarCliente(Cliente cliente) throws ServiceException{
		TransaccionesManager trans = null;
		int modificar=0;
		try {
			 trans = new TransaccionesManager();
			ClienteDAO clienteDAO = trans.getClienteDAO();
			modificar = clienteDAO.modificarCliente(cliente);

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

	
	public Cliente recuperarClienteCompletoByEmail(String email) throws ServiceException{
		TransaccionesManager trans = null;
        Cliente cliente=null;
		try {
			trans = new TransaccionesManager();
			ClienteDAO clientedao = trans.getClienteDAO();
			cliente = clientedao.recuperarClienteByEmail(email);

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
		return cliente;
	}
	
	public Cliente recuperarClienteCompletoById(int cod_cli) throws ServiceException{
		TransaccionesManager trans = null;
        Cliente cliente=null;
		try {
			trans = new TransaccionesManager();
			ClienteDAO clientedao = trans.getClienteDAO();
			cliente = clientedao.recuperarClienteById(cod_cli);

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
		return cliente;
	}
	
	
	public boolean verificarLogin (String email, String clave) throws ServiceException {
		TransaccionesManager trans =  null;
		boolean encontrado;
		try {

			trans =  new TransaccionesManager();
			ClienteDAO clienteDAO = trans.getClienteDAO();
			encontrado=clienteDAO.verificarLogin(email, clave);

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
		
		return encontrado;
	
	}
	
	public boolean esCliente (String email) throws ServiceException {
		TransaccionesManager trans =  null;
		boolean encontrado;
		try {

			trans =  new TransaccionesManager();
			ClienteDAO clienteDAO = trans.getClienteDAO();
			encontrado=clienteDAO.esCliente(email);

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
		
		return encontrado;
	
	}
	
	
	public boolean existeEmail (String email) throws ServiceException {
		TransaccionesManager trans =  null;
		boolean encontrado;
		try {

			trans =  new TransaccionesManager();
			ClienteDAO clienteDAO = trans.getClienteDAO();
			encontrado=clienteDAO.existeEmail(email);

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
		
		return encontrado;
	
	}
	
	public ArrayList<Cliente> recuperarTodosCliente() throws ServiceException {
		TransaccionesManager trans = null;
		ArrayList<Cliente> list = new ArrayList<Cliente>();
		try {

			trans = new TransaccionesManager();
			ClienteDAO clienteDAO = trans.getClienteDAO();
			list = (ArrayList<Cliente>) clienteDAO.recuperarTodosCliente();

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
