package servicios;

import java.util.ArrayList;
import java.util.List;

import daos.ArticuloDAO;
import daos.ClienteDAO;
import daos.EmpleadoDAO;
import daos.TransaccionesManager;
import domain.Articulo;
import domain.Cliente;
import domain.Empleado;
import exceptions.DAOException;
import exceptions.ServiceException;

public class ServicioArticulo {
	
	public ServicioArticulo() {

	}
	
	public void insertarArticulo(Articulo articulo) throws ServiceException{
		TransaccionesManager trans =  null;
		try {

			trans =  new TransaccionesManager();
			ArticuloDAO articuloDAO = trans.getArticuloDAO();
			articuloDAO.insertarArticulo(articulo);


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
	
	public int borrarArticulo(int articulo) throws ServiceException{
		TransaccionesManager trans = null;
		int borrado=0;
		try {
			trans = new TransaccionesManager();
			ArticuloDAO articuloDAO = trans.getArticuloDAO();
			borrado = articuloDAO.borrarArticuloById(articulo);


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
	
	public Articulo recuperarArticuloById(int codArt) throws ServiceException{
		TransaccionesManager trans = null;
		Articulo articulo=null;
		try {
			trans = new TransaccionesManager();
			ArticuloDAO articulodao = trans.getArticuloDAO();
			articulo = articulodao.recuperarArticuloById(codArt);

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
		return articulo;
	}
	
	public ArrayList<Articulo> recuperarTodosArticulo() throws ServiceException{
		TransaccionesManager trans = null;
		ArrayList<Articulo> list = new ArrayList<Articulo>();
		try {

			trans = new TransaccionesManager();
			ArticuloDAO articuloDAO = trans.getArticuloDAO();
			list = (ArrayList<Articulo>) articuloDAO.recuperarTodosArticulo();


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
		return list;
	}
	
	public int modificarArticulo(Articulo articulo) throws ServiceException{
		TransaccionesManager trans = null;
		int modificar=0;
		try {
			 trans = new TransaccionesManager();
			ArticuloDAO articuloDAO = trans.getArticuloDAO();
			modificar = articuloDAO.modificarArticulo(articulo);

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

}
