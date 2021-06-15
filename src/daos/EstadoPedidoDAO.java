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
import exceptions.DAOException;
import exceptions.ServiceException;

public class EstadoPedidoDAO implements ErroresBD  {
	private Connection con;

	public EstadoPedidoDAO(Connection con) {
		this.con = con;
	}

	public EstadoPedido recuperarEstadoPedidoById(int cod_est) throws DAOException {
		PreparedStatement st = null;
		ResultSet rs =null ;
		EstadoPedido objeto=null;
		
			try {
				st = con.prepareStatement(DbQuery.getRecuperarEstadoPedidoById());
				st.setInt(1, cod_est);
				rs=st.executeQuery();
				if (rs.next()){
	
					objeto=new EstadoPedido(rs.getInt(1), 
									   rs.getString(2)); 
					
				}		
				
			} catch (SQLException e) {
				throw new DAOException(DB_ERR, e);
			} finally {
			Recursos.closeResultSet(rs);
			Recursos.closePreparedStatement(st);
			
		}
		
		return objeto;
	}
	
	
	
	
}
