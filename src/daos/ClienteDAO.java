package daos;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import recursos.DbQuery;
import recursos.Recursos;
import daos.interfaces.ErroresBD;
import domain.Cliente;
import domain.Empleado;
import domain.PerfilEmpleado;
import exceptions.DAOException;

public class ClienteDAO implements ErroresBD {
	
	
	private Connection con;

	public ClienteDAO(Connection con) {
		this.con = con;
	}
	
	public void insertarCliente(Cliente cliente)throws DAOException{
		PreparedStatement st = null;
		PreparedStatement sti = null;
		ResultSet rs=null;
		try {
			st = con.prepareStatement(DbQuery.getInsertarCliente());
			st.setString(1, cliente.getNombre());
			st.setString(2, cliente.getTelefono());
			st.setString(3, cliente.getDireccion());
			st.setString(4, cliente.getEmail());
			st.setString(5, cliente.getClave());
			
			// ejecutamos el insert.			
			st.executeUpdate();
		} catch (SQLException e) {
			if (e.getErrorCode() == DUPLICATE_PK) {
				throw new DAOException(" Cliente ya existe");
			}else if (e.getErrorCode() ==FALLO_FK ){
			   throw new DAOException("Operacion no disponible temporalmente, repita proceso");
			} else {
				throw new DAOException(DB_ERR, e);
			}
		} finally {
			Recursos.closePreparedStatement(st);
			Recursos.closePreparedStatement(sti);
		}	
	}
	
	public Cliente recuperarClienteByEmail(String email) throws DAOException {
		PreparedStatement st = null;
		ResultSet rs =null ;
		Cliente objeto=null;
		
			try {
				st = con.prepareStatement(DbQuery.getRecuperarCliente());
				st.setString(1, email);
				rs=st.executeQuery();
				if (rs.next()){
	
					objeto=new Cliente(rs.getInt(1), 
									   rs.getString(2), 
									   rs.getString(3), 
									   rs.getString(4), 
									   rs.getString(5),
									   rs.getString(6)); 
					
				}		
				
			} catch (SQLException e) {
				throw new DAOException(DB_ERR, e);
			} finally {
			Recursos.closeResultSet(rs);
			Recursos.closePreparedStatement(st);
			
		}
		
		return objeto;
	}
	
	public Cliente recuperarClienteById(int cod_cli) throws DAOException {
		PreparedStatement st = null;
		ResultSet rs =null ;
		Cliente objeto=null;
		
			try {
				st = con.prepareStatement(DbQuery.getRecuperarClienteById());
				st.setInt(1, cod_cli);
				rs=st.executeQuery();
				if (rs.next()){
	
					objeto=new Cliente(rs.getInt(1), 
									   rs.getString(2), 
									   rs.getString(3), 
									   rs.getString(4), 
									   rs.getString(5),
									   rs.getString(6)); 
					
				}		
				
			} catch (SQLException e) {
				throw new DAOException(DB_ERR, e);
			} finally {
			Recursos.closeResultSet(rs);
			Recursos.closePreparedStatement(st);
			
		}
		
		return objeto;
	}
	
	public int modificarCliente(Cliente cliente)throws DAOException{
		PreparedStatement st = null;
		PreparedStatement sti = null;
		ResultSet rs=null;
		int modificado = 0;
		try {
			st = con.prepareStatement(DbQuery.getModificarCliente());
			st.setString(1, cliente.getNombre());
			st.setString(2, cliente.getTelefono());
			st.setString(3, cliente.getDireccion());
			st.setString(4, cliente.getEmail());
			st.setString(5, cliente.getClave());
			st.setInt(6, cliente.getCodCli());
			
			modificado=st.executeUpdate();
		} catch (SQLException e) {
			 if (e.getErrorCode() ==FALLO_FK ){
			   throw new DAOException("Operacion no disponible temporalmente,repita proceso");
			}else if  (e.getErrorCode()>=20000 && e.getErrorCode()<=20999){
				String cadena=e.toString().substring(e.toString().indexOf("ORA", 0)+10);
				String cadena1=cadena.substring(0,cadena.indexOf("ORA", 0));
			    throw new DAOException(cadena1);
			} else {
				throw new DAOException(DB_ERR, e);
			}
		} finally {
			Recursos.closePreparedStatement(st);
			Recursos.closePreparedStatement(sti);
		}	
		return modificado;
	}
	
