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
import domain.EstadoPedido;
import domain.Pedido;
import exceptions.DAOException;

public class PedidoDAO implements ErroresBD {
	
	private Connection con;

	public PedidoDAO(Connection con) {
		this.con = con;
	}

	public void insertarPedido(Pedido pedido)throws DAOException{
		PreparedStatement st = null;
		PreparedStatement sti = null;
		ResultSet rs=null;
		try {
			st = con.prepareStatement(DbQuery.getInsertarPedido());
			st.setDate(1, pedido.getFechaped());
			st.setInt(2, pedido.getCliente().getCodCli());
			st.setInt(3, pedido.getEstado().getIdEstado());
			st.setInt(4, pedido.getDomicilio());
			st.setDouble(5, pedido.getImporte());
			
			// ejecutamos el insert.			
			st.executeUpdate();
		} catch (SQLException e) {
			if (e.getErrorCode() == DUPLICATE_PK) {
				throw new DAOException(" Pedido ya existe");
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
	
	public Pedido recuperarUltimoPedido() throws DAOException {
		PreparedStatement st = null;
		ResultSet rs =null ;
		Pedido objeto=null;
		
			try {
				st = con.prepareStatement(DbQuery.getRecuperarUltimoPedido());
				rs=st.executeQuery();
				if (rs.next()){
					objeto=new Pedido (rs.getInt(1)); 
					
				}		
				
			} catch (SQLException e) {
				throw new DAOException(DB_ERR, e);
			} finally {
			Recursos.closeResultSet(rs);
			Recursos.closePreparedStatement(st);
			
		}
		
		return objeto;
	}
	
	public Pedido recuperarPedidoById(int cod_ped) throws DAOException {
		PreparedStatement st = null;
		ResultSet rs =null ;
		Pedido objeto=null;
		
			try {
				st = con.prepareStatement(DbQuery.getRecuperarPedidoById());
				st.setInt(1, cod_ped);
				rs=st.executeQuery();
				if (rs.next()){
	
					objeto=new Pedido(rs.getInt(1), 
									   rs.getDate(2), 
									   new Cliente (rs.getInt(3)), 
									   new EstadoPedido (rs.getInt(4)), 
									   rs.getInt(5),
									   rs.getDouble(6)); 
					
				}		
				
			} catch (SQLException e) {
				throw new DAOException(DB_ERR, e);
			} finally {
			Recursos.closeResultSet(rs);
			Recursos.closePreparedStatement(st);
			
		}
		
		return objeto;
	}
	
	public int modificarPedido(Pedido pedido)throws DAOException{
		PreparedStatement st = null;
		PreparedStatement sti = null;
		ResultSet rs=null;
		int modificado = 0;
		try {
			st = con.prepareStatement(DbQuery.getModificarPedido());
			st.setDate(1, pedido.getFechaped());
			st.setInt(2, pedido.getCliente().getCodCli());
			st.setInt(3, pedido.getEstado().getIdEstado());
			st.setInt(4, pedido.getDomicilio());
			st.setDouble(5, pedido.getImporte());
			st.setInt(6, pedido.getCodped());
			
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
	
	public List<Pedido> recuperarTodosPedido() throws DAOException {
		PreparedStatement st = null;
		ResultSet rs = null;
		List<Pedido> list = new ArrayList<Pedido>();
		try {
			st = con.prepareStatement(DbQuery.getRecuperarTodosPedido());
			rs = st.executeQuery();
			while (rs.next()) {
				list.add(new Pedido(rs.getInt(1), rs.getDate(2), new Cliente(rs.getInt(3)), new EstadoPedido(rs.getInt(4)), rs.getInt(5), rs.getDouble(6)));
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
