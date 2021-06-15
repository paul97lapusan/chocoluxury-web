package domain;

import exceptions.DomainException;
import util.Validator;

public class LinPed {
	private Pedido pedido; 
	private Articulo articulo; 
	private int cantidad; 
	
	// Constructores
	
	LinPed() {
	}
	
	public LinPed(Pedido pedido, Articulo articulo, int cantidad) {
		this.pedido = pedido;
		this.articulo = articulo;
		this.cantidad = cantidad;
	}
	
	public LinPed(Articulo articulo, int cantidad) {
		this.pedido = null;
		setArticulo(articulo);
		setCantidad(cantidad);
	}
	
	// getters y setters

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		if (pedido != null) {
			this.pedido = pedido;
		} else {
			throw new DomainException("El pedido es obligatorio");
		}
	}

	public Articulo getArticulo() {
		return articulo;
	}

	public void setArticulo(Articulo articulo) {
		if (articulo != null) {
			this.articulo = articulo;
		} else {
			throw new DomainException("El articulo es obligatorio");
		}
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		if (cantidad>0 && cantidad<9999) {
			this.cantidad=cantidad;
		} else {
	    	 throw new DomainException("La cantidad no es correcta");
	     }
	}

	
	// toString

	@Override
	public String toString() {
		return "LinPed [pedido=" + pedido + ", articulo=" + articulo + ", cantidad=" + cantidad + "]";
	}
	
	
	
	
	
}