	//verificarLogin sirve tanto para Clientes como para Empleados
	public boolean verificarLogin (String email, String clave) throws DAOException {
		PreparedStatement st = null;
		ResultSet rs=null;
		
		try {
			st = con.prepareStatement(DbQuery.getValidarLogin());
			st.setString(1, email);
			st.setString(2, clave);
			st.setString(3, email);
			st.setString(4, clave);
			rs = st.executeQuery();
			
			if (rs.next()) {
				return true;
			}
			
		} catch (SQLException e) {
			throw new DAOException(DB_ERR, e);
		} finally {
		Recursos.closeResultSet(rs);
		Recursos.closePreparedStatement(st);
		}
		
		return false;
	}
	
	//para saber si es Cliente o Empleado
	public boolean esCliente (String email) throws DAOException {
		PreparedStatement st = null;
		ResultSet rs=null;
		
		try {
			st = con.prepareStatement(DbQuery.getEsCliente());
			st.setString(1, email);
			rs = st.executeQuery();
			
			if (rs.next()) {
				return true;
			}
			
		} catch (SQLException e) {
			throw new DAOException(DB_ERR, e);
		} finally {
		Recursos.closeResultSet(rs);
		Recursos.closePreparedStatement(st);
		}
		
		return false;
	}
	
	//existeEmail sirve para comprobar si el email introducido en el registro ya tiene una cuenta asociada
	public boolean existeEmail (String email) throws DAOException {
		PreparedStatement st = null;
		ResultSet rs=null;
		
		try {
			st = con.prepareStatement(DbQuery.getExisteEmail());
			st.setString(1, email);
			st.setString(2, email);
			rs = st.executeQuery();
			
			if (rs.next()) {
				return true;
			}
			
		} catch (SQLException e) {
			throw new DAOException(DB_ERR, e);
		} finally {
		Recursos.closeResultSet(rs);
		Recursos.closePreparedStatement(st);
		}
		
		return false;
	}

	public List<Cliente> recuperarTodosCliente() throws DAOException {
		PreparedStatement st = null;
		ResultSet rs = null;
		List<Cliente> list = new ArrayList<Cliente>();
		try {
			st = con.prepareStatement(DbQuery.getRecuperarTodosCliente());
			rs = st.executeQuery();
			while (rs.next()) {
				list.add(new Cliente(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));
			}
		} catch (SQLException e) {
			throw new DAOException(DB_ERR, e);
		} finally {
			Recursos.closeResultSet(rs);
			Recursos.closePreparedStatement(st);
		}
		return list;
	}

	public int borrarClienteById(int cliente) throws DAOException {
		PreparedStatement st = null;
		int borrado = 0;
		try {
			st = con.prepareStatement(DbQuery.getBorrarCliente());
			st.setInt(1, cliente);
			borrado = st.executeUpdate();
		} catch (SQLException e) {
			if (e.getErrorCode() == DELETE_FK) {
				throw new DAOException(" No permitido borrar Cliente");
				
			}else if  (e.getErrorCode()>=20000 && e.getErrorCode()<=20999){//para PL/SQL.triggers
				String cadena=e.toString().substring(e.toString().indexOf("ORA", 0)+10);
				String cadena1=cadena.substring(0,cadena.indexOf("ORA", 0));
			    throw new DAOException(cadena1);
			} else {
				throw new DAOException(DB_ERR, e);
			}
		} finally {
			Recursos.closePreparedStatement(st);
		}
		return borrado;
		
	}

	
	
	
	

	

	
	
	
}
