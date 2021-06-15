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
import domain.Articulo;
import domain.LinPed;
import domain.Pedido;
import exceptions.DAOException;

public class LinPedDAO implements ErroresBD {
	
	private Connection con;

	public LinPedDAO(Connection con) {
		this.con = con;
	}

	public void insertarLinPed(LinPed linped)throws DAOException{
		PreparedStatement st = null;
		PreparedStatement sti = null;
		ResultSet rs=null;
		try {
			st = con.prepareStatement(DbQuery.getInsertarLinPed());
			st.setInt(1, linped.getPedido().getCodped());
			st.setInt(2, linped.getArticulo().getCodArt());
			st.setInt(3, linped.getCantidad());
			
			// ejecutamos el insert.			
			st.executeUpdate();
		} catch (SQLException e) {
			if (e.getErrorCode() == DUPLICATE_PK) {
				throw new DAOException(" LinPed ya existe");
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
	
	public List<LinPed> recuperarTodosLinPedById(int codPed) throws DAOException {
		PreparedStatement st = null;
		ResultSet rs = null;
		List<LinPed> list = new ArrayList<LinPed>();
		try {
			st = con.prepareStatement(DbQuery.getRecuperarTodosLinPedById());
			st.setInt(1, codPed);
			rs = st.executeQuery();
			while (rs.next()) {
				list.add(new LinPed(new Pedido(rs.getInt(1)), new Articulo(rs.getInt(2)), rs.getInt(3)));
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
