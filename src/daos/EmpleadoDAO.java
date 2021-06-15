package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import daos.interfaces.ErroresBD;
import domain.Articulo;
import domain.Cliente;
import domain.Empleado;
import domain.PerfilEmpleado;
import exceptions.DAOException;
import recursos.DbQuery;
import recursos.Recursos;

public class EmpleadoDAO implements ErroresBD {
	
	private Connection con;

	public EmpleadoDAO(Connection con) {
		this.con = con;
	}
	
	public void insertarEmpleado(Empleado empleado)throws DAOException{
		PreparedStatement st = null;
		PreparedStatement sti = null;
		ResultSet rs=null;
		try {
			st = con.prepareStatement(DbQuery.getInsertarEmpleado());
			st.setString(1, empleado.getDni());
			st.setString(2, empleado.getEmail());
			st.setString(3, empleado.getClave());
			st.setString(4, empleado.getNombre());
			st.setString(5, empleado.getTelefono());
			st.setInt(6, empleado.getPerfil().getIdPerfil());
			
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
	
	public int borrarEmpleadoById(int empleado) throws DAOException {
		PreparedStatement st = null;
		int borrado = 0;
		try {
			st = con.prepareStatement(DbQuery.getBorrarEmpleado());
			st.setInt(1, empleado);
			borrado = st.executeUpdate();
		} catch (SQLException e) {
			if (e.getErrorCode() == DELETE_FK) {
				throw new DAOException(" No permitido borrar Empleado");
				
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
	
	public int modificarEmpleado(Empleado empleado)throws DAOException{
		PreparedStatement st = null;
		PreparedStatement sti = null;
		ResultSet rs=null;
		int modificado = 0;
		try {
			st = con.prepareStatement(DbQuery.getModificarEmpleado());
			st.setString(1, empleado.getDni());
			st.setString(2, empleado.getEmail());
			st.setString(3, empleado.getClave());
			st.setString(4, empleado.getNombre());
			st.setString(5, empleado.getTelefono());
			st.setInt(6, empleado.getPerfil().getIdPerfil());
			st.setInt(7, empleado.getCodemp());
			
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
	
	public Empleado recuperarEmpleadoByEmail(String email) throws DAOException {
		PreparedStatement st = null;
		ResultSet rs =null ;
		Empleado objeto=null;
		
			try {
				st = con.prepareStatement(DbQuery.getRecuperarEmpleadoByEmail());
				st.setString(1, email);
				rs=st.executeQuery();
				if (rs.next()){
	
					objeto=new Empleado (rs.getInt(1),
										rs.getString(2), 
									   rs.getString(3), 
									   rs.getString(4), 
									   rs.getString(5), 
									   rs.getString(6),
									   new PerfilEmpleado(rs.getInt(7))); 
					
				}		
				
			} catch (SQLException e) {
				throw new DAOException(DB_ERR, e);
			} finally {
			Recursos.closeResultSet(rs);
			Recursos.closePreparedStatement(st);
			
		}
		
		return objeto;
	}
	
	public Empleado recuperarEmpleadoById(int id) throws DAOException {
		PreparedStatement st = null;
		ResultSet rs =null ;
		Empleado objeto=null;
		
			try {
				st = con.prepareStatement(DbQuery.getRecuperarEmpleadoById());
				st.setInt(1, id);
				rs=st.executeQuery();
				if (rs.next()){
	
					objeto=new Empleado (rs.getInt(1),
										rs.getString(2), 
									   rs.getString(3), 
									   rs.getString(4), 
									   rs.getString(5), 
									   rs.getString(6),
									   new PerfilEmpleado(rs.getInt(7))); 
					
				}		
				
			} catch (SQLException e) {
				throw new DAOException(DB_ERR, e);
			} finally {
			Recursos.closeResultSet(rs);
			Recursos.closePreparedStatement(st);
			
		}
		
		return objeto;
	}
	
	public List<Empleado> recuperarTodosEmpleado() throws DAOException {
		PreparedStatement st = null;
		ResultSet rs = null;
		List<Empleado> list = new ArrayList<Empleado>();
		String telefono = null;
		try {
			st = con.prepareStatement(DbQuery.getRecuperarTodosEmpleado());
			rs = st.executeQuery();
			while (rs.next()) {
				telefono = null;
				if (rs.getObject("telefono") != null) {
					telefono = new String(rs.getString("telefono"));
				}
				list.add(new Empleado(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), telefono, new PerfilEmpleado(rs.getInt(7), rs.getString(8))));
			}
		} catch (SQLException e) {
			throw new DAOException(DB_ERR, e);
		} finally {
			Recursos.closeResultSet(rs);
			Recursos.closePreparedStatement(st);
		}
		return list;

	}
	
	

}
