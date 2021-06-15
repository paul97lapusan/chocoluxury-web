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
import exceptions.DAOException;
import recursos.DbQuery;
import recursos.Recursos;

public class ArticuloDAO implements ErroresBD {

	private Connection con;

	public ArticuloDAO(Connection con) {
		this.con = con;
	}
	
	public void insertarArticulo(Articulo articulo)throws DAOException{
		PreparedStatement st = null;
		PreparedStatement sti = null;
		ResultSet rs=null;
		try {
			st = con.prepareStatement(DbQuery.getInsertarArticulo());
			st.setString(1, articulo.getNombreArt());
			st.setString(2, articulo.getDescripcion());
			st.setDouble(3, articulo.getPrecioVenta());
			st.setInt(4, articulo.getStock());
			st.setString(5, articulo.getImagen());
			
			// ejecutamos el insert.			
			st.executeUpdate();
		} catch (SQLException e) {
			if (e.getErrorCode() == DUPLICATE_PK) {
				throw new DAOException(" Articulo ya existe");
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
	
	public int borrarArticuloById(int articulo) throws DAOException {
		PreparedStatement st = null;
		int borrado = 0;
		try {
			st = con.prepareStatement(DbQuery.getBorrarArticulo());
			st.setInt(1, articulo);
			borrado = st.executeUpdate();
		} catch (SQLException e) {
			if (e.getErrorCode() == DELETE_FK) {
				throw new DAOException(" No permitido borrar Articulo");
				
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
	
	public Articulo recuperarArticuloById(int codArt) throws DAOException {
		PreparedStatement st = null;
		ResultSet rs =null ;
		Articulo articulo=null;
		
			try {
				st = con.prepareStatement(DbQuery.getRecuperarArticuloById());
				st.setInt(1, codArt);
				rs=st.executeQuery();
				if (rs.next()){
	
					articulo=new Articulo(rs.getInt(1), 
									   rs.getString(2), 
									   rs.getString(3), 
									   rs.getDouble(4), 
									   rs.getInt(5),
									   rs.getString(6)); 
					
				}		
				
			} catch (SQLException e) {
				throw new DAOException(DB_ERR, e);
			} finally {
			Recursos.closeResultSet(rs);
			Recursos.closePreparedStatement(st);
			
		}
		
		return articulo;	
	}

	public List<Articulo> recuperarTodosArticulo() throws DAOException {
		PreparedStatement st = null;
		ResultSet rs = null;
		List<Articulo> list = new ArrayList<Articulo>();
		String descripcion = null;
		try {
			st = con.prepareStatement(DbQuery.getRecuperarTodosArticulo());
			rs = st.executeQuery();
			while (rs.next()) {
				descripcion = null;
				if (rs.getObject("descripcion") != null) {
					descripcion = new String(rs.getString("descripcion"));
				}
				list.add(new Articulo(rs.getInt(1), rs.getString(2), descripcion, rs.getDouble(4), rs.getInt(5), rs.getString(6)));
			}
		} catch (SQLException e) {
			throw new DAOException(DB_ERR, e);
		} finally {
			Recursos.closeResultSet(rs);
			Recursos.closePreparedStatement(st);
		}
		return list;

	}
	
	
	public int modificarArticulo(Articulo articulo)throws DAOException{
		PreparedStatement st = null;
		PreparedStatement sti = null;
		ResultSet rs=null;
		int modificado = 0;
		try {
			st = con.prepareStatement(DbQuery.getModificarArticulo());
			st.setString(1, articulo.getNombreArt());
			st.setString(2, articulo.getDescripcion());
			st.setDouble(3, articulo.getPrecioVenta());
			st.setInt(4, articulo.getStock());
			st.setString(5, articulo.getImagen());
			st.setInt(6, articulo.getCodArt());
			
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

}
